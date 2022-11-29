package com.miniproject.webmvc.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miniproject.webmvc.entities.RuangEntity;

@Repository
public interface RuangRepo extends JpaRepository<RuangEntity, String> {
    
}
