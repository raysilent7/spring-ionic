package com.raysilent.curso.domain.dto;

import com.raysilent.curso.domain.State;

import java.io.Serializable;

public class StateDTO implements Serializable {

    private Integer id;
    private String name;

    public StateDTO() {
    }

    public StateDTO(State obj) {
        id = obj.getId();
        name = obj.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
