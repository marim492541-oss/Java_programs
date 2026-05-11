package com.jspiders.dao;

import com.jspiders.model.Show;
import com.jspiders.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeatDAO {

    Connection conn = null;

    public SeatDAO()
    {
        conn = DBConnection.buildConnection();
    }

    public void viewSeats(Show show) {

        String selectSQL = "select row_label,seat_no,seat_type,status,price from cms.seats,cms.show_seats " +
                "where seats.seat_id=show_seats.seat_id and show_seats.show_id = ?;";
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(selectSQL);
            pstmt.setInt(1, show.getShowId());
            ResultSet rs = pstmt.executeQuery();
                if(!rs.isBeforeFirst())
                {
                    System.out.println("No show Available.");
                }
            System.out.println();
            System.out.println("SeatNo - SeatType - Status - Price");
            System.out.println("-----------------------------------");
            while (rs.next()) {
                System.out.println(rs.getString(1) + rs.getInt(2) + " - " + rs.getString(3) + " - " + rs.getString(4) + " - " + rs.getInt(5));
            }
            System.out.println();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
