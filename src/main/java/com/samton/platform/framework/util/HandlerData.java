/**
 * 
 */
package com.samton.platform.framework.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.text.SimpleDateFormat;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @Description:处理sql脚本数据。读取excel数据生成sql语句
 * @author:     shenchu
 * @date:        2017年3月13日 上午9:55:58
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class HandlerData {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");  
	//创建文件对象 (数据源)
	File excelFile = new File("D:\\员工名称.xlsx");
	//文件流  
	FileInputStream is = new FileInputStream(excelFile); 
	//这种方式 Excel 2003/2007/2010 都是可以处理的 
	Workbook workbook = WorkbookFactory.create(is);
	//Sheet的数量
	int sheetCount = workbook.getNumberOfSheets();

	//写入文件
	FileWriter writer = new FileWriter("D:\\test.sql");
	
	//拼音格式化
	HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();  
	// UPPERCASE：大写  (ZHONG)  
	// LOWERCASE：小写  (zhong)  
	format.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
	// WITHOUT_TONE：无音标  (zhong)  
	// WITH_TONE_NUMBER：1-4数字表示英标  (zhong4)  
	// WITH_TONE_MARK：直接用音标符（必须WITH_U_UNICODE否则异常）  (zhòng)  
	format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
	// WITH_V：用v表示ü  (nv)  
	// WITH_U_AND_COLON：用"u:"表示ü  (nu:)  
	// WITH_U_UNICODE：直接用ü (nü)  
	format.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);  
	
	
    //遍历每个Sheet  
    for (int s = 0; s < sheetCount; s++) {  
        Sheet sheet = workbook.getSheetAt(s);  
        int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数  
        //遍历每一行  
        for (int r = 0; r < rowCount; r++) {  
            Row row = sheet.getRow(r);  
            int cellCount = row.getPhysicalNumberOfCells(); //获取总列数 
            int i =1000;
            //遍历每一列  
            for (int c = 0; c < cellCount; c++) {  
                Cell cell = row.getCell(c);  
                int cellType = cell.getCellType();  
                String cellValue = null;  
                switch(cellType) {  
                    case Cell.CELL_TYPE_STRING: //文本  
                        cellValue = cell.getStringCellValue();  
                        break;  
                    case Cell.CELL_TYPE_NUMERIC: //数字、日期  
                        if(DateUtil.isCellDateFormatted(cell)) {  
                            cellValue = fmt.format(cell.getDateCellValue()); //日期型  
                        }  
                        else {  
                            cellValue = String.valueOf(cell.getNumericCellValue()); //数字  
                        }  
                        break;  
                    case Cell.CELL_TYPE_BOOLEAN: //布尔型  
                        cellValue = String.valueOf(cell.getBooleanCellValue());  
                        break;  
                    case Cell.CELL_TYPE_BLANK: //空白  
                        cellValue = cell.getStringCellValue();  
                        break;  
                    case Cell.CELL_TYPE_ERROR: //错误  
                        cellValue = "数据异常";  
                        break;  
                    case Cell.CELL_TYPE_FORMULA: //公式  
                        cellValue = "数据异常";  
                        break;  
                    default:  
                        cellValue = "数据异常";  
                }  
                //System.out.print(cellValue+"\n");  
                StringBuffer pybf = new StringBuffer();
                char[] arr = cellValue.toCharArray();  
                for (int n = 0; n < arr.length; n++) {
                	if(arr[n] > 128){
                		pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[n], format)[0]);  
                	}
                }
                
                System.out.println(pybf.toString());
                //cellValue ="INSERT INTO t_proxy(proxy_name, proxy_code,state) VALUES ('"+cellValue+"股份有限公司', '"+(i+r)+"', 1);\n";
               cellValue = "INSERT INTO t_sys_pm_user(login_name, login_flag, pwd, user_name, system_id, depart_id, state) VALUES ('"+pybf.toString()+"', 1, '4297f44b13955235245b2497399d7a93', '"+cellValue+"', 0, 1, 1);\n";
               writer.write(cellValue);

            }  
        }  
    }  	
    writer.flush();
    writer.close();
	}
}
