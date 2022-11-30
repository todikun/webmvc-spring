package com.miniproject.webmvc.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miniproject.webmvc.entities.MahasiswaEntity;

@Repository
public interface MahasiswaRepo extends JpaRepository<MahasiswaEntity, String> {
    
}
