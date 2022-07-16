package uz.epam.webproject.service;

import uz.epam.webproject.dao.exception.DaoException;
import uz.epam.webproject.entity.receipt.RequestReceipt;
import uz.epam.webproject.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface RequestReceiptService {

    List<RequestReceipt> findAll() throws ServiceException;

    boolean addEntity(RequestReceipt requestReceipt) throws ServiceException;

    Optional<RequestReceipt> findById(Long id) throws ServiceException;

    boolean delete(Long id) throws ServiceException;

    List<RequestReceipt> findRequestReceiptsByUserId(Long userId) throws ServiceException;

    void changeStatus(Long id) throws ServiceException;
}
