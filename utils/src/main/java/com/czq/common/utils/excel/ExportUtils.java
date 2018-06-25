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
        setHeaderRowStyle(headerRow,workbook);
        for(int i=0; i< list.size(); i++){
            Field[] fields = list.get(i).getClass().getDeclaredFields();
            if(fields!=null&&fields.length!=0){
                org.apache.poi.ss.usermodel.Row contentRow = sheet.createRow(i+1);
                setContentRowStyle(contentRow,workbook);
                for(int j=0; j< fields.length;){
                    fields[j].setAccessible(true);
                    if(i==0){
                        Row row = fields[j].getAnnotation(Row.class);
                        if(row!=null){
                            headerRow.createCell(j).setCellValue(row.value());
                            setCellValue(contentRow.createCell(j),list.get(i),fields[j]);
                            j++;
                        }
                    }else{
                        Row row = fields[j].getAnnotation(Row.class);
                        if(row!=null){
                            setCellValue(contentRow.createCell(j),list.get(i),fields[j]);
                            j++;
                        }
                    }
                }
            }
        }
        autoColumn(sheet);
        workbook.write(new FileOutputStream(file));
        return workbook;
    }

    private static void setHeaderRowStyle(org.apache.poi.ss.usermodel.Row headerRow, Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontName("黑体");
        font.setFontHeightInPoints((short)14);
        cellStyle.setFont(font);
        headerRow.setRowStyle(cellStyle);
    }

    private static void setContentRowStyle(org.apache.poi.ss.usermodel.Row contentRow, Workbook workbook){
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short)12);
        contentRow.setRowStyle(cellStyle);
    }

    private static void autoColumn(Sheet sheet) {
        if(sheet.getRow(0)!=null){
            int maxColumn = sheet.getRow(0).getLastCellNum();
            for(short i=0; i<maxColumn; i++){
                sheet.autoSizeColumn(i,true);
            }
        }
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
