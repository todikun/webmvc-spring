package com.miniproject.webmvc.repos;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.miniproject.webmvc.entities.KelasEntity;

@Repository
public interface KelasRepo extends JpaRepository<KelasEntity, String> {

    @Query("select t from KelasEntity t where t.jamMulai>= :jamMulai and t.jamSelesai<= :jamSelesai and t.mataKuliahId= :id")
    List<KelasEntity> validasiJamMataKuliah(
        @Param("jamMulai") Date jamMulai,
        @Param("jamSelesai") Date jamSelesai,
        @Param("id") String id
    );

    @Query("select t from KelasEntity t where t.ruangId= :id and t.namaHari= :namaHari and t.jamMulai>= :jamMulai and t.jamSelesai<= :jamSelesai")
    List<KelasEntity> validasiPertama(
            @Param("id") String id,
            @Param("namaHari") String namaHari,
            @Param("jamMulai") Date jamMulai,
            @Param("jamSelesai") Date jamSelesai);

    @Query("select t from KelasEntity t where t.namaHari= :namaHari and t.jamMulai>= :jamMulai and t.jamSelesai<= :jamSelesai")
    List<KelasEntity> validasiHariDanJam(
            @Param("namaHari") String namaHari,
            @Param("jamMulai") Date jamMulai,
            @Param("jamSelesai") Date jamSelesai);

}
