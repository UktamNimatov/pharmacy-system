package uz.epam.webproject.entity.list;

import uz.epam.webproject.entity.AbstractEntity;

import java.io.Serial;

public class OrderMedicineList implements AbstractEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    private long id;
    private long order_id;
    private long medicine_id;
    private int medicine_quantity;
    private double medicine_price;

    public OrderMedicineList() {
    }

    public OrderMedicineList(long order_id, long medicine_id, int medicine_quantity, double medicine_price) {
        this.order_id = order_id;
        this.medicine_id = medicine_id;
        this.medicine_quantity = medicine_quantity;
        this.medicine_price = medicine_price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
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

    public double getMedicine_price() {
        return medicine_price;
    }

    public void setMedicine_price(double medicine_price) {
        this.medicine_price = medicine_price;
    }
}
