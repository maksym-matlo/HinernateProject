package integration;

import com.maximalus.model.User;
import com.maximalus.service.UserService;
import com.maximalus.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Maksym Matlo
 */

public class UserServiceTest {
    private static SessionFactory sessionFactory;
    private static UserService userService;

    @BeforeClass
    public static void init(){
        sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        userService = new UserService();
    }

    @AfterClass
    public static void destroy(){
        HibernateSessionFactoryUtil.close();
    }

    @Test
    public void testSession(){
        assertThat(sessionFactory, notNullValue());
        assertThat(userService, notNullValue());
    }

    @Test
    public void saveTestUser(){
        User user = new User("David", "Brown");
        userService.save(user);
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
        List<User> foundUsers = userService.findAll();
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

        collect.forEach(userService::save);

        return collect;
    }

    @Test
    public void removeUser(){
        User user = new User("Aser", "Roter");
        userService.save(user);

        userService.delete(1L);
        boolean saved = isSaved(user);
        assertThat(saved, is(false));
    }
}
