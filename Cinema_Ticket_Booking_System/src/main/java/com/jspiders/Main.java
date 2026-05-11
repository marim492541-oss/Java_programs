package com.jspiders;

import com.jspiders.services.BookingService;
import com.jspiders.services.MovieService;
import com.jspiders.services.PaymentService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        final Scanner sc = new Scanner(System.in);
        final MovieService movieService = new MovieService();
        final BookingService bookingService = new BookingService();
        final PaymentService paymentService = new PaymentService();

        while (true) {
            System.out.println("\nCHOOSE THE USER TYPE : ");
            System.out.println(" 1. Admin");
            System.out.println(" 2. Customer");
            System.out.println(" 3. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    while (true) {
                        System.out.println("\nADMIN MENU");
                        System.out.println(" 1. Add Movie");
                        System.out.println(" 2. Create Show");
                        System.out.println(" 3. Back");

                        int choice2 = sc.nextInt();
                        sc.nextLine();

                        switch (choice2) {
                            case 1:
                                movieService.addMovie();
                                break;
                            case 2:
                                movieService.createShow();
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Invalid choice");
                        }
                        if (choice2 == 3) break;
                    }
                    break;

                case 2:
                    while (true) {
                        System.out.println("\nCUSTOMER MENU");
                        System.out.println(" 1. View Seats");
                        System.out.println(" 2. Book Tickets");
                        System.out.println(" 3. Confirm Payment");
                        System.out.println(" 4. Cancel Booking");
                        System.out.println(" 5. Back");

                        int choice3 = sc.nextInt();
                        sc.nextLine();

                        switch (choice3) {
                            case 1:
                                bookingService.viewSeats();
                                break;
                            case 2:
                                bookingService.bookTickets();
                                break;
                            case 3:
                                paymentService.confirmPayment();
                                break;
                            case 4:
                                paymentService.cancelPayment();
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("Invalid choice");
                        }

                        if (choice3 == 5) break;
                    }
                    break;

                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
