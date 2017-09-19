package com.ilucky.util.zip;

/**
 * @author IluckySi
 * @date 20141107
 */
public class MainTest {

	public static void main(String[] args) {
		ZipUtil zipUtil = new ZipUtil();
		zipUtil.setCode("GB2312");
		zipUtil.setBuffer(10240);
		zipUtil.setSrcPath("E:\\src");
		zipUtil.setDstPath("E:\\dst\\src.zip");
		long startZip = System.currentTimeMillis();
		zipUtil.startZip();
		long endZip = System.currentTimeMillis();
		System.out.println("压缩文件耗时: " + (endZip - startZip) + "毫秒");
		
		UnZipUtil unZipUtil = new UnZipUtil();
		unZipUtil.setCode("GB2312");
		unZipUtil.setBuffer(10240);
		unZipUtil.setSrcPath("E:\\src\\src.zip");
		unZipUtil.setDstPath("E:\\dst");
		long startUnZip = System.currentTimeMillis();
		//unZipUtil.startUnZip();
		long endUnZip = System.currentTimeMillis();
		System.out.println("解压缩文件耗时: " + (endUnZip - startUnZip) + "毫秒");
	}
}
