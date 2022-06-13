package uz.epam.webproject.dao.mapper.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.dao.mapper.ColumnName;
import uz.epam.webproject.dao.mapper.EntityMapper;
import uz.epam.webproject.entity.list.ReceiptMedicineList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ReceiptMedicineListMapper implements EntityMapper<ReceiptMedicineList> {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Optional<ReceiptMedicineList> map(ResultSet resultSet) {
        try {
            ReceiptMedicineList receiptMedicineList = new ReceiptMedicineList();
            receiptMedicineList.setId(resultSet.getLong(ColumnName.ID));
            receiptMedicineList.setReceipt_id(resultSet.getLong(ColumnName.RECEIPT_ID));
            receiptMedicineList.setMedicine_id(resultSet.getLong(ColumnName.MEDICINE_ID));
            receiptMedicineList.setMedicine_quantity(resultSet.getInt(ColumnName.MEDICINE_QUANTITY));
            return Optional.of(receiptMedicineList);
        } catch (SQLException sqlException) {
            logger.error("error in mapping resultSet into an object (ReceiptMedicineList)", sqlException);
        }
        return Optional.empty();
    }
}
