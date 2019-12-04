package com.benjamin.excel.mapper;

import com.benjamin.excel.pojo.Booking;

import java.util.List;

public interface BookingMapper {

    List<Booking> selectBookings();

    void updateBookingByHawbNo(Booking booking);

    void addBooking(Booking booking);

    int selectByHawbNo(String hawb_no);

}
