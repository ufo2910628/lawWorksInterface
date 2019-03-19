package com.dtxx.platform;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServlet;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public final class RSAEncrypt {

	private static final String DEFAULT_PRIVATE_KEY_FILEPATH = "pkcs8_private_key.pem";
	private static final String DEFAULT_PUBLIC_KEY_FILEPATH = "public_key.pem";

	private static String DEFAULT_PRIVATE_KEY =
              "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMABpLy5kYxiNpy0"
			+ "JY7q+BynBTIxIAbtEEc4YsCY/UYpfBVnzodgUPYlcuCJpRc2PisxIYNAulOqtIzT"
			+ "9CgHBnagQ5Q1oEXjAPIZmdECT3DXqSv269HQL75g6nZeq5HO21a9qRCzMiBSERg3"
			+ "+ROfkF8xI7GqNNSA1O4iRCMTRqexAgMBAAECgYEAqs3LeoepzrRf2K746lZkOTjr"
			+ "CmdhEkg5PuIOFz8BsDSfSXt64jrwSsetlclcr3V1gH7ITTZ7iopE6zJx9FmriLeC"
			+ "kmQC+ZRUGH1nvFWO3tQhCxez/yOmQKDDb5VtDgPyKI9DYCv1731MUy1tusbhzO/q"
			+ "3Djs3PFR6cDKSKPAMEECQQDzcez3CUHaHHN5Ip+GB8F9KkBtNtZNUJM6MWNXa/E7"
			+ "LyPxDvVoPNjqNsn2taFT+ErT9jd3RQuL65EW9IR2J4P9AkEAyeiZ/VmamWMKv5at"
			+ "sdZOOikRrzF3RV95hxAfIFhcOsvh20k5sxZ12V8tfWHU848j3aBdbfcwXacUa+Em"
			+ "unNOxQJAYfzqP6kXO36WMBzyjtVSWVoHYq5e5fHXwOWeMdL93y+jTOUTfGh5exSg"
			+ "iFAlZpIbNGP3gWdmNWSVGuNC6fgP1QJBAJXoPrydlG0h3c8Vfmy1ImuIJnsejOFS"
			+ "25Xt4E5RjFiTG2OGYHKY0HLNeabHxiX3NrfL4tJMXvqJnF3kUq7IAckCQQCBsE8i"
			+ "lzQkQInYbfFiGlRZGRr1uUAey2TArN605Xv+M42O/P3jhd1DUG24acogMM4qrHrB"
            + "UIsvXAj5mRIh0r62";

	private static String DEFAULT_PUBLIC_KEY =
              "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDAAaS8uZGMYjactCWO6vgcpwUy"
			+ "MSAG7RBHOGLAmP1GKXwVZ86HYFD2JXLgiaUXNj4rMSGDQLpTqrSM0/QoBwZ2oEOU"
			+ "NaBF4wDyGZnRAk9w16kr9uvR0C++YOp2XquRzttWvakQszIgUhEYN/kTn5BfMSOx"
            + "qjTUgNTuIkQjE0ansQIDAQAB";

	/**
	 * 私钥
	 */
	private static RSAPrivateKey privateKey;

	/**
	 * 公钥
	 */
    private static RSAPublicKey publicKey;

	/**
	 * 字节数据转字符串专用集合
	 */
	private static final char[] HEX_CHAR = {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
	};

	/**
	 * 获取私钥
	 * 
	 * @return 当前的私钥对象
	 */
	public static RSAPrivateKey getPrivateKey() {
		return privateKey;
	}

	/**
	 * 获取公钥
	 * 
	 * @return 当前的公钥对象
	 */
	public static RSAPublicKey getPublicKey() {
		return publicKey;
	}

	public static String getDefaultPrivateKey() {
		return DEFAULT_PRIVATE_KEY;
	}

	public static String getDefaultPublicKey() {
		return DEFAULT_PUBLIC_KEY;
	}

	static {
		initKey();
	}

	public static void initKey() {
		try {
			loadPrivateKey(DEFAULT_PRIVATE_KEY);
			loadPublicKey(DEFAULT_PUBLIC_KEY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从文件中输入流中加载公钥
	 * 
	 * @param in
	 *            公钥输入流
	 * @throws Exception
	 *             加载公钥时产生的异常
	 */
	public static void loadPublicKey(InputStream in) throws Exception {
		InputStreamReader inputStreamReader = null;
		StringBuilder sb;
		BufferedReader br;
		try {
			inputStreamReader = new InputStreamReader(in, "UTF-8");
			br = new BufferedReader(inputStreamReader);
			String readLine = null;
			sb = new StringBuilder();
			while ((readLine = br.readLine()) != null) {
				if (readLine.charAt(0) != '-') {
					sb.append(readLine);
					sb.append('\r');
				}
			}
			loadPublicKey(sb.toString());
		} catch (IOException e) {
			throw new Exception("公钥数据流读取错误");
		} catch (NullPointerException e) {
			throw new Exception("公钥输入流为空");
		} finally {
			Util.safeClose(inputStreamReader);
			Util.safeClose(in);
		}
	}

	/**
	 * 从字符串中加载公钥
	 * 
	 * @param publicKeyStr
	 *            公钥数据字符串
	 * @throws Exception
	 *             加载公钥时产生的异常
	 */
	public static void loadPublicKey(String publicKeyStr) throws Exception {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64Util.decodeBuffer(publicKeyStr));
			publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("公钥非法");
		} catch (NullPointerException e) {
			throw new Exception("公钥数据为空");
		}
	}

	/**
	 * 从文件中加载私钥
	 * 
	 * @param in 私钥字符串
	 * @return 是否成功
	 * @throws Exception
	 */
	public static void loadPrivateKey(InputStream in) throws Exception {
		InputStreamReader inputStreamReader = null;
		try {
			inputStreamReader = new InputStreamReader(in, "UTF-8");
			BufferedReader br = new BufferedReader(inputStreamReader);
			String readLine = null;
			StringBuilder sb = new StringBuilder();
			while ((readLine = br.readLine()) != null) {
				if (readLine.charAt(0) != '-') {
					sb.append(readLine);
					sb.append('\r');
				}
			}
			loadPrivateKey(sb.toString());
		} catch (IOException e) {
			throw new Exception("私钥数据读取错误");
		} catch (NullPointerException e) {
			throw new Exception("私钥输入流为空");
		} finally {
			Util.safeClose(inputStreamReader);
			Util.safeClose(in);
		}
	}

	public static void loadPrivateKey(String privateKeyStr) throws Exception {
		try {
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64Util.decodeBuffer(privateKeyStr));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("私钥非法");
		} catch (NullPointerException e) {
			throw new Exception("私钥数据为空");
		}
	}
	//从字符串中获取私钥
	public static RSAPrivateKey loadPrivateKeyReturn(String privateKeyStr) throws Exception {
		try {
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64Util.decodeBuffer(privateKeyStr));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("私钥非法");
		} catch (NullPointerException e) {
			throw new Exception("私钥数据为空");
		}
	}
	/**
	 * 从字符串中加载公钥
	 * 
	 * @param publicKeyStr
	 *            公钥数据字符串
	 * @throws Exception
	 *             加载公钥时产生的异常
	 */
	public static RSAPublicKey loadPublicKeyReturn(String publicKeyStr) throws Exception {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64Util.decodeBuffer(publicKeyStr));
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("公钥非法");
		} catch (NullPointerException e) {
			throw new Exception("公钥数据为空");
		}
	}

	/**
	 * 加密过程
	 * 
	 * @param publicKey
	 *            公钥
	 * @param data
	 *            明文数据
	 * @return
	 * @throws Exception
	 *             加密过程中的异常信息
	 */
	public static byte[] encrypt(RSAPublicKey publicKey, byte[] data) throws Exception {
		if (publicKey == null) {
			throw new Exception("加密公钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			// byte[] output = cipher.doFinal(plainTextData);

			// 获得加密块大小，如:加密前数据为128个byte，而key_size=1024 加密块大小为127
			// byte,加密后为128个byte;
			// 因此共有2个加密块，第一个127 byte第二个为1个byte
			int blockSize = cipher.getBlockSize();
			int outputSize = cipher.getOutputSize(data.length);// 获得加密块加密后块大小
			int leavedSize = data.length % blockSize;
			int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
			byte[] raw = new byte[outputSize * blocksSize];
			int i = 0;
			while (data.length - i * blockSize > 0) {
				if (data.length - i * blockSize > blockSize)
					cipher.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);
				else
					cipher.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i * outputSize);
				// 这里面doUpdate方法不可用，查看源代码后发现每次doUpdate后并没有什么实际动作除了把byte[]放到ByteArrayOutputStream中
				// ，而最后doFinal的时候才将所有的byte[]进行加密，可是到了此时加密块大小很可能已经超出了OutputSize所以只好用dofinal方法。
				i++;
			}
			raw = Base64Util.encode(raw).getBytes("UTF-8");

			return raw;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此加密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("加密公钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("明文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("明文数据已损坏");
		}
	}

	/**
	 * 解密过程
	 * 
	 * @param privateKey
	 *            私钥
	 * @param raw
	 *            密文数据
	 * @return 明文
	 * @throws Exception
	 *             解密过程中的异常信息
	 */
	public static byte[] decrypt(RSAPrivateKey privateKey, byte[] raw) throws Exception {
		if (privateKey == null) {
			throw new Exception("解密私钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			// byte[] output = cipher.doFinal(cipherData);
			int blockSize = cipher.getBlockSize();
			ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
			// BASE64Decoder decoder = new BASE64Decoder();
			// raw = decoder.decodeBuffer(new String(raw));
			raw = Base64Util.decodeBuffer(new String(raw));
			int j = 0;
			while (raw.length - j * blockSize > 0) {
				bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
				j++;
			}
			String data = new String(bout.toByteArray());
			return data.getBytes();
			// return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此解密算法");
		} catch (NoSuchPaddingException e) {
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("解密私钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("密文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("密文数据已损坏");
		}
	}

	/**
	 * 字节数据转十六进制字符串
	 * 
	 * @param data
	 *            输入数据
	 * @return 十六进制内容
	 */
	public static String byteArrayToString(byte[] data) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			// 取出字节的高四位 作为索引得到相应的十六进制标识符 注意无符号右移
			stringBuilder.append(HEX_CHAR[(data[i] & 0xf0) >>> 4]);
			// 取出字节的低四位 作为索引得到相应的十六进制标识符
			stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);
			if (i < data.length - 1) {
				stringBuilder.append(' ');
			}
		}

		return stringBuilder.toString();
	}
}
