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

import com.miniproject.webmvc.models.MataKuliahModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mata_kuliah_tab")
public class MataKuliahEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "kode_matkul", length = 20, unique = true)
    private String code;

    @Column(name = "nama_matkul", length = 225)
    private String name;

    @Column(name = "sks")
    private Integer sks;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by", length = 20)
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", length = 20)
    private String updatedBy;

    @OneToMany(mappedBy = "mataKuliah", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<KelasEntity> kelas = new HashSet<>();

    public MataKuliahEntity() {

    }

    public MataKuliahEntity(String id) {
        this.id = id;
    }

    public MataKuliahEntity(String code, String name, Integer sks) {
        this.code = code;
        this.name = name;
        this.sks = sks;
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
        this.id = UUID.randomUUID().toString();
    }

    public MataKuliahEntity(MataKuliahModel model) {
        BeanUtils.copyProperties(model, this);
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = "SYSTEM";
    }

    public void addKelas(KelasEntity kelas) {
        this.kelas.add(kelas);
        kelas.setMataKuliah(this);
    }

    public void removeKelas(KelasEntity kelas) {
        this.kelas.remove(kelas);
        kelas.setMataKuliah(this);
    }

    @PrePersist
    public void onCreated() {
        this.id = UUID.randomUUID().toString();
    }
}
