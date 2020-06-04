package Service;

import DAO.UserDAO;
import DAO.UserDaoFactory;
import User.User;
import exception.DBException;
import org.hibernate.SessionFactory;
import utill.DBHelper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class UserService {

   private static UserService userService;

    private SessionFactory sessionFactory;

    private static UserDAO userDAO;


    public UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService(DBHelper.getConfiguration());
        }
        return userService;
    }

    public UserService() {
    }

    public void updateUser(User user) throws IOException {
//        UserJDBCDao dao = getUserDAO();
//        dao.updateUser(user);
//        UserHibernateDAO dao = new UserHibernateDAO();
//        dao.updateUser(user);
        UserDaoFactory.getUserDao().updateUser(user);

    }

    public User getUserById(long id) throws IOException {
//        UserJDBCDao dao = getUserDAO();
//        return dao.getUserById(id);
//        UserHibernateDAO dao = new UserHibernateDAO();
//        return dao.getUserById(id);
        return UserDaoFactory.getUserDao().getUserById(id);

    }


    public boolean deleteUser(Long id) throws IOException {
//        UserJDBCDao dao = getUserDAO();
//        return dao.deleteUser(id);

//        new UserHibernateDAO().deleteUser(id);
//        return true;

        UserDaoFactory.getUserDao().deleteUser(id);
        return true;

    }


          public List<User> getAllUsers() throws SQLException, IOException {
//        try  {
//            UserJDBCDao dao = getUserDAO();
//            return dao.getAllUsers();
//        } catch (SQLException e){
//            throw new RuntimeException(e);
//        }


//        List<User> list = null;
//              UserHibernateDAO dao = new UserHibernateDAO();
//              list = dao.getAllUsers();
//              return list;
              List<User> list = null;
              list = UserDaoFactory.getUserDao().getAllUsers();
              return list;

    }



   public User getUserByName(String name) throws SQLException, IOException {
//        try  {
//            UserJDBCDao dao = getUserDAO();
//            return dao.getUserByName(name);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }


//       UserHibernateDAO dao = new UserHibernateDAO();
//       return dao.getUserByName(name);
       return UserDaoFactory.getUserDao().getUserByName(name);

    }

    public boolean addUser(User user) throws DBException, SQLException, IOException {
//
//        try  {
//            UserJDBCDao dao = getUserDAO();
//            dao.addUser(user);
//            return true;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        UserHibernateDAO dao = new UserHibernateDAO();
//        dao.addUser(user);
//        return true;
        UserDaoFactory.getUserDao().addUser(user);
        return true;

    }

//    private static UserJDBCDao getUserDAO() {
//        return new UserJDBCDao(DBHelper.getConnection());
//
//    }
}
