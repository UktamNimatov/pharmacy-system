package uz.epam.webproject.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.dao.OrderDao;
import uz.epam.webproject.dao.exception.DaoException;
import uz.epam.webproject.dao.mapper.impl.OrderMapper;
import uz.epam.webproject.entity.order.Order;
import uz.epam.webproject.entity.order.OrderStatus;
import uz.epam.webproject.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {
    private static final Logger logger = LogManager.getLogger();
    private final OrderMapper orderMapper = new OrderMapper();

    private static final String ADD_ORDER = "INSERT INTO order (user_id, status, ordered_time, confirmed_time, completed_time, canceled_time) values (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_ORDERS = "SELECT order.id, order.user_id, order.status, order.ordered_time, order.confirmed_time, order.completed_time, order.canceled_time FROM order";
    private static final String FIND_BY_ID = "SELECT order.id, order.user_id, order.status, order.ordered_time, order.confirmed_time, order.completed_time, order.canceled_time FROM order WHERE order.id = ?";
    private static final String DELETE_ORDER = "DELETE FROM order WHERE order.id = ?";
    private static final String ORDERS_BY_STATUS = "SELECT order.id, order.user_id, order.status, order.ordered_time, order.confirmed_time, order.completed_time, order.canceled_time FROM order WHERE order.status = ?";
    private static final String ORDERS_BY_USER = "SELECT order.id, order.user_id, order.status, order.ordered_time, order.confirmed_time, order.completed_time, order.canceled_time FROM order WHERE order.user_id = ?";

    private static OrderDaoImpl instance;

    public static OrderDaoImpl getInstance(){
        if (instance == null){
            instance = new OrderDaoImpl();
        }
        return instance;
    }

    private OrderDaoImpl() {
    }

    @Override
    public List<Order> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS)){
            return getOrdersFromResultSet(preparedStatement);
        } catch (SQLException sqlException) {
            logger.error("error in getting all orders ", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public boolean addEntity(Order order) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER)){
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setString(2, order.getStatus().name().toLowerCase());
            preparedStatement.setTimestamp(3, order.getOrderedTime());
            preparedStatement.setTimestamp(4, order.getConfirmedTime());
            preparedStatement.setTimestamp(5, order.getCanceledTime());
            preparedStatement.setTimestamp(6, order.getCompletedTime());
            int count = preparedStatement.executeUpdate();
            return count == 1;
        } catch (SQLException sqlException) {
            logger.error("error in adding a new order ", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public Optional<Order> findById(Long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Optional<Order> order = orderMapper.map(resultSet);
                return order;
            }
        } catch (SQLException sqlException) {
            logger.error("error in finding an order by id ", sqlException);
            throw new DaoException(sqlException);
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER)){
            preparedStatement.setLong(1, id);
            int count = preparedStatement.executeUpdate();
            return count == 1;
        } catch (SQLException sqlException) {
            logger.error("error in deleting an order by id ", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public List<Order> findOrdersByStatus(OrderStatus orderStatus) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(ORDERS_BY_STATUS)){
            preparedStatement.setString(1, orderStatus.name().toLowerCase());
            return getOrdersFromResultSet(preparedStatement);
        } catch (SQLException sqlException) {
            logger.error("error in finding all orders by status ", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public List<Order> findOrdersByUserId(Long userId) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ORDERS_BY_USER)){
            preparedStatement.setLong(1, userId);
            return getOrdersFromResultSet(preparedStatement);
        } catch (SQLException sqlException) {
            logger.error("error in finding all orders by a user ", sqlException);
            throw new DaoException(sqlException);
        }
    }

    private List<Order> getOrdersFromResultSet(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Order> orderList = new ArrayList<>();
        while (resultSet.next()){
            Optional<Order> order = orderMapper.map(resultSet);
            order.ifPresent(orderList::add);
        }
        return orderList;
    }
}
