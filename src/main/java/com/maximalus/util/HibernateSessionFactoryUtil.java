package com.maximalus.util;

import com.maximalus.exception.HibernateUtilException;
import com.maximalus.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Maksym Matlo
 */

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory(){
        if(sessionFactory==null){
            try{
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                                .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            }catch (Exception e){
                e.printStackTrace();
                throw new HibernateUtilException("Session factory can not be build", e);
            }
        }
        return sessionFactory;
    }

    public static void close(){
        sessionFactory.close();
    }

    public static void performWithinPersistenceContext(Consumer<Session> consumer){
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            consumer.accept(session);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            throw new HibernateUtilException("Error performing JPA operation. Transaction is rolled back", e);
        }finally {
            session.close();
        }
    }

    public static <T> T performReturningWithinPersistenceContext(Function<Session, T> function){
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            T result = function.apply(session);
            transaction.commit();
            return result;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            throw new HibernateUtilException("Error performing JPA operation. Transaction is rolled back", e);
        }finally {
            session.close();
        }
    }
}