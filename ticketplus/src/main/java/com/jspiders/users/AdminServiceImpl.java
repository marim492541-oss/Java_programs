package com.jspiders.users;

import com.jspiders.dao.AuditoriumDaoImpl;
import com.jspiders.dao.MovieDaoImpl;
import com.jspiders.dto.MovieDTO;
import com.jspiders.dto.ShowDTO;
import com.jspiders.entity.AuditoriumEntity;
import com.jspiders.entity.MovieEntity;
import com.jspiders.enums.CertificateType;
import com.jspiders.enums.MovieStatus;
import com.jspiders.service.MovieServiceImpl;

import java.util.List;
import java.util.Scanner;

public class AdminServiceImpl implements AdminService {

    @Override
    public void createMovie() {

        try {

            Scanner sc = new Scanner(System.in);

            MovieDTO movie = new MovieDTO();

            System.out.println("ENTER MOVIE DETAILS...");

            System.out.print("  Enter movie name : ");
            String title = sc.nextLine().toUpperCase();
            movie.setTitle(title);

            System.out.print("  Enter movie language : ");
            String language = sc.next().toUpperCase();
            movie.setLanguage(language);

            System.out.print("  Enter movie certificate(U/A/U_A) : ");
            String certificate = sc.next().toUpperCase();
            movie.setCertification(CertificateType.valueOf(certificate));

            System.out.print("  Enter movie duration(mins) : ");
            Integer duration = sc.nextInt();
            movie.setDuration(duration);

            movie.setStatus(MovieStatus.AVAILABLE);

            movie.setCreatedBy(301L);

            System.out.println("-----------------Details Verification--------------");
            System.out.println(movie);

            System.out.println("✅ Movie created successfully");

            MovieServiceImpl movieService = new MovieServiceImpl();
            movieService.addMovie(movie);

            System.out.println("❤️ COMPLETED!!!");
        }
        catch (Exception e) {
            System.out.println("❌ ERROR!! Movie creation Failed");
            e.printStackTrace();
        }
    }

    @Override
    public void createShow() {
        try {

            Scanner sc = new Scanner(System.in);

            System.out.println("Here is the Auditorium Details..");

            AuditoriumDaoImpl auditoriumDao = new AuditoriumDaoImpl();
            List<AuditoriumEntity> allAuditorium = auditoriumDao.getAllAuditorium();
            for (AuditoriumEntity auditoriumEntity : allAuditorium) {
                System.out.println(auditoriumEntity);
            }

            System.out.println("Here is the Movie Details..");

            MovieDaoImpl movieDao = new MovieDaoImpl();
            List<MovieEntity> movieList = movieDao.getAllMovies();
            for (MovieEntity movieEntity : movieList) {
                System.out.println(movieEntity);
            }

            ShowDTO show = new ShowDTO();

            System.out.println("ENTER MOVIE DETAILS...");

//            System.out.print("Enter movie name : ");
//            String title = sc.next();
//            movie.setTitle(title);
//
//            System.out.print("Enter movie language : ");
//            String language = sc.next();
//            movie.setLanguage(language);
//
//            System.out.print("Enter movie certificate : ");
//            String certificate = sc.next();
//            movie.setCertification(CertificateType.valueOf(certificate));
//
//            System.out.print("Enter movie duration(mins) : ");
//            Integer duration = sc.nextInt();
//            movie.setDuration(duration);
//
//            movie.setStatus(MovieStatus.AVAILABLE);
//
//            movie.setCreatedBy(301L);
//
//            System.out.println("-----------------Details Verification--------------");
//            System.out.println(movie);
//
//            System.out.println("✅ Movie created successfully");
//
//            MovieServiceImpl movieService = new MovieServiceImpl();
//            movieService.addMovie(movie);

            System.out.println("❤️COMPLETED!!!");
        }
        catch (Exception e) {
            System.out.println("❌ ERROR!! Movie creation Failed");
            e.printStackTrace();
        }
    }

}
