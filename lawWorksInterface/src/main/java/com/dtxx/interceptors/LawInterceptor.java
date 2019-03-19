package com.dtxx.interceptors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.yaml.snakeyaml.Yaml;

import com.dtxx.configurer.SystemYmlConfig;
import com.dtxx.model.LawSysSecretKey;
import com.dtxx.model.dto.Return1Info;
import com.dtxx.platform.RSAEncrypt;
import com.dtxx.platform.ServerEncodeTest;
import com.dtxx.service.LawSysSecretKeyService;
import com.dtxx.util.HttpServlerResponseUtil;
import com.dtxx.util.PropsUtil;
import com.dtxx.util.StringUtil;

//接口拦截器（用于做加密校验）
public class LawInterceptor implements HandlerInterceptor{
	private SystemYmlConfig systemYmlConfig;
	private LawSysSecretKeyService lawSysSecretKeyService;
	//构造方法，用于获取spring中的一些bean信息
	public LawInterceptor(SystemYmlConfig systemYmlConfig,LawSysSecretKeyService lawSysSecretKeyService) {
		this.systemYmlConfig=systemYmlConfig;
		this.lawSysSecretKeyService=lawSysSecretKeyService;
	}
	//该方法将在请求处理之前进行调用
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		    String uri = request.getRequestURI();//返回请求行中的资源名称
//		     String url = request.getRequestURL().toString();//获得客户端发送请求的完整url
//		     String ip = request.getRemoteAddr();//返回发出请求的IP地址
//		     String params = request.getQueryString();//返回请求行中的参数部分
//		     String host=request.getRemoteHost();//返回发出请求的客户机的主机名
//		     int port =request.getRemotePort();//返回发出请求的客户机的端口号。
//		     System.out.println(ip);
//		    System.out.println(url);
//		    System.out.println(uri);
//		    System.out.println(params);
//		    System.out.println(host);
//		    System.out.println(port);
		//获取当前访问路径
		String servletPath=request.getServletPath();
//		System.out.println(servletPath);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//获取源系统标识（用于区分是那个系统）
		String sysCode=request.getParameter("sourceSysCode");
		 
//		String sysCode="FINA_SYS_JITUAN";
		//获取接口传递时间（用于判断是否超时--默认超时时间为60秒）
		String sendDateFormat=request.getParameter("sendDate");
		 
//		String sendDateFormat=sdf.format(new Date());
		if(StringUtil.isEmpty(sysCode)||StringUtil.isEmpty(sendDateFormat)) {
			//关键参数为空
			HttpServlerResponseUtil.println(response, new Return1Info(90001).toString());
			return false;
		}
		//判断时间是否超时
		long interval = (new Date().getTime() - sdf.parse(sendDateFormat).getTime())/1000;
		if(interval>=Integer.parseInt(PropsUtil.get("system.sendDate"))) {
			//连接请求超时
			HttpServlerResponseUtil.println(response, new Return1Info(80001).toString());
	       
			return false;
		}
		
		//根据源系统标识进行公钥，私钥，对称密钥获取
		LawSysSecretKey lawSysSecretKey=lawSysSecretKeyService.selectById(sysCode);
		try {
			//对加密参数进行解密判断
			String sysCodeD=ServerEncodeTest.decode(request.getParameter("sysCodeD"),lawSysSecretKey.getInPrivateKey(),lawSysSecretKey.getAesKey1(),lawSysSecretKey.getAesKey2());			
			if(!sysCode.equals(sysCodeD))
				//参数非法
				HttpServlerResponseUtil.println(response, new Return1Info(90001).toString());
			//判断访问路径是查询还是写入
			if(servletPath.indexOf("select")<0) {
				//判断是incoming的插入还是outcoming的插入
				if(servletPath.indexOf("Incoming")>=0) {
					//对json进行解密
					String json=ServerEncodeTest.decode(request.getParameter("json"),lawSysSecretKey.getInPrivateKey(),lawSysSecretKey.getAesKey1(),lawSysSecretKey.getAesKey2());
				
					if(StringUtil.isEmpty(json))
					{
						//参数非法
						HttpServlerResponseUtil.println(response, new Return1Info(90001).toString());
						return false;
					}else {
						request.setAttribute("jsonDecode", json);
					}
				}else {
					//outcoming
					String errorMsg="";
					if(!StringUtil.isEmpty(request.getParameter("errorMsg"))) {
						//当error对象不为空时进行解密
						errorMsg=ServerEncodeTest.decode(request.getParameter("errorMsg"),lawSysSecretKey.getInPrivateKey(),lawSysSecretKey.getAesKey1(),lawSysSecretKey.getAesKey2());
						
					}
					request.setAttribute("errorMsg", errorMsg);
				}
				
			}else {
				//查询操作
				//对特殊查询进行解密
				if(servletPath.indexOf("OutSysDataMap")>=0) {
					String json=ServerEncodeTest.decode(request.getParameter("json"),lawSysSecretKey.getInPrivateKey(),lawSysSecretKey.getAesKey1(),lawSysSecretKey.getAesKey2());
					if(StringUtil.isEmpty(json))
					{
						//参数非法
						HttpServlerResponseUtil.println(response, new Return1Info(90001).toString());
						return false;
					}else {
						request.setAttribute("jsonDecode", json);
					}
				}
			}
			
		}catch (Exception e) {
			//参数非法
			HttpServlerResponseUtil.println(response, new Return1Info(90001).toString());
			return false;
		}
		//将加密信息写入request中
		request.setAttribute("OutPublicKey", lawSysSecretKey.getOutPublickey());
		request.setAttribute("AesKey1", lawSysSecretKey.getAesKey1());
		request.setAttribute("AesKey2", lawSysSecretKey.getAesKey2());
//		System.out.println(lawSysSecretKey.getInPublicKey());
//		System.out.println(lawSysSecretKey.getInPrivateKey());
//		System.out.println(lawSysSecretKey.getAesKey1());
//		System.out.println(lawSysSecretKey.getAesKey2());
//		System.out.println(lawSysSecretKey.getOutPublickey());
//		System.out.println(systemYmlConfig.getSendDate());
//        System.out.println(request.getRequestURI());
//        System.out.println(request.getRequestURL().toString());
//        System.out.println("访问了test下url路径。");
 
        return true;
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
 
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
 
    }

    public static void main(String[] args) throws ParseException, FileNotFoundException {
//    	   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//           System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
//           String ynewDate="2019-1-30 12:45:06";
//           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//           long interval = (new Date().getTime() - sdf.parse(ynewDate).getTime())/1000;
//           System.out.println(interval);
//    	File dumpFile=new File(System.getProperty("user.dir") + "/src/main/resources/application-systemParameter.yml");
//    	Yaml yaml = new Yaml();
//    	Map map =(Map)yaml.load(new FileInputStream(dumpFile));
//        System.out.println(map.get("system"));
    	//        Person father = (Person) Yaml.loadType(dumpFile, Person.class);
//        StringBuilder stringBuilder=new StringBuilder();
//        stringBuilder.append(father.getName())
//                .append("\t")
//                .append(father.getSex())
//                .append("\t")
//                .append(father.getAge())
//                .append("\t")
//                .append(father.getChildren().size());
//        System.out.println(stringBuilder.toString());
           
	}
}
