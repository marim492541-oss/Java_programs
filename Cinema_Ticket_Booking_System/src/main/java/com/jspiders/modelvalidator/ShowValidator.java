package com.jspiders.modelvalidator;

import com.jspiders.dao.ShowDAO;
import com.jspiders.exceptions.InvalidTimeException;
import com.jspiders.model.Show;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ShowValidator {

    public static void validator(Show show){
        //validateShowId(show);
        validateMovieId(show);
        validateAuditoriumId(show);
        validateShowTime(show);
        validateShowTime2(show);
        validateEndTime(show);
        validateStatus(show);
    }

    public static void validateShowId(Show show){
        if(show.getShowId()<=0){
            throw new RuntimeException("Show Id must be greater than 0");
        }
    }

    public static void validateMovieId(Show show){
        if(show.getMovieId()<=0){
            throw new RuntimeException("Movie Id must be greater than 0");
        }
    }

    public static void validateAuditoriumId(Show show){
        if(show.getAuditoriumId()<=0){
            throw new RuntimeException("Auditorium Id must be greater than 0");
        }
    }

    public static void validateShowTime(Show show){
        try{
        LocalTime time = LocalTime.parse(show.getShowTime());
        }
        catch(DateTimeParseException e){
            throw new InvalidTimeException("Invalid Time Format: Hour must be <24,  Minutes and seconds must be <60");
        }
    }

    public static void validateEndTime(Show show){
        try{
            LocalTime time = LocalTime.parse(show.getEndTime());
            LocalTime showTime = LocalTime.parse(show.getShowTime());
            if(time.isBefore(showTime)){
                throw new InvalidTimeException("End Time must be greater than Show Time");
            }
        }
        catch(DateTimeParseException e){
            throw new InvalidTimeException("Invalid Time Format: Hour must be <24, Minutes and seconds must be <60");
        }
    }

    public static void validateStatus(Show show){
        if(show.getStatus()=="" || show.getStatus().isBlank()){
            throw new RuntimeException("Show Status must not be Blank");
        }
    }

    public static void validateShowTime2(Show show){

        int auditoriumId = show.getAuditoriumId();
        LocalTime start = LocalTime.parse(show.getShowTime());
        List<Show> existingShows = ShowDAO.getShowsByAuditorium(auditoriumId);

        for (Show s : existingShows) {
            LocalTime existingStart = LocalTime.parse(s.getShowTime());
            LocalTime minAllowedStart = existingStart.plusHours(3);

            if (start.isBefore(minAllowedStart)) {
                throw new InvalidTimeException("For Auditorium " + auditoriumId + ", show must start at least 3 hours after previous show starting at " + existingStart);
            }
        }
    }

}
