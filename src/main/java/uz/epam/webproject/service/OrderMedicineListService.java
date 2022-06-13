package uz.epam.webproject.service;

import uz.epam.webproject.dao.exception.DaoException;
import uz.epam.webproject.entity.list.OrderMedicineList;
import uz.epam.webproject.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface OrderMedicineListService {

    List<OrderMedicineList> findAll() throws ServiceException;

    boolean addOrderMedicineList(OrderMedicineList orderMedicineList) throws ServiceException;

    Optional<OrderMedicineList> findById(Long id) throws ServiceException;

    boolean delete(Long id) throws ServiceException;

    List<OrderMedicineList> findAllMedicineOfOneOrder(Long orderId) throws ServiceException;

    boolean deleteByOrderId(Long orderId) throws ServiceException;

}
