package com.jspiders.config;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DbConfig
{
    private static SessionFactory sessionFactory;

    static {
        //load config
        System.out.println("📦 Loading configuration");
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        System.out.println("🏠 Build SessionFactory");
        sessionFactory = configuration.buildSessionFactory();
    }

    public static Session getSession(){
        return sessionFactory.openSession();
    }

    public static void shutdown() {
        sessionFactory.close();
        System.out.println("🔐 Session Factory Closed");
    }
}
