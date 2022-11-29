package com.miniproject.webmvc.services;

import java.util.List;
import java.util.Optional;

import com.miniproject.webmvc.models.DosenModel;

public interface DosenService {
    public List<DosenModel> getAll();
    public DosenModel getById(String id);
    public Optional<DosenModel> save(DosenModel request);
    public Optional<DosenModel> update(String id, DosenModel request);
    public Optional<DosenModel> delete(String id);
}
