package com.miniproject.webmvc.models;

import org.springframework.beans.BeanUtils;

import com.miniproject.webmvc.entities.MataKuliahEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MataKuliahModel {
    private String id;
    private String code;
    private String name;
    private Integer sks;

    public MataKuliahModel() {
    }

    public MataKuliahModel(MataKuliahEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
