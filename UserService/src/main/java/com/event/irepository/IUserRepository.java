package com.event.irepository;

import java.util.List;

import com.event.model.User;

public interface IUserRepository {
    int save(User user);
    List<User> findAll();
    User findById(int id);
    boolean existsById(int id);
    int deleteById(int id);
    int update(User user);
}
