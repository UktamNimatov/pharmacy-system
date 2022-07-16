package uz.epam.webproject.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.dao.RequestReceiptDao;
import uz.epam.webproject.dao.exception.DaoException;
import uz.epam.webproject.dao.mapper.impl.RequestReceiptMapper;
import uz.epam.webproject.entity.order.Order;
import uz.epam.webproject.entity.receipt.RequestReceipt;
import uz.epam.webproject.entity.receipt.RequestReceiptStatus;
import uz.epam.webproject.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RequestReceiptDaoImpl implements RequestReceiptDao {
    private static final Logger logger = LogManager.getLogger();
    private final RequestReceiptMapper requestReceiptMapper = new RequestReceiptMapper();

    private static final String ADD_REQUEST_RECEIPT = "INSERT INTO web_project.request_receipt (request_receipt.illness_description, request_receipt.user_id, request_receipt.status, request_receipt.ordered_time) values (?, ?, ?, ?)";
    private static final String SELECT_ALL_REQUEST_RECEIPTS = "SELECT request_receipt.id, request_receipt.illness_description, request_receipt.user_id, request_receipt.status, request_receipt.ordered_time FROM web_project.request_receipt";
    private static final String DELETE_REQUEST_RECEIPT = "DELETE FROM web_project.request_receipt WHERE web_project.request_receipt.id = ?";
    private static final String FIND_BY_ID = "SELECT request_receipt.id, request_receipt.illness_description, request_receipt.user_id, request_receipt.status, request_receipt.ordered_time FROM web_project.request_receipt WHERE web_project.request_receipt.id = ?";
    private static final String REQUEST_RECEIPTS_BY_STATUS = "SELECT request_receipt.id, request_receipt.illness_description, request_receipt.user_id, request_receipt.status, request_receipt.ordered_time FROM web_project.request_receipt WHERE web_project.request_receipt.status = ?";
    private static final String REQUEST_RECEIPTS_BY_USER = "SELECT request_receipt.id, request_receipt.illness_description, request_receipt.user_id, request_receipt.status, request_receipt.ordered_time FROM web_project.request_receipt WHERE web_project.request_receipt.user_id = ?";
    private static final String FIND_REQUEST_RECEIPT_BY_ORDERED_TIME = "SELECT request_receipt.illness_description, request_receipt.user_id, request_receipt.status, request_receipt.ordered_time FROM web_project.request_receipt WHERE web_project.request_receipt.ordered_time = ?";
    private static final String CHANGE_STATUS = "UPDATE request_receipt SET request_receipt.status = ? WHERE web_project.request_receipt.id = ?";


    private static RequestReceiptDaoImpl instance;

    public static RequestReceiptDaoImpl getInstance(){
        if (instance == null){
            instance = new RequestReceiptDaoImpl();
        }
        return instance;
    }

    private RequestReceiptDaoImpl() {
    }

    @Override
    public List<RequestReceipt> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_REQUEST_RECEIPTS)){
            return getRequestReceiptsFromResultSet(preparedStatement);
        } catch (SQLException sqlException) {
            logger.error("error in getting all request receipts ", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public boolean addEntity(RequestReceipt requestReceipt) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_REQUEST_RECEIPT)){
            preparedStatement.setString(1, requestReceipt.getIllnessDescription());
            preparedStatement.setLong(2, requestReceipt.getUserId());
            preparedStatement.setString(3, requestReceipt.getStatus().toString().toLowerCase());
            preparedStatement.setTimestamp(4, requestReceipt.getOrderedTime());
            int count = preparedStatement.executeUpdate();
            return count == 1;
        } catch (SQLException sqlException) {
            logger.error("error in getting all request receipts ", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public Optional<RequestReceipt> findById(Long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)){
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    Optional<RequestReceipt> requestReceipt = requestReceiptMapper.map(resultSet);
                    return requestReceipt;
                }
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
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REQUEST_RECEIPT)){
            preparedStatement.setLong(1, id);
            int count = preparedStatement.executeUpdate();
            return count == 1;
        } catch (SQLException sqlException) {
            logger.error("error in deleting an order by id ", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public List<RequestReceipt> findRequestReceiptsByUserId(Long userId) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REQUEST_RECEIPTS_BY_USER)){
            preparedStatement.setLong(1, userId);
            return getRequestReceiptsFromResultSet(preparedStatement);
        } catch (SQLException sqlException) {
            logger.error("error in finding all orders by a user ", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public void changeStatus(Long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_STATUS)){
            preparedStatement.setString(1, String.valueOf(RequestReceiptStatus.RESPONDED).toLowerCase());
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            logger.error("error in finding all orders by a user ", sqlException);
            throw new DaoException(sqlException);
        }
    }

    private List<RequestReceipt> getRequestReceiptsFromResultSet(PreparedStatement preparedStatement) throws SQLException {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            List<RequestReceipt> requestReceipts = new ArrayList<>();
            while (resultSet.next()) {
                Optional<RequestReceipt> order = requestReceiptMapper.map(resultSet);
                order.ifPresent(requestReceipts::add);
            }
            return requestReceipts;
        }
    }
}
