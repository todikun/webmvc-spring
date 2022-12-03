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

        // validasi -> Hari, ruang, mk, dosen, jam
        @Query("select t from KelasEntity t where t.namaHari= :namaHari and t.ruangId= :ruangId and t.mataKuliahId= :mataKuliahId and t.dosenId= :dosenId and t.jamMulai>= :jamMulai or t.jamSelesai<= :jamSelesai")
        List<KelasEntity> firstValidation(
                        @Param("namaHari") String namaHari,
                        @Param("ruangId") String ruangId,
                        @Param("mataKuliahId") String mataKuliahId,
                        @Param("dosenId") String dosenId,
                        @Param("jamMulai") Date jamMulai,
                        @Param("jamSelesai") Date jamSelesai);

        // validasi -> Hari, dosen, jam
        @Query("select t from KelasEntity t where t.namaHari= :namaHari and t.dosenId= :dosenId and t.jamMulai>= :jamMulai or t.jamSelesai<= :jamSelesai")
        List<KelasEntity> secondValidation(
                        @Param("namaHari") String namaHari,
                        @Param("dosenId") String dosenId,
                        @Param("jamMulai") Date jamMulai,
                        @Param("jamSelesai") Date jamSelesai);

        // validasi -> Hari, ruang, jam
        @Query("select t from KelasEntity t where t.namaHari= :namaHari and t.ruangId= :ruangId and t.jamMulai>= :jamMulai or t.jamSelesai<= :jamSelesai")
        List<KelasEntity> thirdValidation(
                        @Param("namaHari") String namaHari,
                        @Param("ruangId") String ruangId,
                        @Param("jamMulai") Date jamMulai,
                        @Param("jamSelesai") Date jamSelesai);

}
