package com.miniproject.webmvc.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.miniproject.webmvc.models.UserModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_tab")
public class UserEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "username", length = 64)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", length = 64)
    private String email;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @Column(name = "is_locked")
    private Boolean isLocked;

    @ManyToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role_tab", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<RoleEntity> roles = new HashSet<>();

    public UserEntity(UserModel data, PasswordEncoder passwordEncoder) {
        this.username = data.getUsername();
        this.password = passwordEncoder.encode(data.getPassword());
        this.email = data.getEmail();
        this.isEnabled = true;
        this.isLocked = false;
    }

    public UserEntity(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isEnabled = true;
        this.isLocked = false;
    }

    public UserEntity(String username, String password, String email, List<RoleEntity> roleEntities) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isEnabled = true;
        this.isLocked = false;
        this.roles = new HashSet<>(roleEntities);
    }

    public void addRole(RoleEntity item) {
        this.roles.add(item);
        item.getUsers().add(this);
    }

    public void removeRole(RoleEntity item) {
        this.roles.remove(item);
        item.getUsers().remove(this);
    }

    @PrePersist
    protected void onCreated() {
        id = UUID.randomUUID().toString().replace("-", "");
    }
}
