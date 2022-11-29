package com.miniproject.webmvc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

    @Column(name = "kelas_id")
    private String kelasId;

    @Column(name = "mahasiswa_id")
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

    
}