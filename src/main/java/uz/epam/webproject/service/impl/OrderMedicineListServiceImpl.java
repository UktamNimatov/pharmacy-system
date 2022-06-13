package uz.epam.webproject.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.dao.OrderMedicineListDao;
import uz.epam.webproject.dao.exception.DaoException;
import uz.epam.webproject.dao.impl.OrderMedicineListDaoImpl;
import uz.epam.webproject.entity.list.OrderMedicineList;
import uz.epam.webproject.service.OrderMedicineListService;
import uz.epam.webproject.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class OrderMedicineListServiceImpl implements OrderMedicineListService {
    private static final Logger logger = LogManager.getLogger();

    private final OrderMedicineListDao orderMedicineListDao = OrderMedicineListDaoImpl.getInstance();

    private static OrderMedicineListServiceImpl instance;
    public static OrderMedicineListServiceImpl getInstance(){
        if (instance == null){
            instance = new OrderMedicineListServiceImpl();
        }
        return instance;
    }

    private OrderMedicineListServiceImpl() {
    }

    @Override
    public List<OrderMedicineList> findAll() throws ServiceException {
        try {
            return orderMedicineListDao.findAll();
        } catch (DaoException e) {
            logger.error("error in finding all order medicine lists ", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean addOrderMedicineList(OrderMedicineList orderMedicineList) throws ServiceException {
        try {
            return orderMedicineListDao.addEntity(orderMedicineList);
        } catch (DaoException e) {
            logger.error("error in add a new order medicine list ", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<OrderMedicineList> findById(Long id) throws ServiceException {
        try {
            return orderMedicineListDao.findById(id);
        } catch (DaoException e) {
            logger.error("error in finding a order medicine list by id ", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        try {
            return orderMedicineListDao.delete(id);
        } catch (DaoException e) {
            logger.error("error in deleting a order medicine list by id ", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<OrderMedicineList> findAllMedicineOfOneOrder(Long orderId) throws ServiceException {
        try {
            return orderMedicineListDao.findAllMedicineOfOneOrder(orderId);
        } catch (DaoException e) {
            logger.error("error in finding all order medicine lists by one order ", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteByOrderId(Long orderId) throws ServiceException {
        try {
            return orderMedicineListDao.deleteByOrderId(orderId);
        } catch (DaoException e) {
            logger.error("error in deleting all order medicine lists by one order ", e);
            throw new ServiceException(e);
        }
    }
}
