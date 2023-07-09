public class Contract{
    String id;
    String employee_id;
    String customer_id;
    String car_id;
    Date start_date;
    Date end_date;


    public Contract(String employee_id, String customer_id, String car_id, Date start_date, Date end_date) {
        this.employee_id = employee_id;
        this.customer_id = customer_id;
        this.car_id = car_id;
        this.start_date = start_date;
        this.end_date = end_date;
    }





    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
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
        return String.format("Contract%s;Employee%s;Customer%s;Car%s;%s;%s",id, employee_id, customer_id, car_id, start_date.displayDate(), end_date.displayDate());

    }
}