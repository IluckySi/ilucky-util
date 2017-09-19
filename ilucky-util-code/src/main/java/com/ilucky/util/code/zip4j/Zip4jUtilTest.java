package com.ilucky.util.code.zip4j;

public class Zip4jUtilTest {

	public static void main(String[] args) { 
		//加密.
		Zip4jUtil zip4jUtil  = new Zip4jUtil();
		zip4jUtil.setSrcPath("D:\\test");
		zip4jUtil.setDstPath("D:\\test\\dst.zip");
		zip4jUtil.setPassword("123");
		//zip4jUtil.encrypt();
		
		//解密.
		zip4jUtil.setSrcPath("D:\\test\\dst.zip");
		zip4jUtil.setDstPath("D:\\test\\");
		zip4jUtil.setPassword("123");
		zip4jUtil.decrypt();
	}
}
