package uz.epam.webproject.validator.impl;

import uz.epam.webproject.entity.medicine.Medicine;
import uz.epam.webproject.entity.user.User;
import uz.epam.webproject.validator.MedicineValidator;
import uz.epam.webproject.validator.UserValidator;

public class MedicineValidatorImpl implements MedicineValidator {

    private static final MedicineValidator instance = new MedicineValidatorImpl();
    public static MedicineValidator getInstance(){
        return instance;
    }

    private MedicineValidatorImpl() {
    }

    @Override
    public boolean checkTitle(String title) {
        return false;
    }

    @Override
    public boolean checkPrice(double price) {
        return false;
    }

    @Override
    public boolean checkDescription(String description) {
        return false;
    }

    @Override
    public boolean checkMedicine(Medicine medicine) {
        return true;
    }

}
