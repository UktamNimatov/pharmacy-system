package uz.epam.webproject.service;

import uz.epam.webproject.dao.exception.DaoException;
import uz.epam.webproject.entity.order.Order;
import uz.epam.webproject.entity.order.OrderStatus;
import uz.epam.webproject.service.exception.ServiceException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAll() throws ServiceException;

    boolean addOrder(Order order) throws ServiceException;

    Optional<Order> findById(Long id) throws ServiceException;

    boolean delete(Long id) throws ServiceException;

    List<Order> findOrdersByStatus(OrderStatus orderStatus) throws ServiceException;

    List<Order> findOrdersByUserId(Long userId) throws ServiceException;

    Optional<Order> findOrderByOrderedTime(Timestamp orderedTime) throws ServiceException;
}
