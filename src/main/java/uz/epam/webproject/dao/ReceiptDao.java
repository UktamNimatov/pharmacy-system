package uz.epam.webproject.dao;

import uz.epam.webproject.dao.exception.DaoException;
import uz.epam.webproject.entity.receipt.Receipt;

import java.util.List;
import java.util.Optional;

public interface ReceiptDao extends EntityDao<Receipt> {

    @Override
    List<Receipt> findAll() throws DaoException;

    @Override
    boolean addEntity(Receipt entity) throws DaoException;

    @Override
    Optional<Receipt> findById(Long id) throws DaoException;

    @Override
    boolean delete(Long id) throws DaoException;

    List<Receipt> findAllReceiptsByOneDoctor(Long doctor_id) throws DaoException;

    List<Receipt> findAllReceiptToOneClient(Long client_id) throws DaoException;

}
