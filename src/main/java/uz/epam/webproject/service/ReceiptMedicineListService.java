package uz.epam.webproject.service;

import uz.epam.webproject.dao.exception.DaoException;
import uz.epam.webproject.entity.list.ReceiptMedicineList;
import uz.epam.webproject.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface ReceiptMedicineListService {

    List<ReceiptMedicineList> findAll() throws ServiceException;

    boolean addReceiptMedicineList(ReceiptMedicineList receiptMedicineList) throws ServiceException;

    Optional<ReceiptMedicineList> findById(Long id) throws ServiceException;

    boolean delete(Long id) throws ServiceException;

    List<ReceiptMedicineList> findAllMedicineOfOneReceipt(Long receiptId) throws ServiceException;

    boolean deleteByReceiptId(Long receiptId) throws ServiceException;

}
