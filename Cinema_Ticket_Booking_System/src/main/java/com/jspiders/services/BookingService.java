package com.jspiders.services;

import com.jspiders.dao.BookingDAO;
import com.jspiders.dao.SeatDAO;
import com.jspiders.dao.ShowDAO;
import com.jspiders.model.Booking;
import com.jspiders.model.Seat;
import com.jspiders.model.Show;
import com.jspiders.modelvalidator.BookingValidator;
import com.jspiders.modelvalidator.SeatValidator;
import com.jspiders.modelvalidator.ShowValidator;

import java.util.LinkedList;
import java.util.Scanner;

public class BookingService {

    final private static Scanner sc = new Scanner(System.in);
    ShowDAO showDAO = new ShowDAO();

    public void viewSeats(){

        System.out.println("ENTER SHOW DETAILS..");
        System.out.print(" Enter Auditorium Name : ");
        String audname = sc.nextLine().toUpperCase();

        int auditoriumID = showDAO.getAuditoriumId(audname);

        System.out.print(" Enter Show Time(HH:MM:SS) : ");
        String showTime = sc.next();

        int show_id = showDAO.getShowID(auditoriumID,showTime);

        Show s = new Show();
        s.setShowId(show_id);
        System.out.println("Show Details added Successfully.");

        ShowValidator.validateShowId(s);
        System.out.println("Validated Successfully.");

        SeatDAO seatDAO = new SeatDAO();
        seatDAO.viewSeats(s);
        System.out.println("\nPROCESS COMPLETED!");
    }

    public void bookTickets(){

        System.out.println("ENTER SHOW DETAILS..");
        System.out.print(" Enter Auditorium Name : ");
        String audname = sc.nextLine().toUpperCase();

        int auditoriumID = showDAO.getAuditoriumId(audname);

        System.out.print(" Enter Show Time(HH:MM:SS) : ");
        String showTime = sc.next();

        int show_id = showDAO.getShowID(auditoriumID,showTime);

        Show s = new Show();
        s.setShowId(show_id);
        System.out.println("Show Details added Successfully.");

        System.out.print("\nHow many seats you want to Book Ticket? ");
        int count = sc.nextInt();

        String row;
        int column;

        LinkedList<Seat> seats = new LinkedList<Seat>();

        for(int i=1;i<=count;i++){
            System.out.print("Enter Show Row : ");
            row = sc.next().toUpperCase().charAt(0) + "";
            System.out.print("Enter Show Column : ");
            column = sc.nextInt();
            sc.nextLine();

            Seat seat = new Seat();
            seat.setRowLabel(row);
            seat.setSeatNumber(column);

            ShowValidator.validateShowId(s);
            SeatValidator.validateRowLabel(seat);
            SeatValidator.validateSeatNo(seat);

            seats.add(seat);
            System.out.println(i + " Seat added");
        }
        System.out.println("Validated Successfully");
        System.out.println("Seat Details added Successfully");

        System.out.println("\nEnter your Details... ");
        System.out.print("Enter your name : ");
        String name = sc.nextLine().toUpperCase();
        System.out.print("Enter your phone number : ");
        String phone = sc.next();

        Booking b = new Booking();
        b.setShowId(show_id);
        b.setUserName(name);
        b.setUserPhoneNumber(phone);
        System.out.println("Booking Details added Successfully");

        BookingValidator.validateShowId(b);
        BookingValidator.validateUserName(b);
        BookingValidator.validateUserPhoneNumber(b);
        System.out.println("Validated Successfully..");

        BookingDAO bookingDAO = new BookingDAO();
        bookingDAO.bookTicket(auditoriumID,s, seats,b);
        System.out.println("\nPROCESS COMPLETED!");

    }
}
