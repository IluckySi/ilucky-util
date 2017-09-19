package com.ilucky.util.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

/**
 * windows下路径格式:E:\\src\\test.txt,Linux下路径格式;/home/src/test.txt.
 * 压缩文件支持如下操作:
 * 1.源路径如果是:E:\\src\\test.txt,最后打成的zip包下只有一个文件test.txt.
 * 2.源路径如果是:E:\\src,最后打成的zip包下包含src目录,以及src目录下所有的子文件.
 * 3.源路径如果是:E:\\src\\,最后打成的zip包下没有src目录,只包含src目录下所有的子文件.
 * @author IluckySi
 * @since 20141107
 */
public class ZipUtil {

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
	
	public void startZip () {
		boolean next = true;
		if(new File(srcPath).exists() == false) {
			try {
				next = false;
				throw new Exception("源路径 " + srcPath + "不存在！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String dstDir = dstPath.substring(0, dstPath.lastIndexOf(File.separator));
		if(new File(dstDir).exists() == false) {
			try {
				next = false;
				throw new Exception("目标路径 " + dstDir + "不存在！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(next == false) {
			return;
		}
		File zip = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		ZipOutputStream zos = null;
		try {
			zip = new File(dstPath);
			fos = new FileOutputStream(zip);
			bos = new BufferedOutputStream(fos);
			zos = new ZipOutputStream(bos);
			zos.setEncoding(code);
			generateZip(srcPath, zos);
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				if(zos != null) {
					zos.closeEntry();
					zos.close();
					zos = null;
				} 
				if(bos != null) {
					bos.close();
					bos = null;
				}
				if(fos != null) {
					fos.close();
					fos = null;
				}
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}
	}
	
	public void generateZip(String filePath, ZipOutputStream zos) {
		File file = new File(filePath);
		File[] files = null;
		try {
			if (file.isDirectory()) {
				files = file.listFiles();
			} else if (file.isFile()) {
				files = new File[1];
				files[0] = file;
			}
			for (int i = 0; files != null && i < files.length; i++) {
				
				//关键:获取zip节点文件名称.
				String fileName =  files[i].getPath().substring(srcPath.lastIndexOf(File.separator) + 1);
				if (files[i].isDirectory()) {
					System.out.println("压缩目录" + files[i].getPath());
					zos.putNextEntry(new ZipEntry(fileName + File.separator));
					generateZip(files[i].getPath(), zos);
				} else {
					FileInputStream fis = null;
					BufferedInputStream bis = null;
					try {
						System.out.println("压缩文件" + files[i].getPath());
						zos.putNextEntry(new ZipEntry(fileName));
						fis = new FileInputStream(files[i]);
						bis = new BufferedInputStream(fis);
						byte[] byteArray = new byte[buffer];
						int length = 0;
						while ((length = bis.read(byteArray, 0, buffer)) != -1) {
							zos.write(byteArray, 0, length);
							zos.flush();
						}
					} catch (Exception e) {
						System.out.println(e.toString());
					} finally {
						try {
							if(bis != null) {
								bis.close();
								bis = null;
							}
							if(fis != null) {
								fis.close();
								fis = null;
							}
						} catch (IOException e) {
							System.out.println(e.toString());
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
