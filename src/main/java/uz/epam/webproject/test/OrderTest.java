package uz.epam.webproject.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.dao.mapper.ColumnName;
import uz.epam.webproject.entity.order.Order;
import uz.epam.webproject.entity.order.OrderStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderTest {
    private static final Logger logger = LogManager.getLogger();
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/web_project?useSSL=false&serverTimeZone=UTC";
    private static final String USER = "epamstudent";
    private static final String PASSWORD = "epam@student";
    private static final String SELECT_ALL_ORDERS = "SELECT order.id, order.user_id, order.status, order.ordered_time, order.confirmed_time, order.completed_time, order.canceled_time FROM web_project.order";
//    private static final String SELECT_FROM_ORDERS = "SELECT * FROM users";

    public static void main(String[] args) {
//                List<Order> orderList = new ArrayList<>();
//        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
//             Statement statement = connection.createStatement()){
//            try (ResultSet resultSet = statement.executeQuery(SELECT_ALL_ORDERS)) {
//                while (resultSet.next()) {
//                    Optional<Order> order = map(resultSet);
//                    order.ifPresent(orderList::add);
//                }
//            }
//        } catch (SQLException sqlException) {
//            logger.info("error in finding", sqlException);
//        }
//        for (Order order: orderList) {
//            System.out.println(order.toString());
//        }
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS)){
            System.out.println(connection.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                System.out.println("asdasd");
            }else {
                System.out.println("asdasdasd");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static Optional<Order> map(ResultSet resultSet) {
        try {
            Order order = new Order();
            order.setId(resultSet.getLong(ColumnName.ID));
            order.setUserId(resultSet.getLong(ColumnName.USER_ID));
            order.setStatus(OrderStatus.valueOf(resultSet.getString(ColumnName.STATUS).toUpperCase()));
            order.setOrderedTime(resultSet.getTimestamp(ColumnName.ORDERED_TIME));
            order.setConfirmedTime(resultSet.getTimestamp(ColumnName.CONFIRMED_TIME));
            order.setCompletedTime(resultSet.getTimestamp(ColumnName.COMPLETED_TIME));
            order.setCanceledTime(resultSet.getTimestamp(ColumnName.CANCELED_TIME));
            return Optional.of(order);
        } catch (SQLException sqlException) {
            logger.error("error in mapping resultSet into an object", sqlException);
        }
        return Optional.empty();
    }
}
