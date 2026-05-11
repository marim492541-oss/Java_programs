package com.jspiders.dao;

import com.jspiders.entity.AuditoriumEntity;

import java.util.List;

public interface AuditoriumDao {

    void addAuditorium(AuditoriumEntity auditoriumEntity);

    AuditoriumEntity getAuditoriumById(Long audiId);

    List<AuditoriumEntity> getAllAuditorium();

    void updateAuditorium(Long audiId);

    void deleteAuditorium(Long audiId);

}