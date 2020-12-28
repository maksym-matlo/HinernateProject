package com.maximalus.dao;

import com.maximalus.model.User;

import java.util.List;

/**
 * @author Maksym Matlo
 */

public interface UserDao {
    void save (User user);
    List<User> findAll();
    void delete(Long id);
}
