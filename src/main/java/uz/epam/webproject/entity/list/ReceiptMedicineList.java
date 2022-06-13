package uz.epam.webproject.entity.list;

import uz.epam.webproject.entity.AbstractEntity;

import java.io.Serial;

public class ReceiptMedicineList implements AbstractEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    private long id;
    private long receipt_id;
    private long medicine_id;
    private int medicine_quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getReceipt_id() {
        return receipt_id;
    }

    public void setReceipt_id(long receipt_id) {
        this.receipt_id = receipt_id;
    }

    public long getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(long medicine_id) {
        this.medicine_id = medicine_id;
    }

    public int getMedicine_quantity() {
        return medicine_quantity;
    }

    public void setMedicine_quantity(int medicine_quantity) {
        this.medicine_quantity = medicine_quantity;
    }


}
