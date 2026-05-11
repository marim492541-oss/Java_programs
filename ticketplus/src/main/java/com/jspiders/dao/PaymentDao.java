package com.jspiders.dao;

public interface PaymentDao {

    void addPayment();

    void getPayment(Long paymentId);

    void updatePayment(Long paymentId);

    void deletePayment(Long paymentId);

}
