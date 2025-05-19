package io.github.salazar.ecommerce.service.impl;

import io.github.salazar.ecommerce.model.*;
import io.github.salazar.ecommerce.repository.impl.*;
import io.github.salazar.ecommerce.service.*;

import java.util.List;
import java.util.Optional;

public class ProfileService implements Service<Integer, Profile> {

    private final ProfileRepository repository = new ProfileRepository();

    @Override
    public List<Profile> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Profile> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Profile save(Profile entity) {
        return repository.save(entity);
    }

    @Override
    public Profile update(Profile entity) {
        return repository.update(entity);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
