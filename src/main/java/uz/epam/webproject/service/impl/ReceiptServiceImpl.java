package uz.epam.webproject.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.dao.ReceiptDao;
import uz.epam.webproject.dao.exception.DaoException;
import uz.epam.webproject.dao.impl.ReceiptDaoImpl;
import uz.epam.webproject.entity.receipt.Receipt;
import uz.epam.webproject.service.ReceiptService;
import uz.epam.webproject.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class ReceiptServiceImpl implements ReceiptService {
    private static final Logger logger = LogManager.getLogger();

    private static ReceiptServiceImpl instance;
    private final ReceiptDao receiptDao = ReceiptDaoImpl.getInstance();

    public static ReceiptServiceImpl getInstance() {
        if (instance == null){
            return instance = new ReceiptServiceImpl();
        }
        return instance;
    }

    private ReceiptServiceImpl() {
    }

    @Override
    public List<Receipt> findAll() throws ServiceException {
        try {
            return receiptDao.findAll();
        } catch (DaoException e) {
            logger.error("error in finding all receipts in service layer", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean addReceipt(Receipt receipt) throws ServiceException {
        try {
            return receiptDao.addEntity(receipt);
        } catch (DaoException e) {
            logger.error("error in adding a new receipt in service layer", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Receipt> findById(Long id) throws ServiceException {
        try {
            return receiptDao.findById(id);
        } catch (DaoException e) {
            logger.error("error in finding a receipt by id in service layer", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        try {
            return receiptDao.delete(id);
        } catch (DaoException e) {
            logger.error("error in deleting a receipt in service layer", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Receipt> findAllReceiptsByOneDoctor(Long doctor_id) throws ServiceException {
        try {
            return receiptDao.findAllReceiptsByOneDoctor(doctor_id);
        } catch (DaoException e) {
            logger.error("error in finding all receipts by one doctor in service layer", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Receipt> findAllReceiptToOneClient(Long client_id) throws ServiceException {
        try {
            return receiptDao.findAllReceiptToOneClient(client_id);
        } catch (DaoException e) {
            logger.error("error in finding all receipts assigned to a client in service layer", e);
            throw new ServiceException(e);
        }
    }
}
