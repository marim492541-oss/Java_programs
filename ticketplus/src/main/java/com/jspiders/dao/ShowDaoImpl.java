package com.jspiders.dao;

import com.jspiders.config.DbConfig;
import com.jspiders.entity.ShowEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ShowDaoImpl implements ShowDao {

    @Override
    public void addShow(ShowEntity showEntity) {

        System.out.println("🔓 Session Opened");
        Session session = DbConfig.getSession();

        System.out.println("✅ Transaction Created");
        Transaction transaction = session.beginTransaction();

        session.persist(showEntity);
        transaction.commit();
        System.out.println("✅ Show Saved");

        session.close();
        System.out.println("🔒 Session Closed");

        DbConfig.shutdown();
    }

    @Override
    public ShowEntity getShow(Long showId) {

        System.out.println("🔓 Session Opened");
        Session session = DbConfig.getSession();

        System.out.println("✅ Transaction Created");
        Transaction transaction = session.beginTransaction();

        ShowEntity showEntity = session.find(ShowEntity.class,showId);
        transaction.commit();
        System.out.println("✅ Show Found");

        session.close();
        System.out.println("🔒 Session Closed");

        DbConfig.shutdown();

        return showEntity;
    }

    @Override
    public void updateShow(Long ShowId) {

    }

    @Override
    public void deleteShow(Long ShowId) {

    }
}
