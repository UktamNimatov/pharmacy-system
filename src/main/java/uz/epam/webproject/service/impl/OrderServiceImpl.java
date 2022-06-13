package uz.epam.webproject.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.dao.OrderDao;
import uz.epam.webproject.dao.exception.DaoException;
import uz.epam.webproject.dao.impl.OrderDaoImpl;
import uz.epam.webproject.entity.order.Order;
import uz.epam.webproject.entity.order.OrderStatus;
import uz.epam.webproject.service.OrderService;
import uz.epam.webproject.service.exception.ServiceException;
import uz.epam.webproject.validator.OrderValidator;
import uz.epam.webproject.validator.impl.OrderValidatorImpl;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LogManager.getLogger();
    private final OrderValidator orderValidator = OrderValidatorImpl.getInstance();

    private static OrderServiceImpl instance;
    private final OrderDao orderDao = OrderDaoImpl.getInstance();

    public static OrderServiceImpl getInstance(){
        if (instance == null){
            instance = new OrderServiceImpl();
        }
        return instance;
    }

    private OrderServiceImpl() {
    }

    @Override
    public List<Order> findAll() throws ServiceException {
        try {
            return orderDao.findAll();
        } catch (DaoException e) {
            logger.error("error in finding list of all orders ", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean addOrder(Order order) throws ServiceException {
        try {
           return orderDao.addEntity(order);
        } catch (DaoException e) {
            logger.error("error in making a new order ", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Order> findById(Long id) throws ServiceException {
        try {
            return orderDao.findById(id);
        } catch (DaoException e) {
            logger.error("error in finding order by id ", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        try {
            return orderDao.delete(id);
        } catch (DaoException e) {
            logger.error("error in deleting an order ", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findOrdersByStatus(OrderStatus orderStatus) throws ServiceException {
        try {
            return orderDao.findOrdersByStatus(orderStatus);
        } catch (DaoException e) {
            logger.error("error in finding list of all orders ", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findOrdersByUserId(Long userId) throws ServiceException {
        try {
            return orderDao.findOrdersByUserId(userId);
        } catch (DaoException e) {
            logger.error("error in finding list of all orders ", e);
            throw new ServiceException(e);
        }
    }
}
