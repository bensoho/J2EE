package com.benjamin.excel.service.impl;

import com.benjamin.excel.exception.MyException;
import com.benjamin.excel.mapper.BookingMapper;
import com.benjamin.excel.mapper.UserMapper;
import com.benjamin.excel.pojo.Booking;
import com.benjamin.excel.pojo.EsClientInfo;
import com.benjamin.excel.pojo.User;
import com.benjamin.excel.service.BookingService;
import com.benjamin.excel.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

@Service
public class BookingServiceImpl implements BookingService {


    @Autowired
    private BookingMapper bookingMapper;
    @Autowired
    ElasticsearchOperations elasticsearchTemplate;


    @Override
    public List<Booking> selectBookings() {
        return bookingMapper.selectBookings();
    }



    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean batchImportBooking(String fileName, MultipartFile file) throws Exception {
        boolean notNull = false;
        List<Booking> bookingList = new ArrayList<>();

        System.out.println("FileName: "+fileName);
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new MyException("上传文件格式不正确");
        }

        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if(sheet!=null){
            notNull = true;
        }


            Booking booking;
            Row row3 = sheet.getRow(2);//通过sheet表单对象得到行对象 -- row 3
            booking = new Booking();
//            if( row.getCell(0).getCellType() !=1){//循环时，得到每一行的单元格进行判断
//                throw new MyException("导入失败(第"+(r+1)+"行,用户名请设为文本格式)");
//            }
            String shipper_name = row3.getCell(0).getStringCellValue();//得到每一行第一个单元格的值
            if(shipper_name == null || shipper_name.isEmpty()){//判断是否为空
                throw new MyException("导入失败(第"+(3)+"行, shipper name未填写)");
            }

            row3.getCell(1).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
            String hawb_no = row3.getCell(1).getStringCellValue();

            //System.out.println("hawb: "+hawb_no);
            if(hawb_no==null || hawb_no.isEmpty()){
                throw new MyException("导入失败(第"+(2)+"行, hawb_no未填写)");
            }



            Row row5 = sheet.getRow(4);//通过sheet表单对象得到行对象 -- row 5
            row5.getCell(0).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第1个单元格的值
            String consignee_name = row5.getCell(0).getStringCellValue();
            if(consignee_name==null || consignee_name.isEmpty()){
                throw new MyException("导入失败(第"+(5)+"行,consignee name未填写)");
            }

            Row row7 = sheet.getRow(6);//通过sheet表单对象得到行对象 -- row 7
            row7.getCell(0).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第1个单元格的值
            String notify_party = row7.getCell(0).getStringCellValue();
            if(notify_party==null || notify_party.isEmpty()){
                throw new MyException("导入失败(第"+(7)+"行,notify_party未填写)");
            }




            //完整的循环一次 就组成了一个对象
            booking.setHawb_no(hawb_no);
            booking.setShipper_name(shipper_name);
            booking.setConsignee_name(consignee_name);
            booking.setNotify_party(notify_party);


            //System.out.println("网页传过来的参数："+shipper_name);
            booking.setShipper_gci(getGCI(shipper_name));
            booking.setConsignee_gci(getGCI(consignee_name));

        //System.out.println("ssss:"+getGCI(shipper_name));

        bookingList.add(booking);




        for (Booking bookingRecord : bookingList) {
            String hawbno = bookingRecord.getHawb_no();
            int cnt = bookingMapper.selectByHawbNo(hawbno);
            if (cnt == 0) {
                bookingMapper.addBooking(bookingRecord);
                System.out.println("插入: "+bookingRecord.getHawb_no());
            } else {
                bookingMapper.updateBookingByHawbNo(bookingRecord);
                System.out.println("更新: "+bookingRecord.getHawb_no());
            }
        }
        return notNull;
    }

    public String getGCI(String name){

        String company_id=null;
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryStringQuery(name))
                .build();
        List<EsClientInfo>list = elasticsearchTemplate.queryForList(searchQuery, EsClientInfo.class);
        for (EsClientInfo es:list){
            company_id = es.getCompany_id();

            //System.out.println(company_id);
            break;

        }
        return company_id;
    }


}
