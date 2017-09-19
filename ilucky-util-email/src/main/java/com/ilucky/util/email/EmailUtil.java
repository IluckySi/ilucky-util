package com.ilucky.util.email;

import java.net.URL;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 * @author IluckySi
 * @since 20150108
 */
public class EmailUtil {

	private String hostName;// 邮箱服务器.
	private int port; //服务器端口号.
	private String defaultAuthenticatorUsername;// 用户名.
	private String defaultAuthenticatorPassword;// 密码.
	private String charset = "GB2312";// 邮件编码方式,默认为GB2312.
	private int timeout = 8000;//超时时间,单位毫秒,默认为8000.
	private String from;// 发送方.
	private String to;// 接收方.
	private String[] tos;// 多个接收方.
	private String cc;// 抄送方.
	private String[] ccs;// 多个抄送方.
	private String bcc;// 秘密抄送方.
	private String[] bccs;// 多个秘密抄送方.
	private String subject;// 标题.
	private String msg;// 内容.
	private String localAttachmentPath;// 本地附件.
	private String remoteAttachmentPath;// 远程附件.
	private String attachmentName;//附件名称.
	private String attachDescription;//附件描述.
	private String html;//链接.

	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getDefaultAuthenticatorUsername() {
		return defaultAuthenticatorUsername;
	}
	public void setDefaultAuthenticatorUsername(
			String defaultAuthenticatorUsername) {
		this.defaultAuthenticatorUsername = defaultAuthenticatorUsername;
	}
	public String getDefaultAuthenticatorPassword() {
		return defaultAuthenticatorPassword;
	}
	public void setDefaultAuthenticatorPassword(
			String defaultAuthenticatorPassword) {
		this.defaultAuthenticatorPassword = defaultAuthenticatorPassword;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String[] getTos() {
		return tos;
	}
	public void setTos(String[] tos) {
		this.tos = tos;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String[] getCcs() {
		return ccs;
	}
	public void setCcs(String[] ccs) {
		this.ccs = ccs;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	public String[] getBccs() {
		return bccs;
	}
	public void setBccs(String[] bccs) {
		this.bccs = bccs;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getLocalAttachmentPath() {
		return localAttachmentPath;
	}
	public void setLocalAttachmentPath(String localAttachmentPath) {
		this.localAttachmentPath = localAttachmentPath;
	}
	public String getRemoteAttachmentPath() {
		return remoteAttachmentPath;
	}
	public void setRemoteAttachmentPath(String remoteAttachmentPath) {
		this.remoteAttachmentPath = remoteAttachmentPath;
	}
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public String getAttachDescription() {
		return attachDescription;
	}
	public void setAttachDescription(String attachDescription) {
		this.attachDescription = attachDescription;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	
	/**
	 * 发送普通邮件. 支持一个/多个接收方,一个/多个抄送方,一个/多个秘密抄送方.
	 * @return boolean
	 */
	public boolean sendWithMsg() {
		boolean result = false;
		SimpleEmail email = new SimpleEmail();
		email.setSmtpPort(port);
		try {
			email.setHostName(hostName);
			email.setAuthenticator(new DefaultAuthenticator(
					defaultAuthenticatorUsername, defaultAuthenticatorPassword));
			email.setCharset(charset);
			email.setSocketConnectionTimeout(timeout);
			email.setFrom(from);
			if (to != null) {
				email.addTo(to);
			}
			if (tos != null) {
				email.addTo(tos);
			}
			if (cc != null) {
				email.addCc(cc);
			}
			if (ccs != null) {
				email.addCc(ccs);
			}
			if (bcc != null) {
				email.addBcc(bcc);
			}
			if (bccs != null) {
				email.addBcc(bccs);
			}
			if (subject != null) {
				email.setSubject(subject);
			}
			if (msg != null) {
				email.setMsg(msg);
			}
			if (email.send() != null) {
				System.out.println("发送邮件成功");
				result = true;
			} else {
				System.out.println("发送邮件失败");
			}
		} catch (EmailException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 发送带有附件的邮件. 支持一个/多个接收方,一个/多个抄送方,一个/多个秘密抄送方.
	 * @return boolean
	 */
	public boolean sendWithMsgAndAttachment() {
		boolean result = false;
		MultiPartEmail email = new MultiPartEmail();
		email.setSmtpPort(port);
		EmailAttachment attachment = new EmailAttachment();
		try {
			email.setHostName(hostName);
			email.setAuthenticator(new DefaultAuthenticator(
					defaultAuthenticatorUsername, defaultAuthenticatorPassword));
			email.setCharset(charset);
			email.setSocketConnectionTimeout(timeout);
			email.setFrom(from);
			if (to != null) {
				email.addTo(to);
			}
			if (tos != null) {
				email.addTo(tos);
			}
			if (cc != null) {
				email.addCc(cc);
			}
			if (ccs != null) {
				email.addCc(ccs);
			}
			if (bcc != null) {
				email.addBcc(bcc);
			}
			if (bccs != null) {
				email.addBcc(bccs);
			}
			if (subject != null) {
				email.setSubject(subject);
			}
			if (msg != null) {
				email.setMsg(msg);
			}
			if (localAttachmentPath != null) {
				attachment.setPath(localAttachmentPath);
				attachment.setDisposition(EmailAttachment.ATTACHMENT);
			}
			if (remoteAttachmentPath != null) {
				attachment.setURL(new URL(remoteAttachmentPath));
				attachment.setDisposition(EmailAttachment.ATTACHMENT);
			}
			if(attachmentName != null) {
				attachment.setName(attachmentName);
			}
			if(attachDescription != null) {
				attachment.setDescription(attachDescription);
			}
			email.attach(attachment);
			if (email.send() != null) {
				System.out.println("发送邮件成功");
				result = true;
			} else {
				System.out.println("发送邮件失败");
			}
		} catch (EmailException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 发送带链接的普通邮件. 支持一个/多个接收方,一个/多个抄送方,一个/多个秘密抄送方.
	 * @return boolean
	 */
	public boolean sendWithHtml() {
		boolean result = false;
		HtmlEmail email = new HtmlEmail();
		email.setSmtpPort(port);
		try {
			email.setHostName(hostName);
			email.setAuthenticator(new DefaultAuthenticator(
					defaultAuthenticatorUsername, defaultAuthenticatorPassword));
			email.setCharset(charset);
			email.setSocketConnectionTimeout(timeout);
			email.setFrom(from);
			if(html != null) {
				email.setHtmlMsg(html);
			}
			if (to != null) {
				email.addTo(to);
			}
			if (tos != null) {
				email.addTo(tos);
			}
			if (cc != null) {
				email.addCc(cc);
			}
			if (ccs != null) {
				email.addCc(ccs);
			}
			if (bcc != null) {
				email.addBcc(bcc);
			}
			if (bccs != null) {
				email.addBcc(bccs);
			}
			if (subject != null) {
				email.setSubject(subject);
			}
			if (msg != null) {
				email.setMsg(msg);
			}
			if (email.send() != null) {
				System.out.println("发送邮件成功");
				result = true;
			} else {
				System.out.println("发送邮件失败");
			}
		} catch (EmailException e) {
			e.printStackTrace();
		}
		return result;
	}
}
