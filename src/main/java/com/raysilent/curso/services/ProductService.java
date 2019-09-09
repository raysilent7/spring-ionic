package com.raysilent.curso.services;

import com.raysilent.curso.domain.Category;
import com.raysilent.curso.domain.Product;
import com.raysilent.curso.repositories.CategoryRepository;
import com.raysilent.curso.repositories.ProductRepository;
import com.raysilent.curso.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private CategoryRepository catRepo;

    public Product find(Integer id) {
        Optional<Product> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Product.class.getName()));
    }

    public Page<Product> search(String name, List<Integer> ids, Integer page,
                                Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);

        List<Category> categories = catRepo.findAllById(ids);
        return repo.search(name, categories, pageRequest);
    }
}
