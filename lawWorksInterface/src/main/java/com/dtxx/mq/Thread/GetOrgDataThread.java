package com.dtxx.mq.Thread;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtxx.model.LawOutSysDataBase;
import com.dtxx.mq.RabbitMqConfig;
import com.dtxx.service.LawOutSysDataBaseService;
import com.dtxx.util.ApplicationContextUtil;
import com.dtxx.util.LogUtils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
/**
 * @Description: 获取组织数据
 * 操作类型 operation 增：insert；改update：；删：delete。
 * 组织类型 orgType 部门：1；组织2
 * 组织数据示例：
 * {"operation":"update",
 * "orgType":"1",
 * "orgCode":"test",
 * "orgName":" test ",
 * "parentOrgCode":" test ",
 * "erpCode":" test "}     
 * @author anpeng
 * @date 2019年3月12日
*/
@Service
public class GetOrgDataThread extends Thread{
	private static Logger log = LogUtils.getBussinessLogger();
//	@Autowired
//	private LawOutSysDataBaseService lawOutSysDataBaseService;
	@Override
    public void run() {
		//装载MQ配置文件
				RabbitMqConfig.loadConfig();
				LawOutSysDataBaseService lawOutSysDataBaseService=ApplicationContextUtil.getContext().getBean(LawOutSysDataBaseService.class);

		     try {
		     	Connection connection = RabbitMqConfig.createConnection();
		         //创建一个通道
		         Channel channel = connection.createChannel();
		         //声明一个队列，队列持久化       
		         channel.queueDeclare(RabbitMqConfig.queueNameOrg, true , false, false, null);
		         //绑定交换机和队列，设置routingkey       
		         channel.queueBind(RabbitMqConfig.queueNameOrg, RabbitMqConfig.rabbitExchange,RabbitMqConfig.EXCHANGE_RULE_ORG_ALL_ORG);
		         /*
		         queueDeclare第一个参数表示队列名称、
		                                第二个参数为是否持久化（true表示是，队列将在服务器重启时生存）、
		                                第三个参数为是否是独占队列（创建者可以使用的私有队列，断开后自动删除）、
		                                第四个参数为当所有消费者客户端连接断开时是否自动删除队列、
		                                第五个参数为队列的其他参数.
		         */
		         //关闭通道和连接已自动化
		         //channel.close();
		         //connection.close();
		         //统一时刻服务器只会发一条消息给消费者; 
		         channel.basicQos(1); 
		         //定义队列的消费者 
		         QueueingConsumer consumer = new QueueingConsumer(channel);
		         channel.basicConsume(RabbitMqConfig.queueNameOrg,false,consumer); 
		         //获取消息 
		         while (true) 
		         { 
		            QueueingConsumer.Delivery delivery=null;
		             try {
		                 delivery = consumer.nextDelivery(); 
		                 String message = new String(delivery.getBody()); 
		                 log.info("从用友MQ接收的组织数据：'" + message + "'");
		                 JSONObject json = JSONObject.fromObject(message);
		                //组织类型 orgType 部门：1；组织2
		 		        String orgType=json.getString("orgType");
		 		        //组织编码
		 		        String orgCode=json.getString("orgCode");
		 		        //组织名称
		 		        String orgName=json.getString("orgName");
		 		        //上级组织编码
		 		        String parentOrgCode=json.getString("parentOrgCode");
		 		        //旧系统编码
		 		        String erpCode=json.getString("erpCode");
		 		        //操作类型
		 		        String operation=json.getString("operation");
		 		        //企业层级	1:一级 2:二级3:三级4:四级5:五级
		 		        String businessLevel=json.getString("businessLevel");	
		 		        //三证合一
		 		        String threeCertificates=json.getString("threeCertificates");
		 		        //所属行业	1:火电 2:风电3:水电4:煤矿5:能源化工6:非生产型企业7:其他
		 		        String industry=json.getString("industry");
		 		        //公司地址
		 		        String companyAddress=json.getString("companyAddress");
		 		        //公司邮编	
		 		        String companyZipCode=json.getString("companyZipCode");
		 		        //开票开户行	
		 		        String billingBank=json.getString("billingBank");
		 		        //开票开户账号
		 		        String billingAccount=json.getString("billingAccount");
		 		        //开票税号	
		 		        String invoiceNumber=json.getString("invoiceNumber");
		 		        //开票电话	
		 		        String billingPhone=json.getString("billingPhone");
		 		        //开票地址	
		 		        String billingAddress=json.getString("billingAddress");
		 		        //发票类型	1普票2专票3增值税发票
		 		        String invoiceType=json.getString("invoiceType");
		 		        //开户行	
		 		        String accountBank=json.getString("accountBank");
		 		        // 大唐网银户名	
		 		        String bankName=json.getString("bankName");	
		 		        //大唐网银账号	
		 		        String bankingAccount=json.getString("bankingAccount");
		 		    // id
						String id = json.getString("id");
						// 先查询到对应数据如果没有，先进行insert操作
						LawOutSysDataBase lawOutSysDataBase = new LawOutSysDataBase();
						lawOutSysDataBase.setSyscode("BID_SYS");
						lawOutSysDataBase.setId(id);
						lawOutSysDataBase.setType("org");
						LawOutSysDataBase lawOutSysDataBasese = lawOutSysDataBaseService.selectById(lawOutSysDataBase);
						if (lawOutSysDataBasese == null) {
							lawOutSysDataBase.setCode(orgCode);
							lawOutSysDataBase.setName(orgName);
							lawOutSysDataBase.setParent(parentOrgCode);
							lawOutSysDataBase.setStatus("1");
							lawOutSysDataBase.setCreationDate(new Date());
							lawOutSysDataBaseService.insert(lawOutSysDataBase);
							// lawOutSysDataBasese =lawOutSysDataBaseService.selectById(lawOutSysDataBase);
						} else {
//							if (operation.equals("update")) {
								lawOutSysDataBasese = lawOutSysDataBaseService.selectById(lawOutSysDataBase);
								lawOutSysDataBasese.setCode(orgCode);
								lawOutSysDataBasese.setName(orgName);
								lawOutSysDataBasese.setParent(parentOrgCode);
								lawOutSysDataBasese.setLastUpdateDate(new Date());
								lawOutSysDataBaseService.update(lawOutSysDataBasese);
//							}
						}
		                Thread.sleep(10); 
		             } catch (Exception e) {
		            	 
		             }finally{
		            	//业务流程处理成功后手动返回 确认
		                channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false); 
		             }
		         }
		    
		     } catch (Exception e1) {
		         // TODO Auto-generated catch block
		         e1.printStackTrace();
		     }
	}
}
