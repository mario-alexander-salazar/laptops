package io.github.salazar.ecommerce.service.impl;

import io.github.salazar.ecommerce.model.Category;
import io.github.salazar.ecommerce.repository.impl.CategoryRepository;
import io.github.salazar.ecommerce.service.Service;

import java.util.List;
import java.util.Optional;

public class CategoryService implements Service<Integer, Category> {

    private final CategoryRepository repository = new CategoryRepository();

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Category> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Category save(Category entity) {
        return repository.save(entity);
    }

    @Override
    public Category update(Category entity) {
        return repository.update(entity);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
