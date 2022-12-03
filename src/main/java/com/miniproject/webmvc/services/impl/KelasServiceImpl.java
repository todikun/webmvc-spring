package com.miniproject.webmvc.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.webmvc.entities.KelasEntity;
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<KelasModel> save(KelasModel request) {
        if (request == null) {
            return Optional.empty();
        }

        List<KelasEntity> firstCheck = this.repo.firstValidation(
        request.getNamaHari(),
        request.getRuang().getId(),
        request.getMataKuliah().getId(),
        request.getDosen().getId(),
        request.getJamMulai(),
        request.getJamSelesai());   

        if (!firstCheck.isEmpty()) {
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
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public Optional<KelasModel> delete(String id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }
}
