public class Office {
    private String id;
    private String phone_number;
    private String address;
    private String city;
    private Employee[] employees;
    private Car[] cars;
    private int count_employee;
    static int count_display_employee;
    private int fired_employee;
    private int current_employee;
    private int count_car;
    private int no_EmployeeCount = 0;
    private int no_CarCount = 0;
    private int no_Car_economy = 0;
    private int no_Car_luxury = 0;
    private int no_Car_sports = 0;
    private String carToBuy = "";
    private int requestCount = 0;
    static int count_display_car;
    static boolean error_noEmployee = false;
    static boolean error_noSuchEmployee = false;
    static boolean error_employeeLimit = false;
    static boolean error_noCar = false;

    public int getRequestCount() {
        return requestCount;
    }

    public int getNo_Car_economy() {
        return no_Car_economy;
    }

    public void setNo_Car_economy(int no_Car_economy) {
        this.no_Car_economy = no_Car_economy;
    }

    public int getNo_Car_luxury() {
        return no_Car_luxury;
    }

    public void setNo_Car_luxury(int no_Car_luxury) {
        this.no_Car_luxury = no_Car_luxury;
    }

    public int getNo_Car_sports() {
        return no_Car_sports;
    }

    public void setNo_Car_sports(int no_Car_sports) {
        this.no_Car_sports = no_Car_sports;
    }

    public void setRequestCount(int requestCount) {
        this.requestCount = requestCount;
    }

    public int getCount_employee() {
        return count_employee;
    }

    public int getNo_EmployeeCount() {
        return no_EmployeeCount;
    }

    public void setNo_EmployeeCount(int no_EmployeeCount) {
        this.no_EmployeeCount = no_EmployeeCount;
    }

    public int getNo_CarCount() {
        return no_CarCount;
    }

    public void setNo_CarCount(int no_CarCount) {
        this.no_CarCount = no_CarCount;
    }

    public String getCarToBuy() {
        return carToBuy;
    }

    public void setCarToBuy(String carToBuy) {
        this.carToBuy = carToBuy;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Car[] getCars() {
        return cars;
    }

    public void setCars(Car[] cars) {
        this.cars = cars;
    }

    public void setCount_employee(int count_employee) {
        this.count_employee = count_employee;
    }

    public int getCurrent_employee() {
        return current_employee;
    }

    public void setCurrent_employee(int current_employee) {
        this.current_employee = current_employee;
    }

    public int getCount_car() {
        return count_car;
    }

    public void setCount_car(int count_car) {
        this.count_car = count_car;
    }

    public Office(String id, String phone_number, String address, String city) {
        this.id = id;
        this.phone_number = phone_number;
        this.address = address;
        this.count_employee = 0;
        this.current_employee = 0;
        this.count_car = 0;
        this.employees = new Employee[100];
        this.cars = new Car[100];
        this.city = city;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }


    public void addEmployee(Employee employee){
        if (current_employee < 3) {
            employees[count_employee] = employee;
            count_employee++;
            current_employee++;
            count_display_employee++;
            employees[count_employee - 1].setId(String.format("%d", count_employee));
            employees[count_employee - 1].setDisplay_id(String.format("%d", count_display_employee));

        } else {
            error_employeeLimit = true;
        }
    }

    public void deleteEmployee(String employee_display_id){
        int employee_id = -1;
        for (int i = 0; i < count_employee; i++) {
            if (employees[i] != null && employees[i].getDisplay_id().equals(employee_display_id)) {
                employee_id = i;
            }
        }
        //&& employees[employee_id] != null
        if (employee_id != -1) {
            employees[(employee_id)] = null;
            current_employee--;
            System.out.println("\tEmployee is deleted.");
        } else {
            error_noSuchEmployee = true;
        }
        fired_employee ++;

    }

    public void addCar(Car car){
        cars[count_car] = car;
        count_car++;
        count_display_car ++;
        cars[count_car -1].setId(String.format("%d", count_car));
        cars[count_car -1].setDisplay_id(String.format("%d", count_display_car));
    }





    public String display(){
        return String.format("Office%s;%s;%s;%s", id, phone_number, address, city);
    }

    public void listEmployees() {
        if (current_employee != 0) {
            for (int i = 0; i < count_employee; i++) {
                if (employees[i] != null) {
                    System.out.printf("\t%s;%s\n", employees[i].display(), id);
                }
            }
        } else {
            error_noEmployee = true;

        }
    }


    public void listCars(){
        if (count_car != 0) {
            for (int i = 0; i < count_car; i++) {
                System.out.printf("\t%s\n", cars[i].display());
            }
        } else {
            error_noCar = true;
        }
    }

    public int getFired_employee() {
        return fired_employee;
    }

    public void setFired_employee(int fired_employee) {
        this.fired_employee = fired_employee;
    }
}
