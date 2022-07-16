package uz.epam.webproject.dao.mapper.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.dao.mapper.ColumnName;
import uz.epam.webproject.dao.mapper.EntityMapper;
import uz.epam.webproject.entity.receipt.Receipt;
import uz.epam.webproject.entity.receipt.RequestReceipt;
import uz.epam.webproject.entity.receipt.RequestReceiptStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class RequestReceiptMapper implements EntityMapper<RequestReceipt> {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Optional<RequestReceipt> map(ResultSet resultSet) {
        try {
            RequestReceipt requestReceipt = new RequestReceipt();
            requestReceipt.setId(resultSet.getLong(ColumnName.ID));
            requestReceipt.setIllnessDescription(resultSet.getString(ColumnName.ILLNESS_DESCRIPTION));
            requestReceipt.setUserId(resultSet.getLong(ColumnName.USER_ID));
            requestReceipt.setStatus(RequestReceiptStatus.valueOf(resultSet.getString(ColumnName.STATUS).toUpperCase()));
            requestReceipt.setOrderedTime(resultSet.getTimestamp(ColumnName.ORDERED_TIME));
//            requestReceipt.setRespondedTime(resultSet.getTimestamp(ColumnName.RESPONDED_TIME));
            return Optional.of(requestReceipt);
        } catch (SQLException sqlException) {
            logger.error("error in mapping resultSet into an object", sqlException);
        }
        return Optional.empty();
    }
}
