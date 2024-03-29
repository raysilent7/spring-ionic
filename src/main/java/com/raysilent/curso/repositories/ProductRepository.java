package com.raysilent.curso.repositories;

import com.raysilent.curso.domain.Category;
import com.raysilent.curso.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <Product, Integer> {

    @Transactional(readOnly=true)
    List<Product> findAllByOrderByName();

    @Transactional(readOnly=true)
    @Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cat WHERE\n" +
            "obj.name LIKE %:name% AND cat IN :categories")
    Page<Product> search(@Param("name") String name, @Param("categories")List<Category> categories, Pageable pageRequest);
}
