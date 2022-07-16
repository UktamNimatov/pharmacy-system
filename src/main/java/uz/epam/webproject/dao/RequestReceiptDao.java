package uz.epam.webproject.dao;

import uz.epam.webproject.dao.exception.DaoException;
import uz.epam.webproject.entity.receipt.RequestReceipt;

import java.util.List;
import java.util.Optional;

public interface RequestReceiptDao extends EntityDao<RequestReceipt> {

    @Override
    List<RequestReceipt> findAll() throws DaoException;

    @Override
    boolean addEntity(RequestReceipt entity) throws DaoException;

    @Override
    Optional<RequestReceipt> findById(Long id) throws DaoException;

    @Override
    boolean delete(Long id) throws DaoException;

    List<RequestReceipt> findRequestReceiptsByUserId(Long userId) throws DaoException;

    void changeStatus(Long id) throws DaoException;
}
