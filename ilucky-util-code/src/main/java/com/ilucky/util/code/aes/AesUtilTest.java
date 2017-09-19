package com.ilucky.util.code.aes;

public class AesUtilTest {

    public static void main(String[] args) throws Exception {
    	//字符串加密解密.
    	System.out.println(AesUtil.encrypt("{\"key\":\"value1\", \"key2\":\"value2\"}"));
    	System.out.println(AesUtil.decrypt("D71418938910EC8E2D6361B6ABF28D589B7E34DCB93805E96CDDB2DFEE4B9ECE6EDEE6633203374C9B9AA727F7B1203C"));
   
    	//文件内容加密解密.
    	AesUtil.EncryptFile("D:\\test\\2水电费.txt", "D:\\test\\code.txt");
    	AesUtil.DecryptFile("D:\\test\\code.txt", "D:\\test\\2水电费2.txt");
    }
}
