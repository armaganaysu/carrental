import java.util.Scanner;
import java.io.*;

public class project_cars {
    static boolean error_invalidFileName = false;
    static boolean error_invalidCommand = false;




        public static void main(String[] args) throws IOException {
            boolean program_is_on = true;
            Menu menu = new Menu();
            Company company = new Company();

            menu.logo();
            System.out.printf("--- Date:%s ---\n", company.date.displayDate());
            Scanner scan = new Scanner(System.in);
            while (program_is_on) {
                String choice = scan.nextLine();
                String [] choice_splitted = choice.split(";");
                String command_type = "";
                if (choice_splitted[0].length()>=3) {
                    command_type = choice_splitted[0].substring(0,3);
                }
                if (command_type.equals(">>>")) {
                    choice_splitted[0] = choice_splitted[0].replace(">>>", "");
                    if (choice_splitted[0].equals("help")) {
                        menu.help();

                    } else if (choice_splitted[0].equals("load")) {
                        try {
                            File f = new File(String.format("src/%s", choice_splitted[1]));
                            Scanner scan_file = new Scanner(f);
                            while (scan_file.hasNextLine()) {
                                String current_line = scan_file.nextLine();
                                String[] splitted_current_line = current_line.split(";");
                                System.out.printf(">%s\n", current_line);
                                company.operations(splitted_current_line);
                                if (!Company.Nrandom) {
                                    menu.errorMessages();
                                }
                            }
                        } catch (Exception exception) {
                            System.out.println(exception);
                           error_invalidFileName = true;
                        }
                    } else {
                        company.operations(choice_splitted);
                    }
                } else {
                   error_invalidCommand = true;

                }
                if (!Company.Nrandom) {
                    menu.errorMessages();
                }

            }
        }
    }

