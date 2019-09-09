package com.raysilent.curso.services;

import com.raysilent.curso.domain.Category;
import com.raysilent.curso.domain.dto.CategoryDTO;
import com.raysilent.curso.repositories.CategoryRepository;
import com.raysilent.curso.services.exception.DataIntegrityException;
import com.raysilent.curso.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public Category find(Integer id) {
        Optional<Category> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
    }

    public Category insert(Category obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Category update(Category obj) {
        Category newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(obj);
    }

    public Category fromDto(CategoryDTO objDto) {
        return new Category(objDto.getId(), objDto.getName());
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Can't delete a category with products associated");
        }
    }

    public List<Category> findAll() {
        return repo.findAll();
    }

    public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    private void updateData(Category newObj, Category obj) {
        newObj.setName(obj.getName());
    }
}
