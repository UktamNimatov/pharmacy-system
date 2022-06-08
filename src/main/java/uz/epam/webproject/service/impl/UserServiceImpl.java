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
import uz.epam.webproject.validator.UserValidator;
import uz.epam.webproject.validator.impl.UserValidatorImpl;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private final UserValidator userValidator = UserValidatorImpl.getInstance();

    private static UserServiceImpl instance;
    private final UserDao<User> userDao = UserDaoImpl.getInstance();

    public static UserServiceImpl getInstance() {
        if (instance == null){
            return instance = new UserServiceImpl();
        }
        return instance;
    }

    private UserServiceImpl() {
    }

    @Override
    public boolean registerUser(User user) throws ServiceException {
        if (userValidator.checkUser(user)){
            try {
                return userDao.registerUser(user);
            } catch (DaoException e) {
                logger.error("error in adding user in service layer", e);
                throw new ServiceException(e);
            }
        }else {
            return false;
        }
    }

    @Override
    public boolean authenticate(String login, String email) throws ServiceException {
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
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            logger.error("error in getting users", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) throws ServiceException {
        try {
            return userDao.findByLogin(login);
        } catch (DaoException e) {
            logger.error("error in finding user by login", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public UserRole findUserRole(String login) throws ServiceException {
        try {
            return userDao.findUserRole(login);
        } catch (DaoException e) {
            logger.error("error in finding user role by login", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isLoginAvailable(String login) throws ServiceException {
        try {
            return userDao.isLoginAvailable(login);
        } catch (DaoException e) {
            logger.error("error in finding out whether login is available or not", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isEmailAvailable(String email) throws ServiceException {
        try {
            return userDao.isLoginAvailable(email);
        } catch (DaoException e) {
            logger.error("error in finding out whether email is available or not", e);
            throw new ServiceException(e);
        }
    }



    @Override
    public boolean updatePassword(String login, String newPassword) throws ServiceException {
        try {
            userDao.updatePassword(login, newPassword);
        } catch (DaoException e) {
            logger.error("error in updating the password", e);
            throw new ServiceException(e);
        }
        return false;
    }

}
