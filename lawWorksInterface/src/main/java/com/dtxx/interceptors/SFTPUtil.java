package com.dtxx.interceptors;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * 类说明 sftp工具类
 */
public class SFTPUtil {
	protected static Logger logger = LoggerFactory.getLogger(SFTPUtil.class);

	private ChannelSftp sftp;

	private Session session;
	/** SFTP 登录用户名 */
	private String username;
	/** SFTP 登录密码 */
	private String password;
	/** 私钥 */
	private String privateKey;
	/** SFTP 服务器地址IP地址 */
	private String host;
	/** SFTP 端口 */
	private int port;

	
	/**
	 * 构造基于密码认证的sftp对象
	 */
	public SFTPUtil(String username, String password, String host, int port) {
		this.username = username;
		this.password = password;
		this.host = host;
		this.port = port;
	}

	/**
	 * 构造基于秘钥认证的sftp对象
	 */
	public SFTPUtil(String username, String host, int port, String privateKey) {
		this.username = username;
		this.host = host;
		this.port = port;
		this.privateKey = privateKey;
	}

	public SFTPUtil() {
	}

	/**
	 * 连接sftp服务器
	 */
	public void login() {
		try {
			JSch jsch = new JSch();
			if (privateKey != null) {
				jsch.addIdentity(privateKey);// 设置私钥
			}

			session = jsch.getSession(username, host, port);

			if (password != null) {
				session.setPassword(password);
			}
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");

			session.setConfig(config);
			session.connect();

			Channel channel = session.openChannel("sftp");
			channel.connect();

			sftp = (ChannelSftp) channel;
		} catch (JSchException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭连接 server
	 */
	public void logout() {
		if (sftp != null) {
			if (sftp.isConnected()) {
				sftp.disconnect();
			}
		}
		if (session != null) {
			if (session.isConnected()) {
				session.disconnect();
			}
		}
	}

	/**
	 * 将输入流的数据上传到sftp作为文件。文件完整路径=basePath+directory
	 * 
	 * @param basePath
	 *            服务器的基础路径
	 * @param directory
	 *            上传到该目录
	 * @param sftpFileName
	 *            sftp端文件名
	 * @param in
	 *            输入流
	 */
	public void upload(String basePath, String directory, String sftpFileName, InputStream input) throws SftpException {
		try {
			sftp.cd(basePath);
			sftp.cd(directory);
		} catch (SftpException e) {
			// 目录不存在，则创建文件夹
			String[] dirs = directory.split("/");
			String tempPath = basePath;
			for (String dir : dirs) {
				if (null == dir || "".equals(dir))
					continue;
				tempPath += "/" + dir;
				try {
					sftp.cd(tempPath);
				} catch (SftpException ex) {
					sftp.mkdir(tempPath);
					sftp.chmod(Integer.parseInt("777", 8), tempPath);
					sftp.cd(tempPath);
				}
			}
		}
		sftp.put(input, sftpFileName, ChannelSftp.RESUME); // 上传文件
	}

	/**
	 * 下载文件。
	 * 
	 * @param directory 下载目录
	 * @param downloadFile 下载的文件
	 * @param saveFile 存在本地的路径
	 */
	public void download(String directory, String downloadFile, String saveFile) throws SftpException, FileNotFoundException {
		if (directory != null && !"".equals(directory)) {
			sftp.cd(directory);
		}
		File file = new File(saveFile);
		sftp.get(downloadFile, new FileOutputStream(file));
	}	

	/**
	 * 下载文件
	 * 
	 * @param directory
	 *            下载目录
	 * @param downloadFile
	 *            下载的文件名
	 * @return 字节数组
	 */
	public byte[] download(String directory, String downloadFile) throws SftpException, IOException {
		if (directory != null && !"".equals(directory)) {
			sftp.cd(directory);
		}
		InputStream is = sftp.get(downloadFile);

		byte[] fileData = this.input2byte(is);

		return fileData;
	}

	/**
	 * 删除文件
	 * 
	 * @param directory
	 *            要删除文件所在目录
	 * @param deleteFile
	 *            要删除的文件
	 */
	public void delete(String directory, String deleteFile) throws SftpException {
		sftp.cd(directory);
		sftp.rm(deleteFile);
	}

	/**
	 * 列出目录下的文件
	 * 
	 * @param directory
	 *            要列出的目录
	 * @param sftp
	 */
	public Vector<?> listFiles(String directory) throws SftpException {
		return sftp.ls(directory);
	}

	// 上传文件测试
	public static void main(String[] args) throws SftpException, IOException {
		SFTPUtil sftp = new SFTPUtil("admin", "123456", "10.81.80.100", 22);
		sftp.login();
		File file = new File("C:\\Code\\Install\\python-3.7.1-amd64.exe");
		
		sftp.logout();
	}
	
	 public final byte[] input2byte(InputStream inStream)  
	            throws IOException {  
	        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
	        byte[] buff = new byte[100];  
	        int rc = 0;  
	        while ((rc = inStream.read(buff, 0, 100)) > 0) {  
	            swapStream.write(buff, 0, rc);  
	        }  
	        byte[] in2b = swapStream.toByteArray();  
	        return in2b;  
	    }  
	 
	    /**
		 * 判断目标是否是目录
		 * @param path
		 * @return
		 */
		public boolean isDictionary(String path) {
			try {
				sftp.cd(path);
				return true;
			} catch (SftpException e) {
				return false;
			}
		}
		
		public Vector<?> getFiles(String path) throws SftpException {
			return sftp.ls(path);
		}
		
		
		/**
		 * 计数
		 * @param filePath
		 * @return
		 * @throws SftpException
		 */
		public int countFile(String filePath) throws SftpException {
			if (!isDictionary(filePath)) {
				return 1;
			}

			sftp.cd(filePath);
			int countFile = 0;
			Vector<LsEntry> lsEntries = sftp.ls(".");
			for (LsEntry lsEntrie : lsEntries) {
				String name = lsEntrie.getFilename();
				if (".".equals(name) || "..".equals(name)) {
					continue;
				}
				
				if (isDictionary(name)) {
					countFile += countFile(name);
				} else {
					countFile++;
				}
			}
			return countFile;

		}

	
}