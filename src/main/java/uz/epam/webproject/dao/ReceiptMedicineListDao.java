package uz.epam.webproject.dao;

import uz.epam.webproject.dao.exception.DaoException;
import uz.epam.webproject.entity.list.OrderMedicineList;
import uz.epam.webproject.entity.list.ReceiptMedicineList;

import java.util.List;
import java.util.Optional;

public interface ReceiptMedicineListDao extends EntityDao<ReceiptMedicineList> {

    @Override
    List<ReceiptMedicineList> findAll() throws DaoException;

    @Override
    boolean addEntity(ReceiptMedicineList entity) throws DaoException;

    @Override
    Optional<ReceiptMedicineList> findById(Long id) throws DaoException;

    @Override
    boolean delete(Long id) throws DaoException;

    List<ReceiptMedicineList> findAllMedicineOfOneReceipt(Long receiptId) throws DaoException;

    boolean deleteByReceiptId(Long receiptId) throws DaoException;


}
