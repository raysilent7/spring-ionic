package com.raysilent.curso.controllers;

import com.raysilent.curso.domain.Product;
import com.raysilent.curso.domain.dto.ProductDTO;
import com.raysilent.curso.resources.utils.URL;
import com.raysilent.curso.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id) {
        Product obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value="/categories")
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value="name", defaultValue="") String name,
            @RequestParam(value="categories", defaultValue="") String categories,
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="name")String orderBy,
            @RequestParam(value="direction", defaultValue="ASC")String direction) {
        String decodedName = URL.decodeParam(name);
        List<Integer> ids = URL.decodeIntList(categories);
        Page<Product> list = service.search(decodedName, ids, page, linesPerPage, orderBy, direction);
        Page<ProductDTO> listDto = list.map(obj -> new ProductDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }
}
