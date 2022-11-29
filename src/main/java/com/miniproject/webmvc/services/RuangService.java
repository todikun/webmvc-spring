package com.miniproject.webmvc.services;

import java.util.List;
import java.util.Optional;

import com.miniproject.webmvc.models.RuangModel;

public interface RuangService {
    public List<RuangModel> getAll();
    public RuangModel getById(String id);
    public Optional<RuangModel> save(RuangModel request);
    public Optional<RuangModel> update(String id, RuangModel request);
    public Optional<RuangModel> delete(String id);
}
