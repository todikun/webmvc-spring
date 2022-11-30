package com.miniproject.webmvc.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miniproject.webmvc.entities.FakultasEntity;

@Repository
public interface FakultasRepo extends JpaRepository<FakultasEntity, String> {
    
}
