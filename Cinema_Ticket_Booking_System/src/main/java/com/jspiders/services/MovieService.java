package com.jspiders.services;

import com.jspiders.dao.MovieDAO;
import com.jspiders.dao.ShowDAO;
import com.jspiders.model.Movie;
import com.jspiders.model.Show;
import com.jspiders.modelvalidator.MovieValidator;
import com.jspiders.modelvalidator.ShowValidator;

import java.util.Scanner;

public class MovieService {

    final private static Scanner sc = new Scanner(System.in);

    public void addMovie() {

        System.out.println("\nENTER MOVIE DETAILS..");

        System.out.print(" Enter Movie Name : ");
        String movieName = sc.nextLine().toUpperCase();
        System.out.print(" Enter Movie Language : ");
        String movieLang = sc.nextLine().toUpperCase();
        System.out.print(" Enter Movie Duration in Minutes : ");
        int movieDuration = sc.nextInt();
        sc.nextLine();
        System.out.print(" Enter Movie Certification : ");
        String movieCertification = sc.next().toUpperCase();
        sc.nextLine();
        System.out.print(" Enter Movie Status : ");
        String movieStatus = sc.nextLine().toUpperCase();
        System.out.print(" Enter Movie Release Date(YYYY-MM-DD) : ");
        String movieReleaseDate = sc.next();

        Movie m = new Movie();
        m.setTitle(movieName);
        m.setLanguage(movieLang);
        m.setDurationInMinutes(movieDuration);
        m.setCertification(movieCertification);
        m.setStatus(movieStatus);
        m.setCreatedAt(movieReleaseDate);
        System.out.println("Movie Details added successfully..");

        MovieValidator.validator(m);
        System.out.println("validated successfully..");

        MovieDAO movieDAO = new MovieDAO();
        movieDAO.addMovie(m);
        System.out.println("\nPROCESS COMPLETED!");
    }

    public void createShow() {

        System.out.println("\nENTER SHOW DETAILS..");
        sc.nextLine();
        System.out.print(" Enter Movie Name : ");
        String movieName = sc.nextLine().toUpperCase();
        System.out.print(" Enter Auditorium Name : ");
        String audName = sc.nextLine().toUpperCase();
        System.out.print(" Enter Show Time(HH:MM:SS) : ");
        String showTime = sc.next();
        System.out.print(" Enter End Time(HH:MM:SS) : ");
        String endTime = sc.next();
        sc.nextLine();
        System.out.print("Enter status of the show : ");
        String status = sc.nextLine().toUpperCase();

        Show s =  new Show();
        ShowDAO showDAO = new ShowDAO();

        int movieID = showDAO.getMovieId(movieName);
        int auditoriumID = showDAO.getAuditoriumId(audName);

        s.setMovieId(movieID);
        s.setAuditoriumId(auditoriumID);
        s.setShowTime(showTime);
        s.setEndTime(endTime);
        s.setStatus(status);
        System.out.println("Show Details added successfully..");

        ShowValidator.validator(s);
        System.out.println("validated successfully..");

        showDAO.createShow(s);
        System.out.println("Show Created..");

        //updateShowSeats();
        int showId = showDAO.getShowID(auditoriumID,showTime);
        showDAO.setShowSeats(showId,auditoriumID);

        System.out.println("Show Seats Updated..");

        System.out.println("\nPROCESS COMPLETED!");
    }

}
