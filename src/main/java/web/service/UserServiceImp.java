package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.entities.User;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public List<User> read() {
        return userDao.read();
    }

    public List<User> userPage(long id) { return userDao.userPage(id);
    }

    public User delete(long id) {
        return userDao.delete(id);
    }

    @Override
    public User update(long id, String name, String lastname) {
        return userDao.update(id, name, lastname);
    }

    @Override
    public User upPage(long id) {
        return userDao.upPage(id);
    }


}
