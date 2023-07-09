public class Statistics {
    String office_id;
    Car car;
    Employee employee;
    Customer customer;
    Date start_date;
    Date end_date;
    private double rental_period;


    public Statistics(String office, Car car,  Employee employee, Customer customer, Date start_date, Date end_date, double rental_period){
        this.office_id = office;
        this.car = car;
        this.employee = employee;
        this.customer = customer;
        this.start_date=start_date;
        this.end_date = end_date;
        this.rental_period=rental_period;
    }

    public double getRental_period() {
        return rental_period;
    }

    public void setRental_period(double rental_period) {
        this.rental_period = rental_period;
    }

    public String getOffice_id() {
        return office_id;
    }

    public void setOffice_id(String office_id) {
        this.office_id = office_id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
}
