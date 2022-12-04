package com.miniproject.webmvc.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.webmvc.entities.DosenEntity;
import com.miniproject.webmvc.entities.KelasEntity;
import com.miniproject.webmvc.entities.MataKuliahEntity;
import com.miniproject.webmvc.entities.RuangEntity;
import com.miniproject.webmvc.models.KelasModel;
import com.miniproject.webmvc.repos.KelasRepo;
import com.miniproject.webmvc.services.KelasService;

@Service
public class KelasServiceImpl implements KelasService {

    private KelasRepo repo;

    @Autowired
    public KelasServiceImpl(KelasRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<KelasModel> getAll() {
        return this.repo.findAll().stream().map(KelasModel::new).collect(Collectors.toList());
    }

    @Override
    public KelasModel getById(String id) {
        return this.repo.findById(id).map(KelasModel::new).orElse(new KelasModel());
    }

    @Override
    public Optional<KelasModel> save(KelasModel request) {
        if (request == null) {
            return Optional.empty();
        }

        List<KelasEntity> firstCheck = this.repo.firstValidation(
                request.getNamaHari(),
                request.getRuang().getId(),
                request.getDosen().getId(),
                request.getJamMulai(),
                request.getJamSelesai());

        List<KelasEntity> secondCheck = this.repo.secondValidation(
                request.getNamaHari(),
                request.getRuang().getId(),
                request.getJamMulai(),
                request.getJamSelesai());

        if (!firstCheck.isEmpty() || !secondCheck.isEmpty()) {
            return Optional.empty();
        }

        KelasEntity result = new KelasEntity(request);
        try {
            this.repo.save(result);
            return Optional.of(new KelasModel(result));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<KelasModel> update(String id, KelasModel request) {
        Optional<KelasEntity> result = this.repo.findById(id);
        if (result.isEmpty()) {
            return Optional.empty();
        }

        List<KelasEntity> firstCheck = this.repo.firstValidation(
                request.getNamaHari(),
                request.getRuang().getId(),
                request.getDosen().getId(),
                request.getJamMulai(),
                request.getJamSelesai());

        List<KelasEntity> secondCheck = this.repo.secondValidation(
                request.getNamaHari(),
                request.getRuang().getId(),
                request.getJamMulai(),
                request.getJamSelesai());

        if (!firstCheck.isEmpty() || !secondCheck.isEmpty()) {
            if (firstCheck.size() > 0) {
                if (firstCheck.get(0).getId() != id) {
                    return Optional.empty();
                }
            } else if (secondCheck.size() > 0) {
                if (secondCheck.get(0).getId() != id) {
                    return Optional.empty();
                }
            }
        }

        KelasEntity data = result.get();
        BeanUtils.copyProperties(request, data);
        RuangEntity ruang = new RuangEntity(request.getRuang().getId());
        MataKuliahEntity mataKuliah = new MataKuliahEntity(request.getMataKuliah().getId());
        DosenEntity dosen = new DosenEntity(request.getDosen().getId());
        data.setId(id);
        data.setRuang(ruang);
        data.setMataKuliah(mataKuliah);
        data.setDosen(dosen);
        data.setUpdatedAt(LocalDateTime.now());

        try {
            this.repo.save(data);
            return Optional.of(new KelasModel(data));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<KelasModel> delete(String id) {
        Optional<KelasEntity> result = this.repo.findById(id);
        if (result.isEmpty()) {
            return Optional.empty();
        }

        try {
            KelasEntity data = result.get();
            RuangEntity ruang = data.getRuang();
            MataKuliahEntity mataKuliah = data.getMataKuliah();
            DosenEntity dosen = data.getDosen();

            ruang.removeKelas(data);
            mataKuliah.removeKelas(data);
            dosen.removeKelas(data);

            data.setRuang(null);
            data.setMataKuliah(null);
            data.setDosen(null);

            this.repo.delete(data);
            return Optional.of(new KelasModel(data));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
