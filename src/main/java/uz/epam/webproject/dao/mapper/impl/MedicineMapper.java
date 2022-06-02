package uz.epam.webproject.dao.mapper.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.dao.exception.DaoException;
import uz.epam.webproject.dao.mapper.ColumnName;
import uz.epam.webproject.dao.mapper.EntityMapper;
import uz.epam.webproject.entity.medicine.Medicine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MedicineMapper implements EntityMapper<Medicine> {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Optional<Medicine> map(ResultSet resultSet) throws DaoException {
        try {
            Medicine medicine = new Medicine();
            medicine.setId(resultSet.getInt(ColumnName.ID));
            medicine.setTitle(resultSet.getString(ColumnName.TITLE));
            medicine.setPrice(resultSet.getDouble(ColumnName.PRICE));
            medicine.setDescription(resultSet.getString(ColumnName.DESCRIPTION));
            medicine.setWithPrescription(resultSet.getBoolean(ColumnName.WITH_PRESCRIPTION));
            return Optional.of(medicine);
        } catch (SQLException sqlException) {
            logger.error("error in mapping resultSet into an object", sqlException);
//            return Optional.empty(); //fixme
            throw new DaoException(sqlException);
        }
    }
}
