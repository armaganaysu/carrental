
import java.io.*;
import java.util.Scanner;
public class Menu {
static boolean anyerror = false;


    public void logo() throws IOException{
        System.out.println();
        File logo = new File("src/logo.txt");
        Scanner scanf = new Scanner(logo);
        while (scanf.hasNextLine()){
            System.out.println(scanf.nextLine());
        }
    }


    public void help(){
        System.out.println("COMMANDS\n" +
                "load;input.txt ->load and run \"input.txt\" \n" +
                "addOffice;phone;address;city\n" +
                "addEmployee;name;surname;gender;birthdate;office_id\n" +
                "addCar;brand;model;class;km;office_id\n" +
                "addCustomer;name;surname\n" +
                "addCarRequest;office_id;customer_id;brand;model;class;start_date;end_date\n" +
                "addCarRequestRandom;office_id;class\n" +
                "addCarRequestNRandom;min_request_number;max_request_number   \n" +
                "listOffice\n" +
                "listEmployee;office_id\n" +
                "listCar;office_id\n" +
                "listCustomer\n" +
                "listCarRequest ->customer car requests for current day\n" +
                "listContract ->contracts for current day\n" +
                "deleteOffice;office_id\n" +
                "deleteEmployee;office_id;employee_id\n" +
                "nextday");

    }

    public  static void Extratab(){
        if (Company.Nrandom && anyerror ) {
            System.out.print("\t");
        }
    }



    public  static void errorMessages() {


        if (Office.error_noCar) {

            System.out.println("\tError:There is no car in the office");
            Office.error_noCar = false;
        }
        if (Office.error_noEmployee) {
            System.out.println("\tError:There is no employee in the office");
            Office.error_noEmployee = false;
        }
        if (Office.error_employeeLimit) {
            System.out.println("\tError:Office already has 3 employees");
            Office.error_employeeLimit = false;
        }
        if (Company.error_invalidInput) {
            System.out.println("Please enter valid input. Valid commands: >>>help");
            Company.error_invalidInput = false;
        }
        if (Company.error_noOffice) {
            System.out.println("\tError:There is no office");
            Company.error_noOffice = false;
        }
        if (Company.error_noCustomer) {
            System.out.println("\tError:There is no customer");
            Company.error_noCustomer = false;
        }
        if (project_cars.error_invalidFileName) {
            System.out.println("Please enter load with valid file name.");
            project_cars.error_invalidFileName = false;
        }
        if (project_cars.error_invalidCommand) {
            System.out.println("Please enter command with >>>");
            project_cars.error_invalidCommand = false;
        }
        if (Company.error_noSuchOffice) {
            Extratab();
            System.out.println("\tError:Operation can not be done. There is no such office.");
            Company.error_noSuchOffice = false;
        }
        if (Office.error_noSuchEmployee) {
            System.out.println("\tError: There is no such employee.");
            Office.error_noSuchEmployee = false;
        }
        if ( Company.error_noCarRequest ) {
            System.out.println("\tError: There is no car request.");
            Company.error_noCarRequest = false;
        }
        if (Company.error_noCarForContract) {
            Extratab();
            System.out.println("\tError: No car.");
            Company.error_noCarForContract = false;
        }
        if (Company.error_noEmployeeForContract) {
            Extratab();
            System.out.println("\tError: No employee for the contract");
            Company.error_noEmployeeForContract = false;
        }
        if (Company.error_invalidType) {
            System.out.println("\tError: Invalid car class");
            Company.error_invalidType = false;
        }
        if (Company.error_carNotAvailableForContract) {
            Extratab();
            System.out.println("\tError: Car not available.");
            Company.error_carNotAvailableForContract = false;
        }
        if (Company.error_startNotToday) {
            Extratab();
            System.out.println("\tError: Car request must be for today.");
            Company.error_startNotToday = false;
        }
        if (Company.error_tooLongPeriodForContract) {
            Extratab();
            System.out.println("\tError: Car requests must be for 1-4 days.");
            Company.error_tooLongPeriodForContract = false;
        }
        if (Company.error_noContract) {
            System.out.println("\tError: There is no contract to list.");
            Company.error_noContract = false;
        }
        if (Company.error_noData) {
            System.out.print("There is no data.");
            Company.error_noData=false;
        }

            anyerror = false;

    }





}
