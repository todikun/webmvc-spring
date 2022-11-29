package com.miniproject.webmvc.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import com.miniproject.webmvc.entities.JurusanEntity;

@Getter
@Setter
public class JurusanModel {
    private String id;
    private String code;
    private String name;
    private String fakultasId;
    private FakultasModel fakultas;

    public JurusanModel() {

    }

    public JurusanModel(JurusanEntity entity) {
        BeanUtils.copyProperties(entity, this);
        if (entity.getFakultas() != null) {
            fakultasId = entity.getFakultas().getId();
            fakultas = new FakultasModel(entity.getFakultas());
        }
    }
}