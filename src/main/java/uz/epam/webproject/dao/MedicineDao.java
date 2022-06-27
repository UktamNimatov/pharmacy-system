package uz.epam.webproject.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.webproject.dao.exception.DaoException;
import uz.epam.webproject.entity.medicine.Medicine;

import java.util.List;
import java.util.Optional;

public interface MedicineDao extends EntityDao<Medicine>{

    @Override
    List<Medicine> findAll() throws DaoException;

    @Override
    boolean addEntity(Medicine medicine) throws DaoException;

    @Override
    Optional<Medicine> findById(Long id) throws DaoException;

    @Override
    boolean delete(Long id) throws DaoException;

    List<Medicine> findMedicineByQuery(String searchQuery) throws DaoException;

    List<Medicine> findAllMedicineWithPrescription() throws DaoException;

    List<Medicine> findAllMedicineWithoutPrescription() throws DaoException;

    boolean updateMedicine(Medicine medicine) throws DaoException;

}
