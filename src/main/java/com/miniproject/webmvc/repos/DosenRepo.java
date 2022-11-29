package com.miniproject.webmvc.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miniproject.webmvc.entities.DosenEntity;

@Repository
public interface DosenRepo extends JpaRepository<DosenEntity, String> {
    
}
