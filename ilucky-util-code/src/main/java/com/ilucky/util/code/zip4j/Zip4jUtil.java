package com.ilucky.util.code.zip4j;

import java.io.File;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

/**
 * @author cloudwise
 */
public class Zip4jUtil {

	private String srcPath;
	private String dstPath;
	private String password = "123qweasdzxc!@#ilucky";
	
	public String getSrcPath() {
		return srcPath;
	}
	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}
	public String getDstPath() {
		return dstPath;
	}
	public void setDstPath(String dstPath) {
		this.dstPath = dstPath;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 加密
	 * 支持将某个文件或某个目录下所有的文件加密.
	 * 1. 某个文件:D:\\test\\src.zip.
	 * 2. 某个目录:D:\\test\\src
	 * @return boolean
	 */
	public boolean encrypt() {
		try {
			if(!new File(srcPath).exists()) {
				System.out.println("源路径不存在: "+srcPath);
				return false;
			}
			ZipParameters parameters = new ZipParameters();  
	        parameters.setEncryptFiles(true);  
	        parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);  
			parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);  
	        parameters.setPassword(password.toCharArray());  
	        File srcFile = new File(srcPath);
	        ZipFile destFile = new ZipFile(dstPath);  
	        if(srcFile.isDirectory()) {
                 destFile.addFolder(srcFile, parameters);  
	        } else {
	        	destFile.addFile(srcFile, parameters); 
	        }
	        return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 解密
	 * 支持将某个加密文件解压缩到某个指定目录下面.
	 * @return boolean
	 */
	public boolean decrypt() {
		 try {
			 if(!new File(srcPath).exists()) {
				 System.out.println("源路径不存在: "+srcPath);
				return false;
			 }
			 ZipFile srcFile = new ZipFile(srcPath);  
			 srcFile.setFileNameCharset("GBK");  
			 srcFile.setPassword(password.toCharArray());  
			 srcFile.extractAll(dstPath);
			 return true;
		} catch (ZipException e) {
			return false;
		}
	}
}
