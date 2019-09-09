package com.raysilent.curso.repositories;

import com.raysilent.curso.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository <City, Integer> {


}
