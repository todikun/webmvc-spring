package com.miniproject.webmvc.services;

import java.util.List;
import java.util.Optional;

import com.miniproject.webmvc.models.KelasModel;

public interface KelasService {
    public List<KelasModel> getAll();
    public KelasModel getById(String id);
    public Optional<KelasModel> save(KelasModel request);
    public Optional<KelasModel> update(String id, KelasModel request);
    public Optional<KelasModel> delete(String id);
}
