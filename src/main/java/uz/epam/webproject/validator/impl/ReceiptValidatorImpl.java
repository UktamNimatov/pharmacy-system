package uz.epam.webproject.validator.impl;

import uz.epam.webproject.validator.ReceiptValidator;
import uz.epam.webproject.validator.UserValidator;

public class ReceiptValidatorImpl implements ReceiptValidator {

    private static final ReceiptValidator instance = new ReceiptValidatorImpl();
    public static ReceiptValidator getInstance(){
        return instance;
    }

    private ReceiptValidatorImpl() {
    }

    @Override
    public boolean checkDoctorId(long doctorId) {
        return false;
    }

    @Override
    public boolean checkClientId(long clientId) {
        return false;
    }

    @Override
    public boolean checkMedicineId(long medicineId) {
        return false;
    }
}
