package com.miniproject.webmvc.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miniproject.webmvc.entities.KelasEntity;

@Repository
public interface KelasRepo extends JpaRepository<KelasEntity, String> {
    
}
