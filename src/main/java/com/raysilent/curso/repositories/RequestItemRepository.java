package com.raysilent.curso.repositories;

import com.raysilent.curso.domain.RequestItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestItemRepository extends JpaRepository <RequestItem, Integer> {


}
