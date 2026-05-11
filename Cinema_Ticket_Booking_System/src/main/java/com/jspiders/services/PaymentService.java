package com.jspiders.services;

import com.jspiders.dao.PaymentDAO;
import com.jspiders.model.Booking;
import com.jspiders.model.Payment;
import com.jspiders.modelvalidator.PaymentValidator;

import java.util.Scanner;

public class PaymentService {

    PaymentDAO pdao = new PaymentDAO();
    final private static Scanner sc = new Scanner(System.in);

    public void confirmPayment(){

        System.out.println("Enter your Booking Details..");
        System.out.print("Enter your name : ");
        String name = sc.nextLine().toUpperCase();
        System.out.print("Enter your phone number : ");
        String phone = sc.next();

        Booking booking = new Booking();
        booking.setUserName(name);
        booking.setUserPhoneNumber(phone);

        int bookingId = pdao.getBookingIdOfTheUser(booking);

        System.out.print("Enter your method for payment(GPay/PhonePe/Cash/Card) : ");
        String method = sc.nextLine();
        System.out.println("Enter your amount to pay : ");
        int amount = sc.nextInt();
        System.out.println("Enter your txn reference : ");
        String txnRef = sc.next();

        Payment payment = new Payment();
        payment.setBookingId(bookingId);
        payment.setMethod(method);
        payment.setAmount(amount);
        payment.setTxnReference(txnRef);
        System.out.println("Payment details added successfully.");

        PaymentValidator.paymentValidator(payment);
        System.out.println("Validated Successfully...");

        pdao.confirmPayment(payment);
        System.out.println("\nPROCESS COMPLETED!!");


    }

    public void cancelPayment(){

    }
}
