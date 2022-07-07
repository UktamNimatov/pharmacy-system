package uz.epam.webproject.validator.impl;

import uz.epam.webproject.entity.medicine.Medicine;
import uz.epam.webproject.entity.user.User;
import uz.epam.webproject.validator.MedicineValidator;
import uz.epam.webproject.validator.UserValidator;

import java.util.regex.Pattern;

public class MedicineValidatorImpl implements MedicineValidator {
    private static final String INCORRECT_VALUE_PARAMETER = " - incorrect value";
    private static final String TITLE_REGEX = "[\\p{Alpha}]{3,29}";
    private static final String PRICE_REGEX = "^\\d{0,8}(\\.\\d{1,4})?$";

    private static final MedicineValidator instance = new MedicineValidatorImpl();

    public static MedicineValidator getInstance() {
        return instance;
    }

    private MedicineValidatorImpl() {
    }

    @Override
    public boolean checkTitle(String title) {
        return (title != null && Pattern.matches(TITLE_REGEX, title));
    }

    @Override
    public boolean checkPrice(double price) {
        return (price > 0.0 && Pattern.matches(PRICE_REGEX, String.valueOf(price)));
    }

    @Override
    public boolean checkDescription(String description) {
        if (description.contains("<script>") || description.contains("</script>")) {
            String newDescription;
            newDescription = description.replaceAll("<script>", "");
            newDescription = newDescription.replaceAll("</script>", "");
            return description.equalsIgnoreCase(newDescription);
        }
        return true;
    }

    @Override
    public boolean checkMedicine(Medicine medicine) {
        boolean isValid = true;
        if (!checkTitle(medicine.getTitle())) {
            medicine.setTitle(medicine.getTitle() + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!checkPrice(medicine.getPrice())) {
            isValid = false;
        }
        if (!checkDescription(medicine.getDescription())) {
            medicine.setDescription(medicine.getDescription() + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        return isValid;
    }

}
