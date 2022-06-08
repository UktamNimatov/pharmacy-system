package uz.epam.webproject.validator.impl;

import uz.epam.webproject.validator.ReceiptValidator;

public class ReceiptValidatorImpl implements ReceiptValidator {

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
