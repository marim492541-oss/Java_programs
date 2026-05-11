package com.jspiders.modelvalidator;

import com.jspiders.model.Payment;

import java.time.LocalDate;

public class PaymentValidator {

    public static void paymentValidator(Payment payment){

        //validateId(payment);
        validateBookingId(payment);
        validateAmount(payment);
        validatePaymentMethod(payment);
        //validateStatus(payment);
        validateTxnRef(payment);
        //validatePaidAt(payment);

    }

    public static void validateId(Payment payment){
        if(payment.getPaymentId()<0){
            throw new RuntimeException("Payment ID must be greater than zero.");
        }
    }

    public static void validateBookingId(Payment payment){
        if(payment.getBookingId()<0){
            throw new RuntimeException("Payment ID must be greater than zero.");
        }
    }

    public static void validateAmount(Payment payment){
        if(payment.getAmount()<0){
            throw new RuntimeException("Payment Amount must be greater than zero.");
        }
    }

    public static void validatePaymentMethod(Payment payment){
        if(payment.getMethod()==null || payment.getMethod().isBlank()){
            throw new RuntimeException("Payment Method is required.");
        }
        if(!payment.getMethod().equals("CASH") && !payment.getMethod().equals("CARD") && !payment.getMethod().equals("GPAY") && !payment.getMethod().equals("PHONEPE") && !payment.getMethod().equals("PAYTM") ){
            throw new RuntimeException("Payment Method can be cash/card/gpay/phonepe/paytm only.");
        }
    }

    public static void validateStatus(Payment payment){
        if(payment.getStatus()==null || payment.getStatus().isBlank()){
            throw new RuntimeException("Status can't be null or empty.");
        }
    }

    public static void validateTxnRef(Payment payment){
        if(payment.getTxnReference()==null || payment.getTxnReference().isBlank()){
            throw new RuntimeException("Txn reference is required.");
        }
    }

    public static void validatePaidAt(Payment payment){
        LocalDate paidAt = LocalDate.parse(payment.getPaidAt());
        LocalDate now = LocalDate.now();
        if(paidAt.isAfter(now)){
            throw new RuntimeException("Paid At must not be after today.");
        }
    }
}
