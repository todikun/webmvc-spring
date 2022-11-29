package com.miniproject.webmvc.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.webmvc.entities.GedungEntity;
import com.miniproject.webmvc.models.GedungModel;
import com.miniproject.webmvc.repos.GedungRepo;
import com.miniproject.webmvc.services.GedungService;

@Service
public class GedungServiceImpl implements GedungService {

    private GedungRepo repo;

    @Autowired
    public GedungServiceImpl(GedungRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<GedungModel> getAll() {
        return this.repo.findAll().stream().map(GedungModel::new).collect(Collectors.toList());
    }

    @Override
    public GedungModel getById(String id) {
        return this.repo.findById(id).map(GedungModel::new).orElse(new GedungModel());
    }

    @Override
    public Optional<GedungModel> save(GedungModel request) {
        if (request == null) {
            return Optional.empty();
        }

        GedungEntity result = new GedungEntity(request);
        try {
            this.repo.save(result);
            return Optional.of(new GedungModel(result));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<GedungModel> update(String id, GedungModel request) {
        Optional<GedungEntity> result = this.repo.findById(id);
        if (result.isEmpty()) {
            return Optional.empty();
        }

        GedungEntity data = result.get();
        BeanUtils.copyProperties(request, data);
        data.setId(id);
        data.setUpdatedAt(LocalDateTime.now());

        try {
            this.repo.save(data);
            return Optional.of(new GedungModel(data));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<GedungModel> delete(String id) {
        Optional<GedungEntity> result = this.repo.findById(id);
        if (result.isEmpty()) {
            return Optional.empty();
        }
        try {
            GedungEntity data = result.get();
            this.repo.delete(data);
            return Optional.of(new GedungModel(data));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}