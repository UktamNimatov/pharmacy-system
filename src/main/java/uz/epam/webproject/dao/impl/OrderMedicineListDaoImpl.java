package uz.epam.webproject.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.dao.exception.DaoException;
import uz.epam.webproject.dao.OrderMedicineListDao;
import uz.epam.webproject.dao.mapper.impl.OrderMedicineListMapper;
import uz.epam.webproject.entity.list.OrderMedicineList;
import uz.epam.webproject.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderMedicineListDaoImpl implements OrderMedicineListDao {

    private static final Logger logger = LogManager.getLogger();
    private final OrderMedicineListMapper orderMedicineListMapper = new OrderMedicineListMapper();

    private static final String SELECT_ALL = "SELECT order_medicine_list.id, order_medicine_list.order_id, order_medicine_list.medicine_id, order_medicine_list.medicine_quantity, order_medicine_list.medicine_price FROM order_medicine_list";
    private static final String ADD_ORDER_MEDICINE_LIST = "INSERT INTO order_medicine_list (order_id, medicine_id, medicine_quantity, medicine_price) values (?, ?, ?, ?)";
    private static final String FIND_ALL_MEDICINE_OF_ONE_ORDER = "SELECT order_medicine_list.id, order_medicine_list.order_id, order_medicine_list.medicine_id, order_medicine_list.medicine_quantity, order_medicine_list.medicine_price FROM order_medicine_list WHERE order_medicine_list.order_id = ?";
    private static final String DELETE_MEDICINE_LIST = "DELETE FROM order_medicine_list WHERE order_medicine_list.id = ?";
    private static final String DELETE_MEDICINE_LIST_BY_ORDER_ID = "DELETE FROM order_medicine_list WHERE order_medicine_list.order_id = ?";

    private static OrderMedicineListDaoImpl instance;
    public static OrderMedicineListDaoImpl getInstance(){
        if (instance == null){
            instance = new OrderMedicineListDaoImpl();
        }
        return instance;
    }

    private OrderMedicineListDaoImpl() {
    }

    @Override
    public List<OrderMedicineList> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)){
            return getOrderMedicineListByResultSet(preparedStatement);
        } catch (SQLException sqlException) {
            logger.error("error in finding all order medicine lists ", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public boolean addEntity(OrderMedicineList medicineList) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER_MEDICINE_LIST)){
            preparedStatement.setLong(1, medicineList.getOrder_id());
            preparedStatement.setLong(2, medicineList.getMedicine_id());
            preparedStatement.setInt(3, medicineList.getMedicine_quantity());
            preparedStatement.setDouble(4, medicineList.getMedicine_price());
            int count = preparedStatement.executeUpdate();
            return count == 1;
        } catch (SQLException sqlException) {
            logger.error("error in adding a new list of medicines", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public Optional<OrderMedicineList> findById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MEDICINE_LIST)){
            preparedStatement.setLong(1, id);
            int count = preparedStatement.executeUpdate();
            return count == 1;
        } catch (SQLException sqlException) {
            logger.error("error in deleting a list of medicines by id ", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public List<OrderMedicineList> findAllMedicineOfOneOrder(Long orderId) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_MEDICINE_OF_ONE_ORDER)){
            preparedStatement.setLong(1, orderId);
            return getOrderMedicineListByResultSet(preparedStatement);
        } catch (SQLException sqlException) {
            logger.error("error in finding all order medicine lists ", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public boolean deleteByOrderId(Long orderId) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MEDICINE_LIST_BY_ORDER_ID)){
            preparedStatement.setLong(1, orderId);
            int count = preparedStatement.executeUpdate();
            return count >= 1;
        } catch (SQLException sqlException) {
            logger.error("error in deleting a list of medicines by order id ", sqlException);
            throw new DaoException(sqlException);
        }
    }

    private List<OrderMedicineList> getOrderMedicineListByResultSet(PreparedStatement preparedStatement) throws SQLException {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            List<OrderMedicineList> orderMedicineLists = new ArrayList<>();
            while (resultSet.next()) {
                Optional<OrderMedicineList> orderMedicineList = orderMedicineListMapper.map(resultSet);
                orderMedicineList.ifPresent(orderMedicineLists::add);
            }
            return orderMedicineLists;
        }
    }
}
