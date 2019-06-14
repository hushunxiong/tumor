package com.wonders.health.tumor.common.utils.excel;

import com.wonders.health.tumor.common.utils.StringUtils;
import com.wonders.health.tumor.common.utils.excel.annotation.ExcelField;
import com.wonders.health.tumor.common.utils.excel.annotation.ExcelFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExcelUtils {
    /**
     * 使用浏览器选择路径下载
     * @param response
     * @param fileName
     * @param data
     * @throws Exception
     */
    private static void exportExcel(HttpServletResponse response, String fileName, ExcelData data) throws Exception {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        exportExcel(data, response.getOutputStream());
    }

    public static void exportExcel(HttpServletResponse response, List<?> data) throws Exception {
        ExcelData excelData=resolveData(data);
        exportExcel(response,excelData.getName(),excelData);
    }


    private static ExcelData resolveData(List<?> datas) throws Exception{
        ExcelData excelData = new ExcelData();
        List<String> title = new ArrayList<>();
        List<List<Object>> rowDatas = new ArrayList<>();
        boolean titleOk = false;
        for (Object data : datas) {
            Class clzz= data.getClass();
            if(!titleOk){
                if(!clzz.isAnnotationPresent(ExcelFile.class)){
                    throw new IllegalArgumentException("非excel file class:"+clzz.getSimpleName());
                }
                ExcelFile excelFile = (ExcelFile) clzz.getAnnotation(ExcelFile.class);
                String fileName = excelFile.fileName();
                if(StringUtils.isBlank(fileName)){
                    throw new IllegalArgumentException("excel文件名不能为空");
                }
                fileName = fileName+excelFile.subfix();
                excelData.setName(fileName);
            }
            List<Object> item = new ArrayList<>();
            for (Class cl=clzz;cl!=Object.class;cl=cl.getSuperclass()){
                for (Field field:cl.getDeclaredFields()){
                    if(field.isAnnotationPresent(ExcelField.class)){
                        ExcelField excelField=field.getAnnotation(ExcelField.class);
                        if(!titleOk){
                            String titleItem = excelField.title();
                            title.add(titleItem);
                        }
                        field.setAccessible(true);
                        item.add(Optional.ofNullable(field.get(data)).orElse(excelField.defValue()));
                        field.setAccessible(false);
                    }
                }
            }
            rowDatas.add(item);
            titleOk=true;
        }
        excelData.setTitles(title);
        excelData.setRows(rowDatas);
        return excelData;
    }



    private static int exportExcel(ExcelData data, OutputStream out) throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook();
        int rowIndex = 0;
        try {
            String sheetName = data.getName();
            if (null == sheetName) {
                sheetName = "Sheet1";
            }
            XSSFSheet sheet = wb.createSheet(sheetName);
            rowIndex = writeExcel(wb, sheet, data);
            wb.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //此处需要关闭 wb 变量
            out.close();
        }
        return rowIndex;
    }


    /**
     * 表显示字段
     * @param wb
     * @param sheet
     * @param data
     * @return
     */
    private static int writeExcel(XSSFWorkbook wb, Sheet sheet, ExcelData data) {
        int rowIndex = 0;
        rowIndex = writeTitlesToExcel(wb, sheet, data.getTitles());
        rowIndex = writeRowsToExcel(wb, sheet, data.getRows(), rowIndex);
        autoSizeColumns(sheet, data.getTitles().size() + 1);
        return rowIndex;
    }
    /**
     * 设置表头
     *
     * @param wb
     * @param sheet
     * @param titles
     * @return
     */
    private static int writeTitlesToExcel(XSSFWorkbook wb, Sheet sheet, List<String> titles) {
        int rowIndex = 0;
        int colIndex = 0;
        Font titleFont = wb.createFont();
        //设置字体
        titleFont.setFontName("simsun");
        //设置粗体
        //titleFont.setBoldweight(Short.MAX_VALUE);
        //设置字号
        titleFont.setFontHeightInPoints((short) 14);
        //设置颜色
        titleFont.setColor(IndexedColors.BLACK.index);
        XSSFCellStyle titleStyle = wb.createCellStyle();
        //水平居中
        titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        //垂直居中
        titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        //设置图案颜色
//        titleStyle.setFillForegroundColor(new XSSFColor(new Color(182, 184, 192)));
        //设置图案样式
        //titleStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        titleStyle.setFont(titleFont);
//        setBorder(titleStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));
        Row titleRow = sheet.createRow(rowIndex);
        titleRow.setHeightInPoints(25);
        colIndex = 0;
        for (String field : titles) {
            Cell cell = titleRow.createCell(colIndex);
            cell.setCellValue(field);
            cell.setCellStyle(titleStyle);
            colIndex++;
        }
        rowIndex++;
        return rowIndex;
    }

    /**
     * 设置内容
     *
     * @param wb
     * @param sheet
     * @param rows
     * @param rowIndex
     * @return
     */
    private static int writeRowsToExcel(XSSFWorkbook wb, Sheet sheet, List<List<Object>> rows, int rowIndex) {
        int colIndex;
        Font dataFont = wb.createFont();
        dataFont.setFontName("simsun");
        dataFont.setFontHeightInPoints((short) 14);
        dataFont.setColor(IndexedColors.BLACK.index);

        XSSFCellStyle dataStyle = wb.createCellStyle();
        dataStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        dataStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        dataStyle.setFont(dataFont);
        for (List<Object> rowData : rows) {
            Row dataRow = sheet.createRow(rowIndex);
            dataRow.setHeightInPoints(25);
            colIndex = 0;
            for (Object cellData : rowData) {
                Cell cell = dataRow.createCell(colIndex);
                if (cellData != null) {
                    cell.setCellValue(cellData.toString());
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(dataStyle);
                colIndex++;
            }
            rowIndex++;
        }
        return rowIndex;
    }

    /**
     * 自动调整列宽
     *
     * @param sheet
     * @param columnNumber
     */
    private static void autoSizeColumns(Sheet sheet, int columnNumber) {
        for (int i = 0; i < columnNumber; i++) {
            int orgWidth = sheet.getColumnWidth(i);
            sheet.autoSizeColumn(i, true);
            int newWidth = (int) (sheet.getColumnWidth(i) + 100);
            if (newWidth > orgWidth) {
                sheet.setColumnWidth(i, newWidth);
            } else {
                sheet.setColumnWidth(i, orgWidth);
            }
        }
    }


}

