package uz.epam.webproject.validator;

import uz.epam.webproject.entity.medicine.Medicine;

public interface MedicineValidator {

    boolean checkTitle(String title);

    boolean checkPrice(double price);

    boolean checkDescription(String description);

    boolean checkMedicine(Medicine medicine);

}
