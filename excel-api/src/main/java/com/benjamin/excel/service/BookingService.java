package com.benjamin.excel.service;

import com.benjamin.excel.pojo.Booking;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookingService {
    List<Booking> selectBookings();
    boolean batchImportBooking(String fileName, MultipartFile file) throws Exception;
}
