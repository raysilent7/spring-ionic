package com.raysilent.curso.repositories;

import com.raysilent.curso.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository <State, Integer> {

    @Transactional(readOnly=true)
    List<State> findAllByOrderByName();
}
