package com.miniproject.webmvc.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miniproject.webmvc.entities.JurusanEntity;

@Repository
public interface JurusanRepo extends JpaRepository<JurusanEntity, String> {
    
}
