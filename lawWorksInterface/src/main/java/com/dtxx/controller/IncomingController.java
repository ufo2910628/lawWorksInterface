package com.dtxx.controller;

import com.dtxx.interceptors.SFTPUtil;
import com.dtxx.model.LawIncoming;
import com.dtxx.model.LawIncomingKey;
import com.dtxx.model.LawIncomingRet;
import com.dtxx.model.LawIncomingRetKey;
import com.dtxx.model.SymContractBase;
import com.dtxx.model.dto.Return1Info;
import com.dtxx.model.dto.ReturnInfoJson; 
import com.dtxx.service.IncomingRetService;
import com.dtxx.service.IncomingService;
import com.dtxx.util.JsonUtils;
import com.dtxx.util.LogUtils;
import com.dtxx.util.ReturnUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/LawInterceptor/Incoming")
public class IncomingController {
	@Autowired
    private IncomingService incomingService;
	@Autowired
    private IncomingRetService incomingRetService; 
	Logger ExceptionLogger = LogUtils.getExceptionLogger();
	Logger BussinessLogger = LogUtils.getBussinessLogger();
	Logger DBLogger = LogUtils.getDBLogger();
	/** SFTP 登录用户名 */
	private static final String SFTP_USER_NAME = "admin";
	/** SFTP 登录密码 */
	private static final String SFTP_PASSWORD = "123456";
	/** SFTP 服务器地址IP地址 */
	private static final String SFTP_HOST = "10.81.80.100";
	/** SFTP 端口 */
	private static final int SFTP_PORT = 22;

	/**
	 * lawIncoming写入接口。
	 */
	@RequestMapping(method = RequestMethod.POST,value = "/insert")
	@ResponseBody
	public ReturnInfoJson insert(LawIncoming lawIncoming,HttpServletRequest request){
		//替换加密参数为解密后的数据
		lawIncoming.setJson(request.getAttribute("jsonDecode")+"");
		try { 
		//	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Integer attachCount=lawIncoming.getAttachCount();
//			   SFTPUtil sftp = new SFTPUtil(SFTP_USER_NAME, SFTP_PASSWORD, SFTP_HOST, SFTP_PORT);
//               sftp.login();
//            if (attachCount!=null && attachCount !=0) { //判断附件个数不为0或者不为空的情况下，把整个合同的文件夹附件转移
//               String Path="/sftp_incoming_yc/yc/"+lawIncoming.getInId();
//               String newPath="/sftp_outcoming_yc/yc"+lawIncoming.getInId();
//           
//             
//               Integer aInteger=sftp.countFile(Path);  
//              
//		   if (aInteger==attachCount) {//判断附件个数字段与实际文件夹内有多少个附件
//		          this.copyFolderWithSelf(Path, newPath);
//				} else { 
//					return new ReturnInfoJson(88888);
//				} 
//			}
//            sftp.logout();
            lawIncoming.setStatus("ready");	
            lawIncoming.setCreationDate(new Date());
            incomingService.insert(lawIncoming);
			return new ReturnInfoJson(0);
		}catch (Exception e) {
			// TODO: handle exception
			DBLogger.error(String.format("IncomingController|insert|error|%s|%s|%s", lawIncoming.getSourceSysCode(),lawIncoming.getIntfCode(),lawIncoming.getJson()));
//			return ReturnUtil.returnEncodeData(request, JsonUtils.serialize(new ReturnInfoJson(99999)));
			return new ReturnInfoJson(99999);
		}
	}
	
	
	
	
	
	
	
	
	
	@RequestMapping(method = RequestMethod.POST,value = "/select/{id}/{sourceSysCode}")
	@ResponseBody
	public ReturnInfoJson selectRet(@PathVariable("id")int id, @PathVariable("sourceSysCode") String sourceSysCode,HttpServletRequest request){
		try {
			LawIncoming lawIncoming=incomingService.selectById(new LawIncomingKey(id, sourceSysCode));
			return new ReturnInfoJson(0,ReturnUtil.returnEncodeData(request, JsonUtils.serialize(lawIncoming)));
//			return ReturnUtil.returnEncodeData(request, JsonUtils.serialize(new ReturnInfoJson(0)));
		}catch (Exception e) {
			DBLogger.error(String.format("IncomingController|selectRet|error|%s|%s", id,sourceSysCode));
//			return ReturnUtil.returnEncodeData(request, JsonUtils.serialize(new ReturnInfoJson(99999)));
			return new ReturnInfoJson(99999);
		}
	}
	
	/**
	 * lawIncoming读取接口。
	 */
	@RequestMapping(method = RequestMethod.POST,value = "/select/{sourceSysCode}")
	@ResponseBody
	public ReturnInfoJson selectRet(@PathVariable("sourceSysCode") String sourceSysCode,HttpServletRequest request){
		try { 
			 List<LawIncoming> lawIncoming=incomingService.selectReading(new LawIncomingKey(sourceSysCode));
				return new ReturnInfoJson(0,ReturnUtil.returnEncodeData(request, JsonUtils.serialize(lawIncoming))); 
		}catch (Exception e) {
			DBLogger.error(String.format("IncomingController|selectRet|error|%s|%s", sourceSysCode));
//			return ReturnUtil.returnEncodeData(request, JsonUtils.serialize(new ReturnInfoJson(99999)));
			return new ReturnInfoJson(99999);
		}
	}
	
	
	
	
	
	/**
	 * lawIncoming更新状态
	 */
//	@RequestMapping(method = RequestMethod.POST,value = "/update")
//	public ReturnInfoJson update(LawIncoming lawIncoming,HttpServletRequest request){
//		lawIncoming.setJson(request.getAttribute("jsonDecode")+""); 
//		try { 
//			incomingService.updateWithBlobs(lawIncoming); 
//			 
//			return new ReturnInfoJson(0);
//		}catch (Exception e) {
//			// TODO: handle exception
//			DBLogger.error(String.format("IncomingController|insert|error|%s|%s|%s", lawIncoming.getSourceSysCode(),lawIncoming.getIntfCode(),lawIncoming.getJson())); 
//			return new ReturnInfoJson(99999);
//		}   
//	}

//	@RequestMapping(method = RequestMethod.GET,value = "/select/{id}/{sourceSysCode}")
//	public LawIncoming select(@PathVariable("id")int id, @PathVariable("sourceSysCode") String sourceSysCode){
//		return incomingService.selectById(new LawIncomingKey(id, sourceSysCode));
//	}
	
	

//
//	@RequestMapping(method = RequestMethod.GET,value = "/selectAll/{pageNum}/{pageSize}")
//	public List<LawIncoming> selectAll(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
//		return incomingService.selectAll(pageNum,pageSize);
//	}
// 
//	@GetMapping("/lawIncoming")
//	public LawIncoming lawIncoming(String sourceSysCode, String intfCode, String json) {
//		LawIncoming lawIncoming = new LawIncoming();
//		incomingService.insert(lawIncoming);
//		return lawIncoming;
//	}

	@GetMapping("/attachment")
	public String receiveFile(String sourceSysCode, String intfCode, MultipartFile attachment) {
		String savePath = "/";
		String fileName = "";
		String suffix = "";
		Map<String, String> result = new HashMap<String, String>();

		int dotPoint = attachment.getOriginalFilename().lastIndexOf(".");
		if (dotPoint == -1) {
			fileName = attachment.getOriginalFilename();
		} else {
			fileName = attachment.getOriginalFilename().substring(0, dotPoint);
			suffix = attachment.getOriginalFilename().substring(dotPoint);
		}

		// 格式判断

		// 文件传输
		try {
			FileCopyUtils.copy(attachment.getInputStream(), new FileOutputStream(savePath + sourceSysCode + "/" + intfCode + "/" + fileName + suffix));
			result.put("received", "true");
			result.put("reason", "");
		} catch (IOException e) {
			e.printStackTrace();
			result.put("received", "false");
			result.put("reason", "IO异常:"+e.getMessage());
		}

		return JsonUtils.serialize(result);
	}

 //文件移动案例
//	   public String moveFile(String attachment ,String name)
//			try {
//				File afile = new File("C:\\zuidaima_com_a\\zuidaima_com.txt");
//				if (afile.renameTo(new File("C:\\zuidaima_com_b\\" + afile.getName()))) {
//					afile.delete();
//					System.out.println("File is moved successful!");
//				} else {
//					System.out.println("File is failed to move!");
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	
	
	/**
     * 复制整个文件夹的内容(含自身)
     * @param oldPath 准备拷贝的目录
     * @param newPath 指定绝对路径的新目录
     * @return
     */
    public static void copyFolderWithSelf(String oldPath, String newPath) {
        try {
            new File(newPath).mkdirs(); //如果文件夹不存在 则建立新文件夹
            File dir = new File(oldPath);
			// 目标
            newPath +=  File.separator + dir.getName();
			File moveDir = new File(newPath);
			if(dir.isDirectory()){
				if (!moveDir.exists()) {
					moveDir.mkdirs();
				}
			}
            String[] file = dir.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }
                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath +
                            "/" +
                            (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) { //如果是子文件夹
                	copyFolderWithSelf(oldPath + "/" + file[i], newPath);
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
 
//    public Integer getFileAndDirectory(String filePath){
//    	  
//    	
//    	int countDirctory = 0;
//        File file=new File(filePath);
//    	int countFile = 0;
//    	if(sftp.isDictionary(filePath)){
//	    	File []files = file.listFiles();
//	    	for(File fileIndex:files){
//		    	if(fileIndex.isDirectory()){
//		    		countDirctory++;
//			    	getFileAndDirectory(fileIndex);
//			
//		    	}else {
//		    		countFile++;
//		    	}
//	    	}
//    	}
//    	 
//		return countFile;
//     
//
//    	}
//	
	
}
