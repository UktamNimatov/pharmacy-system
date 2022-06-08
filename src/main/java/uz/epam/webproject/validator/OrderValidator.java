package uz.epam.webproject.validator;

import uz.epam.webproject.entity.order.OrderStatus;

import java.sql.Timestamp;

public interface OrderValidator {

    boolean checkUserId(long userId);

    boolean checkStatus(String orderStatus);

    boolean checkOrderedTime(Timestamp orderedTime);
}
