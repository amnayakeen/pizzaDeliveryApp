import java.time.LocalTime;
import java.util.*;

public class Order {

    private int orderID = 0;
    private int customerID;
    private String pizzaName;
    private String description;
    private static double totalPrice = 0.0;
    private String orderTime;

    public static Scanner scanner = new Scanner(System.in);
    private static int nextOrderId = 1;
    public static Map<Integer, List<Order>> orders = new HashMap<>();
    static Order order = new Order(new OrderedState());

    private OrderState currentState;

    public Order(){}

    public Order(OrderState initialState){
        this.currentState = new OrderedState();
    }

    public void setState(OrderState newState) {
        this.currentState = newState;
    }

    public void ordered() {currentState.ordered(this);}
    public void preparing() {currentState.preparing(this);}
    public void outForDelivery() {currentState.outForDelivery(this);}
    public void readyForPickup() {currentState.readyForPickup(this);}
    public void delivered() {currentState.delivered(this);}
    public void pickedUp() {currentState.pickedUp(this);}


    public Order(int orderID, int customerID, String pizzaName, String description, double totalPrice, String orderTime){
        this.orderID = orderID;
        this.customerID = customerID;
        this.pizzaName = pizzaName;
        this.description = description;
        Order.totalPrice = totalPrice;
        this.orderTime = orderTime;
    }

    public static void orderCustomizedPizza() {
        PlaceOrderHandler sizeHandler = new SizeHandler();
        PlaceOrderHandler crustHandler = new CrustHandler();
        PlaceOrderHandler sauceHandler = new SauceHandler();
        PlaceOrderHandler toppingHandler = new ToppingHandler();
        PlaceOrderHandler cheeseHandler = new CheeseHandler();

        sizeHandler.setNextHandler(crustHandler);
        crustHandler.setNextHandler(sauceHandler);
        sauceHandler.setNextHandler(toppingHandler);
        toppingHandler.setNextHandler(cheeseHandler);

        Pizza pizza = new PizzaBuilder().build();
        sizeHandler.handleRequest(pizza, totalPrice);

        System.out.print("\nGive a name to your pizza: ");
        String pizzaName = scanner.nextLine();

        System.out.print("\nDo you want to add this to your Favourites? (enter 1 if yes or 0): ");
        int yesOrNo = scanner.nextInt();

        int customerId = Main.customer.getCustomerID();
        int orderId = nextOrderId++;
        String orderTime = LocalTime.now().toString();
        double finalPrice = pizza.getTotalPrice();

        if(yesOrNo == 1){
            String favoritePizzaDetails = String.format(
                    "Pizza Name: %s, Size: %s, Crust: %s, Sauce: %s, Topping: %s, Cheese: %s, Price: Rs.%.2f",
                    pizzaName, pizza.getSize(), pizza.getCrust(), pizza.getSauce(), pizza.getTopping(), pizza.getCheese(), finalPrice);
            Customer.addFavourites(customerId, favoritePizzaDetails);
        }

        // adding order to the map
        Order newOrder = new Order(orderId, customerId, pizzaName, pizza.toString(), totalPrice, orderTime);
        orders.computeIfAbsent(customerId, k -> new ArrayList<>()).add(newOrder);

        System.out.println("\n--------------------------YOUR FINAL ORDER--------------------------\n" + pizza);

        LoyaltyProgram.getLoyaltyAmount(customerId, Main.customer.getUsername());

        System.out.print("Do you want to view loyalty program offers? (enter 1 if yes):");
        yesOrNo = scanner.nextInt();

        if(yesOrNo == 1){
            finalPrice = LoyaltyProgram.useLoyaltyAmount(customerId, pizza.getTotalPrice());
        }

        LoyaltyProgram.addLoyaltyAmount(customerId, Main.customer.getUsername());

        finalPrice = SeasonalPromotionAndDiscountsStrategy.choosePromotionOrDiscount(finalPrice);

        int pickupOrDelivery = orderForPickupOrDelivery();
        PaymentStrategy.choosePaymentMethod(pickupOrDelivery, finalPrice);

        trackOrder(pickupOrDelivery);
        getOrderFeedback(orderId);
    }

    public static void orderFromPizzaMenu() {
        double pizzaPrice = Pizza.getPizzaMenu();
        PizzaComponent basePizza = null;
        double extraSaucePrice = 0;
        double extraCheesePrice = 0;
        double extraPackagePrice = 0;

        if (pizzaPrice == 3900.00) {
            basePizza = new VeggiePizza();
        } else if (pizzaPrice == 4000.00) {
            basePizza = new ChickenPizza();
        } else if (pizzaPrice == 4200.00) {
            basePizza = new PepperoniPizza();
        }

        System.out.print("\nDo you want to add extra sauce? (enter 1 if yes): ");
        int yesOrNo = scanner.nextInt();

        if (yesOrNo == 1) {
            basePizza = new SauceDecorator(basePizza);
            extraSaucePrice = basePizza.getExtraPrice();
            System.out.println("Added " + extraSaucePrice);
        }

        System.out.print("\nDo you want to add extra cheese? (enter 1 if yes): ");
        yesOrNo = scanner.nextInt();

        if (yesOrNo == 1) {
            basePizza = new CheeseDecorator(basePizza);
            extraCheesePrice = basePizza.getExtraPrice();
            System.out.println("Added " + extraCheesePrice);
        }

        System.out.print("\nDo you want special packaging? (enter 1 if yes): ");
        yesOrNo = scanner.nextInt();

        if (yesOrNo == 1) {
            basePizza = new PackageDecorator(basePizza);
            extraPackagePrice = basePizza.getExtraPrice();
            System.out.println("Added " + extraPackagePrice);
        }

        assert basePizza != null;
        double finalPrice = pizzaPrice + extraSaucePrice + extraCheesePrice + extraPackagePrice;

        System.out.println("\n--------------------------YOUR FINAL ORDER--------------------------\n");
        System.out.println(basePizza.getPizzaName() + "\t\t\t\t\t\t\tRs." + pizzaPrice + "\n" +
                basePizza.getDescription() + "\nExtra Sauce: \t\t\t\t\t\t\tRs." + extraSaucePrice + "\n" +
                "Extra Cheese: \t\t\t\t\t\t\tRs." + extraCheesePrice + "\n" + "Special Packaging: \t\t\t\t\t\tRs." + extraPackagePrice +
                "\nYour Total\t\t\t\t\t\t\t\tRs." + finalPrice + "\n");

        int customerId = Main.customer.getCustomerID(); // Assuming `customer` has `getCustomerID()`
        int orderId = nextOrderId++;
        String orderTime = LocalTime.now().toString();

        LoyaltyProgram.getLoyaltyAmount(customerId, Main.customer.getUsername());
        System.out.print("Do you want to view loyalty program offers? (enter 1 if yes):");
        yesOrNo = scanner.nextInt();

        if(yesOrNo == 1){
            finalPrice = LoyaltyProgram.useLoyaltyAmount(customerId, finalPrice);
        }
        LoyaltyProgram.addLoyaltyAmount(customerId, Main.customer.getUsername());

        finalPrice = SeasonalPromotionAndDiscountsStrategy.choosePromotionOrDiscount(finalPrice);

        int pickupOrDelivery = orderForPickupOrDelivery();
        PaymentStrategy.choosePaymentMethod(pickupOrDelivery, finalPrice);

        // adding order to the map
        Order newOrder = new Order(orderId, customerId, basePizza.getPizzaName(), basePizza.getDescription(), finalPrice, orderTime);
        orders.computeIfAbsent(customerId, k -> new ArrayList<>()).add(newOrder);

        trackOrder(pickupOrDelivery);
        getOrderFeedback(orderId);
    }

    public static void orderFromFavourites(String pizzaDetails){

        String[] details = pizzaDetails.split(", ");
        String pizzaName = "";
        StringBuilder description = new StringBuilder();
        double finalPrice = 0.0;

        //loop to get specific details from concatenated string
        for (String detail : details) {
            if (detail.startsWith("Pizza Name: ")) {
                pizzaName = detail.substring("Pizza Name: ".length());
            } else if (detail.startsWith("Price: Rs.")) {
                try {
                    finalPrice = Double.parseDouble(detail.substring("Price: Rs.".length()));
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing price: " + e.getMessage());
                }
            } else {
                String[] labelValue = detail.split(": ", 2);
                if (labelValue.length == 2) {
                    description.append(labelValue[1]).append(", ");
                } else {
                    description.append(detail).append(", ");
                }
            }
        }

        // format the text to remove spaces
        if (!description.isEmpty()) {
            description = new StringBuilder(description.substring(0, description.length() - 2));
        }

        int orderId = nextOrderId++;

        // format the text to be displayed
        System.out.println("\n--------------------------YOUR FINAL ORDER--------------------------\n");
        System.out.println("Pizza Name: " + pizzaName);
        System.out.println("Description: " + description);
        System.out.printf("Your total: Rs.%.2f%n", finalPrice); // Ensure price has two decimal places
        System.out.println("--------------------------");



        LoyaltyProgram.getLoyaltyAmount(Main.customer.getCustomerID(), Main.customer.getUsername());
        System.out.print("Do you want to view loyalty program offers? (enter 1 if yes):");
        int yesOrNo = scanner.nextInt();

        if(yesOrNo == 1){
            finalPrice = LoyaltyProgram.useLoyaltyAmount(Main.customer.getCustomerID(), finalPrice);
        }
        LoyaltyProgram.addLoyaltyAmount(Main.customer.getCustomerID(), Main.customer.getUsername());

        finalPrice = SeasonalPromotionAndDiscountsStrategy.choosePromotionOrDiscount(finalPrice);

        int pickupOrDelivery = orderForPickupOrDelivery();
        PaymentStrategy.choosePaymentMethod(pickupOrDelivery, finalPrice);

        trackOrder(pickupOrDelivery);
        getOrderFeedback(orderId);
    }

    public static int orderForPickupOrDelivery(){
        System.out.print("Do you want to pickup the order or get it delivered? (enter 1 if pickup or 2 for delivery): ");
        return scanner.nextInt();
    }

    public static void trackOrder(int pickupOrDelivery){

        Customer customerName = new Customer(Main.customer.getUsername());

        OrderStatusNotifier notifier = new OrderStatusNotifier();
        notifier.addObserver(customerName);

        order.ordered();
        order.preparing();
        notifier.setStatus("is prepared");
        if (pickupOrDelivery == 1){
            order.readyForPickup();
            notifier.setStatus("is waiting to be picked up");
        } else{
            order.outForDelivery();
            notifier.setStatus("is at your doorstep");
        }
        if (pickupOrDelivery == 1){
            order.pickedUp();
        } else{
            order.delivered();
        }
        notifier.setStatus("is completed");
        notifier.removeObserver(customerName);
    }

    public static void getOrderFeedback(int orderID){
        FeedbackReceiver feedbackReceiver = new FeedbackReceiver();
        FeedbackInvoker invoker = new FeedbackInvoker();

        System.out.print("\nPlease give your feedback: ");
        String feedback = scanner.next();

        Command getFeedbackCommand = new GetFeedbackCommand(feedbackReceiver, orderID, feedback);
        invoker.setCommand(getFeedbackCommand);
        invoker.pressButton();

        System.out.print("\nDo you want to undo your feedback? (enter 1 if yes): ");
        int yesOrNo = scanner.nextInt();

        if(yesOrNo == 1){
            invoker.pressUndo();
        }

        Command viewFeedbackCommand = new ViewFeedbackCommand(feedbackReceiver, orderID);
        invoker.setCommand(viewFeedbackCommand);
        invoker.pressButton();
        System.out.println("Thank you for your feedback!");
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        Order.totalPrice = totalPrice;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public static int getNextOrderId() {
        return nextOrderId;
    }

    public static void setNextOrderId(int nextOrderId) {
        Order.nextOrderId = nextOrderId;
    }

    public static Map<Integer, List<Order>> getOrders() {
        return orders;
    }

    public static void setOrders(Map<Integer, List<Order>> orders) {
        Order.orders = orders;
    }

}
