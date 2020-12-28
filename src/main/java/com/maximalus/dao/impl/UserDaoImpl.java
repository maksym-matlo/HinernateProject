package com.maximalus.dao.impl;

import com.maximalus.dao.UserDao;
import com.maximalus.model.User;
import com.maximalus.util.HibernateSessionFactoryUtil;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * @author Maksym Matlo
 */

public class UserDaoImpl implements UserDao {
    private SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(User user) {
        HibernateSessionFactoryUtil
                .performWithinPersistenceContext(session -> session.persist(user));
    }

    @Override
    public List<User> findAll() {
        return HibernateSessionFactoryUtil.performReturningWithinPersistenceContext(session ->
                session.createQuery("From User", User.class).list());
    }

    @Override
    public void delete(Long id) {
        HibernateSessionFactoryUtil.performWithinPersistenceContext(session -> {
            User user = session.load(User.class, id);
            session.delete(user);
        });
    }
}
