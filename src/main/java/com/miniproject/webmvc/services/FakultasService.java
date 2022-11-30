package com.miniproject.webmvc.services;

import java.util.List;
import java.util.Optional;

import com.miniproject.webmvc.models.FakultasModel;

public interface FakultasService {
    public List<FakultasModel> getAll();
    public FakultasModel getById(String id);
    public Optional<FakultasModel> save(FakultasModel request);
    public Optional<FakultasModel> update(String id, FakultasModel request);
    public Optional<FakultasModel> delete(String id);
}
