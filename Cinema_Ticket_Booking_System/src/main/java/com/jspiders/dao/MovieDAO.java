package com.jspiders.dao;

import com.jspiders.exceptions.DupticateMovieNameException;
import com.jspiders.model.Movie;
import com.jspiders.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;


public class MovieDAO {

    Connection conn;

    public MovieDAO() {
        conn = DBConnection.buildConnection();
    }

    public void addMovie(Movie movie){

        String insertSQL = "insert into cms.movies values(0,?,?,?,?,?,?);";
        try {
            PreparedStatement ps = conn.prepareStatement(insertSQL);
            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getLanguage());
            ps.setInt(3,movie.getDurationInMinutes());
            ps.setString(4, movie.getCertification());
            ps.setString(5,movie.getStatus());
            ps.setString(6, movie.getCreatedAt());
            int row = ps.executeUpdate();
            System.out.println();
            System.out.println(row + " ROWS INSERTED");
        }
        catch (SQLIntegrityConstraintViolationException e){
            System.err.println("Movie Name is Invalid");
            throw new DupticateMovieNameException("Duplicate Movie Name not allowed");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}