package uz.epam.webproject.dao.mapper.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.dao.mapper.ColumnName;
import uz.epam.webproject.dao.mapper.EntityMapper;
import uz.epam.webproject.entity.list.OrderMedicineList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class OrderMedicineListMapper implements EntityMapper<OrderMedicineList> {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Optional<OrderMedicineList> map(ResultSet resultSet) {
        try {
            OrderMedicineList orderMedicineList = new OrderMedicineList();
            orderMedicineList.setId(resultSet.getLong(ColumnName.ID));
            orderMedicineList.setMedicine_id(resultSet.getLong(ColumnName.MEDICINE_ID));
            orderMedicineList.setMedicine_quantity(resultSet.getInt(ColumnName.MEDICINE_QUANTITY));
            orderMedicineList.setMedicine_price(resultSet.getDouble(ColumnName.MEDICINE_PRICE));
            return Optional.of(orderMedicineList);
        } catch (SQLException sqlException) {
            logger.error("error in mapping resultSet into an object (OrderMedicineList)", sqlException);
        }
        return Optional.empty();
    }
}
