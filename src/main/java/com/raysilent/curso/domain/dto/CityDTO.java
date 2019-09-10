package com.raysilent.curso.domain.dto;

import com.raysilent.curso.domain.City;
import com.raysilent.curso.domain.State;

import java.io.Serializable;

public class CityDTO implements Serializable {

    private Integer id;
    private String name;
    private State state;

    public CityDTO() {
    }

    public CityDTO(City obj) {
        id = obj.getId();
        name = obj.getName();
        state = obj.getState();
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

    public State getState() {
        return state;
    }
}
