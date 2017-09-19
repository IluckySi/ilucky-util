package com.ilucky.util.pinyin;

/**
 * @author IluckySi
 * @since 20141119
 */
public class PinYinUtilTest {

	public static void main(String[] args) {
		String str = "你好,司冬雪!";
		//将字符串中的汉字替换为汉字的全拼.
		System.out.println(PinYinUtil.getPinYin(str));
		//将字符串中的汉字替换为汉字的全拼,并且每个汉字全拼的首字母大写.
		System.out.println(PinYinUtil.getPinYin2(str));
		//将字符串中的汉字替换为汉字全拼的首字母.
		System.out.println(PinYinUtil.getPinYinHeadChar(str));
		
		String str2 = "你1a好2,司冬344c雪!6";
		//将字符串中的汉字替换为汉字的全拼.
		System.out.println(PinYinUtil.getPinYin(str2));
		//将字符串中的汉字替换为汉字的全拼,并且每个汉字全拼的首字母大写.
		System.out.println(PinYinUtil.getPinYin2(str2));
		//将字符串中的汉字替换为汉字全拼的首字母.
		System.out.println(PinYinUtil.getPinYinHeadChar(str2));
	}
}
/**
nihao,sidongxue!
NiHao,SiDongXue!
nh,sdx!
ni1ahao2,sidong344cxue!6
Ni1aHao2,SiDong344cXue!6
n1ah2,sd344cx!6
*/