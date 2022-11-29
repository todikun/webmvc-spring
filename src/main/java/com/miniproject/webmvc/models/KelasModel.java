package com.miniproject.webmvc.models;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.miniproject.webmvc.entities.KelasEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KelasModel {
    private String id;
    private String kode;
    private String namaHari;
    private Date jamMulai;
    private Date jamSelesai;
    private String ruangId;
    private String mataKuliahId;
    private String dosenId;
    private String status;
    private Integer tahunAjaran;
    private String semester;
    private Integer quota;
    private boolean bisaOnline;

    public KelasModel() {
    }
    
    public KelasModel(KelasEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }

}
