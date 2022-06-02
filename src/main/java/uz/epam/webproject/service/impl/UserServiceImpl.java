package uz.epam.webproject.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.dao.UserDao;
import uz.epam.webproject.dao.exception.DaoException;
import uz.epam.webproject.dao.impl.UserDaoImpl;
import uz.epam.webproject.entity.user.User;
import uz.epam.webproject.entity.user.UserRole;
import uz.epam.webproject.service.UserService;
import uz.epam.webproject.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();

    private static UserServiceImpl instance;

    public static UserServiceImpl getInstance() {
        if (instance == null){
            return instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean registerUser(User user) throws ServiceException {
        UserDao<User> userDao = UserDaoImpl.getInstance();
        try {
            return userDao.registerUser(user);
        } catch (DaoException e) {
            logger.error("error in adding user in service layer", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean authenticate(String login, String email) throws ServiceException {
        UserDao<User> userDao = UserDaoImpl.getInstance();
        boolean match;
        try {
            match = userDao.authenticate(login, email);
            logger.info(match);
        } catch (DaoException e) {
            logger.error("error in authenticating the user", e);
            throw new ServiceException(e);
        }
        return match;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        UserDao<User> userDao = UserDaoImpl.getInstance();
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            logger.error("error in getting users", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) throws ServiceException {
        UserDao<User> userDao = UserDaoImpl.getInstance();
        try {
            return userDao.findByLogin(login);
        } catch (DaoException e) {
            logger.error("error in finding user by login", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public UserRole findUserRole(String login) throws ServiceException {
        UserDao<User> userDao = UserDaoImpl.getInstance();
        try {
            return userDao.findUserRole(login);
        } catch (DaoException e) {
            logger.error("erro in finding user role by login", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean checkLogin(String login) throws ServiceException {
        UserDao<User> userDao = UserDaoImpl.getInstance();
        return false;
    }

    @Override
    public boolean updatePassword(String login, String newPassword) throws ServiceException {
        UserDao<User> userDao = UserDaoImpl.getInstance();
        try {
            userDao.updatePassword(login, newPassword);
        } catch (DaoException e) {
            logger.error("error in updating the password", e);
            throw new ServiceException(e);
        }
        return false;
    }

}
