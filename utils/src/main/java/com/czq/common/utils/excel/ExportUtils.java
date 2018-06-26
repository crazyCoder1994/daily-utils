package com.czq.common.utils.excel;

import com.czq.common.model.order.annotations.Row;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.List;

public class ExportUtils {

    public static <T> XSSFWorkbook exportToExcel(List<T> list,File file) throws IOException, IllegalAccessException {
        if(list==null||list.size()==0){
            return null;
        }
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("demo");
        org.apache.poi.ss.usermodel.Row headerRow = sheet.createRow(0);
        for(int i=0; i< list.size(); i++){
            Field[] fields = list.get(i).getClass().getDeclaredFields();
            if(fields!=null&&fields.length!=0){
                org.apache.poi.ss.usermodel.Row contentRow = sheet.createRow(i+1);
                for(int j=0; j< fields.length;){
                    fields[j].setAccessible(true);
                    if(i==0){
                        Row row = fields[j].getAnnotation(Row.class);
                        if(row!=null){
                            Cell headerCell = headerRow.createCell(j);
                            headerCell.setCellValue(row.value());
                            sheet.setColumnWidth(j,4000);
                            setHeaderCellStyle(headerCell,workbook);
                            Cell contentCell = contentRow.createCell(j);
                            setContentCellStyle(contentCell,workbook);
                            setCellValue(contentCell,list.get(i),fields[j]);
                            j++;
                        }
                    }else{
                        Row row = fields[j].getAnnotation(Row.class);
                        if(row!=null){
                            Cell contentCell = contentRow.createCell(j);
                            setContentCellStyle(contentCell,workbook);
                            setCellValue(contentCell,list.get(i),fields[j]);
                            j++;
                        }
                    }
                }
            }
        }
        workbook.write(new FileOutputStream(file));
        return workbook;
    }

    private static void setHeaderCellStyle(Cell headerCell, Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setWrapText(true);
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short)12);
        cellStyle.setFont(font);
        headerCell.setCellStyle(cellStyle);
    }

    private static void setContentCellStyle(Cell contentCell, Workbook workbook){
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setWrapText(true);
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short)10);
        cellStyle.setFont(font);
        contentCell.setCellStyle(cellStyle);
    }


    private static <T> void setCellValue(Cell cell, T t, Field field) throws IllegalAccessException {
        String fieldType = field.getType().getSimpleName().toLowerCase();
        if(fieldType.equals("integer")){
            fieldType = "int";
        }
        switch (fieldType) {
            case "string":
                cell.setCellValue(String.valueOf(field.get(t)!=null?field.get(t):""));
                break;
            case "int":
                cell.setCellValue(field.getInt(t));
                break;
            case "float":
                cell.setCellValue(field.getFloat(t));
                break;
            case "boolean":
                cell.setCellValue(field.getBoolean(t));
                break;
            case "date":
                cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(field.get(t)));
            case "double":
                cell.setCellValue(field.getDouble(t));
                break;
            case "byte":
                cell.setCellValue(field.getByte(t));
                break;
            case "short":
                cell.setCellValue(field.getShort(t));
                break;

        }
    }

}
