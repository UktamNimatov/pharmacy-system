package uz.epam.webproject.dao;

import uz.epam.webproject.dao.exception.DaoException;
import uz.epam.webproject.entity.user.User;
import uz.epam.webproject.entity.user.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserDao<T extends User> {

    boolean registerUser(User user) throws DaoException;

    boolean authenticate(String login, String email) throws DaoException;

    List<T> findAll() throws DaoException;

    Optional<T> findByLogin(String login) throws DaoException;

    UserRole findUserRole(String login)throws DaoException;

    boolean isLoginAvailable(String login) throws DaoException;

    boolean isEmailAvailable(String email) throws DaoException;

    boolean updatePassword(String login, String newPassword) throws DaoException;

}
