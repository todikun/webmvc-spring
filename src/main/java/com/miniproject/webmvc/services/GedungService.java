package com.miniproject.webmvc.services;

import java.util.List;
import java.util.Optional;

import com.miniproject.webmvc.models.GedungModel;

public interface GedungService {
    public List<GedungModel> getAll();
    public GedungModel getById(String id);
    public Optional<GedungModel> save(GedungModel request);
    public Optional<GedungModel> update(String id, GedungModel request);
    public Optional<GedungModel> delete(String id);
}
