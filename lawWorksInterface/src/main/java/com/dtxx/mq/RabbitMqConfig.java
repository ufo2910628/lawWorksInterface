package com.dtxx.mq;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;

import com.dtxx.util.LogUtils;
import com.dtxx.util.PropsUtil;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/*
 * 加载MQ配置信息
 *@author anpeng
 * @date 2019年3月12日
 */
public class RabbitMqConfig {
	private static Logger log = LogUtils.getBussinessLogger();
	/**消息中间件IP**/
	public static String rabbitIp;
	/**消息中间件端口号**/
	public static String rabbitPort;
	/**消息中间件用户名**/
	public static String rabbitUser;
    /**消息中间件密码**/
	public static String rabbitPwd;
    /**绑定交换器名称**/
	public static String rabbitExchange;
	/**绑定交换器名称(用户)**/
	public static String rabbitUserExchange;
	/**绑定交换器名称(供应商)**/
	public static String rabbitSupplyExchange;
    /**机构队列名称**/
	public static String queueNameOrg;
	/**部门队列名称**/
	public static String queueNameDept;
	/**用户队列名称**/
	public static String queueNameUser;
	/**供应商队列名称**/
	public static String queueNameSupply;
    /**绑定路由规则-接收组织部门全量信息**/
	public final static String EXCHANGE_RULE_ORG_ALL="datang.org.all.*";
    /**绑定路由规则-只接收部门信息**/
	public final static String EXCHANGE_RULE_ORG_ALL_DEPT="datang.org.all.dept";
    /**绑定路由规则-只接收组织信息**/
	public final static String EXCHANGE_RULE_ORG_ALL_ORG="datang.org.all.org";
    /**绑定路由规则-接收全部用户信息**/
	public final static String EXCHANGE_RULE_USER_ALL="datang.user.all.all";
	/**绑定路由规则-接收全部供应商信息**/
	public final static String EXCHANGE_RULE_SUPPLY_ALL="datang.supply.all.all";
	/**操作标识 增加**/
	public final static String OPERATION_INSERT="insert";
	/**操作标识 修改**/
	public final static String OPERATION_UPDATE="update";
	/**操作标识 删除**/
	public final static String OPERATION_DELETE="delete";
	/**
	 * 载入配置文件(config.props)
	 */
	public static void loadConfig() {
         rabbitIp = PropsUtil.get("rabbitmq.rabbitIp");
         rabbitPort=PropsUtil.get("rabbitmq.rabbitPort");
         rabbitUser=PropsUtil.get("rabbitmq.rabbitUser");
         rabbitPwd=PropsUtil.get("rabbitmq.rabbitPwd");
         rabbitExchange=PropsUtil.get("rabbitmq.rabbitExchange");
         rabbitUserExchange=PropsUtil.get("rabbitmq.rabbitUserExchange");
         queueNameOrg=PropsUtil.get("rabbitmq.queueNameOrg");
         queueNameDept=PropsUtil.get("rabbitmq.queueNameDept");
         queueNameUser=PropsUtil.get("rabbitmq.queueNameUser");
         rabbitSupplyExchange=PropsUtil.get("rabbitmq.rabbitSupplyExchange");
         queueNameSupply=PropsUtil.get("rabbitmq.queueNameSupply");
         log.info("MQ连接信息: "+rabbitUser+"/"+rabbitPwd+"@"+rabbitIp+":"+rabbitPort+" use "+rabbitExchange);
	}
	/**
	    * @Description: 建立连接
	    * @param @return    
	    * @return Connection      
	    * @author zhanglt  
	 * @throws TimeoutException 
	    * @date 2018年3月9日
	 */
	public static Connection createConnection() throws TimeoutException{
		//创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ相关信息
        factory.setHost(RabbitMqConfig.rabbitIp);
        factory.setUsername(RabbitMqConfig.rabbitUser);
        factory.setPassword(RabbitMqConfig.rabbitPwd);
        factory.setPort(Integer.valueOf(RabbitMqConfig.rabbitPort));
        //创建一个新的连接
        Connection connection=null;
        try {
			connection = factory.newConnection();
			log.info("建立MQ连接成功！");
		} catch (IOException e) {
			e.printStackTrace();
		}
        return connection;
	}
}
