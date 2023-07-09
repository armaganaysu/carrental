import javax.swing.*;

public class Request {
    private String id;
    private String brand;
    private String model;
    private String office_id;
    private String customer_id;
    private String car_type;
    private Date start_date;
    private Date end_date;
    private int min;
    private int max;

    public Request(String office_id, String customer_id, String brand, String model, String car_type, Date start_date, Date end_date) {
        this.brand = brand;
        this.model = model;
        this.office_id = office_id;
        this.customer_id = customer_id;
        this.car_type = car_type;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public Request(String office_id, String car_type){
        this.office_id=office_id;
        this.car_type=car_type;
    }

    public Request(int min, int max){
        this.office_id = "-1";
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOffice_id() {
        return office_id;
    }

    public void setOffice_id(String office_id) {
        this.office_id = office_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String display(){
        if (brand != null) {
            return String.format("CarRequest%s;%s;%s;%s;%s;%s;%s;%s", id, office_id, customer_id, brand, model, car_type, start_date.displayDate(), end_date.displayDate());
        }
        else {
            if (max != 0 ) {
                return String.format("CarRequest%s;%d;%d -random", id, min, max);

            }
            else {
                return String.format("CarRequest%s;%s;%s -random", id, office_id, car_type);

            }
        }

    }
}
