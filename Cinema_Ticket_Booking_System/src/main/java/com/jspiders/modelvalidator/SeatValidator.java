package com.jspiders.modelvalidator;

import com.jspiders.model.Seat;

public class SeatValidator {

    public static void validator(Seat seat){
        validateSeatId(seat);
        validateAuditirumId(seat);
        validateRowLabel(seat);
        validateSeatNo(seat);
    }

    public static void validateSeatId(Seat seat){
        if(seat.getSeatId()<=0){
            throw new RuntimeException("Seat Id must be greater than 0");
        }
    }

    public static void validateAuditirumId(Seat seat){
        if (seat.getAuditoriumId()<=0){
            throw new RuntimeException("Auditorium Id must be greater than 0");
        }
    }

    public static void validateRowLabel(Seat seat){
        char ch = seat.getRowLabel().charAt(0);
        if(ch<'A' || ch>'Z'){
            throw new RuntimeException("Row Label must be between A and Z");
        }
    }

    public static void validateSeatNo(Seat seat){
        if(seat.getSeatNumber()<=0){
            throw new RuntimeException("Seat Id must be greater than 0");
        }
    }
}
