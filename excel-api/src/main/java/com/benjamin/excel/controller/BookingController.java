package com.benjamin.excel.controller;


import com.benjamin.excel.pojo.Booking;
import com.benjamin.excel.pojo.EsClientInfo;
import com.benjamin.excel.service.BookingService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;


@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    ElasticsearchOperations elasticsearchTemplate;

    @RequestMapping("/ebook")
    public String ebook(){
        return "ebooking.html";
    }

    @RequestMapping("/book")
    public String showBookings(Model model) {
        List<Booking> bookings = bookingService.selectBookings();

//        for (Booking b:bookings){
//            System.out.println("shipper gci"+b.getShipper_gci());
//            System.out.println("consignee gci"+b.getConsignee_gci());
//        }


        model.addAttribute("booking", bookings);

        return "booking";
    }


    @RequestMapping(value = "/exportbooking")
    @ResponseBody
    public void export(HttpServletResponse response) throws IOException {
        List<Booking> bookings = bookingService.selectBookings();

        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet("获取excel测试表格");

        HSSFRow row = null;

        row = sheet.createRow(0);//创建第一个单元格
        row.setHeight((short) (26.25 * 20));
        row.createCell(0).setCellValue("用户信息列表");//为第一行单元格设值

        /*为标题设计空间
         * firstRow从第1行开始
         * lastRow从第0行结束
         *
         *从第1个单元格开始
         * 从第3个单元格结束
         */
        CellRangeAddress rowRegion = new CellRangeAddress(0, 0, 0, 2);
        sheet.addMergedRegion(rowRegion);

      /*CellRangeAddress columnRegion = new CellRangeAddress(1,4,0,0);
      sheet.addMergedRegion(columnRegion);*/


        /*
         * 动态获取数据库列 sql语句 select COLUMN_NAME from INFORMATION_SCHEMA.Columns where table_name='user' and table_schema='test'
         * 第一个table_name 表名字
         * 第二个table_name 数据库名称
         * */
        row = sheet.createRow(1);
        row.setHeight((short) (22.50 * 20));//设置行高
        row.createCell(0).setCellValue("Booking Id");//为第一个单元格设值
        row.createCell(1).setCellValue("hawbno");//为第二个单元格设值
        row.createCell(2).setCellValue("shipper");//为第三个单元格设值
        row.createCell(3).setCellValue("consignee");//为第三个单元格设值
        row.createCell(4).setCellValue("notifyparty");//为第三个单元格设值
        row.createCell(4).setCellValue("shipper_gci");//为第三个单元格设值
        row.createCell(4).setCellValue("consignee_gci");//为第三个单元格设值

        for (int i = 0; i < bookings.size(); i++) {
            row = sheet.createRow(i + 2);
            Booking booking = bookings.get(i);
            row.createCell(0).setCellValue(booking.getBid());
            row.createCell(1).setCellValue(booking.getHawb_no());
            row.createCell(2).setCellValue(booking.getShipper_name());
            row.createCell(3).setCellValue(booking.getConsignee_name());
            row.createCell(4).setCellValue(booking.getNotify_party());
            row.createCell(4).setCellValue(booking.getShipper_gci());
            row.createCell(4).setCellValue(booking.getConsignee_gci());
        }
        sheet.setDefaultRowHeight((short) (16.5 * 20));
        //列宽自适应
        for (int i = 0; i <= 13; i++) {
            sheet.autoSizeColumn(i);
        }
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream os = response.getOutputStream();
        response.setHeader("Content-disposition", "attachment;filename=booking.xls");//默认Excel名称
        wb.write(os);
        os.flush();
        os.close();
    }
    @RequestMapping(value = "/booking")
    public String exImport(@RequestParam(value = "filename")MultipartFile file, HttpSession session) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = bookingService.batchImportBooking(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:book";
    }


    @RequestMapping("/search")
    @ResponseBody
    public EsClientInfo getClientInfo(@RequestParam(name = "name") String name){
        System.out.println("name: "+name);
        String company_id=null;
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryStringQuery(name))
                .build();
        List<EsClientInfo>list = elasticsearchTemplate.queryForList(searchQuery, EsClientInfo.class);
//        for (EsClientInfo es:list){
//            company_id = es.getCompany_id();
//
//            //System.out.println(company_id);
//            break;
//
//        }
        return list.get(0);
    }



}
