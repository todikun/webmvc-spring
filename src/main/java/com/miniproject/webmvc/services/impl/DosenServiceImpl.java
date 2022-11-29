package com.miniproject.webmvc.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.webmvc.entities.DosenEntity;
import com.miniproject.webmvc.models.DosenModel;
import com.miniproject.webmvc.repos.DosenRepo;
import com.miniproject.webmvc.services.DosenService;

@Service
public class DosenServiceImpl implements DosenService {

    DosenRepo repo;

    @Autowired
    public DosenServiceImpl(DosenRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<DosenModel> getAll() {
        return this.repo.findAll().stream().map(DosenModel::new).collect(Collectors.toList());
    }

    @Override
    public DosenModel getById(String id) {
        return this.repo.findById(id).map(DosenModel::new).orElse(null);
    }

    @Override
    public Optional<DosenModel> save(DosenModel request) {
        if (request == null) {
            return Optional.empty();
        }

        DosenEntity result = new DosenEntity(request);
        try {
            this.repo.save(result);
            return Optional.of(new DosenModel(result));   
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<DosenModel> update(String id, DosenModel request) {
        Optional<DosenEntity> result = this.repo.findById(id);
        if (result.isEmpty()) {
            return Optional.empty();
        }

        DosenEntity data = result.get();
        BeanUtils.copyProperties(request, data);
        data.setId(id);
        data.setUpdatedAt(LocalDateTime.now());

        try {
            this.repo.save(data);
            return Optional.of(new DosenModel(data));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<DosenModel> delete(String id) {
        Optional<DosenEntity> result = this.repo.findById(id);
        if (result.isEmpty()) {
            return Optional.empty();
        }

        try {
            DosenEntity data = result.get();
            this.repo.delete(data);
            return Optional.of(new DosenModel(data));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
