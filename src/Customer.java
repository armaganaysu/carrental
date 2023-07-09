
public class Customer {
    private String id ;
    private String name;
    private String surname;
    private int number_of_rented_cars;

    public Customer( String name, String surname) {
        this.name = name;
        this.surname = surname;
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

    public int getNumber_of_rented_cars() {
        return number_of_rented_cars;
    }

    public void setNumber_of_rented_cars(int number_of_rented_cars) {
        this.number_of_rented_cars = number_of_rented_cars;
    }

    public String display(){
        return String.format("Customer%s;%s;%s", id, name, surname);
    }



}

