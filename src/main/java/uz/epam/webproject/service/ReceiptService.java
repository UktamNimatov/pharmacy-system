package uz.epam.webproject.service;

import uz.epam.webproject.dao.exception.DaoException;
import uz.epam.webproject.entity.receipt.Receipt;
import uz.epam.webproject.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface ReceiptService {

    List<Receipt> findAll() throws ServiceException;

    boolean addReceipt(Receipt receipt) throws ServiceException;

    Optional<Receipt> findById(Long id) throws ServiceException;

    boolean delete(Long id) throws ServiceException;

    List<Receipt> findAllReceiptsByOneDoctor(Long doctor_id) throws ServiceException;

    List<Receipt> findAllReceiptToOneClient(Long client_id) throws ServiceException;

}
