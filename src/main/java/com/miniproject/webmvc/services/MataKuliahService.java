package com.miniproject.webmvc.services;

import java.util.List;
import java.util.Optional;

import com.miniproject.webmvc.models.MataKuliahModel;

public interface MataKuliahService {
    public List<MataKuliahModel> getAll();
    public MataKuliahModel getById(String id);
    public Optional<MataKuliahModel> save(MataKuliahModel request);
    public Optional<MataKuliahModel> update(String id, MataKuliahModel request);
    public Optional<MataKuliahModel> delete(String id);
}
