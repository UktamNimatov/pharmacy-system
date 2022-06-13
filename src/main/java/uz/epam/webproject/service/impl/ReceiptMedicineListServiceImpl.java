package uz.epam.webproject.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.dao.ReceiptMedicineListDao;
import uz.epam.webproject.dao.exception.DaoException;
import uz.epam.webproject.dao.impl.ReceiptMedicineListDaoImpl;
import uz.epam.webproject.entity.list.ReceiptMedicineList;
import uz.epam.webproject.service.ReceiptMedicineListService;
import uz.epam.webproject.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class ReceiptMedicineListServiceImpl implements ReceiptMedicineListService {
    private static final Logger logger = LogManager.getLogger();

    private final ReceiptMedicineListDao receiptMedicineListDao = ReceiptMedicineListDaoImpl.getInstance();

    private static ReceiptMedicineListServiceImpl instance;
    public static ReceiptMedicineListServiceImpl getInstance(){
        if (instance == null){
            instance = new ReceiptMedicineListServiceImpl();
        }
        return instance;
    }

    @Override
    public List<ReceiptMedicineList> findAll() throws ServiceException {
        try {
            return receiptMedicineListDao.findAll();
        } catch (DaoException e) {
            logger.error("error in finding all receipt medicine lists (in service layer)", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean addReceiptMedicineList(ReceiptMedicineList receiptMedicineList) throws ServiceException {
        try {
            return receiptMedicineListDao.addEntity(receiptMedicineList);
        } catch (DaoException e) {
            logger.error("error in adding a new receipt medicine list (in service layer)", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<ReceiptMedicineList> findById(Long id) throws ServiceException {
        try {
            return receiptMedicineListDao.findById(id);
        } catch (DaoException e) {
            logger.error("error in finding receipt medicine list by id (in service layer)", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        try {
            return receiptMedicineListDao.delete(id);
        } catch (DaoException e) {
            logger.error("error in deleting receipt medicine list by id (in service layer)", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ReceiptMedicineList> findAllMedicineOfOneReceipt(Long receiptId) throws ServiceException {
        try {
            return receiptMedicineListDao.findAllMedicineOfOneReceipt(receiptId);
        } catch (DaoException e) {
            logger.error("error in finding all receipt medicine lists of one receipt (in service layer)", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteByReceiptId(Long receiptId) throws ServiceException {
        try {
            return receiptMedicineListDao.deleteByReceiptId(receiptId);
        } catch (DaoException e) {
            logger.error("error in deleting all receipt medicine lists of one receipt (in service layer)", e);
            throw new ServiceException(e);
        }
    }
}
