package com.jspiders.service;

import com.jspiders.dao.MovieDaoImpl;
import com.jspiders.dto.MovieDTO;
import com.jspiders.entity.MovieEntity;

import java.time.LocalDate;
import java.util.List;

public class MovieServiceImpl implements MovieService{

    final MovieDaoImpl movieDao = new MovieDaoImpl();

    @Override
    public void addMovie(MovieDTO movieDTO) {

        //convert movieDTO to movieEntity
        MovieEntity movieEntity=new MovieEntity();

        //mapping movieDTO data to movieEntity Data
        movieEntity.setTitle(movieDTO.getTitle());
        movieEntity.setLanguage(movieDTO.getLanguage());
        movieEntity.setDuration(movieDTO.getDuration());
        movieEntity.setStatus(movieDTO.getStatus());
        movieEntity.setCertification(movieDTO.getCertification());
        movieEntity.setCreatedBy(movieDTO.getCreatedBy());
        movieEntity.setCreatedAt(LocalDate.now());

        movieDao.addMovie(movieEntity);
        System.out.println("✅ Movie added successfully in DB");

    }

    @Override
    public MovieEntity getMovie(Long movieId) {
        return null;
    }

    @Override
    public List<MovieEntity> getAllMovies() {
        return List.of();
    }

}
