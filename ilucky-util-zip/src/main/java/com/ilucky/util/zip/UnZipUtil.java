package com.ilucky.util.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

/**
 * windows下路径格式:E:\\src\\test.txt,Linux下路径格式;/home/src/test.txt.
 * 解压缩文件支持如下操作:
 * 将某压缩文件解压缩到某目录.
 * @author IluckySi
 * @since 20141107
 */
public class UnZipUtil {

	public String code = "GB2312";
	public int buffer = 1024;
	public String srcPath;
	public String dstPath;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getBuffer() {
		return buffer;
	}
	public void setBuffer(int buffer) {
		this.buffer = buffer;
	}
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
	
	@SuppressWarnings("rawtypes")
	public void startUnZip () {
		boolean next = true;
		if(new File(srcPath).exists() == false) {
			try {
				next = false;
				throw new Exception("源路径 " + srcPath + "不存在！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(new File(dstPath).exists() == false) {
			try {
				next = false;
				throw new Exception("目标路径" + dstPath + "不存在！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(next == false) {
			return;
		}
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(srcPath, code); 
			Enumeration emu = zipFile.getEntries();
			while(emu.hasMoreElements()) {
				InputStream is = null;
				BufferedInputStream bis = null;
				FileOutputStream fos = null;
				BufferedOutputStream bos = null;
				try {
					ZipEntry entry = (ZipEntry) emu.nextElement();
					
					//注意:遍历压缩文件中所有非目录节点.
					if(!entry.isDirectory()) {
						
						//关键:创建父文件,mkdirs是创建多层目录.
						File file = new File(dstPath + File.separator + entry.getName());
						File parent = file.getParentFile();
						if (parent != null && (!parent.exists())) {
							parent.mkdirs();
							System.out.println("创建目录" + parent.getPath());
						}
						is = zipFile.getInputStream(entry);
						bis = new BufferedInputStream(is);
						fos = new FileOutputStream(file);
						bos = new BufferedOutputStream(fos, buffer);
						byte[] byteArray = new byte[buffer];
						int length = 0;
						while ((length = bis.read(byteArray, 0, buffer)) != -1) {
							bos.write(byteArray, 0, length);
							bos.flush();
						}
					}
				} catch (Exception e) {
					System.out.println(e.toString());
				} finally {
					try {
						if(bos != null) {
							bos.close();
							bos = null;
						}
						if(fos != null) {
							fos.close();
							fos = null;
						}
						if(bis != null) {
							bis.close();
							bis = null;
						}
						if(is != null) {
							is.close();
							is = null;
						}
					} catch (IOException e) {
						System.out.println(e.toString());
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}