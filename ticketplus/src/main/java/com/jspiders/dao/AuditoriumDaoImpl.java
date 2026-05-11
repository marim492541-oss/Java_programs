package com.jspiders.dao;

import com.jspiders.config.DbConfig;
import com.jspiders.entity.AuditoriumEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AuditoriumDaoImpl implements AuditoriumDao {

    @Override
    public void addAuditorium(AuditoriumEntity auditoriumEntity) {

        System.out.println("🔓 Session Opened");
        Session session = DbConfig.getSession();

        System.out.println("✅ Transaction Created");
        Transaction transaction = session.beginTransaction();

        session.persist(auditoriumEntity);

        transaction.commit();
        System.out.println("✅ Auditorium Saved");

        session.close();
        System.out.println("🔒 Session Closed");

        DbConfig.shutdown();
    }

    @Override
    public AuditoriumEntity getAuditoriumById(Long audiId) {

        System.out.println("🔓 Session Opened");
        Session session = DbConfig.getSession();

        System.out.println("✅ Transaction Created");
        Transaction transaction = session.beginTransaction();

        AuditoriumEntity auditorium = session.find(AuditoriumEntity.class, audiId);
        System.out.println("✅ Auditorium Found");

        session.close();
        System.out.println("🔒 Session Closed");

        DbConfig.shutdown();

        return auditorium;
    }

    @Override
    public List<AuditoriumEntity> getAllAuditorium() {

        System.out.println("🔓 Session Opened");
        Session session = DbConfig.getSession();

        System.out.println("✅ Transaction Created");
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from AuditoriumEntity");
        List<AuditoriumEntity> list = query.getResultList();
        System.out.println("✅ Auditoriums Found");

        session.close();
        System.out.println("🔒 Session Closed");

        DbConfig.shutdown();

        return list;
    }

    @Override
    public void updateAuditorium(Long audiId) {

    }

    @Override
    public void deleteAuditorium(Long audiId) {

    }
}
