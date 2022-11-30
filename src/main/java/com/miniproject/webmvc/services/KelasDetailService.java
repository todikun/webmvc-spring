package com.miniproject.webmvc.services;

import java.util.List;
import java.util.Optional;

import com.miniproject.webmvc.models.KelasDetailModel;

public interface KelasDetailService {
    public List<KelasDetailModel> getAll();
    public KelasDetailModel getById(String id);
    public Optional<KelasDetailModel> save(KelasDetailModel request);
    public Optional<KelasDetailModel> update(String id, KelasDetailModel request);
    public Optional<KelasDetailModel> delete(String id);
}
