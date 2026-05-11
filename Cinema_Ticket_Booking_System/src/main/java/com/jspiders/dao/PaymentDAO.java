package com.jspiders.dao;

import com.jspiders.exceptions.BookingIdCannotBeFoundException;
import com.jspiders.exceptions.InvalidAmountException;
import com.jspiders.model.Booking;
import com.jspiders.model.Payment;
import com.jspiders.util.DBConnection;

import java.sql.*;
import java.time.LocalDate;

public class PaymentDAO {
    Connection con = null;
    Savepoint s1;

    public PaymentDAO() {
        con = DBConnection.buildConnection();
    }

    public int getBookingIdOfTheUser(Booking booking) {

        int bookingId = 0;
        String query = "Select bookingId from Booking where username=? and phone=?";

        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, booking.getUserName());
            pstmt.setString(2, booking.getUserPhoneNumber());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                bookingId = rs.getInt(1);
            }
            else{
                throw new BookingIdCannotBeFoundException("Username or Phone number not found");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return bookingId;
    }

    public void confirmPayment(Payment payment) {

        int total = getTotalAmount(payment);
        insertValues(total,payment);
        updateToHeld(payment);
        //updateHeld(payment);
    }

    public int getTotalAmount(Payment payment) {

        String query = "Select total_amount from cms.booking where bookingId=?";
        int totalAmount = 0;
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, payment.getBookingId());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                totalAmount = rs.getInt(1);
            }
            else{
                throw new BookingIdCannotBeFoundException("Username or Phone number not found");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return totalAmount;
    }

    public void insertValues(int total,Payment payment) {

        String query = "insert into cms.payment(payment_id,booking_id,amount,method,status,txn_ref,paid_at) values(0,?,?,?,?,?,?)";
        try {
            con.setAutoCommit(false);
            LocalDate date = LocalDate.now();
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, payment.getBookingId());
            pstmt.setInt(2, payment.getAmount());
            pstmt.setString(3, payment.getMethod());
            pstmt.setString(4, "PAID");
            pstmt.setString(5, payment.getTxnReference());
            pstmt.setString(6, date.toString());
            if(!(total==payment.getAmount())){
                throw new InvalidAmountException("Total amount is invalid");
            }
            else{
                int row = pstmt.executeUpdate();
                System.out.println(row + " rows affected");
                s1 = con.setSavepoint("SAVEPOINT1");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateToHeld(Payment payment) {

        String updateQuery = "update cms.booking set status='HELD' where bookingId=?";
        try {
            PreparedStatement pstmt = con.prepareStatement(updateQuery);
            pstmt.setInt(1, payment.getBookingId());
            int row = pstmt.executeUpdate();
            if(row != 0) {
                System.out.println(row + " rows affected");
            }
            else{
                throw new BookingIdCannotBeFoundException("Username or Phone number not found");
            }
        }
        catch (SQLException e) {
            try {
                con.rollback(s1);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    private void updateHeld(Booking booking, int totalAmount) {

        String updateQuery = "update cms.booking set status='HELD',total_amount=?,booked_at=? where username=?";

        try {
            PreparedStatement ps = con.prepareStatement(updateQuery);
            ps.setInt(1, totalAmount);
            ps.setString(2, LocalDate.now().toString());
            ps.setString(3, booking.getUserName());
            int row = ps.executeUpdate();
            if(row == 0)
            {
                throw new RuntimeException("Booking not inserted - User not found");
            }
            else
            {
                System.out.println(row + " row updated");
                con.commit();
            }
        }
        catch (SQLException e)
        {
            try
            {
                con.rollback();
            }
            catch (SQLException ex)
            {
                throw new RuntimeException("Rollback Failed : " + ex.getMessage());
            }
            throw new RuntimeException(e);
        }
    }
}
