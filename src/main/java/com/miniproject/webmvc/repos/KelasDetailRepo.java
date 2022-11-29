package com.miniproject.webmvc.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miniproject.webmvc.entities.KelasDetailEntity;

@Repository
public interface KelasDetailRepo extends JpaRepository<KelasDetailEntity, String> {

}
