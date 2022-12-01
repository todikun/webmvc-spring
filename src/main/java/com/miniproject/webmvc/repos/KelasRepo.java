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

        @Query("select t from KelasEntity t where t.jamMulai>= :jamMulai or t.jamSelesai<= :jamSelesai and t.ruangId= :ruangId")
        List<KelasEntity> validasiJamRuang(
                        @Param("jamMulai") Date jamMulai,
                        @Param("jamSelesai") Date jamSelesai,
                        @Param("ruangId") String ruangId);

        @Query("select t from KelasEntity t where t.ruangId= :ruangId and t.namaHari= :namaHari and t.jamMulai>= :jamMulai or t.jamSelesai<= :jamSelesai")
        List<KelasEntity> validasiRuangHariJam(
                        @Param("ruangId") String ruangId,
                        @Param("namaHari") String namaHari,
                        @Param("jamMulai") Date jamMulai,
                        @Param("jamSelesai") Date jamSelesai);

        @Query("select t from KelasEntity t where t.jamMulai>= :jamMulai or t.jamSelesai<= :jamSelesai")
        List<KelasEntity> validasiJam(
                        @Param("jamMulai") Date jamMulai,
                        @Param("jamSelesai") Date jamSelesai);

}
