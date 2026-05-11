package com.jspiders.dao;

import com.jspiders.config.DbConfig;
import com.jspiders.entity.AuditoriumEntity;
import com.jspiders.entity.MovieEntity;
import com.jspiders.enums.MovieStatus;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class MovieDaoImpl implements MovieDao {

    @Override
    public void addMovie(MovieEntity movieEntity) {

        Session session = DbConfig.getSession();
        System.out.println("🔓 Session Opened");

        Transaction transaction = session.beginTransaction();
        System.out.println("✅ Transaction Created");

        session.persist(movieEntity);
        transaction.commit();
        System.out.println("✅ Movie Saved");

        session.close();
        System.out.println("🔒 Session Closed");

    }

    @Override
    public MovieEntity getMovieById(Long movieId) {

        Session session = DbConfig.getSession();
        System.out.println("🔓 Session Opened");

        Transaction transaction = session.beginTransaction();
        System.out.println("✅ Transaction Created");

        MovieEntity movieEntity = session.find(MovieEntity.class, movieId);
        System.out.println("✅ Movie Found");

        session.close();
        System.out.println("🔒 Session Closed");

        return movieEntity;
    }

    @Override
    public List<MovieEntity> getMovieByStatus(MovieStatus status) {

        Session session = DbConfig.getSession();
        System.out.println("🔓 Session Opened");

        Transaction transaction = session.beginTransaction();
        System.out.println("✅ Transaction Created");

        String HQLQuery = "from MovieEntity where status=:status";
        Query<MovieEntity> SQLquery = session.createQuery(HQLQuery, MovieEntity.class);
        SQLquery.setParameter("status", status);
        List<MovieEntity> movies = SQLquery.getResultList();
        System.out.println("✅ Movie Found");

        session.close();
        System.out.println("🔒 Session Closed");

        return movies;
    }

    @Override
    public MovieEntity getMovieByTitle(String title){

        Session session = DbConfig.getSession();
        System.out.println("🔓 Session Opened");

        Transaction transaction = session.beginTransaction();
        System.out.println("✅ Transaction Created");

        String HQLQuery = "from MovieEntity where title=:title";
        Query<MovieEntity> SQLquery = session.createQuery(HQLQuery, MovieEntity.class);
        SQLquery.setParameter("title", title);
        MovieEntity movie = SQLquery.uniqueResult();
        System.out.println("✅ Movie Found");

        session.close();
        System.out.println("🔒 Session Closed");

        return movie;
    }

    @Override
    public void updateMovie(Long movieId) {

    }

    @Override
    public void deleteMovie(Long movieId) {

    }

    @Override
    public List<MovieEntity> getAllMovies() {

        System.out.println("🔓 Session Opened");
        Session session = DbConfig.getSession();

        System.out.println("✅ Transaction Created");
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from MovieEntity");
        List<MovieEntity> list = query.getResultList();
        System.out.println("✅ Movies Found");

        session.close();
        System.out.println("🔒 Session Closed");

        return list;
    }
}
