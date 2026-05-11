package com.jspiders.service;

import com.jspiders.dto.MovieDTO;
import com.jspiders.entity.MovieEntity;

import java.util.List;

public interface MovieService {

    void addMovie(MovieDTO movieDTO);

    MovieEntity getMovie(Long movieId);

    List<MovieEntity> getAllMovies();

}
