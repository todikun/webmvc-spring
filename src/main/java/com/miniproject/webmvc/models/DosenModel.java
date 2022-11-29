package com.miniproject.webmvc.models;

import org.springframework.beans.BeanUtils;

import com.miniproject.webmvc.entities.DosenEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DosenModel {
    private String id;
    private String nip;
    private String name;
    private String jk;
    private String alamat;
    private String gelar;

    public DosenModel() {

    }

    public DosenModel(DosenEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
