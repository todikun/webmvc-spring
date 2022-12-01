package com.miniproject.webmvc.models;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.miniproject.webmvc.entities.KelasEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KelasModel {
    private String id;
    private String kode;
    private String namaHari;
    @DateTimeFormat(pattern = "HH:mm")
    private Date jamMulai;
    @DateTimeFormat(pattern = "HH:mm")
    private Date jamSelesai;
    private String ruangId;
    private RuangModel ruang;
    private String mataKuliahId;
    private MataKuliahModel mataKuliah;
    private String dosenId;
    private DosenModel dosen;
    private String status;
    private Integer tahunAjaran;
    private String semester;
    private Integer quota;
    private boolean bisaOnline;

    public KelasModel() {
    }

    public KelasModel(KelasEntity entity) {
        BeanUtils.copyProperties(entity, this);
        if (entity.getRuang() != null) {
        ruangId = entity.getRuangId();
        ruang = new RuangModel(entity.getRuang());
        }

        if (entity.getMataKuliah() != null) {
        mataKuliahId = entity.getMataKuliahId();
        mataKuliah = new MataKuliahModel(entity.getMataKuliah());
        }

        if (entity.getDosen() != null) {
        dosenId = entity.getDosenId();
        dosen = new DosenModel(entity.getDosen());
        }

    }

}
