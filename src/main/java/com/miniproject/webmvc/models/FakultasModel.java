package com.miniproject.webmvc.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import com.miniproject.webmvc.entities.FakultasEntity;

@Getter
@Setter
public class FakultasModel {
    private String id;
    private String code;
    private String name;
    private String alamat;

    public FakultasModel() {

    }

    public FakultasModel(FakultasEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }
}