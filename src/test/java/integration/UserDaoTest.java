package integration;

import com.maximalus.dao.UserDao;
import com.maximalus.dao.impl.UserDaoImpl;
import com.maximalus.model.User;
import com.maximalus.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

/**
 * @author Maksym Matlo
 */

public class UserDaoTest {
    private static SessionFactory sessionFactory;
    private static UserDao userDao;

    @BeforeClass
    public static void init(){
        sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        userDao = new UserDaoImpl(sessionFactory);
    }

    @AfterClass
    public static void destroy(){
        HibernateSessionFactoryUtil.close();
    }

    @Test
    public void testSession(){
        assertThat(sessionFactory, notNullValue());
        assertThat(userDao, notNullValue());
    }

    @Test
    public void saveTestUser(){
        User user = new User("David", "Brown");
        userDao.save(user);
        boolean saved = isSaved(user);

        assertThat(saved, is(true));
    }

    private boolean isSaved(User testUser) {
        Session session = sessionFactory.openSession();
        User user = session.find(User.class, 1L);
        return user != null;
    }

    @Test
    public void findAllUsers(){
        List<User> savedUsers = saveListOfUsers();
        List<User> foundUsers = userDao.findAll();
        assertThat(savedUsers, everyItem(isIn(foundUsers)));
    }

    private List<User> saveListOfUsers(){
        List<User> collect = Stream.of(
                new User("Oran", "Sevid"),
                new User("Deras", "Lotos"),
                new User("Seder", "Rehad"),
                new User("Emal", "Ratir"),
                new User("Erat", "Vetor")
        ).collect(Collectors.toList());

        collect.forEach(userDao::save);

        return collect;
    }

    @Test
    public void removeUser(){
        User user = new User("Aser", "Roter");
        userDao.save(user);

        userDao.delete(1L);
        boolean saved = isSaved(user);
        assertThat(saved, is(false));
    }
}
