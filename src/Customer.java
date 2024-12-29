import java.io.*;
import java.time.LocalDate;
import java.util.*;

class Customer extends Person implements Observer {
    private int customerID;
    private String nic;
    private String email;
    private String observerName;

    public static Scanner scanner = new Scanner(System.in);
    public static Map<String, List<Customer>> customers = new HashMap<>();
    public static Map<Integer, List<String>> favourites = new HashMap<>();
    private static int nextCustomerId = 1;

    public Customer(){
        super();
    }

    public Customer(int customerID, String username, String password, String address, int contactNumber, String nic, String email, String type) {
        super(username, password, contactNumber, address, type);
        this.customerID = customerID;
        this.nic = nic;
        this.email = email;
    }

    public Customer(String name) {
        this.observerName = name;
    }

    @Override
    public void login() {
        System.out.println("\n----Customer Login----");

        try {
            System.out.print("Enter your Username: ");
            String inputUsername = scanner.nextLine();

            System.out.print("Enter your Password: ");
            String inputPassword = scanner.nextLine();

            int isLoggedIn = 0;

            for (List<Customer> customerList : customers.values()) {
                for (Customer customer : customerList) {
                    if (customer.getUsername().equals(inputUsername) && customer.getPassword().equals(inputPassword)) {
                        System.out.println("Login successful! Welcome, " + customer.getUsername());
                        setAuthenticated(true);
                        isLoggedIn = 1;
                        Main.customer = customer; // Set the logged-in customer in Main
                        Main.getSecondMenu();
                        return;
                    }
                }
            }

            if (isLoggedIn == 0) {
                System.out.println("Invalid Username or Password. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred during login: " + e.getMessage());
        }
    }

    public static void registerCustomer() {
        System.out.println("\n----Register a New Customer----");

        try {
            // input customer details
            System.out.print("Enter NIC: ");
            String nic = scanner.nextLine();

            boolean nicExists = false;
            for (List<Customer> customerList : customers.values()) {
                for (Customer customer : customerList) {
                    if (customer.getNic().equals(nic)) {
                        nicExists = true;
                        break; // Exit the inner loop
                    }
                }
                if (nicExists) break; // Exit the outer loop if NIC is found
            }

            if (nicExists) {
                System.out.println("A customer with this NIC is already registered.");
                return;
            }

            System.out.print("Enter Your Username: ");
            String name = scanner.nextLine();

            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            System.out.print("Enter Address: ");
            String address = scanner.nextLine();

            System.out.print("Enter Your Email: ");
            String email = scanner.nextLine();

            System.out.print("Enter Phone Number: ");
            int contactNumber = scanner.nextInt();


            // Create and register patient
            Customer newCustomer = new Customer(nextCustomerId++, name, password, address, contactNumber, nic, email, "Customer");
            customers.computeIfAbsent(name, k -> new ArrayList<>()).add(newCustomer);

            // save Patient to customer.csv
            saveCustomer();


            System.out.println("You are registered successfully with ID: " + newCustomer.getCustomerID()+ "\n");
            setAuthenticated(true);
            Main.customer = newCustomer;
            Main.getSecondMenu();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input type. Please enter the correct data type for each field.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public static void addFavourites(int customerID, String favouritePizzaDetails){
        favourites.computeIfAbsent(customerID, k -> new ArrayList<>()).add(favouritePizzaDetails);
        System.out.println("Pizza added to favorites!");
    }

    // View all favorite pizzas
    public void viewFavouritePizzas() {
        List<String> favoritePizzas = favourites.get(customerID);

        if (favoritePizzas == null || favoritePizzas.isEmpty()) {
            System.out.println("No favorite pizzas found.");
            return;
        }

        // Display the list of favorite pizzas
        System.out.println("\n----Favorite Pizzas----");
        for (int i = 0; i < favoritePizzas.size(); i++) {
            System.out.println((i + 1) + ". " + favoritePizzas.get(i));
        }

        System.out.print("\nEnter the number of the pizza you want to order: ");
        Scanner scanner = new Scanner(System.in);
        int choice;
        try {
            choice = scanner.nextInt();
            if (choice < 1 || choice > favoritePizzas.size()) {
                System.out.println("Invalid choice. Please try again.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return;
        }

        String selectedPizza = favoritePizzas.get(choice - 1);

        Order.orderFromFavourites(selectedPizza);
    }

    public void update(String status) {
        System.out.println("Notification for " + observerName + ": Your order " + status);
    }

    // to get Customer from CSV
    public static Customer fromCSV(String line) throws Exception {
        String[] fields = line.split(",");
        int id = Integer.parseInt(fields[0]);
        String username = fields[1];
        String password = fields[2];
        String address = fields[3];
        int number = Integer.parseInt(fields[4]);
        String nic = fields[5];
        String email = fields[6];
        String type = fields[7];
        return new Customer(id, username, password, address, number, nic, email, type);
    }

    // to convert a Customer to a CSV line
    public String toCSV() {
        return customerID + "," + getUsername() + "," + getPassword() + "," + getAddress() + "," + getContactNumber() + "," + nic + "," + email + "," + getType();
    }

    //save Customer to customers.csv file
    public static void saveCustomer() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("customers.csv"))) {
            for (List<Customer> customerList : customers.values()) {
                for (Customer customer : customerList) {
                    pw.println(customer.toCSV());
                }
            }
            System.out.println("Customer saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving patients: " + e.getMessage());
        }
    }

    //get Customer details from customers.csv file
    public static void loadCustomers() {
        try (BufferedReader br = new BufferedReader(new FileReader("customers.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Customer customer = Customer.fromCSV(line);
                customers.computeIfAbsent(customer.getUsername(), k -> new ArrayList<>()).add(customer);
                nextCustomerId = Math.max(nextCustomerId, customer.getCustomerID() + 1); // Ensure unique patient IDs
            }
            System.out.println("Customer loaded successfully.");
        } catch (Exception e) {
            System.out.println("Error loading patients: " + e.getMessage());
        }
    }

    public int getCustomerID(){
        return customerID;
    }

    public void setCustomerID(int customerID){
        this.customerID = customerID;
    }

    public String getNic(){
        return nic;
    }

    public void setNic(String type){
        this.nic = nic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
