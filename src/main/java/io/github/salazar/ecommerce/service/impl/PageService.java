package io.github.salazar.ecommerce.service.impl;

import io.github.salazar.ecommerce.model.*;
import io.github.salazar.ecommerce.repository.impl.*;
import io.github.salazar.ecommerce.service.*;

import java.util.List;
import java.util.Optional;

public class PageService implements Service<Integer, Page> {

    private final PageRepository repository = new PageRepository();

    @Override
    public List<Page> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Page> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Page save(Page entity) {
        return repository.save(entity);
    }

    @Override
    public Page update(Page entity) {
        return repository.update(entity);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
