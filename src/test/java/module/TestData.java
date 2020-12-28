package module;

import com.maximalus.model.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestData {
    public static User getUser(){
        User user = new User("Torio", "Valensa");
        return user;
    }

    public static List<User> getUserList(){
        List<User> collect = Stream.of(
                new User("Torio", "Valensa"),
                new User("Torio", "Valensa"),
                new User("Torio", "Valensa"),
                new User("Torio", "Valensa"),
                new User("Torio", "Valensa")
        ).collect(Collectors.toList());
        return collect;
    }
}
