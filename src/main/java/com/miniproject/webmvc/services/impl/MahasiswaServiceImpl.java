package com.miniproject.webmvc.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.webmvc.entities.JurusanEntity;
import com.miniproject.webmvc.entities.MahasiswaEntity;
import com.miniproject.webmvc.models.MahasiswaModel;
import com.miniproject.webmvc.repos.MahasiswaRepo;
import com.miniproject.webmvc.services.MahasiswaService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MahasiswaServiceImpl implements MahasiswaService {

    private MahasiswaRepo repo;

    @Autowired
    public MahasiswaServiceImpl(MahasiswaRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<MahasiswaModel> getAll() {
        return this.repo.findAll().stream().map(MahasiswaModel::new).collect(Collectors.toList());
    }

    @Override
    public MahasiswaModel getById(String id) {
        return this.repo.findById(id).map(MahasiswaModel::new).orElse(new MahasiswaModel());
    }

    @Override
    public Optional<MahasiswaModel> save(MahasiswaModel request) {
        if (request == null) {
            return Optional.empty();
        }

        MahasiswaEntity result = new MahasiswaEntity(request);
        try {
            this.repo.save(result);
            return Optional.of(new MahasiswaModel(result));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<MahasiswaModel> update(String id, MahasiswaModel request) {
        Optional<MahasiswaEntity> result = this.repo.findById(id);
        if (result.isEmpty()) {
            return Optional.empty();
        }

        MahasiswaEntity data = result.get();
        BeanUtils.copyProperties(request, data);
        JurusanEntity jurusan = new JurusanEntity(request.getJurusan().getId());
        data.setId(id);
        data.setJurusan(jurusan);
        data.setUpdatedAt(LocalDateTime.now());

        try {
            this.repo.save(data);
            return Optional.of(new MahasiswaModel(data));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<MahasiswaModel> delete(String id) {
        Optional<MahasiswaEntity> result = this.repo.findById(id);
        if (result.isEmpty()) {
            return Optional.empty();
        }

        try {
            MahasiswaEntity data = result.get();
            this.repo.delete(data);
            return Optional.of(new MahasiswaModel(data));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
