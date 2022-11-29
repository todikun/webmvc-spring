package com.miniproject.webmvc.models;

import org.springframework.beans.BeanUtils;

import com.miniproject.webmvc.entities.GedungEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GedungModel {
    private String id;
    private String code;
    private String name;
    private Integer jmlLantai;

    public GedungModel() {

    }

    public GedungModel(GedungEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
