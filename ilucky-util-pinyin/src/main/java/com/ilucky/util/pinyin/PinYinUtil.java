package com.ilucky.util.pinyin;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author IluckySi
 * @since 20141119
 */
public class PinYinUtil {

	/**
	 * 将字符串中的汉字替换为汉字的全拼.
	 * @param str
	 * @return String
	 */
	public static String getPinYin(String str) {
		String result = "";
		char[] t1 = str.toCharArray();
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
		try {
			for (int i = 0; i < t1.length; i++) {
				//判断是不是汉字.
				if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
					result += PinyinHelper.toHanyuPinyinStringArray(t1[i], format)[0];
				} else {
					result += Character.toString(t1[i]);
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 将字符串中的汉字替换为汉字的全拼,并且每个汉字全拼的首字母大写.
	 * @param str
	 * @return String
	 */
	public static String getPinYin2(String str) {
		String result = "";
		char[] charArray = str.toCharArray();
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
		try {
			for (int i = 0; i < charArray.length; i++) {
				//判断是不是汉字.
				if (Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+")) {
					String old = PinyinHelper.toHanyuPinyinStringArray(charArray[i], format)[0];
					result += old.substring(0, 1).toString().toUpperCase() + old.substring(1);
				} else {
					result += Character.toString(charArray[i]);
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将字符串中的汉字替换为汉字的首字母.
	 * @param str
	 * @return String
	 */
	public static String getPinYinHeadChar(String str) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			char word = str.charAt(i);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				result += pinyinArray[0].charAt(0);
			} else {
				result += word;
			}
		}
		return result;
	}
}