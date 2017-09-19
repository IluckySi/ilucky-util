package com.ilucky.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * 注意:此工具类只支持解析excel2003,不支持解析高版本的excel,如果解析高版本excel会报如下错误:
 * jxl.read.biff.BiffException: Unable to recognize OLE stream
 * 解决方案:将高版本excel文件另存为Excel97-2003工作薄,然后再解析.
 * jxl较poi的好处是跨平台,因为是用纯java编写,poi虽然功能比jxl强大,但是是基于windows系统的.
 * @author IluckySi
 * @since 20141215
 */
public class JxlUtil {

	private String filePath;

	public String getPath() {
		return filePath;
	}
	public void setPath(String filePath) {
		this.filePath = filePath;
	}
	
	/**
	 * 解析excel文件.
	 * @return Map<String, List<List<String>>>
	 */
	public Map<String, List<List<String>>> parse() {
		File file = new File(filePath);
		if(!file.exists() || !file.getName().endsWith(".xls")) {
			try {
				//throw new Exception("要解析的路径有问题: " + filePath);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Map<String, List<List<String>>> listListMap = new HashMap<String, List<List<String>>>();
		Workbook workBook = null;  
		FileInputStream fis = null;
        try {
        	fis = new FileInputStream(file);
			workBook = Workbook.getWorkbook(fis);
			Sheet[] sheetArray = workBook.getSheets();
			for(int i = 0; sheetArray != null && i < sheetArray.length; i++) {
				Sheet sheet = sheetArray[i];
				List<List<String>> listList = parseSheet(sheet);
				if(listList != null && listList.size() > 0) {
					listListMap.put(sheet.getName(), listList);
				}
			}
		} catch (BiffException e) {
			System.out.println("解析文件发生异常: " + e);
		} catch (IOException e) {
			System.out.println("解析文件发生异常: " + e);
		} finally {
			try {
				if(workBook != null) {
					workBook.close();
					workBook = null;
				}
				if(fis != null) {
					fis.close();
					fis = null;
				}
			} catch (Exception e) {
				System.out.println("关闭文件流发生异常: " + e);
			}
		}
        return listListMap;
	}
	
	/**
	 * 解析sheet,需要注意的地方:合并单元格,
	 * 例:如果A6-A12合并了单元格,那么解析excel时,解析类库只认为A6有值.
	 * @param sheet
	 */
	private List<List<String>> parseSheet(Sheet sheet) {
		List<List<String>> listList = new ArrayList<List<String>>();
		int rowCount = sheet.getRows();
		for(int i = 0; i < rowCount; i++) {
			List<String> list = new ArrayList<String>();
			Cell[] cellArray = sheet.getRow(i);
			for(int j = 0; cellArray != null && j < cellArray.length; j++) {
				list.add(cellArray[j].getContents());
			}
			listList.add(list);
		}
		return listList;
	}
}
