package com.dtxx.interceptors;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SocketFactory;
/**
 * 相关链接： JSCH api：http://epaul.github.io/jsch-documentation/javadoc/ Example:
 * http://www.jcraft.com/jsch/examples/
 * 
 * @author xxxx
 * 
 */
public class Jschuntil {
  private static JSch jsch = new JSch();
  /**
   * 创建Session，并打开Session连接
   * 
   * @param dstIp
   * @param dstPort
   * @param localIp
   * @param localPort
   * @param userName
   * @param password
   * @param timeOut
   * @return
   * @throws JSchException
   */
  public static Session createSession(String dstIp, int dstPort,
      final String localIp, final int localPort, String userName,
      String password, final int timeOut) throws JSchException {
    //jsch.setKnownHosts("/home/foo/.ssh/known_hosts");
    
    // A Session represents a connection to a SSH server
    Session session = jsch.getSession(userName, dstIp, dstPort);
    session.setPassword(password);
    
    Properties sshConfig = new Properties();
    sshConfig.put("StrictHostKeyChecking", "no");//To skip host-key check
    session.setConfig(sshConfig);
    // this socket factory is used to create a socket to the target host,
    // and also create the streams of this socket used by us
    session.setSocketFactory(new SocketFactory() {
      @Override
      public OutputStream getOutputStream(Socket socket)
          throws IOException {
        return socket.getOutputStream();
      }
      @Override
      public InputStream getInputStream(Socket socket) throws IOException {
        return socket.getInputStream();
      }
      @Override
      public Socket createSocket(String host, int port)
          throws IOException, UnknownHostException {
        Socket socket = new Socket();
        if (localIp != null) {
          socket.bind(new InetSocketAddress(InetAddress
              .getByName(localIp), localPort));
        }
        socket.connect(
            new InetSocketAddress(InetAddress.getByName(host), port),
            timeOut);
        return socket;
      }
    });
    session.connect(timeOut);
    return session;
  }
 
  
  
  /**
   * 测试程序
   * @param args
   */
  public static void main(String[] args) {
    String ip = "10.81.80.100";
    int port = 22;
    String localIp = null;
    int localPort = 0;
    int timeOut = 3000;
    String userName = "tomcat";
    String password = "tomcat";
    String[] cmds = new String[] { "cd /sftp/sftp_incoming_yc/yc",
        "mv 2  /sftp/sftp_outcoming_yc/outyc" };
    String[] result = null;
    try {
      result = execShellCmdBySSH(ip, port, localIp, localPort, timeOut,
          userName, password, cmds);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
    
    /**
     * 使用SSH协议，连接到Linux Shell，执行脚本命令，并获取结果
     * 
     * @param dstIp
     * @param dstport
     *            default :22
     * @param localIp
     * @param localPort
     * @param timeOut
     * @param userName
     * @param password
     * @param cmds
     * @return
     * @throws Exception
     */
    public static String[] execShellCmdBySSH(String dstIp, int dstport,
        String localIp, int localPort, int timeOut, String userName,
        String password, String... cmds) throws Exception {
      Session session = null;
      Channel channel = null;
      InputStream is = null;
      OutputStream os = null;
      try {
        session =createSession(dstIp, dstport, localIp,
            localPort, userName, password, timeOut);
        channel = session.openChannel("shell");
      
        // Enable agent-forwarding.
        // ((ChannelShell)channel).setAgentForwarding(true);
        // Choose the pty-type "vt102".
        // ((ChannelShell)channel).setPtyType("vt102");
        // Set environment variable "LANG" as "ja_JP.eucJP".
        // ((ChannelShell)channel).setEnv("LANG", "ja_JP.eucJP");
        channel.connect();
        is = channel.getInputStream();
        os = channel.getOutputStream();
        String[] result = new String[cmds.length];
        for (int i = 0; i < cmds.length; i++) {
          result[i] = sendCommand(is, os, cmds[i]);
        }
        return result;
      } catch (JSchException e) {
        if (e.getMessage().contains("Auth fail")) {
          throw new Exception("Auth error");
        } else {
          throw new Exception("Connect error");
        }
      } catch (Exception e) {
        throw e;
      } finally {
        try {
          is.close();
        } catch (IOException e) {
        }
        try {
          os.close();
        } catch (IOException e) {
        }
        System.out.println("1");
        channel.disconnect();
        session.disconnect();
      }
    }
    
    /**
     * 执行Shell命令，并获取执行结果
     * 
     * @param is
     * @param os
     * @param cmd
     * @return
     * @throws IOException
     */
    private static String sendCommand(InputStream is, OutputStream os,
        String cmd) throws IOException {
      os.write(cmd.getBytes());
      os.flush();
      StringBuffer sb = new StringBuffer();
      int beat = 0;
      while (true) {
        if (beat > 3) {
          break;
        }
        if (is.available() > 0) {
          byte[] b = new byte[is.available()];
          is.read(b);
          sb.append(new String(b));
          beat = 0;
        } else {
          if (sb.length() > 0) {
            beat++;
          }
          try {
            Thread.sleep(sb.toString().trim().length() == 0 ? 1000
                : 300);
          } catch (InterruptedException e) {
          }
        }
      }
      return sb.toString();
    }
}
