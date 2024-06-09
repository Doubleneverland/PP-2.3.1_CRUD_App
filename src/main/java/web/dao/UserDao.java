package web.dao;

import web.entities.User;

import java.util.List;

public interface UserDao {

    void save(User user);

    List<User> read();

    User update(long id, String name, String lastname);

    User delete(long id);

//    void firstUser();

    List<User> userPage(long id);

    User upPage(long id);

}
