package com.raysilent.curso.repositories;

import com.raysilent.curso.domain.Client;
import com.raysilent.curso.domain.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RequestRepository extends JpaRepository <Request, Integer> {

    @Transactional(readOnly=true)
    Page<Request> findByClient(Client client, Pageable pageRequest);
}
