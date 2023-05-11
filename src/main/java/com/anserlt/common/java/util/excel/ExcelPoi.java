package com.anserlt.common.java.util.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelPoi {

    /**
     * 将二维数组转化为可以直接导出的excel文件byte数组。
     */
    public byte[] transform2DStringListToXlsxBytes(List<List<String>> list, String sheetName) {
        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);
        for (int i = 0; i < list.size(); i++) {
            Row row = sheet.createRow(i);
            List<String> innerList = list.get(i);
            for (int j = 0; j < innerList.size(); j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(innerList.get(j));
            }
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = null;
        try {
            workbook.write(outputStream);
            outputStream.flush();
            bytes = outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (null != outputStream) {
                    outputStream.close();
                }
                if (null != workbook) {
                    workbook.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

}
