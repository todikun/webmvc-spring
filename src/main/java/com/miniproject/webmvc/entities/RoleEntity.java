package com.miniproject.webmvc.entities;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role_tab")
public class RoleEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "role_name", unique = true, nullable = false, length = 100)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles", cascade = CascadeType.ALL)
    private Set<UserEntity> users = new HashSet<>();

    public RoleEntity(String name) {
        this.name = name;
    }

    @PrePersist
    protected void onCreated() {
        id = UUID.randomUUID().toString().replace("-", "");
    }
}
