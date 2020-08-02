package com.topideal.supplychain.ocp.utils;

import java.math.BigDecimal;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;

public class MyPOIUtils {

	private MyPOIUtils() {};
	/**
	 * <p>desc：设置单元格的值，包括样式</p>
	 * @date 2017年9月26日
	 * @param style
	 * @param value
	 * @param row
	 * @param i
	 */
	public static void setCellValueWithStyle(CellStyle style, String value, Row row,int i) {
		Cell cell = row.createCell(i);
		cell.setCellStyle(style);
		cell.setCellValue(value);
	}
	
	/**
	 * 判断是否是数字
	 * @param str
	 * @return
	 */
    public static boolean isNumberic(String str) {
        try {
            new BigDecimal(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    /**
     * 字符串转换
     * @param cell
     * @return
     */
    public static String getStringCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        cell.setCellType(CellType.STRING);
        String value = cell.getStringCellValue();
        return value == null ? "" : value.trim();
    }
    
	// 判断行为空
	public static int CheckRowNull(Row row, int columnSize) {
		if (null == row) {
			return columnSize;
		}
		int num = 0;
		for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
			
			Cell cell = row.getCell(i, MissingCellPolicy.CREATE_NULL_AS_BLANK);
			cell.setCellType(CellType.STRING);
			String s = cell.getStringCellValue();
			if (s == null || s.trim().length() == 0) {
				num++;
			}		
		}
		
		if (row.getPhysicalNumberOfCells() < columnSize) {
			num += (columnSize - row.getPhysicalNumberOfCells());
		}
		return num;
	}
}
