package com.jspiders.dao;

import com.jspiders.entity.ShowEntity;

public interface ShowDao {

    void addShow(ShowEntity showEntity);

    ShowEntity getShow(Long ShowId);

    void updateShow(Long ShowId);

    void deleteShow(Long ShowId);

}
