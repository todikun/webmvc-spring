package com.miniproject.webmvc.services;

import java.util.List;
import java.util.Optional;

import com.miniproject.webmvc.models.JurusanModel;

public interface JurusanService {
    public List<JurusanModel> getAll();
    public JurusanModel getById(String id);
    public Optional<JurusanModel> save(JurusanModel request);
    public Optional<JurusanModel> update(String id, JurusanModel request);
    public Optional<JurusanModel> delete(String id);
}
