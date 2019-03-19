package com.dtxx.mq.Thread;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.dtxx.mq.RabbitMqConfig;
import com.dtxx.util.LogUtils;
import com.dtxx.util.StringUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

/**
 * 供应商数据示例：
{"operation":"update",
"name":"1",
"businessLicenseNo":"10",
"registCur":"",
"entNatureShowName":"",
"nationTypeShowName":"",
"proShowName":"",
"logo":"",
"legalBody":"16",
"foundDate":1520524800000,
"registerFund":"14",
"registUnit":"万元",
"originalCurrency":"CNY",
"country":"",
"provinceId":"",
"cityId":"",
"districtId":"",
"corpAddress":" 北京北京北京市",
"serviceRange":"4",
"entProfile":"8",
"awardOrgName":"",
"creditLevel":"",
"industryType":"",
"manageNature":"",
"supplierTypeM":"1",
"supplierTypeS":"1",
"supplierTypeC":"1",
"supplierTypeMaker":"",
"supplierTypeService":"",
"supplierTypeConstruction":"",
"proService":"",
"telephone":"17",
"fax":"19",
"mailingAddress":"",
"postCode":"",
"linkmanName":"222",
"linkmanCard":"",
"linkmanCell":"225",
"linkmanEmail":"18@163.com",
"numCompany":0,
"bank":"",
"invoiceAccountlink":"",
"bankAcount":"",
"bankAddress":"",
"bankOnly":"",
"taxpayerNumber":"",
"invoiceAddress":"",
"invoicePhone":"",
"invoiceBank":"",
"invoiceAccount":"",
"supplierApproval":"0",
"prohibitAuthorize":"0",
"supplierFrozen":"0",
"prohibitAuthorize":"0",
"fileUrl":"",
"usercode":""}
    * @ClassName: GetSupplierInfoThread  
    * @Description: 从用友获取供应商数据
 * @author anpeng
 * @date 2019年3月12日  
    *
 */
@Service
public class GetSupplierInfoThread extends Thread{
	private static Logger log = LogUtils.getBussinessLogger();

	@Override
    public void run() {
		//装载MQ配置文件
				RabbitMqConfig.loadConfig();
		     try {
		     	Connection connection = RabbitMqConfig.createConnection();
		         //创建一个通道
		         Channel channel = connection.createChannel();
		         //声明一个队列，队列持久化       
		         channel.queueDeclare(RabbitMqConfig.queueNameSupply, true , false, false, null);
		         //绑定交换机和队列，设置routingkey       
		         channel.queueBind(RabbitMqConfig.queueNameSupply, RabbitMqConfig.rabbitSupplyExchange,RabbitMqConfig.EXCHANGE_RULE_SUPPLY_ALL);
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
		         channel.basicConsume(RabbitMqConfig.queueNameSupply,false,consumer);
		         //获取消息 
		         while (true) 
		         { 
			         QueueingConsumer.Delivery delivery=null;
		             try {
		                 delivery = consumer.nextDelivery(); 
		                 String message = new String(delivery.getBody()); 
		                 log.info("从用友MQ接收的供应商数据：'" + message + "'");
		               
		                 JSONObject json = JSONObject.fromObject(message);
		 		        //企业名称
		 		        String name=json.getString("name");
		 		        
		 		        //营业执照编码
		 		        String businessLicenseNo = json.getString("businessLicenseNo");
		 		        //企业类型 0-政府机关1-企业单位2-事业单位3-自然人4-其他
		 		        String registCur = json.getString("registCur");
		 		        //企业性质 0-国有企业1-集体企业2-联营企业3-股份合作制企业4-私营企业5-个体户6-合伙企业7-有限责任公司8-股份有限公司9-其他
		 		        String entNatureShowName = json.getString("entNatureShowName");
		 		        //国别 0-境内 1-境外
		 		        String nationTypeShowName = json.getString("nationTypeShowName");
		 		        //业务类型 0-招标一级 1-招标二级 2-非招标采购
		 		        String proShowName = json.getString("proShowName");
		 		        //企业logo 静态URL
		 		        String logo = json.getString("logo");
		 		        //法定代表人
		 		        String legalBody = json.getString("legalBody");
		 		        //成立日期
		 		        String foundDate = json.getString("foundDate");
		 		        Long  foundDateTime=null;
		 		        if(!StringUtil.isEmpty(foundDate)){
		 		        	foundDateTime=Long.parseLong(foundDate);
		 		        }
		 		        //注册资本
		 		        String registerFund = json.getString("registerFund");
		 		        //注册资本单位 固定为“万元”
		 		        String registUnit = json.getString("registUnit");
		 		        //注册资本币种1-人民币 2-美元3-欧元4-日元
		 		        String basisCurrency = json.getString("originalCurrency");
		 		        //国家
		 		        String country = json.getString("country");
		 		        //注册所在省
		 		        String provinceId = json.getString("provinceId");
		 		        //注册所在市
		 		        String cityId = json.getString("cityId");
		 		        //注册所在县（区）
		 		        String districtId = json.getString("districtId");
		 		        //注册地址
		 		        String corpAddress = json.getString("corpAddress");
		 		        //经营范围
		 		        String serviceRange = json.getString("serviceRange");
		 		        //企业简介
		 		        String entProfile = json.getString("entProfile");
		 		        //核发机关机构
		 		        String awardOrgName = json.getString("awardOrgName");
		 		        //资信等级
		 		        String creditLevel = json.getString("creditLevel");
		 		        //所属行业
		 		        String industryType = json.getString("industryType");
		 		        //制造供应商标志 0-是1-否
		 		        String supplierTypeM = json.getString("supplierTypeM");
		 		        //服务供应商标志0-是1-否
		 		        String supplierTypeS = json.getString("supplierTypeS");
		 		        //施工供应商标志0-是1-否
		 		        String supplierTypeC = json.getString("supplierTypeC");
		 		        //制造供应商类型 选择类型编码,以”,”间隔
		 		        String supplierTypeMaker = json.getString("supplierTypeMaker");
		 		        //服务供应商类型 选择类型编码,以”,”间隔
		 		        String supplierTypeService = json.getString("supplierTypeService");
		 		        //施工供应商类型 选择类型编码,以”,”间隔
		 		        String supplierTypeConstruction = json.getString("supplierTypeConstruction");
		 		        //公司电话(法人电话)
		 		        String telephone = json.getString("telephone");
		 		        //公司传真
		 		        String fax = json.getString("fax");
		 		        //邮寄地址
		 		        String mailingAddress = json.getString("mailingAddress");
		 		        //邮政编码
		 		        String postCode = json.getString("postCode");
		 		        //联系人姓名
		 		        String linkmanName = json.getString("linkmanName");
		 		        //联系人身份证号
		 		        String linkmanCard = json.getString("linkmanCard");
		 		        //联系人电话（手机）
		 		        String linkmanCell = json.getString("linkmanCell");
		 		        //电子邮箱
		 		        String email = json.getString("linkmanEmail");
		 		        //企业人数
		 		        String numCompany = json.getString("numCompany");
		 		        //开户银行名称
		 		        String bank = json.getString("bank");
		 		        //开户行联行号
		 		        String invoiceAccountlink = json.getString("invoiceAccountlink");
		 		        //开户银行账号
		 		        String bankAcount = json.getString("bankAcount");
		 		        //开户银行地址
		 		        String bankAddress = json.getString("bankAddress");
		 		        //基本开户银行  1.是（是否唯一）2.否
		 		        String bankOnly = json.getString("bankOnly");
		 		        //纳税人识别号
		 		        String taxpayerNumber = json.getString("taxpayerNumber");
		 		        //开票地址
		 		        String invoiceAddress = json.getString("invoiceAddress");
		 		        //开票电话
		 		        String invoicePhone = json.getString("invoicePhone");
		 		        //开票开户行
		 		        String invoiceBank = json.getString("invoiceBank");
		 		        //开票账号
		 		        String invoiceAccount = json.getString("invoiceAccount");
		 		        //供应商审核
		 		        String supplierApproval = json.getString("supplierApproval");
		 		        //供应商禁止授标
		 		        String prohibitAuthorize = json.getString("prohibitAuthorize");
		 		        //供应商冻结
		 		        String supplierFrozen = json.getString("supplierFrozen");
		 		        //营业执照附件
		 		        String fileUrl = json.getString("fileUrl");
		 		        //管理员用户名
		 		        String usercode = json.getString("usercode");
		 		        //管理员手机号
		 		        String usercell = json.getString("usercell");
		 		        //操作类型
		 		        String operation=json.getString("operation");
		 		       
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
