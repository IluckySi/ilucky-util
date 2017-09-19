package com.ilucky.util.escape;

public class EscapeUtilTest {

	public static void main(String[] args) {
		String tmp = "<p>测试</p>！";
		System.out.println("编码：   " + EscapeUtil.escape(tmp));
		System.out.println("解码：   " + EscapeUtil.unescape(tmp));
	}
}
/**
编码：   %3cp%3e%u6d4b%u8bd5%3c%2fp%3e%uff01
解码：   <p>测试</p>！
*/