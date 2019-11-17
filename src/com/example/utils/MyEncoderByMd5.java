package com.example.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class MyEncoderByMd5{
	//md5加密
	public String EncoderByMd5(String str)  {
		

//		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'}; 
//        try {
//            // 按照相应编码格式获取byte[]
//            byte[] btInput = str.getBytes("utf-8");
//            // 获得MD5摘要算法的 MessageDigest 对象
//            MessageDigest mdInst = MessageDigest.getInstance("MD5");
//            // 使用指定的字节更新摘要
//            mdInst.update(btInput);
//            // 获得密文
//            byte[] md = mdInst.digest();
//            // 把密文转换成十六进制的字符串形式
//            int j = md.length;
//            char string[] = new char[j * 2];
//            int k = 0;
//
//            for (int i = 0; i < j; i++) {
//                byte byte0 = md[i];
//                string[k++] = hexDigits[byte0 >>> 4 & 0xf];
//                string[k++] = hexDigits[byte0 & 0xf];
//            }
//            return new String(string);
//        } catch (Exception e) {
//            return "-1";
//        }
//    }
		
		
		MessageDigest md5;
		String newStr = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64Encoder=new BASE64Encoder();		
			newStr=base64Encoder.encode(md5.digest(str.getBytes("utf-8")));		

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newStr;


		
		
//        MessageDigest md5 = null;
//        try{
//            md5 = MessageDigest.getInstance("MD5");
//        }catch (Exception e){
//            System.out.println(e.toString());
//            e.printStackTrace();
//            return "";
//        }
//        char[] charArray = str.toCharArray();
//        byte[] byteArray = new byte[charArray.length];
// 
//        for (int i = 0; i < charArray.length; i++)
//            byteArray[i] = (byte) charArray[i];
//        byte[] md5Bytes = md5.digest(byteArray);
//        StringBuffer hexValue = new StringBuffer();
//        for (int i = 0; i < md5Bytes.length; i++){
//            int val = ((int) md5Bytes[i]) & 0xff;
//            if (val < 16)
//                hexValue.append("0");
//            hexValue.append(Integer.toHexString(val));
//        }
//        return hexValue.toString();
//
//	}
	
	}
}
