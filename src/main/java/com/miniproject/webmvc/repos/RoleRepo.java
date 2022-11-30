package com.miniproject.webmvc.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.miniproject.webmvc.entities.RoleEntity;

public interface RoleRepo extends JpaRepository<RoleEntity, String> {
    RoleEntity findByName(String name);

    @Query("select t from RoleEntity t where t.name in :names")
    List<RoleEntity> findByNamesFetchMenus(@Param("names") List<String> names);

    @Query("select t from RoleEntity t where t.id=:id")
    RoleEntity findByIdFetchMenuAndPrivilege(@Param("id") String id);
}
