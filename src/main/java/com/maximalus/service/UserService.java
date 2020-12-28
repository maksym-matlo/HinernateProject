package com.maximalus.service;

import com.maximalus.dao.UserDao;
import com.maximalus.dao.impl.UserDaoImpl;
import com.maximalus.model.User;
import com.maximalus.util.HibernateSessionFactoryUtil;

import java.util.List;

/**
 * @author Maksym Matlo
 */

public class UserService {
    private UserDao userDao;

    public UserService() {
        userDao = new UserDaoImpl(HibernateSessionFactoryUtil.getSessionFactory());
    }

    public void save(User user){
        userDao.save(user);
    }

    public List<User> findAll(){
        return userDao.findAll();
    }

    public void delete(Long id){
        userDao.delete(id);
    }
}
