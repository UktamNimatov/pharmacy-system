package uz.epam.webproject.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.dao.ReceiptMedicineListDao;
import uz.epam.webproject.dao.exception.DaoException;
import uz.epam.webproject.dao.mapper.impl.OrderMedicineListMapper;
import uz.epam.webproject.dao.mapper.impl.ReceiptMedicineListMapper;
import uz.epam.webproject.entity.list.OrderMedicineList;
import uz.epam.webproject.entity.list.ReceiptMedicineList;
import uz.epam.webproject.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReceiptMedicineListDaoImpl implements ReceiptMedicineListDao {
    private static final Logger logger = LogManager.getLogger();
    private final ReceiptMedicineListMapper receiptMedicineListMapper = new ReceiptMedicineListMapper();

    private static final String SELECT_ALL = "SELECT receipt_medicine_list.id, receipt_medicine_list.receipt_id, receipt_medicine_list.medicine_id, receipt_medicine_list.medicine_quantity FROM receipt_medicine_list";
    private static final String ADD_RECEIPT_MEDICINE_LIST = "INSERT INTO receipt_medicine_list (receipt_id, medicine_id, medicine_quantity) values (?, ?, ?)";
    private static final String SELECT_ALL_MEDICINE_OF_ONE_RECEIPT = "SELECT receipt_medicine_list.id, receipt_medicine_list.receipt_id, receipt_medicine_list.medicine_id, receipt_medicine_list.medicine_quantity FROM receipt_medicine_list WHERE receipt_medicine_list.receipt_id = ?";
    private static final String DELETE_MEDICINE_LIST = "DELETE FROM receipt_medicine_list WHERE receipt_medicine_list.id = ?";
    private static final String DELETE_MEDICINE_LIST_BY_RECEIPT_ID = "DELETE FROM receipt_medicine_list WHERE receipt_medicine_list.receipt_id = ?";

    private static ReceiptMedicineListDaoImpl instance;
    public static ReceiptMedicineListDaoImpl getInstance(){
        if (instance == null){
            instance = new ReceiptMedicineListDaoImpl();
        }
        return instance;
    }

    private ReceiptMedicineListDaoImpl() {
    }

    @Override
    public List<ReceiptMedicineList> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)){
            return getReceiptMedicineListByResultSet(preparedStatement);
        } catch (SQLException sqlException) {
            logger.error("error in finding all order medicine lists ", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public boolean addEntity(ReceiptMedicineList receiptList) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_RECEIPT_MEDICINE_LIST)){
            preparedStatement.setLong(1, receiptList.getReceipt_id());
            preparedStatement.setLong(2, receiptList.getMedicine_id());
            preparedStatement.setInt(3, receiptList.getMedicine_quantity());
            int count = preparedStatement.executeUpdate();
            return count == 1;
        } catch (SQLException sqlException) {
            logger.error("error in adding a new list of medicines", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public Optional<ReceiptMedicineList> findById(Long id) throws DaoException {
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
    public List<ReceiptMedicineList> findAllMedicineOfOneReceipt(Long receiptId) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MEDICINE_OF_ONE_RECEIPT)){
            preparedStatement.setLong(1, receiptId);
            return getReceiptMedicineListByResultSet(preparedStatement);
        } catch (SQLException sqlException) {
            logger.error("error in finding all order medicine lists ", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public boolean deleteByReceiptId(Long receiptId) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MEDICINE_LIST_BY_RECEIPT_ID)){
            preparedStatement.setLong(1, receiptId);
            int count = preparedStatement.executeUpdate();
            return count >= 1;
        } catch (SQLException sqlException) {
            logger.error("error in deleting a list of medicines by receipt id ", sqlException);
            throw new DaoException(sqlException);
        }
    }

    private List<ReceiptMedicineList> getReceiptMedicineListByResultSet(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        List<ReceiptMedicineList> receiptMedicineLists = new ArrayList<>();
        while (resultSet.next()){
            Optional<ReceiptMedicineList> receiptMedicineList = receiptMedicineListMapper.map(resultSet);
            receiptMedicineList.ifPresent(receiptMedicineLists::add);
        }
        return receiptMedicineLists;
    }

}
