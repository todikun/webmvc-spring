package com.miniproject.webmvc.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.webmvc.entities.MataKuliahEntity;
import com.miniproject.webmvc.models.MataKuliahModel;
import com.miniproject.webmvc.repos.MataKuliahRepo;
import com.miniproject.webmvc.services.MataKuliahService;

@Service
public class MataKuliahServiceImpl implements MataKuliahService {

    private MataKuliahRepo repo;

    @Autowired
    public MataKuliahServiceImpl(MataKuliahRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<MataKuliahModel> getAll() {
        return this.repo.findAll().stream().map(MataKuliahModel::new).collect(Collectors.toList());
    }

    @Override
    public MataKuliahModel getById(String id) {
        return this.repo.findById(id).map(MataKuliahModel::new).orElse(new MataKuliahModel());
    }

    @Override
    public Optional<MataKuliahModel> save(MataKuliahModel request) {
        if (request == null) {
            return Optional.empty();
        }

        MataKuliahEntity result = new MataKuliahEntity(request);
        try {
            this.repo.save(result);
            return Optional.of(new MataKuliahModel(result));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<MataKuliahModel> update(String id, MataKuliahModel request) {
        Optional<MataKuliahEntity> result = this.repo.findById(id);
        if (result.isEmpty()) {
            return Optional.empty();
        }

        MataKuliahEntity data = result.get();
        BeanUtils.copyProperties(request, data);
        data.setId(id);
        data.setUpdatedAt(LocalDateTime.now());

        try {
            this.repo.save(data);
            return Optional.of(new MataKuliahModel(data));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<MataKuliahModel> delete(String id) {
        Optional<MataKuliahEntity> result = this.repo.findById(id);
        if (result.isEmpty()) {
            return Optional.empty();
        }

        try {
            MataKuliahEntity data = result.get();
            this.repo.delete(data);
            return Optional.of(new MataKuliahModel(data));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
