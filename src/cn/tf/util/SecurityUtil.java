package cn.tf.util;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class SecurityUtil {
	public static String md5(String message){
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte b[] = md.digest(message.getBytes());
			BASE64Encoder base64 = new BASE64Encoder();
			return base64.encode(b);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	public static String base64Encode(String message){
		BASE64Encoder base64 = new BASE64Encoder();
		return base64.encode(message.getBytes());
	}
	public static String base64Decode(String message){
		try {
			BASE64Decoder base64 = new BASE64Decoder();
			byte b[] = base64.decodeBuffer(message);
			return new String(b);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public static void main(String[] args) {
		System.out.println(md5("123"));
	}

}
