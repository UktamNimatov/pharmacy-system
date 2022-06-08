package uz.epam.webproject.validator;

public interface ReceiptValidator {

    boolean checkDoctorId(long doctorId);

    boolean checkClientId(long clientId);

    boolean checkMedicineId(long medicineId);

}
