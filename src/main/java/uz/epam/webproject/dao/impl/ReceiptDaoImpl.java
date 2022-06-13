package uz.epam.webproject.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.dao.ReceiptDao;
import uz.epam.webproject.dao.exception.DaoException;
import uz.epam.webproject.dao.mapper.impl.OrderMapper;
import uz.epam.webproject.dao.mapper.impl.ReceiptMapper;
import uz.epam.webproject.entity.receipt.Receipt;
import uz.epam.webproject.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReceiptDaoImpl implements ReceiptDao {
    private static final Logger logger = LogManager.getLogger();
    private final ReceiptMapper receiptMapper = new ReceiptMapper();

    private static final String ADD_RECEIPT = "INSERT INTO receipt (doctor_user_id, client_user_id, usage, assigned_time) values (?, ?, ?, ?)";
    private static final String SELECT_ALL_RECEIPTS = "SELECT receipt.id, receipt.doctor_user_id, receipt.client_user_id, receipt.usage, receipt.assigned_time FROM receipt";
    private static final String FIND_BY_ID = "SELECT receipt.id, receipt.doctor_user_id, receipt.client_user_id, receipt.usage, receipt.assigned_time FROM receipt WHERE receipt.id = ?";
    private static final String DELETE_RECEIPT = "DELETE FROM receipt WHERE receipt.id = ?";
    private static final String FIND_ALL_RECEIPTS_BY_DOCTOR = "SELECT receipt.id, receipt.doctor_user_id, receipt.client_user_id, receipt.usage, receipt.assigned_time FROM receipt WHERE receipt.doctor_user_id = ?";
    private static final String FIND_ALL_RECEIPTS_TO_CLIENT = "SELECT receipt.id, receipt.doctor_user_id, receipt.client_user_id, receipt.usage, receipt.assigned_time FROM receipt WHERE receipt.client_user_id = ?";

    private static ReceiptDaoImpl instance;

    public static ReceiptDaoImpl getInstance(){
        if (instance == null){
            instance = new ReceiptDaoImpl();
        }
        return instance;
    }

    private ReceiptDaoImpl() {
    }

    @Override
    public List<Receipt> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_RECEIPTS)){
            return getReceiptListFromResultSet(preparedStatement);
        } catch (SQLException sqlException) {
            logger.error("error in finding all receipts ", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public boolean addEntity(Receipt receipt) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_RECEIPT)){
            preparedStatement.setLong(1, receipt.getFromUserId());
            preparedStatement.setLong(2, receipt.getToUserId());
            preparedStatement.setString(3, receipt.getUsage());
            preparedStatement.setTimestamp(4, receipt.getAssignedTime());
            int count = preparedStatement.executeUpdate();
            return count == 1;
        } catch (SQLException sqlException) {
            logger.error("error in adding a receipt ", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public Optional<Receipt> findById(Long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Optional<Receipt> receipt = receiptMapper.map(resultSet);
                return receipt;
            }
        } catch (SQLException sqlException) {
            logger.error("error in finding a receipt by id ", sqlException);
            throw new DaoException(sqlException);
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RECEIPT)){
            preparedStatement.setLong(1, id);
            int count = preparedStatement.executeUpdate();
            return count == 1;
        } catch (SQLException sqlException) {
            logger.error("error in deleting a receipt by id ", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public List<Receipt> findAllReceiptsByOneDoctor(Long doctor_id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_RECEIPTS_BY_DOCTOR)){
            preparedStatement.setLong(1, doctor_id);
            return getReceiptListFromResultSet(preparedStatement);
        } catch (SQLException sqlException) {
            logger.error("error in finding all receipts by a doctor ", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public List<Receipt> findAllReceiptToOneClient(Long client_id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_RECEIPTS_TO_CLIENT)){
            preparedStatement.setLong(1, client_id);
            return getReceiptListFromResultSet(preparedStatement);
        } catch (SQLException sqlException) {
            logger.error("error in finding all receipts assigned to a client ", sqlException);
            throw new DaoException(sqlException);
        }
    }

    private List<Receipt> getReceiptListFromResultSet(PreparedStatement preparedStatement) throws SQLException {
        List<Receipt> receipts = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Optional<Receipt> receipt = receiptMapper.map(resultSet);
            receipt.ifPresent(receipts::add);
        }
        return receipts;
    }
}
