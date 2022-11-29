package com.miniproject.webmvc.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miniproject.webmvc.entities.GedungEntity;

@Repository
public interface GedungRepo extends JpaRepository<GedungEntity, String> {
    
}
