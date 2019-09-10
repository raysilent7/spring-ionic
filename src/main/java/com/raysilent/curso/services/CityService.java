package com.raysilent.curso.services;

import com.raysilent.curso.domain.City;
import com.raysilent.curso.repositories.CityRepository;
import com.raysilent.curso.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository repo;

    public List<City> findAll(Integer stateId) {
        List<City> list = repo.findCities(stateId);
        if (list.size()==0) {
            throw new ObjectNotFoundException("There's no cities for this state.");
        }
        return list;
    }
}
