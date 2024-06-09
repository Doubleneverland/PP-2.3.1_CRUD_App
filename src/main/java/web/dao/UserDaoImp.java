package web.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.entities.User;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Component
public class UserDaoImp implements UserDao/*, InitializingBean*/ {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserDao userDao;

    @PostConstruct
    @Transactional
    public void firstUser() {
        try {
            userDao.save(new User("FirstUserName", "FirstUserLastname"));
        } catch (Exception e) {
            System.out.println("FIRST_USER FAULT");
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void save(User user) {
        if (user != null) {
            entityManager.persist(user);
        }

    }

    public List<User> userPage(long id) {
        TypedQuery<User> query =
                entityManager.createQuery("SELECT u FROM User u where u.id = :userID", User.class);
        query.setParameter("userID", id);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<User> read() {

        TypedQuery<User> query = (TypedQuery<User>) entityManager.createQuery("from User ");

        return query.getResultList();
    }

    @Override
    @Transactional
    public User update(long id, String name, String lastname) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            user.setName(name);
            user.setLastName(lastname);
            entityManager.merge(user);
        }
        return user;
    }

    @Transactional
    public User upPage(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public User delete(long id) {
        User user = entityManager.find(User.class, id);
        if(user != null) {
            entityManager.remove(user);
        }
        return user;
    }



}
