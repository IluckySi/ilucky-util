package com.ilucky.util.excel;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author IluckySi
 * @since 20141215
 */
public class MainTest {

	public static void main(String[] args) {
		//解析excel.
		JxlUtil ju = new JxlUtil();
		ju.setPath("C:\\Users\\IluckySi\\Downloads\\女装-2017-03-11.xls");
		Map<String, List<List<String>>> listListMap = ju.parse();
		for(Entry<String, List<List<String>>> entry : listListMap.entrySet()) {
			System.out.println(entry.getKey());
			for(List<String> list : entry.getValue()) {
				StringBuilder sb = new StringBuilder();
				for(String s : list) {
					sb.append("#"+s);
				}
				System.out.println(sb);
			}
		}
	}
}
