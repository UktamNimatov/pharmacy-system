package uz.epam.webproject.service;

import uz.epam.webproject.dao.exception.DaoException;
import uz.epam.webproject.entity.medicine.Medicine;
import uz.epam.webproject.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface MedicineService {

    List<Medicine> findAll() throws ServiceException;

    boolean addEntity(Medicine medicine) throws ServiceException;

    Optional<Medicine> findById(Long id) throws ServiceException;

    boolean delete(Long id) throws ServiceException;

    List<Medicine> findMedicineByQuery(String searchQuery) throws ServiceException;

    List<Medicine> findAllMedicineWithPrescription() throws ServiceException;

    List<Medicine> findAllMedicineWithoutPrescription() throws ServiceException;

    boolean updateMedicine(Medicine medicine) throws ServiceException;
}
