package com.sonik.practicum.repository.Interface;

import com.sonik.practicum.models.Entity.User;

import java.util.List;

public interface UserRepo {
    List<User> findAll();
    void save(User user);
    void deleteById(Long id);
}
