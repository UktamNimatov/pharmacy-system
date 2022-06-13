package uz.epam.webproject.dao;

import uz.epam.webproject.dao.EntityDao;
import uz.epam.webproject.dao.exception.DaoException;
import uz.epam.webproject.entity.list.OrderMedicineList;

import java.util.List;
import java.util.Optional;

public interface OrderMedicineListDao extends EntityDao<OrderMedicineList> {

    @Override
    List<OrderMedicineList> findAll() throws DaoException;

    @Override
    boolean addEntity(OrderMedicineList entity) throws DaoException;

    @Override
    Optional<OrderMedicineList> findById(Long id) throws DaoException;

    @Override
    boolean delete(Long id) throws DaoException;

    List<OrderMedicineList> findAllMedicineOfOneOrder(Long orderId) throws DaoException;

    boolean deleteByOrderId(Long orderId) throws DaoException;

}
