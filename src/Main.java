import java.util.*;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    static Customer customer = new Customer();
    public static int yesOrNo;
    public static int option = 0;

    public static void main(String[] args) {

        loadData();
        Runtime.getRuntime().addShutdownHook(new Thread(Main::saveData));

        System.out.println();
        System.out.println("\n---------WELCOME TO PIZZA DELIVERY APP---------");
        System.out.println();

        while (!customer.isAuthenticated()) {
            getFirstMenu();
        }

        do {
            getSecondMenu();
        } while (yesOrNo == 1);


        scanner.close();
    }

    public static void getFirstMenu(){
        System.out.println("Option 1  : Login");
        System.out.println("Option 2  : Register");
        System.out.print("------Choose an option------ : ");

        try{
            option = scanner.nextInt();

            if(option == 1){
                customer.login();
            } else if(option == 2){
                Customer.registerCustomer();
            } else {
                System.out.println("Invalid option");
            }
        } catch (Exception e){
            System.out.println("Invalid option. Please enter the number of the action you want to perform");
        }

    }

    public static void getSecondMenu(){
        System.out.print("Do you want to view the menu? (enter 1 if yes or enter 0 to exit): ");
        yesOrNo = scanner.nextInt();

        if(yesOrNo == 1){
            getMenu();
        } else if(yesOrNo == 0){
            System.exit(0);
        }else {
            System.out.println("Invalid input");
        }
    }

    public static void getMenu(){
        System.out.println("\n---------PIZZA DELIVERY APP---------");
        System.out.println("Option 1  : Order Customized Pizza");
        System.out.println("Option 2  : Order from Pizza Menu");
        System.out.println("Option 3  : Order from Favourites");
        System.out.println("Option 4  : Go Back");
        System.out.println("Option 5 : Exit");
        System.out.print("------Choose an option------ : ");

        try{
            option = scanner.nextInt();

            if(option == 1){
                Order.orderCustomizedPizza();
            } else if(option == 2){
                Order.orderFromPizzaMenu();
            } else if(option == 3){
                customer.viewFavouritePizzas();
            }  else if(option == 4){
                getFirstMenu();
            } else if(option == 5){
                System.exit(0);
            } else {
                System.out.println("Invalid option");
            }
        } catch (Exception e){
            System.out.println("Invalid option. Please enter the number of the action you want to perform");
        }

    }

    public static void loadData() {
        Customer.loadCustomers();
    }


    public static void saveData() {
        Customer.saveCustomer();
    }

    public static String getOption(Map<String, Double> options, Scanner scanner) {
        int index = 1;
        List<String> keys = new ArrayList<>(options.keySet());
        for (String option : keys) {
            System.out.printf("%d. %s (Rs.%.2f)\n", index++, option, options.get(option));
        }
        int choice = -1;
        while (choice < 1 || choice > keys.size()) {
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
        }
        return keys.get(choice - 1);
    }

    public static List<String> getMultipleOptions(Map<String, Double> options, Scanner scanner) {
        List<String> selected = new ArrayList<>();
        List<String> keys = new ArrayList<>(options.keySet());
        int index = 1;

        for (String option : keys) {
            System.out.printf("%d. %s (Rs.%.2f)\n", index++, option, options.get(option));
        }

        while (true) {
            System.out.print("Enter your choice (or type '0'): ");
            int input = scanner.nextInt();
            if (input == 0) {
                break;
            }
            try {
                if (input < 1 || input > keys.size()) {
                    System.out.println("Invalid choice. Try again.");
                } else {
                    String chosenOption = keys.get(input - 1);
                    if (!selected.contains(chosenOption)) {
                        selected.add(chosenOption);
                    } else {
                        System.out.println("Already selected. Try a different option.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number or '0'.");
            }
        }
        return selected;
    }

    public static void waitFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.err.println("Sleep interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
