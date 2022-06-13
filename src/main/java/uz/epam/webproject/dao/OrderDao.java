package uz.epam.webproject.dao;

import uz.epam.webproject.dao.exception.DaoException;
import uz.epam.webproject.entity.order.Order;
import uz.epam.webproject.entity.order.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends EntityDao<Order> {

    @Override
    List<Order> findAll() throws DaoException;

    @Override
    boolean addEntity(Order order) throws DaoException;

    @Override
    Optional<Order> findById(Long id) throws DaoException;

    @Override
    boolean delete(Long id) throws DaoException;

    List<Order> findOrdersByStatus(OrderStatus orderStatus) throws DaoException;

    List<Order> findOrdersByUserId(Long userId) throws DaoException;
}
