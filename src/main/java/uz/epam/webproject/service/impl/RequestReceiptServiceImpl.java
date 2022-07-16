package uz.epam.webproject.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.dao.RequestReceiptDao;
import uz.epam.webproject.dao.UserDao;
import uz.epam.webproject.dao.exception.DaoException;
import uz.epam.webproject.dao.impl.ReceiptDaoImpl;
import uz.epam.webproject.dao.impl.RequestReceiptDaoImpl;
import uz.epam.webproject.dao.impl.UserDaoImpl;
import uz.epam.webproject.entity.receipt.RequestReceipt;
import uz.epam.webproject.service.RequestReceiptService;
import uz.epam.webproject.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class RequestReceiptServiceImpl implements RequestReceiptService {
    private static final Logger logger = LogManager.getLogger();

    private static RequestReceiptServiceImpl instance;
    private final RequestReceiptDao requestReceiptDao = RequestReceiptDaoImpl.getInstance();

    public static RequestReceiptServiceImpl getInstance() {
        if (instance == null){
            return instance = new RequestReceiptServiceImpl();
        }
        return instance;
    }

    private RequestReceiptServiceImpl() {
    }

    @Override
    public List<RequestReceipt> findAll() throws ServiceException {
        try {
            return requestReceiptDao.findAll();
        } catch (DaoException e) {
            logger.error("error in finding all receipt requests in service layer", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean addEntity(RequestReceipt requestReceipt) throws ServiceException {
        if (requestReceipt.getIllnessDescription().contains("<script>") || requestReceipt.getIllnessDescription().contains("</script>")) {
            String newIllnessDescription = "";
            newIllnessDescription = requestReceipt.getIllnessDescription().replaceAll("<script>", "");
            newIllnessDescription = newIllnessDescription.replaceAll("</script>", "");
            requestReceipt.setIllnessDescription(newIllnessDescription);
        }
        try {
            return requestReceiptDao.addEntity(requestReceipt);
        } catch (DaoException e) {
            logger.error("error in adding a receipt request in service layer", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<RequestReceipt> findById(Long id) throws ServiceException {
        try {
            return requestReceiptDao.findById(id);
        } catch (DaoException e) {
            logger.error("error in finding a receipt request by id in service layer", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        try {
            return requestReceiptDao.delete(id);
        } catch (DaoException e) {
            logger.error("error in deleting a receipt request by id in service layer", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<RequestReceipt> findRequestReceiptsByUserId(Long userId) throws ServiceException {
        try {
            return requestReceiptDao.findRequestReceiptsByUserId(userId);
        } catch (DaoException e) {
            logger.error("error in finding all receipt requests by userId in service layer", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void changeStatus(Long id) throws ServiceException {
        try {
            requestReceiptDao.changeStatus(id);
        } catch (DaoException e) {
            logger.error("error in update receipt requests by id in service layer", e);
            throw new ServiceException(e);
        }
    }
}
