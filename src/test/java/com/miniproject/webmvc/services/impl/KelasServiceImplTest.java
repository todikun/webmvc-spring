package com.miniproject.webmvc.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.miniproject.webmvc.entities.KelasEntity;
import com.miniproject.webmvc.models.KelasModel;
import com.miniproject.webmvc.repos.KelasRepo;
import com.miniproject.webmvc.utils.DateUtil;

@ExtendWith(MockitoExtension.class)
public class KelasServiceImplTest {

    @Autowired
    @InjectMocks
    private KelasServiceImpl service;

    @Mock
    private KelasRepo repo;

    private static List<KelasEntity> kelasList;
    private static String ruangId;
    private static String dosenId;
    private static String mkId;

    @BeforeEach
    void setup() {
        ruangId = UUID.randomUUID().toString();
        dosenId = UUID.randomUUID().toString();
        mkId = UUID.randomUUID().toString();

        kelasList = Arrays.asList(
                new KelasEntity(
                    "K001", "SENIN", "08:00:00", "09:45:00", ruangId, mkId, dosenId, false),
                new KelasEntity(
                    "K002", "SENIN", "10:00:00", "11:45:00", ruangId, mkId, dosenId, true),
                new KelasEntity(
                    "K003", "SENIN", "13:00:00", "14:45:00", ruangId, mkId, dosenId, false),
                new KelasEntity("K004", "SENIN", "15:00:00", "16:45:00", ruangId, mkId, dosenId, true));
    }

    @Test
    void testGetAll() {
        when(repo.findAll()).thenReturn(kelasList);

        List<KelasModel> result = service.getAll();
        assertNotNull(result);
        assertEquals(4, result.size());
        assertEquals("K002", kelasList.get(1).getKode());
        assertEquals("SENIN", kelasList.get(1).getNamaHari());
        assertEquals(true, kelasList.get(1).getBisaOnline());
        
        Date jamMulai = DateUtil.getTime("10:00:00");
        assertEquals(jamMulai, kelasList.get(1).getJamMulai());
        
        Date jamSelesai = DateUtil.getTime("11:45:00");
        assertEquals(jamSelesai, kelasList.get(1).getJamSelesai());
    }

    @Test
    void testGetById() {
        KelasModel result = service.getById("");
        assertNotNull(result);
        assertNull(result.getKode());

        Optional<KelasEntity> entity = Optional.of(kelasList.get(1));
        when(repo.findById(any(String.class))).thenReturn(entity);

        result = service.getById("12345");
        assertNotNull(result);
        assertEquals("K002", result.getKode());

    }

    @Test
    void testSave() {

    }

    @Test
    void testUpdate() {

    }

    @Test
    void testDelete() {

    }

}
