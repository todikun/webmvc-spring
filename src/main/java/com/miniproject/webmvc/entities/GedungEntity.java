package com.miniproject.webmvc.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.miniproject.webmvc.models.GedungModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "gedung_tab")
public class GedungEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "kode_gedung", length = 20, unique = true)
    private String code;

    @Column(name = "nama_gedung", length = 225)
    private String name;

    @Column(name = "jml_lantai")
    private Integer jmlLantai;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by", length = 20)
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", length = 20)
    private String updatedBy;

    @OneToMany(mappedBy = "gedung", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RuangEntity> ruangs = new HashSet<>();

    public GedungEntity() {

    }

    public GedungEntity(String id) {
        this.id = id;
    }

    public GedungEntity(String code, String name, Integer jmlLantai) {
        this.code = code;
        this.name = name;
        this.jmlLantai = jmlLantai;
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
        this.id = UUID.randomUUID().toString();
    }

    public GedungEntity(GedungModel model) {
        BeanUtils.copyProperties(model, this);
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = "SYSTEM";
    }

    public void addRuang(RuangEntity ruang) {
        this.ruangs.add(ruang);
        ruang.setGedung(this);
    }

    public void removeRuang(RuangEntity ruang) {
        this.ruangs.remove(ruang);
        ruang.setGedung(null);
    }

    @PrePersist
    public void onCreated() {
        this.id = UUID.randomUUID().toString();
    }
}
