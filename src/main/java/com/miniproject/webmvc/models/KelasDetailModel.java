package com.miniproject.webmvc.models;

import com.miniproject.webmvc.entities.KelasDetailEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KelasDetailModel {
    private String id;
    private String kelasId;
    private KelasModel kelas;
    private String mahasiswaId;
    private MahasiswaModel mahasiswa;

    public KelasDetailModel() {
    }

    public KelasDetailModel(KelasDetailEntity entity) {
        if (entity.getKelas() != null) {
            kelasId = entity.getKelasId();
            kelas = new KelasModel(entity.getKelas());
        }

        if (entity.getMahasiswa() != null) {
            mahasiswaId = entity.getMahasiswaId();
            mahasiswa = new MahasiswaModel(entity.getMahasiswa());
        }
    }
}
