package com.inf.system.utiles;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Excel 工具类
 * 
 * @author Administrator
 * 
 */
public class ExcelUtils {

	/**
	 * 导出为Excel文件
	 * 
	 * @param list
	 * @param dirPath
	 * @param fileName
	 * @throws Exception
	 */
	public static void exportExcel(List<Object> data, List<String> titleRow, OutputStream os)
			throws Exception {

		WritableWorkbook workbook = Workbook.createWorkbook(os);
		WritableSheet sheet = workbook.createSheet("sheet1", 0);

		if (titleRow != null && titleRow.size() > 0) {
			for (int j = 0; j < titleRow.size(); j++) {
				sheet.addCell(new Label(j, 0, titleRow.get(j)));
			}
		}
		
		if (data != null && titleRow.size() > 0) {
			int i = 1;
			for (Object obj : data) {
				Class<? extends Object> cls = obj.getClass();
				Field[] fields = cls.getDeclaredFields();

				for (int j = 0; j < fields.length; j++) {
					Field field = fields[j];
					field.setAccessible(true);
					if (field.getType().getName().equals("double")) {
						sheet.addCell(new Label(j, i, String.valueOf(field.getDouble(obj))));
					} else if (field.getType().getName().equals("int")) {
						sheet.addCell(new Label(j, i, String.valueOf(field.getInt(obj))));
					} else {
						sheet.addCell(new Label(j, i, String.valueOf(field.get(obj))));
					}
				}
				
				i++;
			}
		}

		workbook.write();
		workbook.close();
	}
}
