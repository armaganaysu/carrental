public class Employee {

    private String id;
    private String display_id;
    private String name;
    private String surname;
    private String gender;
    private String birth_date;
    private String office_id;
    private int salary;
    private int daily_bonus;
    private int total_bonus;
    private boolean available;
    private int number_of_rented_cars;

    public String getOffice_id() {
        return office_id;
    }

    public void setOffice_id(String office_id) {
        this.office_id = office_id;
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

    public Employee(String name, String surname, String gender, String birth_date, String office_id) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birth_date = birth_date;
        this.office_id = office_id;
        this.available = true;
        this.salary = 30;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public int getNumber_of_rented_cars() {
        return number_of_rented_cars;
    }

    public void setNumber_of_rented_cars(int number_of_rented_cars) {
        this.number_of_rented_cars = number_of_rented_cars;
    }

    public String display(){
        return String.format("Employee%s;%s;%s;%s;%s", display_id, name, surname, gender, birth_date);
    }

    public String display_statistic(){
        return String.format("Employee%s;%s;%s", display_id, name, surname);
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getDaily_bonus() {
        return daily_bonus;
    }

    public void setDaily_bonus(int daily_bonus) {
        this.daily_bonus = daily_bonus;
    }

    public int getTotal_bonus() {
        return total_bonus;
    }

    public void setTotal_bonus(int total_bonus) {
        this.total_bonus = total_bonus;
    }
}

