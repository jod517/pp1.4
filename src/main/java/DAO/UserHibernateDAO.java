package DAO;


import User.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utill.DBHelper;

import java.util.List;

public class UserHibernateDAO implements UserDAO {

    private static UserHibernateDAO instance = new UserHibernateDAO();
    private static SessionFactory sessionFactory = DBHelper.getConfiguration();


    public UserHibernateDAO() {
    }
    public static UserHibernateDAO getInstance() {
        return instance;
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public User getUserById(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = (User) session.load(User.class,id);
        session.close();
        return user;
    }

    @Override
    public boolean deleteUser(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> allusers = session.createQuery("FROM User").list();
        session.getTransaction().commit();
        session.close();
        return allusers;
    }

    @Override
    public User getUserByName(String name) {
        Session session = sessionFactory.openSession();
        User user = null;
        session.beginTransaction();
        user = (User)session.get(User.class, name);
        session.getTransaction().commit();
        return user;


    }

    @Override
    public boolean addUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        return true;
    }
}
