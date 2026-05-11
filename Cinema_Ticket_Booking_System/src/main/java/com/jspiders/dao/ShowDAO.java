package com.jspiders.dao;

import com.jspiders.exceptions.*;
import com.jspiders.model.Show;
import com.jspiders.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowDAO {

    Connection conn;

    public ShowDAO() {
        conn = DBConnection.buildConnection();
    }

    public int getAuditoriumId(String aud_name){

        String sql = "select auditorium_id from cms.auditoriums where name = ?";
        int auditoriumID;

        try
        {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, aud_name);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                auditoriumID = rs.getInt("auditorium_id");
            }
            else{
                throw new AuditoriumIdCannotBeFoundException("Auditorium not found");
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return auditoriumID;
    }

    public int getMovieId(String movie_name){

        String sql = "select movie_id from cms.movies where title = ?";
        int movieID;

        try
        {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, movie_name);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                movieID = rs.getInt("movie_id");
            }
            else{
                throw new MovieIdCannnotBeFoundException("Movie not found");
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return movieID;

    }

    public void createShow(Show s) {

        String insertSQL = "insert into cms.shows values(0,?,?,?,?,?);";

        try
        {
            PreparedStatement ps = conn.prepareStatement(insertSQL);
            ps.setInt(1, s.getAuditoriumId());
            ps.setString(2, s.getShowTime());
            ps.setString(3, s.getEndTime());
            ps.setString(4, s.getStatus());
            ps.setInt(5, s.getMovieId());
            int row = ps.executeUpdate();
            System.out.println();
            System.out.println(row + " ROWS INSERTED");
        }
        catch (SQLIntegrityConstraintViolationException e)
        {
            String message = e.getMessage();
            if (message.contains("movie_id"))
            {
                System.err.println("Movie Id is Invalid");
                throw new MovieIdCannnotBeFoundException("Movie Id must be there in Movie Table");
            }
            else if (message.contains("auditorium_id"))
            {
                System.err.println("Auditorium Id is Invalid");
                throw new AuditoriumIdCannotBeFoundException("Auditorium Id must be there in Auditorium Table");
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public int getShowID(int auditoriumID,String showTime) {

        int showID;
        String sql = "Select show_id from cms.shows where auditorium_id=? and show_time=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, auditoriumID);
            pst.setString(2,showTime);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                showID = rs.getInt("show_id");
            }
            else{
                throw new RuntimeException("Show ID not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return showID;
    }

    public void setShowSeats(int ShowId, int auditoriumId){

        String sql = "select seat_id,seat_type from cms.seats where auditorium_id=?";
        try
        {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, auditoriumId);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int seatID = rs.getInt("seat_id");
                String seatType = rs.getString("seat_type");
                insertSeats(ShowId,seatID,seatType);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        System.out.println("\nSeats Updated..");
    }

    public void insertSeats(int ShowId,int seatId, String seatType){

        String insertSQL = "insert into cms.show_seats values(0,?,?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(insertSQL);
            pst.setString(1,"AVAILABLE");
            int price = 0;
            if(seatType.equals("REGULAR")){
                price = 150;
            }
            else{
                price = 200;
            }
            pst.setInt(2,price);
            pst.setInt(3,ShowId);
            pst.setInt(4,seatId);

            int row = pst.executeUpdate();

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Show> getShowsByAuditorium(int auditoriumId) {

        List<Show> shows = new ArrayList<>();
        String query = "SELECT show_time FROM shows WHERE auditorium_id = ?";

        try
        {
            Connection connection = DBConnection.buildConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, auditoriumId);
            ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Show show1 = new Show();
                    show1.setShowTime(rs.getString("show_time"));
                    shows.add(show1);
                }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return shows;
    }

}

