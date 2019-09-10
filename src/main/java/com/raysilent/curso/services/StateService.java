package com.raysilent.curso.services;

import com.raysilent.curso.domain.State;
import com.raysilent.curso.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    @Autowired
    private StateRepository repo;

    public List<State> findAll() {
        return repo.findAllByOrderByName();
    }
}
