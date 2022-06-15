package uz.epam.webproject.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.dao.MedicineDao;
import uz.epam.webproject.dao.exception.DaoException;
import uz.epam.webproject.dao.mapper.impl.MedicineMapper;
import uz.epam.webproject.entity.medicine.Medicine;
import uz.epam.webproject.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MedicineDaoImpl implements MedicineDao {
    private static final Logger logger = LogManager.getLogger();
    private final MedicineMapper medicineMapper = new MedicineMapper();

    private static final String ADD_MEDICINE = "INSERT INTO medicine (title, price, description, with_prescription) values (?, ?, ?, ?)";
    private static final String SELECT_ALL_MEDICINE = "SELECT medicine.id, medicine.title, medicine.price, medicine.description, medicine.with_prescription FROM medicine";
    private static final String FIND_BY_ID = "SELECT medicine.id, medicine.title, medicine.price, medicine.description, medicine.with_prescription FROM medicine WHERE medicine.id = ?";
    private static final String DELETE_MEDICINE = "DELETE FROM medicine WHERE medicine.id = ?";
    private static final String MEDICINE_SEARCH_QUERY = "SELECT medicine.id, medicine.title, medicine.price, medicine.description, medicine.with_prescription FROM medicine WHERE medicine.title LIKE CONCAT ('%', ?, '%') OR medicine.description LIKE CONCAT ('%', ?, '%')";
    private static final String MEDICINE_PRESCRIPTION = "SELECT medicine.id, medicine.title, medicine.price, medicine.description, medicine.with_prescription FROM medicine WHERE medicine.with_prescription = ?";

    private static MedicineDaoImpl instance;
    public static MedicineDaoImpl getInstance(){
        if (instance == null){
            instance = new MedicineDaoImpl();
        }
        return instance;
    }

    private MedicineDaoImpl() {
    }

    @Override
    public List<Medicine> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MEDICINE)){
            return getMedicinesFromResultSet(preparedStatement);
        } catch (SQLException sqlException) {
            logger.error("error in getting all medicine ", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public boolean addEntity(Medicine medicine) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_MEDICINE)){
            preparedStatement.setString(1, medicine.getTitle());
            preparedStatement.setDouble(2, medicine.getPrice());
            preparedStatement.setString(3, medicine.getDescription());
            preparedStatement.setBoolean(4, medicine.isWithPrescription());
            int count = preparedStatement.executeUpdate();
            return count == 1;
        } catch (SQLException sqlException) {
            logger.error("error in adding a new medicine ", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public Optional<Medicine> findById(Long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)){
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Optional<Medicine> medicine = medicineMapper.map(resultSet);
                    return medicine;
                }
            }
        } catch (SQLException sqlException) {
            logger.error("error in finding a medicine by id ", sqlException);
            throw new DaoException(sqlException);
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MEDICINE)){
            preparedStatement.setLong(1, id);
            int count = preparedStatement.executeUpdate();
            return count == 1;
        } catch (SQLException sqlException) {
            logger.error("error in deleting a medicine by id ", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public List<Medicine> findMedicineByQuery(String searchQuery) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(MEDICINE_SEARCH_QUERY)){
            preparedStatement.setString(1, searchQuery);
            preparedStatement.setString(2, searchQuery);
            return getMedicinesFromResultSet(preparedStatement);
        } catch (SQLException sqlException) {
            logger.error("error in finding medicine by search query", sqlException);
            throw new DaoException(sqlException);
        }
    }


    @Override
    public List<Medicine> findAllMedicineWithPrescription() throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(MEDICINE_PRESCRIPTION)){
            preparedStatement.setBoolean(1, true);
            return getMedicinesFromResultSet(preparedStatement);
        } catch (SQLException sqlException) {
            logger.error("error in finding all medicine with prescription", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public List<Medicine> findAllMedicineWithoutPrescription() throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(MEDICINE_PRESCRIPTION)){
            preparedStatement.setBoolean(1, false);
            return getMedicinesFromResultSet(preparedStatement);
        } catch (SQLException sqlException) {
            logger.error("error in finding all medicine with prescription", sqlException);
            throw new DaoException(sqlException);
        }
    }

    private List<Medicine> getMedicinesFromResultSet(PreparedStatement preparedStatement) throws SQLException {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Medicine> medicineList = new ArrayList<>();
            while (resultSet.next()) {
                Optional<Medicine> medicine = medicineMapper.map(resultSet);
                medicine.ifPresent(medicineList::add);
            }
            return medicineList;
        }
    }
}
