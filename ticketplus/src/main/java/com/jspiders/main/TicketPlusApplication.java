package com.jspiders.main;

import com.jspiders.config.DbConfig;
import com.jspiders.users.AdminServiceImpl;

import java.util.Scanner;

public class TicketPlusApplication {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("---------------Welcome to Ticket Plus🩵---------------");

        while(true) {

            System.out.println("\nSelect User Type: \n 1.Admin \n 2.Customer \n Any Number for Exit");
            int choice  = sc.nextInt();

            switch (choice) {

                case 1:
                    AdminServiceImpl adminService = new AdminServiceImpl();
                    while (true) {
                        System.out.println("\nAdmin Controls: \n 1.Add Movie \n 2.Create Show \n 3.Back");
                        int adminChoice = sc.nextInt();
                        switch (adminChoice) {
                            case 1:
                                System.out.println("---ADD MOVIE---");
                                adminService.createMovie();
                                break;
                            case 2:
                                System.out.println("---CREATE SHOW---");
                                break;
                            default:
                                break;
                        }
                        if(adminChoice != 1 && adminChoice != 2) {
                            break;
                        }
                    }
                    break;

                case 2:
                    while (true) {
                        System.out.println("User Controls: ");
                        break;
                    }
                    break;

                default:
                    System.out.println("EXITING...");
                    DbConfig.shutdown();
                    System.out.println("---------------THANKS, VISIT AGAIN❤️---------------");
                    System.exit(0);
            }
        }
    }
}
