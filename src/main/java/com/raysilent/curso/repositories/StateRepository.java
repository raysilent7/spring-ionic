package com.raysilent.curso.repositories;

import com.raysilent.curso.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository <State, Integer> {


}
