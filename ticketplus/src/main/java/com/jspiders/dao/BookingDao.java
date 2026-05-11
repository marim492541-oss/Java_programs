package com.jspiders.dao;

public interface BookingDao {

    void addBooking();

    void getBooking(Long bookingId);

    void updateBooking(Long bookingId);

    void deleteBooking(Long bookingId);

}
