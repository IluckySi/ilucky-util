package com.ilucky.util.email;

import com.ilucky.util.email.EmailUtil;

/**
 * @author IluckySi
 * @since 20150108
 * 注意: 以后可以扩展回复邮件, 定时发送等.
 * 注意: 如果发送有家报如下错误: 550 User has no permission
 * 原因是: 没有权限, 需要开启, 解决办法是: 进入设置-客户端授权密码，默认是关闭，开启即可.
 * 如果开启，需要设置授权码，设置授权码后，就可以使用授权码登录第三方邮件客户端了.
 * 参考url: http://blog.csdn.net/hughnes/article/details/52070878
 * 修改AUTHE_PASSWORD使用功能.
 */
public class EmailUtilTest {

	public static final String HOST_NAME = "smtp.163.com";
	public static final int PORT = 25;
	public static final String AUTHE_NAME = "18514477499";
	public static final String AUTHE_PASSWORD = "sdx12716585";
	public static final String ENCOD = "GB2312";
	
	public static void main(String[] args) {
		//post1();
		//post2();
		//post3();
		post4();
	}
	
	public static void post1() {
		EmailUtil eu = new EmailUtil();
		eu.setHostName(HOST_NAME);
		eu.setPort(PORT);
		eu.setDefaultAuthenticatorUsername(AUTHE_NAME);
		eu.setDefaultAuthenticatorPassword(AUTHE_PASSWORD);
		eu.setCharset(ENCOD);
		eu.setTimeout(16000);
		eu.setFrom("18514477499@163.com");
		eu.setTo("1151262684@qq.com");
		eu.setSubject("测试邮件");
		eu.setMsg("亲, 这是一封测试邮件!");
		System.out.println("发送邮件结果: " + eu.sendWithMsg());
	}
	
	public static void post2() {
		EmailUtil eu = new EmailUtil();
		eu.setHostName(HOST_NAME);
		eu.setPort(PORT);
		eu.setDefaultAuthenticatorUsername(AUTHE_NAME);
		eu.setDefaultAuthenticatorPassword(AUTHE_PASSWORD);
		eu.setCharset(ENCOD);
		eu.setTimeout(30000);
		eu.setFrom("sidongxue@sohu.com");
		String[] tos = new String[]{"1151262684@qq.com", "570258762@qq.com"};
		eu.setTos(tos);
		String[] ccs = new String[]{"1016336364@qq.com", "pengzhongchen@gmail.com"};
		eu.setCcs(ccs);
		String[] bccs = new String[]{"1198377646@qq.com", "sidongxue@sohu.com"};
		eu.setBccs(bccs);
		eu.setSubject("测试邮件");
		eu.setMsg("亲,这是一封测试邮件,如果收到邮件,请及时回复,十分感谢,如果回复晚了,后果自负!");
		System.out.println("发送邮件结果: " + eu.sendWithMsg());
	}
	
	public static void post3() {
		EmailUtil eu = new EmailUtil();
		eu.setHostName(HOST_NAME);
		eu.setPort(PORT);
		eu.setDefaultAuthenticatorUsername(AUTHE_NAME);
		eu.setDefaultAuthenticatorPassword(AUTHE_PASSWORD);
		eu.setCharset(ENCOD);
		eu.setTimeout(16000);
		eu.setFrom("sidongxue@sohu.com");
		eu.setTo("sidongxue@sohu.com");
		eu.setSubject("测试邮件"); 
		eu.setMsg("亲,这是一封测试邮件!");
		eu.setAttachmentName("哈哈");
		eu.setAttachDescription("这是一个文件,点我!");
		eu.setLocalAttachmentPath("D:\\ilucky\\新建文本文档22.txt");
		System.out.println("发送邮件结果: " + eu.sendWithMsgAndAttachment());
	}
	
	public static void post4() {
		EmailUtil eu = new EmailUtil();
		eu.setHostName(HOST_NAME);
		eu.setPort(PORT);
		eu.setDefaultAuthenticatorUsername(AUTHE_NAME);
		eu.setDefaultAuthenticatorPassword(AUTHE_PASSWORD);
		eu.setCharset(ENCOD);
		eu.setTimeout(16000);
		eu.setFrom("18514477499@163.com");
		eu.setTo("1151262684@qq.com");
		eu.setSubject("测试邮件");
		eu.setHtml("<a href='http://www.csdn.net'>csdn</a>");
		System.out.println("发送邮件结果: " + eu.sendWithHtml());
	}
}
