package com.jspiders.modelvalidator;

import com.jspiders.model.Movie;

public class MovieValidator {

    public static void validator(Movie movie){
        //validateId(movie);
        validateName(movie);
        validateLanguage(movie);
        validateDuration(movie);
        validateCertification(movie);
        validateStatus(movie);
        validateCreatedDate(movie);
    }

    public static void validateId(Movie movie){
        if(movie.getMovieId()<=0){
            throw new RuntimeException("Movie ID must be greater than zero.");
        }
    }

    public static void validateName(Movie movie){
        if(movie.getTitle()==null || movie.getTitle().isBlank()){
            throw new RuntimeException("Title should not be empty.");
        }
    }

    public static void validateLanguage(Movie movie){
        if(movie.getLanguage()==null || movie.getLanguage().isBlank()){
            throw new RuntimeException("Language should not be empty.");
        }
    }

    public static void validateDuration(Movie movie){
        if(movie.getDurationInMinutes()<=0){
            throw new RuntimeException("Duration should be greater than zero.");
        }
    }

    public static void validateCertification(Movie movie){
        if(!(movie.getCertification().equals("U") ||  movie.getCertification().equals("A") ||  movie.getCertification().equals("U/A") || movie.getCertification().equals("S"))){
            throw new RuntimeException("Certification should be U or A or U/A or S.");
        }
    }

    public static void validateStatus(Movie movie){
        if(movie.getStatus()==null){
            throw new RuntimeException("movie status should not be empty.");
        }
    }

    public static void validateCreatedDate(Movie movie){
//        LocalDate today = LocalDate.now();
//        LocalDate releaseDate = LocalDate.parse(movie.getCreatedAt());
//        if(releaseDate.isAfter(today)){
//            throw new RuntimeException("Creation Date is invalid");
//        }
    }
}
