package com.ilucky.util.code.md5;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author diandao
 */
public class Md5Util {

	/**
	 * @param before
	 * @return
	 */
	public static String md5(String before) {
		return DigestUtils.md5Hex(before).toString();
	}
}
