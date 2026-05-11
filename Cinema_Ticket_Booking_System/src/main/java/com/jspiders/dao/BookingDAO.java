package com.jspiders.dao;

import com.jspiders.exceptions.SeatIdInvalidException;
import com.jspiders.exceptions.SeatUnavailableException;
import com.jspiders.model.Booking;
import com.jspiders.model.Seat;
import com.jspiders.model.Show;
import com.jspiders.util.DBConnection;
import java.sql.*;
import java.time.LocalDate;

import java.util.LinkedList;

public class BookingDAO {

    Connection con;

    public BookingDAO() {
        con = DBConnection.buildConnection();
    }

    public void bookTicket(int aud_id,Show show, LinkedList<Seat> seats, Booking booking){

        LinkedList<Integer> seatList = getSeatList(aud_id, seats);
        insertion(show, booking);
        int totalAmount = getTotalAmount(show, seats, seatList);
        updateBooking(booking, totalAmount);
        updateSeats(seatList);
        System.out.println("\nBOOKING DONE!");

    }

    private LinkedList<Integer> getSeatList(int aud_id, LinkedList<Seat> seats) {

        String seatIdQuery = "Select seat_id from seats where row_label=? and seat_no=? and auditorium_id=?";
        int seat_id;
        LinkedList<Integer> seatList = new LinkedList<>();

        try
        {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(seatIdQuery);
            for(int i = 0; i< seats.size(); i++)
            {
                seat_id = 0;
                String rowLabel = seats.get(i).getRowLabel() + "";
                int column = seats.get(i).getSeatNumber();
                ps.setString(1, rowLabel);
                ps.setInt(2, column);
                ps.setInt(3, aud_id);
                ResultSet rs = ps.executeQuery();
                while(rs.next())
                {
                    seat_id = rs.getInt(1);
                }
                if(seat_id == 0)
                {
                    throw new SeatIdInvalidException("No Seat exist like " + rowLabel + column);
                }
                seatList.add(seat_id);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seatList;
    }

    private void insertion(Show show, Booking booking) {

        String insertSql = "insert into booking(booking_id,username,phone,show_id) values(0,?,?,?)";
        try
        {
            PreparedStatement ps = con.prepareStatement(insertSql);
            //ps.setInt(1, booking.getBookingId());
            ps.setString(1, booking.getUserName());
            ps.setString(2, booking.getUserPhoneNumber());
            ps.setInt(3, show.getShowId());
            int row = ps.executeUpdate();
            if(row == 0)
            {
                throw new RuntimeException("values not inserted");
            }
            System.out.println(row + " row inserted");
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    private int getTotalAmount(Show show, LinkedList<Seat> seats, LinkedList<Integer> seatList) {

        String Available = "select status,price from cms.show_seats where show_id=? and seat_id=?";
        int totalAmount = 0;

        try
        {
            PreparedStatement ps = con.prepareStatement(Available);
            for (int i = 0; i< seatList.size(); i++){
                ps.setInt(1, show.getShowId());
                ps.setInt(2, seatList.get(i));
                ResultSet rs = ps.executeQuery();
                while(rs.next())
                {
                    if(!rs.getString(1).equals("AVAILABLE"))
                    {
                        throw new SeatUnavailableException("The Selected Seat is not AVAILABLE : " + seats.get(i).getRowLabel() + seats.get(i).getSeatNumber());
                    }
                    else
                    {
                        totalAmount += rs.getInt(2);
                    }
                }
            }
            System.out.println("\nYour Total Amount is Rs." + totalAmount);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return totalAmount;
    }

    private void updateBooking(Booking booking, int totalAmount) {

        String updateQuery = "update booking set status='BOOKED',total_amount=?,booked_at=? where username=?";

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

    private void updateSeats(LinkedList<Integer> seatList) {

        String update = "update cms.show_seats set status='BOOKED' where seat_id=?";
        try
        {
            PreparedStatement ps = con.prepareStatement(update);
            for(int i = 0; i< seatList.size(); i++)
            {
                ps.setInt(1, seatList.get(i));
                int row = ps.executeUpdate();
                if(row == 0)
                {
                    throw new RuntimeException("Status is not Updated");
                }
                else
                {
                    System.out.println("Seats Updated...");
                }
            }
            con.commit();
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
