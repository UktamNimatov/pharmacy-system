package uz.epam.webproject.dao.mapper.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.dao.exception.DaoException;
import uz.epam.webproject.dao.mapper.ColumnName;
import uz.epam.webproject.dao.mapper.EntityMapper;
import uz.epam.webproject.entity.medicine.Medicine;
import uz.epam.webproject.entity.receipt.Receipt;
import uz.epam.webproject.entity.user.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Optional;

public class ReceiptMapper implements EntityMapper<Receipt> {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Optional<Receipt> map(ResultSet resultSet) {
        try {
            Receipt receipt = new Receipt();
            receipt.setId(resultSet.getInt(ColumnName.ID));
            receipt.setFromUserId(resultSet.getInt(ColumnName.DOCTOR_USER_ID));
            receipt.setToUserId(resultSet.getInt(ColumnName.CLIENT_USER_ID));
            receipt.setUsage(resultSet.getString(ColumnName.USAGE));
            receipt.setAssignedTime(resultSet.getTimestamp(ColumnName.ASSIGNED_TIME));
            return Optional.of(receipt);
        } catch (SQLException sqlException) {
            logger.error("error in mapping resultSet into an object", sqlException);
        }
        return Optional.empty();
    }
}
