package org.excel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class GeneracionCampos {
    public void CreacionCeldaFila(XSSFSheet sheet) {
        XSSFRow row = sheet.getRow(0);
        XSSFCell Estado_Pasado = row.createCell(4);
        XSSFCell Estado_Actual = row.createCell(5);

        Estado_Pasado.setCellValue("Estado Pasado");
        Estado_Actual.setCellValue("Estado Actual");

        sheet.setColumnWidth(4, 15 * 256);
        sheet.setColumnWidth(5, 15 * 256);
    }
}