package com.czq.common.utils.excel;

import com.czq.common.model.order.OrderDetail;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ExportUtilsTest {

    @Test
    public void exportToExcel() throws IOException, IllegalAccessException {
        List<OrderDetail> list = new ArrayList<OrderDetail>();
        OrderDetail detail = new OrderDetail();
        detail.setPrice(1000);
        detail.setAddress("hello");
        detail.setBuyDate("2018-08-01 00:00:00");
        list.add(detail);
        list.add(new OrderDetail());
        File file = new File("C:\\Users\\DELL\\Desktop\\demo.xlsx");
        if(!file.exists()){
            file.createNewFile();
        }
        Workbook book = ExportUtils.exportToExcel(list,file);
        book.close();
    }
}