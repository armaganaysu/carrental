public class Car {
    private String id;
    private String display_id;
    private String brand;
    private String model;
    private String type;
    private String office_id;
    private int total_kilometer;
    private int daily_kilometer;
    private double daily_expense;
    private final double expense_constant;
    private double total_expense;
    private boolean available;
    private Date end_of_contract;
    private final int  maintenance;
    private int total_maintenance;
    private int income;
    private int total_income;
    private int rental_period;
    private int count_rent;



    public Car( String brand, String model, String type, int kilometer, String office_id) {
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.total_kilometer = kilometer;
        this.office_id = office_id;
        this.available = true;
        if (this.type.equals("economy")) {
            this.maintenance = 20;
            this.expense_constant = 0.05;
        } else if (this.type.equals("sports")) {
            this.maintenance = 70;
            this.expense_constant = 0.1;
        } else {
            this.maintenance = 120;
            this.expense_constant = 0.15;
        }
    }

    public Date getEnd_of_contract() {
        return end_of_contract;
    }

    public void setEnd_of_contract(Date end_of_contract) {
        this.end_of_contract = end_of_contract;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotal_kilometer() {
        return total_kilometer;
    }

    public void setTotal_kilometer(int total_kilometer) {
        this.total_kilometer = total_kilometer;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getDisplay_id() {
        return display_id;
    }

    public void setDisplay_id(String display_id) {
        this.display_id = display_id;
    }

    public String getOffice_id() {
        return office_id;
    }

    public void setOffice_id(String office_id) {
        this.office_id = office_id;
    }

    public int getRental_period() {
        return rental_period;
    }

    public void setRental_period(int rental_period) {
        this.rental_period = rental_period;
    }

    public int getCount_rent() {
        return count_rent;
    }

    public void setCount_rent(int count_rent) {
        this.count_rent = count_rent;
    }

    public String display(){
        String is_available;
        if (available) {
            is_available = "available";
        } else {
            is_available = "not available";
        }
        return String.format("Car%s;%s;%s;%s;%d;%s -%s",display_id, brand, model, type, total_kilometer, office_id, is_available);
    }

    public String display_statistic(){
        return String.format("Car%s;%s;%s",display_id, brand, model);
    }


    public int getDaily_kilometer() {
        return daily_kilometer;
    }

    public void setDaily_kilometer(int daily_kilometer) {
        this.daily_kilometer = daily_kilometer;
    }

    public int getMaintenance() {
        return maintenance;
    }


    public int getTotal_maintenance() {
        return total_maintenance;
    }

    public void setTotal_maintenance(int total_maintenance) {
        this.total_maintenance = total_maintenance;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getTotal_income() {
        return total_income;
    }

    public void setTotal_income(int total_income) {
        this.total_income = total_income;
    }

    public double getDaily_expense() {
        return daily_expense;
    }

    public void setDaily_expense(double daily_expense) {
        this.daily_expense = daily_expense;
    }

    public double getTotal_expense() {
        return total_expense;
    }

    public void setTotal_expense(double total_expense) {
        this.total_expense = total_expense;
    }

    public double getExpense_constant() {
        return expense_constant;
    }


}
