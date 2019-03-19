package com.dtxx.mq.Thread;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;

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
 * @Description: 获取用户数据
 * 操作类型operation 增：insert；改update：；删：delete。
 * 用户数据示例：
 * {"operation":"insert",
 * "orgId":"test",
 * "userCode":"test",
 * "userName":"test",
 * "userMobile":"test",
 * "userEmail":"test"}    
 * @author anpeng
 * @date 2019年3月12日
*/
@Service
public class GetUserDataThread extends Thread{
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
			   channel.queueDeclare(RabbitMqConfig.queueNameUser, true , false, false, null);
			   //绑定交换机和队列，设置routingkey       
			   channel.queueBind(RabbitMqConfig.queueNameUser, RabbitMqConfig.rabbitUserExchange,RabbitMqConfig.EXCHANGE_RULE_USER_ALL);
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
			      channel.basicConsume(RabbitMqConfig.queueNameUser,false,consumer); 
			      //获取消息 
			      while (true) 
			      { 
				     QueueingConsumer.Delivery delivery=null;
			       try {
			         delivery = consumer.nextDelivery(); 
			         String message = new String(delivery.getBody());
			         log.info("从用友MQ接收的用户数据：'" + message + "'");
			        
			        JSONObject json = JSONObject.fromObject(message);
			        //用户编码
			        String userCode=json.getString("userCode");

			        //用户姓名
			        String userName=json.getString("userName");
			        //手机号码
			        String userMobile=json.getString("userMobile");
			        //邮箱
			        String userEmail=json.getString("userEmail");
			        //所属组织部门
			        String orgId=json.getString("orgId");
			        //操作类型
			        String operation=json.getString("operation");
			     // id
					String id = json.getString("id");
					// 先查询到对应数据如果没有，先进行insert操作
					LawOutSysDataBase lawOutSysDataBase = new LawOutSysDataBase();
					lawOutSysDataBase.setSyscode("BID_SYS");
//					lawOutSysDataBase.setCode(userCode);
					lawOutSysDataBase.setId(id);
					lawOutSysDataBase.setType("user");
					LawOutSysDataBase lawOutSysDataBasese = lawOutSysDataBaseService.selectById(lawOutSysDataBase);
					if (lawOutSysDataBasese == null) {
						lawOutSysDataBase.setCode(userCode);
						lawOutSysDataBase.setName(userName);
						lawOutSysDataBase.setParent(orgId);
						lawOutSysDataBase.setStatus("1");
						lawOutSysDataBase.setCreationDate(new Date());
						lawOutSysDataBaseService.insert(lawOutSysDataBase);
						// lawOutSysDataBasese =lawOutSysDataBaseService.selectById(lawOutSysDataBase);
					} else {
//						if (operation.equals("update")) {
							lawOutSysDataBasese = lawOutSysDataBaseService.selectById(lawOutSysDataBase);
							lawOutSysDataBasese.setCode(userCode);
							lawOutSysDataBasese.setName(userName);
							lawOutSysDataBasese.setParent(orgId);
							lawOutSysDataBasese.setLastUpdateDate(new Date());
							lawOutSysDataBaseService.update(lawOutSysDataBasese);
//						}
					}
			        Thread.sleep(10);
			       } catch (Exception e) {
			    	 
		          }finally{
		        	//业务流程处理成功后手动返回 确认
			        channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false); 
		          }
			  }
			       
			} catch (Exception e1) {
			   e1.printStackTrace();
		   }
		}

}
