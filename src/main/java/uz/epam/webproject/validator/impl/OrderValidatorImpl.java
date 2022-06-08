package uz.epam.webproject.validator.impl;

import uz.epam.webproject.entity.order.Order;
import uz.epam.webproject.validator.OrderValidator;

import java.sql.Timestamp;

public class OrderValidatorImpl implements OrderValidator {

    private static final OrderValidator instance = new OrderValidatorImpl();
    public static OrderValidator getInstance() {
        return instance;
    }

    private OrderValidatorImpl() {
    }

    @Override
    public boolean checkUserId(long userId) {
        return false;
    }

    @Override
    public boolean checkStatus(String orderStatus) {
        return false;
    }

    @Override
    public boolean checkOrderedTime(Timestamp orderedTime) {
        return false;
    }
}
