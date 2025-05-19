package io.github.salazar.ecommerce.service.impl;


import io.github.salazar.ecommerce.model.*;
import io.github.salazar.ecommerce.repository.impl.*;
import io.github.salazar.ecommerce.service.*;

import java.util.List;
import java.util.Optional;

public class PageProfileService implements Service<Integer, PageProfile> {

    private final PageProfileRepository repository = new PageProfileRepository();

    @Override
    public List<PageProfile> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<PageProfile> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public PageProfile save(PageProfile entity) {
        return repository.save(entity);
    }

    @Override
    public PageProfile update(PageProfile entity) {
        return repository.update(entity);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
