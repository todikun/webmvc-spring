package com.miniproject.webmvc.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.miniproject.webmvc.models.KelasDetailModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "kelas_detail_tab")
public class KelasDetailEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "kelas_id", insertable = false, updatable = false)
    private String kelasId;

    @Column(name = "mahasiswa_id", insertable = false, updatable = false)
    private String mahasiswaId;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kelas_id", nullable = false)
    private KelasEntity kelas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mahasiswa_id", nullable = false)
    private MahasiswaEntity mahasiswa;

    public KelasDetailEntity() {
    }
    
    public KelasDetailEntity(KelasDetailModel model) {
        BeanUtils.copyProperties(model, this);
        this.id = UUID.randomUUID().toString();

        if (model.getKelas() != null) {
            this.kelas = new KelasEntity(model.getKelas().getId());
        }

        if (model.getMahasiswa() != null) {
            this.kelas = new KelasEntity(model.getMahasiswa().getId());
        }
    }

}