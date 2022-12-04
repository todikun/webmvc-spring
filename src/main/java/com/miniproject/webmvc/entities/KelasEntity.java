package com.miniproject.webmvc.entities;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.BeanUtils;

import com.miniproject.webmvc.models.KelasModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "kelas_tab")
public class KelasEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "kode")
    private String kode;

    @Column(name = "hari")
    private String namaHari;

    @Temporal(TemporalType.TIME)
    @Column(name = "jam_mulai")
    private Date jamMulai;

    @Temporal(TemporalType.TIME)
    @Column(name = "jam_selesai")
    private Date jamSelesai;

    @Column(name = "ruang_id", insertable = false, updatable = false)
    private String ruangId;

    @Column(name = "matakuliah_id", insertable = false, updatable = false)
    private String mataKuliahId;

    @Column(name = "dosen_id", insertable = false, updatable = false)
    private String dosenId;

    @Column(name = "status")
    private String status;

    @Column(name = "tahun_ajaran")
    private Integer tahunAjaran;

    @Column(name = "semester")
    private String semester;

    @Column(name = "quota")
    private Integer quota;

    @Column(name = "bisa_online")
    private Boolean bisaOnline;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by", length = 20)
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", length = 20)
    private String updatedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ruang_id", nullable = false)
    private RuangEntity ruang;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "matakuliah_id", nullable = false)
    private MataKuliahEntity mataKuliah;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dosen_id", nullable = false)
    private DosenEntity dosen;

    public KelasEntity() {
    }

    public KelasEntity(String id) {
        this.id = id;
    }

    public KelasEntity(KelasModel model) {
        BeanUtils.copyProperties(model, this);
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = "SYSTEM";

        if (model.getRuang() != null) {
            RuangEntity ruangEntity = new RuangEntity();
            ruangEntity.setId(model.getRuang().getId());
            this.ruang = ruangEntity;
        }

        if (model.getMataKuliah() != null) {
            MataKuliahEntity mataKuliahEntity = new MataKuliahEntity();
            mataKuliahEntity.setId(model.getMataKuliah().getId());
            this.mataKuliah = mataKuliahEntity;
        }

        if (model.getDosen() != null) {
            DosenEntity dosenEntity = new DosenEntity();
            dosenEntity.setId(model.getDosen().getId());
            this.dosen = dosenEntity;
        }
    }

    @PrePersist
    public void onCreated() {
        this.id = UUID.randomUUID().toString();
    }
}