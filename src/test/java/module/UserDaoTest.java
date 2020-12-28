package module;

import com.maximalus.dao.UserDao;
import com.maximalus.model.User;
import com.maximalus.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static module.TestData.getUser;
import static module.TestData.getUserList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest {
    @Mock
    private UserDao userDao;

    @Mock
    private UserService userService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveUser(){
        User user = getUser();
        userDao.save(user);

        verify(userDao, times(1)).save(user);
    }

    @Test
    public void testFindAllUsers(){
        List<User> userList = getUserList();
        when(userDao.findAll()).thenReturn(userList);

        assertEquals(userDao.findAll(), userList);
    }

    @Test
    public void testDeleteUserById(){
        User user = getUser();
        userDao.delete(1L);
        verify(userDao, times(1)).delete(1L);
    }
}