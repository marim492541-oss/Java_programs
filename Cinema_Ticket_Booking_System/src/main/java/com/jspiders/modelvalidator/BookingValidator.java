package com.jspiders.modelvalidator;

import com.jspiders.model.Booking;
import com.jspiders.model.Movie;

import java.time.LocalDate;

public class BookingValidator {

    public static void validator(Booking booking){
        validateId(booking);
        validateUserName(booking);
        validateUserPhoneNumber(booking);
        validateTotalAmount(booking);
        validateStatus(booking);
        validatePaidAt(booking);
        validateShowId(booking);
    }

    public static void validateId(Booking booking){
        if(booking.getBookingId()>0){
            throw new RuntimeException("Booking ID must be greater than 0");
        }
    }

    public static void validateUserName(Booking booking){
        if(booking.getUserName()==null || booking.getUserName().isBlank()){
            throw new RuntimeException("Username cannot be empty");
        }
    }

    public static void validateUserPhoneNumber(Booking booking){
        if(booking.getUserPhoneNumber()==null || Integer.valueOf(booking.getUserPhoneNumber())<0 || booking.getUserPhoneNumber().isBlank() || !(booking.getUserPhoneNumber().length()==10)){
            throw new RuntimeException("User phone number must be length 10");
        }
    }

    public static void validateShowId(Booking booking){
        if(booking.getShowId()<0){
            throw new RuntimeException("Show ID must be greater than 0");
        }
    }

    public static void validateTotalAmount(Booking booking){
        if(booking.getTotalAmount()<0){
            throw new RuntimeException("Total amount must be greater than 0");
        }
    }

    public static void validateStatus(Booking booking){
        if(booking.getStatus()==null || booking.getStatus().isBlank()){
            throw new RuntimeException("Status cannot be empty");
        }
    }

    public static void validatePaidAt(Booking booking){
        LocalDate localDate = LocalDate.now();
        LocalDate date = LocalDate.parse(booking.getBookedAt());
        if(date.isAfter(localDate)){
            throw new RuntimeException("Booked Date cannot be before today date");
        }
    }
}
