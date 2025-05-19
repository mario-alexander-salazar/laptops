package io.github.salazar.ecommerce.service.impl;


import io.github.salazar.ecommerce.model.*;
import io.github.salazar.ecommerce.repository.impl.*;
import io.github.salazar.ecommerce.service.*;

import java.util.List;
import java.util.Optional;

public class ProductService implements Service<Integer, Product> {

    private final ProductRepository repository = new ProductRepository();

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Product save(Product entity) {
        return repository.save(entity);
    }

    @Override
    public Product update(Product entity) {
        return repository.update(entity);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
