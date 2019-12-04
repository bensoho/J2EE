package com.benjamin.charjs_demo.service.Impl;

import com.benjamin.charjs_demo.entity.model.User;
import com.benjamin.charjs_demo.repository.dao.UserMapper;
import com.benjamin.charjs_demo.service.UserService;
import com.benjamin.charjs_demo.transaction.MyException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int addUser(MultipartFile file) throws Exception {
        int result = 0;
        //存放excel表中所有user
        List<User> userList = new ArrayList<>();

        /**
         *
         * 判断文件版本
         */
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);

        System.out.println("suffix="+suffix);

        InputStream ins = file.getInputStream();

        Workbook wb = null;

        if(suffix.equals("xlsx")){

            wb = new XSSFWorkbook(ins);

        }else{
            wb = new HSSFWorkbook(ins);
        }
        /**
         * 获取excel表单
         */
        Sheet sheet = wb.getSheetAt(0);


        /**
         * line = 2 :从表的第三行开始获取记录
         *
         */
        if(null != sheet){

            for(int line = 2; line <= sheet.getLastRowNum();line++){

                User user = new User();

                Row row = sheet.getRow(line);

                if(null == row){
                    continue;
                }
                /**
                 * 判断单元格类型是否为文本类型
                 */
                if(1 != row.getCell(0).getCellType()){
                    throw new MyException("单元格类型不是文本类型！");
                }

                /**
                 * 获取第一个单元格的内容
                 */
                String username = row.getCell(0).getStringCellValue();

                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                /**
                 * 获取第二个单元格的内容
                 */

                String password = row.getCell(1).getStringCellValue();

                user.setName(username);
                user.setPassword(password);
                userList.add(user);

            }

            for(User userInfo:userList){

                /**
                 * 判断数据库表中是否存在用户记录，若存在，则更新，不存在，则保存记录
                 */
                String name = userInfo.getName();

                int count = userMapper.selectUser(name);

                if(0 == count){
                    result = userMapper.addUser(userInfo);
                }else{
                    result = userMapper.updateUser(userInfo);
                }

            }
        }

        return result;
    }

}
