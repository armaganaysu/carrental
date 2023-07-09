import java.util.Random;
public class Company {
    int temp = 0;
    static boolean Nrandom = false;
    Date date = new Date();
    Random rnd = new Random();
    private Office[] offices;
    private int count_office;
    static Customer[] customers;
    private int count_customer;
    private Request[] requests;
    private Contract [] contracts;
    private Statistics[] statistics;
    private String [] types = {"economy","luxury","sports"};
    private int [] kms = {100, 200, 300};
    private int count_contracts;
    private int count_request;
    private int count_statistics;
    private int rental_period;
    private int count_day;
    private String available_employee_id;
    private String available_car_id;
    static boolean error_noCustomer = false;
    static boolean error_noOffice = false;
    static boolean error_invalidInput = false;
    static boolean error_noSuchOffice = false;
    static boolean error_noCarRequest = false;
    static boolean error_invalidType = false;
    static boolean error_startNotToday = false;
    static boolean error_noEmployeeForContract = false;
    static boolean error_tooLongPeriodForContract = false;
    static boolean error_carNotAvailableForContract = false;
    static boolean error_noContract = false;
    static boolean error_noCarForContract = false;
    static boolean error_noData = false;
    public Customer[] getCustomers() {
        return customers;
    }

    public void setCustomers(Customer[] customers) {
        this.customers = customers;
    }

    public Request[] getRequests() {
        return requests;
    }

    public void setRequests(Request[] requests) {
        this.requests = requests;
    }

    public int getCount_customer() {
        return count_customer;
    }

    public void setCount_customer(int count_customer) {
        this.count_customer = count_customer;
    }

    public Company() {
        this.offices = new Office[100];
        this.count_office = 0;
        this.customers = new Customer[100];
        this.count_customer = 0;
        this.requests = new Request[150];
        this.count_request = 0;
        this.contracts = new Contract[200];
        this.count_contracts = 0;
        this.statistics = new Statistics[100];
        this.count_statistics = 0;
        count_day = 1;
    }

    public int getCount_office() {
        return count_office;
    }

    public Office[] getOffices() {
        return offices;
    }

    public void setOffices(Office[] offices) {
        this.offices = offices;
    }

    public void setCount_office(int count_office) {
        this.count_office = count_office;
    }

    public void addStatistics(Statistics statistic){
        statistics[count_statistics] = statistic;
        count_statistics++;
    }

    public void addCustomer(Customer customer) {
        customers[count_customer] = customer;
        count_customer++;
        customers[count_customer - 1].setId(String.format("%d", count_customer));
    }

    public void addCarRequest(Request request) {
        requests[count_request] = request;
        count_request++;
        requests[count_request - 1].setId(String.format("%d", count_request));
    }

    public void addContract(Contract contract){
        contracts[count_contracts] = contract;
        contracts[count_contracts].setId(String.format("%d", count_contracts +1));
        count_contracts ++;

    }

    public void listCarRequests() {
        if (count_request != 0) {
            for (int i = 0; i < count_request; i++) {

                System.out.printf("\t%s\n", requests[i].display());
            }
        } else {
            error_noCarRequest = true;
        }
    }


    public void listCustomers() {
        if (count_customer != 0) {
            for (int i = 0; i < count_customer; i++) {
                System.out.printf("\t%s\n", customers[i].display());
            }
        } else {
            error_noCustomer = true;
        }
    }
    public void listContracts(){
        if (count_contracts != 0) {
            for (int i = 0; i < count_contracts; i++) {
                System.out.printf("\t%s\n", contracts[i].display());
            }
        } else {
            error_noContract = true;
        }
    }
    public void addOffice(Office office) {
        offices[count_office] = office;
        count_office++;
    }

    public void deleteOffice(String office_id) {
        int officeid = -1;
        for (int i = 0; i < count_office; i++) {
            if (offices[i] != null && offices[i].getId().equals(office_id)) {
                officeid = i;
            }
        }
        if (officeid != -1) {
            offices[Integer.parseInt(office_id) - 1] = null;
            System.out.println("\tOffice is deleted.");
        } else {
            error_noSuchOffice= true;
        }

    }

    public void listOffice() {
        if (count_office != 0) {
            for (int i = 0; i < count_office; i++) {
                if (offices[i] != null) {
                    System.out.println("\t" + offices[i].display());
                }
            }
        } else {
            error_noOffice = true;
        }
    }

    public boolean EndOfRent(Date end_date){
        if ( end_date != null && end_date.getDay() == date.getDay() && end_date.getMonth() == date.getMonth() && end_date.getYear() == date.getYear()) {
            return true;
        } else {
            return false;
        }
    }

    public void CarAvailable(){
        for (int i = 0; i < count_office; i++) {
            if (offices[i] != null) {
                for (int j = 0; j < offices[i].getCount_car(); j++) {
                    Date end_of_contract = offices[i].getCars()[j].getEnd_of_contract();
                    if ( EndOfRent(end_of_contract)) {
                        offices[i].getCars()[j].setAvailable(true);
                        offices[i].getCars()[j].setRental_period(0);
                        offices[i].getCars()[j].setEnd_of_contract(null);
                    }
                }
            }
        }

    }

    public void EmployeeAvailable(){
        for (int i = 0; i < count_office; i++) {
            if (offices[i] != null) {
                for (int j = 0; j < offices[i].getCount_employee(); j++) {
                        if (offices[i].getEmployees()[j] != null) {
                            offices[i].getEmployees()[j].setAvailable(true);
                        }
                    }
                }
            }
        }


    public Request fixRequest(Request random_request){
        String customer_id = randomChoice(customers).getId();
        String office_id = "";
        String type = "";
        int day = rnd.nextInt(5);
        Date end_date = new Date();
        end_date.setDay(date.getDay()+day);

        if (random_request.getOffice_id().equals("-1")) {
            office_id = randomChoice(offices).getId();
                type = randomChoice(types);
        }else{
            office_id=random_request.getOffice_id();
            if (random_request.getCar_type().equals("*")) {
                type = randomChoice(types);
            } else {
                type = random_request.getCar_type();
            }
        }
        Request request = new Request(office_id,customer_id,"*","*",type,date,end_date);
        return request;
    }

    public void deleteDayStatistic (){
        Date temp = new Date(date.getDay(), date.getMonth(), date.getYear());
        temp.setDay(temp.getDay() - 10);
        int temp_count = 0;
        for (int i = 0; i < count_statistics; i++) {
            int day = statistics[i].getStart_date().getDay();
            int month = statistics[i].getStart_date().getMonth();
            int year = statistics[i].getStart_date().getYear();
            if (day == temp.getDay() && month == temp.getMonth() && year == temp.getYear()) {
                statistics[i].getEmployee().setNumber_of_rented_cars(statistics[i].getEmployee().getNumber_of_rented_cars() - 1);
                statistics[i].getCustomer().setNumber_of_rented_cars(statistics[i].getCustomer().getNumber_of_rented_cars() -1);
                statistics[i].getCar().setCount_rent(statistics[i].getCar().getCount_rent() -1);
                statistics[i].getEmployee().setTotal_bonus(statistics[i].getEmployee().getTotal_bonus() - statistics[i].getEmployee().getDaily_bonus());
                statistics[i].getCar().setTotal_income(statistics[i].getCar().getTotal_income() - statistics[i].getCar().getIncome());
                statistics[i] = null;
                temp_count ++;
            }
        }
        int a = 0;
        for (int i = 0; i < temp_count; i++) {
            Statistics tempStatistic = statistics[0];
            int j;
            for (j = 0; j < count_statistics-1; j++) {
                statistics[j] = statistics[j+1];
            }
            statistics[j]=tempStatistic;
        }

        count_statistics -= temp_count;
    }

    public double AverageRentalPeriod(String office_ID){
        double count = 0;
        double average = 0;
        int sum = 0;
        for (int i = 0; i <count_statistics ; i++) {
            if (statistics[i].getOffice_id().equals(office_ID)) {
                sum += statistics[i].getRental_period();
                count++;
            }
        }
        average = sum / count;
        if (Double.isNaN(average)||Double.isInfinite(average)) {
            average = 0;
        }
        return average;
    }

    public Employee [] EmployeeWhoRentedMost(String office_ID){
        int max = -1;
        int same = 0;

        for (int i = 0; i < count_statistics; i++) {
            if (statistics[i].getOffice_id().equals(office_ID) &&  statistics[i].getEmployee().getNumber_of_rented_cars() >= max) {
               max = statistics[i].getEmployee().getNumber_of_rented_cars();
            }
        }
        for (int i = 0; i <count_statistics ; i++) {
            if (statistics[i].getOffice_id().equals(office_ID) &&  statistics[i].getEmployee().getNumber_of_rented_cars() == max) {
                same ++;
            }
        }
        Employee [] mostrentedEmployees = new Employee[same];
        int a = 0;
        for (int i = 0; i <count_statistics ; i++) {
            if (statistics[i].getOffice_id().equals(office_ID) && !isExist(mostrentedEmployees, statistics[i].getEmployee())) {
                if (statistics[i].getEmployee().getNumber_of_rented_cars() == max) {
                    mostrentedEmployees[a] = statistics[i].getEmployee();
                    a++;
                }
            }
        }
        return mostrentedEmployees;
    }

    public Customer [] CustomerWhoRentedMost(String office_ID){
        int max = -1;
        int same = 0;

        for (int i = 0; i < count_statistics; i++) {
            if (statistics[i].getOffice_id().equals(office_ID) &&  statistics[i].getCustomer().getNumber_of_rented_cars() >= max) {
                max = statistics[i].getCustomer().getNumber_of_rented_cars();
            }
        }
        for (int i = 0; i <count_statistics ; i++) {
            if (statistics[i].getOffice_id().equals(office_ID) &&  statistics[i].getCustomer().getNumber_of_rented_cars() == max) {
                same ++;
            }
        }
        Customer [] mostrentedCustomers = new Customer[same];
        int a = 0;
        for (int i = 0; i <count_statistics ; i++) {
            if (statistics[i].getOffice_id().equals(office_ID) && !isExist(mostrentedCustomers, statistics[i].getCustomer())) {
                if (statistics[i].getCustomer().getNumber_of_rented_cars() == max) {
                    mostrentedCustomers[a] = statistics[i].getCustomer();
                    a++;
                }
            }
        }
        return mostrentedCustomers;
    }

    public boolean isExist(Object[] arr, Object element){
        boolean isExist = false;
        for (int i = 0; i < arr.length ; i++) {
            if ( arr[i] != null && arr[i].equals(element) && element != null) {
                isExist = true;
                break;

            }
        }
        return isExist;
    }

    public Car [] MostRentedCar(String office_ID){
        int max = -1;
        int same = 0;

        for (int i = 0; i < count_statistics; i++) {
            if (statistics[i].getOffice_id().equals(office_ID) &&  statistics[i].getCar().getCount_rent() >= max) {
                max = statistics[i].getCar().getCount_rent();
            }
        }
        for (int i = 0; i <count_statistics ; i++) {
            if (statistics[i].getOffice_id().equals(office_ID) &&  statistics[i].getCar().getCount_rent() == max) {
                same ++;
            }
        }

        Car [] mostrentedCar = new Car[same];
        int a = 0;
        for (int i = 0; i <count_statistics ; i++) {
            if (statistics[i].getOffice_id().equals(office_ID) && !isExist(mostrentedCar, statistics[i].getCar())) {
                if (statistics[i].getCar().getCount_rent() == max) {
                    mostrentedCar[a] = statistics[i].getCar();
                    a++;
                }
            }
        }
        return mostrentedCar;
    }

    public String[] MostRentedCarClass(String office_ID){
        int print_count = 0;
        int count = 0;
        int max = 0;
        String str = "";
        for (int i = 0; i <count_statistics ; i++) {
            if (statistics[i].getOffice_id().equals(office_ID)) {
                for (int j = 0; j <count_statistics ; j++) {
                    if (statistics[j].getOffice_id().equals(office_ID) && statistics[i].getCar().getType().equals(statistics[j].getCar().getType()) && i != j) {
                        count ++;
                    }
                }
                if (count > max) {
                    max = count;
                    str = "";
                    print_count = 0;
                    str = String.format("%s",statistics[i].getCar().getType());
                    print_count ++;
                }
                else if (count == max) {
                    if (print_count != 0) {
                        str += ";";
                    }
                    str +=String.format("%s",statistics[i].getCar().getType());
                    print_count ++;
                }
                count = 0;
            }
        }
        if (str.equals("")) {
            String [] error = {"error"};
            return error;
        }else{
           String[] splitted_str = str.split(";");
           int same = 0;
            for (int i = 0; i <splitted_str.length ; i++) {
                for (int j = 0; j <splitted_str.length ; j++) {
                    if (i!=j && splitted_str[i].equals(splitted_str[j])) {
                        splitted_str[j] = "";
                        if (j == splitted_str.length -1) {
                            same ++;
                        }
                    }
                }
            }
            String [] arr= new String[splitted_str.length - same];
            for (int i = 0; i < splitted_str.length; i++) {
                if (!splitted_str[i].equals("")) {
                    arr[i] = splitted_str[i];
                }
            }
            return arr;
        }
    }

    public Employee [] MostProfitableEmployee(String office_ID){
        int max = -1;
        int same = 0;

        for (int i = 0; i < count_statistics; i++) {
            int profit = statistics[i].getCar().getTotal_income() - (statistics[i].getEmployee().getTotal_bonus() +statistics[i].getEmployee().getSalary());
            if (statistics[i].getOffice_id().equals(office_ID) && profit>= max) {
                max = profit;
            }
        }
        for (int i = 0; i < count_statistics; i++) {
            int profit =  statistics[i].getCar().getTotal_income() - (statistics[i].getEmployee().getTotal_bonus() +statistics[i].getEmployee().getSalary());
            if (statistics[i].getOffice_id().equals(office_ID) && profit == max) {
                same ++;
            }
        }
        Employee [] mostprofitableemployee = new Employee[same];
        int a = 0;
        for (int i = 0; i <count_statistics ; i++) {
            int profit =  statistics[i].getCar().getTotal_income() - (statistics[i].getEmployee().getTotal_bonus() +statistics[i].getEmployee().getSalary());
            if (statistics[i].getOffice_id().equals(office_ID) && !isExist(mostprofitableemployee, statistics[i].getEmployee()) ) {
                if (profit == max) {
                    mostprofitableemployee[a] = statistics[i].getEmployee();
                    a++;
                }
            }
        }
        return mostprofitableemployee;
    }

    public Car [] MostProfitableCar(String office_ID){
        double max = -1;
        int same = 0;

        for (int i = 0; i < count_statistics; i++) {
            double profit = statistics[i].getCar().getTotal_income() - (statistics[i].getCar().getMaintenance() +statistics[i].getCar().getTotal_expense());
            if (statistics[i].getOffice_id().equals(office_ID) && profit>= max) {
                max = profit;
            }
        }
        for (int i = 0; i < count_statistics; i++) {
            double profit = statistics[i].getCar().getTotal_income() - (statistics[i].getCar().getMaintenance() +statistics[i].getCar().getTotal_expense());
            if (statistics[i].getOffice_id().equals(office_ID) && profit == max) {
                same ++;
            }
        }
        Car [] mostprofitablecar = new Car[same];
        int a = 0;
        for (int i = 0; i <count_statistics ; i++) {
            double profit = statistics[i].getCar().getTotal_income() - (statistics[i].getCar().getMaintenance() +statistics[i].getCar().getTotal_expense());
            if (statistics[i].getOffice_id().equals(office_ID) && !isExist(mostprofitablecar,statistics[i].getCar() ) ) {
                if (profit == max) {
                    mostprofitablecar[a] = statistics[i].getCar();
                    a++;
                }
            }
        }
        return mostprofitablecar;
    }

    public String  MostProfitableCarClass(String office_ID){
        String str = "";
        double profit_sports = 0;
        double profit_luxury = 0;
        double profit_economy = 0;
        for (int i = 0; i < count_statistics; i++) {
           Car car = statistics[i].getCar();
            if (car.getOffice_id().equals(office_ID)) {
                if (car.getType().equals("sports")) {
                    profit_sports += car.getTotal_income() - (car.getMaintenance() + car.getTotal_expense());
                } else if (car.getType().equals("luxury")) {
                    profit_luxury += car.getTotal_income() - (car.getMaintenance() + car.getTotal_expense());
                } else {
                    profit_economy += car.getTotal_income() - (car.getMaintenance() + car.getTotal_expense());
                }
            }
        }
        if (profit_economy != 0 || profit_luxury !=0 || profit_sports != 0){
            double a,b;
            a =Math.max(profit_sports,profit_luxury);
            b = Math.max(a, profit_economy);
            if (b == profit_economy) {
                str = "economy";
            }
            if (b == profit_luxury) {
                str += " - luxury";
            }
            if (b == profit_sports) {
                str += " - sports";
            }
            if (str.charAt(0) == ' ') {
                str = str.replaceFirst(" - ", "");
            }
        }
        if (str.equals("")) {
            str = "There is no data.";
        }
        return str;
    }

    public double AverageIncomeLevelsOfEmployees(String office_ID){
        double average = 0;
        double sum = 0;
        for (int i = 0; i < count_statistics; i++) {
            if (statistics[i].getOffice_id().equals(office_ID) &&!statistics[i].getCar().isAvailable()) {
                sum += statistics[i].getCar().getIncome() - (statistics[i].getCar().getMaintenance() + statistics[i].getCar().getDaily_expense());
            }
        }
        average = sum / offices[Integer.parseInt(office_ID)-1].getCurrent_employee();
        if (Double.isNaN(average)||Double.isInfinite(average)) {
            average = 0;
        }
        return average;
    }

    public void KilometerGenerator(){
        for (int i = 0; i < count_statistics; i++) {
            if (!statistics[i].getCar().isAvailable()) {
                int random_km = randomChoice(kms);// yukarıda tanımlı 100 200 300 içinden random
                statistics[i].getCar().setTotal_kilometer(statistics[i].getCar().getTotal_kilometer() + random_km);
                statistics[i].getCar().setDaily_kilometer(random_km);
                //assigning expense
                statistics[i].getCar().setDaily_expense(random_km * statistics[i].getCar().getExpense_constant());
                statistics[i].getCar().setTotal_expense(statistics[i].getCar().getTotal_expense() + statistics[i].getCar().getDaily_expense());
            }
        }
    }

    public void Maintenance_and_Income(){
        for (int i = 0; i <count_statistics ; i++) {
            Date temp = new Date(statistics[i].getEnd_date().getDay(),statistics[i].getEnd_date().getMonth(), statistics[i].getEnd_date().getYear());
            if (date_isGreater(date, statistics[i].getEnd_date()) ||(date.getDay() == temp.getDay() && date.getMonth() == temp.getMonth() && date.getYear() == temp.getYear())) {
                statistics[i].getCar().setTotal_maintenance(statistics[i].getCar().getTotal_maintenance()+statistics[i].getCar().getMaintenance());
                statistics[i].getCar().setTotal_income(statistics[i].getCar().getTotal_income() + statistics[i].getCar().getIncome());
            }
        }
    }

    public void list_for_statistics(String office_ID, Employee[] employees){
        if (employees.length!=0) {
            for (int i = 0; i < employees.length; i++) {
                if (employees[i] != null) {
                    if (i != 0 ) {
                        System.out.printf(" -  %s", employees[i].display_statistic());
                    } else {
                        System.out.print(employees[i].display_statistic());
                    }
                }
            }
        }else{
            error_noData=true;
        }
        Menu.errorMessages();
        System.out.println();
    }

    public void list_for_statistics(String office_ID, Car[] cars){
        if (cars.length != 0) {
            for (int i = 0; i < cars.length; i++) {
                if (cars[i] != null) {
                    if (i != 0) {
                        System.out.printf(" -  %s", cars[i].display_statistic());
                    } else {
                        System.out.print(cars[i].display_statistic());
                    }
                }
            }
        }
            else{
            error_noData=true;
        }
        Menu.errorMessages();
        System.out.println();
    }

    public void list_for_statistics(String office_ID, Customer[] customers){
        if (customers.length != 0) {
            for (int i = 0; i < customers.length; i++) {
                if (customers[i] != null) {
                    if (i != 0) {
                        System.out.printf(" -  %s", customers[i].display());
                    } else {
                        System.out.print(customers[i].display());
                    }
                }
            }
        }else{
            error_noData=true;
        }
        Menu.errorMessages();
        System.out.println();
    }

    public boolean date_isGreater(Date start_date, Date end_date) {
        boolean greater = false;
        if (end_date.getYear() > start_date.getYear()) {
            greater = true;
        } else if (end_date.getYear() < start_date.getYear()) {
            greater = false;
        } else {
            if (end_date.getMonth() > start_date.getMonth()) {
                greater = true;
            } else if (end_date.getMonth() < start_date.getMonth()) {
                greater = false;
            } else {
                if (end_date.getDay() > start_date.getDay()) {
                    greater = true;
                } else if (end_date.getDay() <= start_date.getDay()) {
                    greater = false;
                }
            }
        }
        return greater;
    }
    public void OfficeProfits(String office_ID){
        int count_step = 0;
        int total_income = 0;
        double total_expense = 0;
        int office_rent = 100;
        int total_employee_compensation = 0;
        int employee_salaries = offices[Integer.parseInt( office_ID) -1].getCurrent_employee() * 30;
        double total_car_maintenance = 0;
        double performance_bonuses = 0;
        String car_incomes = "";
        String employee_bonuses = "";
        String car_maintenance = "";
        for (int i = 0; i < count_statistics; i++) {
            if (statistics[i].getOffice_id().equals(office_ID) && statistics[i].getStart_date().getDay() == date.getDay() && statistics[i].getStart_date().getMonth() == date.getMonth() && statistics[i].getStart_date().getYear() == date.getYear()) {

                int income = statistics[i].getCar().getIncome();
                int performancebonus = statistics[i].getEmployee().getDaily_bonus();
                double carmaintenance = statistics[i].getCar().getDaily_expense() + statistics[i].getCar().getMaintenance();
                total_income += income;
                if (count_step != 0) {
                    car_incomes += ";";
                    employee_bonuses += ";";
                    car_maintenance += ";";
                }
                car_incomes += String.format("\tCar%s: %d",statistics[i].getCar().getDisplay_id(), income);
                performance_bonuses += performancebonus;
                employee_bonuses += String.format("\tEmployee%s bonus: %d", statistics[i].getEmployee().getDisplay_id(), performancebonus);
                total_car_maintenance += carmaintenance;
                car_maintenance += String.format("\tCar%s maintenance: %d + %.1f = %.1f (%d km)",statistics[i].getCar().getDisplay_id(), statistics[i].getCar().getMaintenance(), statistics[i].getCar().getDaily_expense(), carmaintenance, statistics[i].getCar().getDaily_kilometer());
                count_step ++;
            }
        }
        total_employee_compensation = 200 * offices[Integer.parseInt(office_ID) -1].getFired_employee();
        total_expense = performance_bonuses + total_car_maintenance + office_rent + employee_salaries + total_employee_compensation;
        String [] car_incomes_splitted = car_incomes.split(";");
        String [] employee_bonuses_splitted = employee_bonuses.split(";");
        String [] car_maintenance_splitted= car_maintenance.split(";");
        System.out.printf("Office%s incomes: %d cp\n",office_ID, total_income);
        for (int i = 0; i < car_incomes_splitted.length; i++) {
            if (!car_incomes_splitted[i].equals("")) {
                System.out.println(car_incomes_splitted[i]);
            }
        }
        System.out.printf("Office%s expenses: %.1f cp\n",office_ID, total_expense);
        System.out.println("\tOffice rent: 100");
        if (offices[Integer.parseInt(office_ID) -1].getFired_employee() != 0) {
            System.out.printf("\tEmployee compensation: %d\n",total_employee_compensation);
        }
        System.out.printf("\tEmployee salaries: %d\n",employee_salaries);
        System.out.printf("\tEmployee bonuses: %.1f\n", performance_bonuses);
        for (int i = 0; i < employee_bonuses_splitted.length; i++) {
            if (!employee_bonuses_splitted[i].equals("")) {
                System.out.println(employee_bonuses_splitted[i]);
            }
        }
        for (int i = 0; i < car_maintenance_splitted.length; i++) {
            if (!car_maintenance_splitted[i].equals("")) {
                System.out.println(car_maintenance_splitted[i]);
            }
        }
        double profit = total_income - total_expense;
        System.out.printf("Office%s profit: %.1f\n-------------------------------------------------------\n",office_ID, profit);
        offices[Integer.parseInt(office_ID) -1].setFired_employee(0);
    }

    public void SystemReport(String office_ID){
        System.out.printf("--- Office%s ---\n",office_ID);
        Car [] cars = MostRentedCar(office_ID);
        String [] type = MostRentedCarClass(office_ID);
        Customer [] customers = CustomerWhoRentedMost(office_ID);
        Employee [] employees = EmployeeWhoRentedMost(office_ID);
        System.out.print("\tThe most rented car: ");
        list_for_statistics(office_ID, cars);
        System.out.print("\tThe most rented car class: ");
        if (!type[0].equals("error")) {
            for (int i = 0; i < type.length; i++) {
                if (i != 0) {
                    System.out.printf(" -  %s", type[i]);
                } else {
                    System.out.print(type[i]);
                }
            }
        }else{
            error_noData=true;
            Menu.errorMessages();
        }
        System.out.println();
        cars = MostProfitableCar(office_ID);
        System.out.print("\tThe car with the highest profit: ");
        list_for_statistics(office_ID, cars);
        System.out.printf("\tThe car class with the highest profit: %s\n", MostProfitableCarClass(office_ID));
        System.out.printf("\tThe average number of days the cars are rented: %.1f\n", AverageRentalPeriod(office_ID));
        System.out.print("\tThe customer who rented most: ");
        list_for_statistics(office_ID, customers);
        System.out.print("\tThe employee who rented most: ");
        list_for_statistics(office_ID, employees);
        employees = MostProfitableEmployee(office_ID);
        System.out.print("\tThe most profitable employee: ");
        list_for_statistics(office_ID, employees);
        System.out.printf("\tAverage income levels of the employees for the office: %.1f\n",AverageIncomeLevelsOfEmployees(office_ID));
    }
    
    public void SystemReccomendations(String office_ID, int office_index){
        String car_to_buy = "";
        if (offices[office_index].getNo_CarCount()>1) {
            int a = Math.max(offices[office_index].getNo_Car_economy(), offices[office_index].getNo_Car_luxury());
            int b = Math.max(a, offices[office_index].getNo_Car_sports());
            if (b == offices[office_index].getNo_Car_economy()) {
                car_to_buy += " economy";
            }
            if (b == offices[office_index].getNo_Car_sports()) {
                car_to_buy += " sports";
            }
            if (b == offices[office_index].getNo_Car_luxury()) {
                car_to_buy += " luxury";
            }
            System.out.printf("Office%s: Buy new car (%s class )\n",office_ID,car_to_buy);
        }else if (offices[office_index].getNo_EmployeeCount()>1 && offices[office_index].getCurrent_employee()<3) {
            System.out.printf("Office%s: Hire an employee.\n",office_ID);
        }else if (offices[office_index].getRequestCount()<3 && offices[office_index].getCurrent_employee()>1) {
            System.out.printf("Office%s: Dismiss an employee.\n",office_ID);
        }else if (offices[office_index].getRequestCount()<5 || offices[office_index].getCurrent_employee()==0) {
            System.out.printf("Office%s: Close the office.\n",office_ID);
        }else{
            System.out.println("There is not enough data to determine.");
        }
        offices[office_index].setNo_CarCount(0);
        offices[office_index].setNo_EmployeeCount(0);
        offices[office_index].setNo_CarCount(0);
        offices[office_index].setCarToBuy("");
    }

    public void operations(String[] command) {
        int number_of_parameter = command.length;
        if (command[0].equals("addOffice") && number_of_parameter == 4) {
            String office_id = String.format("%d", getCount_office() + 1);
            Office office = new Office(office_id, command[1], command[2], command[3]);
            addOffice(office);
        } else if (command[0].equals("addEmployee") && number_of_parameter == 6) {
            Employee employee = new Employee(command[1], command[2], command[3], command[4], command[5]);
            int office_id = Integer.parseInt(command[5]) - 1;
            if (getOffices()[office_id] != null) {
                getOffices()[office_id].addEmployee(employee);
            } else {
                error_noSuchOffice = true;
            }
        } else if (command[0].equals("addCar") && number_of_parameter == 6) {
            Car car = new Car(command[1], command[2], command[3], Integer.parseInt(command[4]), command[5]);
            int office_id = Integer.parseInt(command[5]) - 1;
            if (command[3].equals("luxury") || command[3].equals("sports") || command[3].equals("economy")) {
                if (getOffices()[office_id] != null) {
                    getOffices()[office_id].addCar(car);
                } else {
                    error_noSuchOffice = true;
                }
            } else {
                error_invalidType = true;
            }
        } else if (command[0].equals("addCustomer") && number_of_parameter == 3) {
            Customer customer = new Customer(command[1], command[2]);
            addCustomer(customer);
        } else if (command[0].equals("addCarRequest") && number_of_parameter == 8) {
            Date start_date = new Date(command[6]);
            Date end_date = new Date(command[7]);
            Request request = new Request(command[1], command[2], command[3], command[4], command[5], start_date, end_date);
            addCarRequest(request);
            if (isRequestAppropriate(request)) {
                Contract contract = new Contract(available_employee_id, command[2],available_car_id ,start_date ,end_date);
                addContract(contract);
                System.out.printf("\tContract:%s \n",contract.display());
            }
        } else if (command[0].equals("addCarRequestRandom")&&number_of_parameter == 3) {
            Request random_request = new Request(command[1],command[2]);
            addCarRequest(random_request);
            Request new_request=fixRequest(random_request);
// fonksiyon
            if(isRequestAppropriate(new_request)){
                Contract contract = new Contract(available_employee_id, new_request.getCustomer_id(),available_car_id ,new_request.getStart_date() ,new_request.getEnd_date());
                addContract(contract);
                System.out.printf("\tContract:%s \n",contract.display());
            }
        }
        else if (command[0].equals("addCarRequestNRandom")&&number_of_parameter == 3) {
            Nrandom = true;
            int Nrandom_count = 0;
            int min = Integer.parseInt(command[1]);
            int max = Integer.parseInt(command[2]);
            int  random_chocie = rnd.nextInt(max - min +1) + min;
            for (int i = 0; i < random_chocie; i++) {
                Request random_request = new Request(min, max);
                addCarRequest(random_request);
                Request new_request=fixRequest(random_request);
                Nrandom_count ++;
                System.out.printf("\t%d- Office:%s, CustomerID:%s, CarType:%s, RentalPeriod:%s - %s\n",Nrandom_count, new_request.getOffice_id(), new_request.getCustomer_id(), new_request.getCar_type(), new_request.getStart_date().displayDate(), new_request.getEnd_date().displayDate());
                if(isRequestAppropriate(new_request)){
                    Contract contract = new Contract(available_employee_id, new_request.getCustomer_id(),available_car_id ,new_request.getStart_date() ,new_request.getEnd_date());
                    addContract(contract);
                    System.out.printf("\t\tContract:%s \n",contract.display());
                }
                Menu.errorMessages();
            }
            Nrandom = false;
        }
        else if (command[0].equals("listOffice") && number_of_parameter == 1) {
            listOffice();
        } else if (command[0].equals("listEmployee") && number_of_parameter == 2) {
            int office_id = Integer.parseInt(command[1]) - 1;
            if (getOffices()[office_id] != null) {
                getOffices()[office_id].listEmployees();
            } else {
                error_noSuchOffice = true;
            }
        } else if (command[0].equals("listCar") && number_of_parameter == 2) {
            int office_id = Integer.parseInt(command[1]) - 1;
            if (getOffices()[office_id] != null) {
                getOffices()[office_id].listCars();
            } else {
                error_noSuchOffice = true;
            }
        } else if (command[0].equals("listCustomer") && number_of_parameter == 1) {
            listCustomers();
        } else if (command[0].equals("listCarRequest") && number_of_parameter == 1) {
            listCarRequests();
        }else if (command[0].equals("listContract") && number_of_parameter == 1) {
            listContracts();
        } else if (command[0].equals("deleteOffice") && number_of_parameter == 2) {
            deleteOffice(command[1]);
        } else if (command[0].equals("deleteEmployee") && number_of_parameter == 3) {
            int office_id = Integer.parseInt(command[1]) - 1;
            if (getOffices()[office_id] != null) {
                getOffices()[office_id].deleteEmployee(command[2]);
            } else {
                error_noSuchOffice = true;
            }
        } else if (command[0].equals("nextday") && number_of_parameter == 1) {
            try {
                EmployeeAvailable();
                KilometerGenerator();
                Maintenance_and_Income();
                System.out.println("--- Office Profits ----");
                for (int i = 0; i < count_office; i++) {
                    if (offices[i] != null) {
                        OfficeProfits(offices[i].getId());
                    }
                }
                System.out.println("--- Office Statistics of the Last 10 Days ---");
                for (int i = 0; i < count_office; i++) {
                    if (offices[i] != null) {
                        SystemReport(offices[i].getId());
                    }
                }

                if (count_day % 3 == 0) {
                    System.out.println("--- System Recommendations  ---");
                    for (int i = 0; i < count_office; i++) {
                        if (offices[i] != null) {
                            int office_index = Integer.parseInt(offices[i].getId()) - 1;
                            SystemReccomendations(offices[i].getId(), office_index);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }

            count_day ++;
            //bunu ellemiyorsun! 371-374
            date.nextDay();
            CarAvailable();
            if (count_day > 10) {
                deleteDayStatistic();
            }
            temp ++;
            if (temp == 5) {
                int a = 0;
            }
            System.out.printf("-------------------------------------------------------------------------------------\n--- Date:%s ---\n", date.displayDate());

        } else {
            error_invalidInput = true;
        }
    }

    public boolean compareDate(Date date_current, Date date_request) {
        Date temp = new Date(date_current.getDay(), date_current.getMonth(), date_current.getYear());
        boolean valid_date = false;
        boolean isStartDateToday = false;
        if (temp.getDay() == date.getDay() && temp.getMonth() == date.getMonth() && temp.getYear() == date.getYear()) {
            isStartDateToday = true;
        }
        for (int i = 0; i < 4; i++) {
            temp.setDay(temp.getDay() + i);
            if (date_request.getDay() == temp.getDay() && date_request.getMonth() == temp.getMonth() && date_request.getYear() == temp.getYear()) {
                valid_date = true;
                rental_period = i + 1;
                break;
            } else {
                temp.setDay(temp.getDay() - i);
            }
        }
        if (!valid_date){
            error_tooLongPeriodForContract = true;
            Menu.anyerror =true;
        }
        if (!isStartDateToday){
            error_startNotToday = true;
            Menu.anyerror =true;
        }
        if (valid_date && isStartDateToday) {
            return true;
        } else {
            return false;
        }
    }

    public String  randomChoice (String [] array){
        int index = rnd.nextInt(array.length);
        return array[index];
    }

    public int  randomChoice (int [] array){
        int index = rnd.nextInt(array.length);
        return array[index];
    }

    public Office randomChoice(Office [] array){
        int index = rnd.nextInt(array.length);
        while (array[index]==null)
            index = rnd.nextInt(array.length);
        return array[index];
    }

    public Customer randomChoice(Customer [] array){
        int index = rnd.nextInt(array.length);
        while (array[index]==null)
            index = rnd.nextInt(array.length);
        return array[index];
    }

    public boolean isRequestAppropriate(Request request){
        boolean result = true;
        boolean compare_date = compareDate(request.getStart_date(), request.getEnd_date());
        String available_car_index = "";
        String available_employee_index = "";
        int office_index = Integer.parseInt(request.getOffice_id()) -1;
        String brand = request.getBrand();
        String model = request.getModel();
        String type = request.getCar_type();
        if (offices[office_index]!=null) {
            String str = "";
            offices[office_index].setRequestCount(offices[office_index].getRequestCount() +1);
            //available employee bulmak için
            for (int i = 0; i < offices[office_index].getCount_employee(); i++) {
                if (offices[office_index].getEmployees()[i]!= null && offices[office_index].getEmployees()[i].isAvailable() ) {
                    str += (i) +" ";
                }
            }
            if (!str.equals("")) {
                String [] available_employee_ids = str.split(" ");
                available_employee_index = randomChoice(available_employee_ids);
                str = "";
            }
            else{
                error_noEmployeeForContract = true;
                offices[office_index].setNo_EmployeeCount(offices[office_index].getNo_EmployeeCount()+1);
                Menu.anyerror =true;
            }


            //araba için rastgele seçim
            if (!brand.equals("*")&&!model.equals("*")&&!type.equals("*")) {
                for (int i = 0; i < offices[office_index].getCount_car() ; i++) {
                    if (offices[office_index].getCars()[i].getBrand().equals(brand)
                            && offices[office_index].getCars()[i].getModel().equals(model)
                            && offices[office_index].getCars()[i].getType().equals(type)) {
                        str += i + " ";
                    }
                }
            }
            else if (!model.equals("*")) {
                for (int i = 0; i < offices[office_index].getCount_car(); i++) {
                    if (offices[office_index].getCars()[i].getModel().equals(model)) {
                        str += i + " ";
                    }

                }
            }
            else if (model.equals("*")) {
                if (!brand.equals("*") && !type.equals("*")) {
                    for (int i = 0; i < offices[office_index].getCount_car(); i++) {
                        if (offices[office_index].getCars()[i].getBrand().equals(brand) &&
                                offices[office_index].getCars()[i].getType().equals(type)) {
                            str += i + " ";
                        }
                    }
                }
                else if (!type.equals("*")) {
                    for (int i = 0; i < offices[office_index].getCount_car() ; i++) {
                        if (offices[office_index].getCars()[i].getType().equals(type)) {
                            str += i + " ";
                        }
                    }
                }
                else if (!brand.equals("*")) {
                    for (int i = 0; i < offices[office_index].getCount_car(); i++) {
                        if (offices[office_index].getCars()[i].getBrand().equals(brand)) {
                            str += i + " ";
                        }
                    }
                }
            }
            if (!str.equals("")) {
                String [] available_car_ids = str.split(" ");
                str = "";
                for (int i = 0; i < available_car_ids.length; i++) {
                    if (offices[office_index].getCars()[Integer.parseInt(available_car_ids[i])].isAvailable()) {
                        str += available_car_ids[i] + " ";  // yukarıda özelliklere uyan arabaların şimdi de avaible olup olmadığğını kontrol ediyor
                    }
                }
                 if (str.equals("")) {
                     error_carNotAvailableForContract = true;
                     offices[office_index].setNo_CarCount(offices[office_index].getNo_CarCount()+1);
                     if (request.getCar_type().equals("sports")) {
                         offices[office_index].setNo_Car_sports(offices[office_index].getNo_CarCount() + 1);
                     } else if (request.getCar_type().equals("economy")) {
                         offices[office_index].setNo_Car_economy(offices[office_index].getNo_CarCount() + 1);
                     } else {
                         offices[office_index].setNo_Car_luxury(offices[office_index].getNo_CarCount() + 1);
                     }
                     offices[office_index].setCarToBuy(request.getCar_type());
                     Menu.anyerror =true;
                }
                else if (!error_noEmployeeForContract){
                    available_car_ids = str.split(" ");
                    available_car_index = randomChoice(available_car_ids);
                }
            }
            else{
                error_noCarForContract = true;
                Menu.anyerror =true;
                result = false;
            }
            if (available_car_index.equals("") || available_employee_index.equals("") || !compare_date) {
                result = false;
            }
        }
        else{
            error_noSuchOffice=true;
            Menu.anyerror = true;
            result=false;
        }
        if (result) {
            Date temp = new Date(date.getDay(), date.getMonth(), date.getYear());
            Office office = offices[office_index];
            Car car = office.getCars()[Integer.parseInt(available_car_index)];
            Employee employee = office.getEmployees()[Integer.parseInt(available_employee_index)];
            Customer customer = customers[Integer.parseInt(request.getCustomer_id()) -1];
            car.setAvailable(false);
            car.setRental_period(rental_period);
            temp.setDay(temp.getDay() + car.getRental_period());
            car.setEnd_of_contract(temp);
            employee.setAvailable(false);
            available_car_id = car.getDisplay_id();
            available_employee_id = employee.getDisplay_id();
            Statistics contract_for_statistics= new Statistics(office.getId(), car, employee, customer, request.getStart_date(), request.getEnd_date(), (double)rental_period);
            employee.setNumber_of_rented_cars(employee.getNumber_of_rented_cars() + 1);
            customer.setNumber_of_rented_cars(customer.getNumber_of_rented_cars() + 1);
            car.setCount_rent(car.getCount_rent() + 1 );
            if (car.getType().equals("sports")) {
                employee.setDaily_bonus(10);
                car.setIncome(200);
            } else if (car.getType().equals("luxury")) {
                employee.setDaily_bonus(15);
                car.setIncome(300);
            } else {
                employee.setDaily_bonus(5);
                car.setIncome(100);
            }
            employee.setTotal_bonus(employee.getTotal_bonus() + employee.getDaily_bonus());
            addStatistics(contract_for_statistics);
        }
        return result;
    }
}


