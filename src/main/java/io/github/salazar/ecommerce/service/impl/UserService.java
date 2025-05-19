package io.github.salazar.ecommerce.service.impl;


import io.github.salazar.ecommerce.model.*;
import io.github.salazar.ecommerce.repository.impl.*;
import io.github.salazar.ecommerce.service.*;

import java.util.List;
import java.util.Optional;

public class UserService implements Service<Integer, User> {

    private final UserRepository repository = new UserRepository();

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public User save(User entity) {
        return repository.save(entity);
    }

    @Override
    public User update(User entity) {
        return repository.update(entity);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
