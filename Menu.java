import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);

    public static boolean isNotInt(String str) {
        if (str == null || str.trim().isEmpty()) {
            return true; // if empty, then its not int
        }
        try {
            Integer.parseInt(str);
            return false; // is int
        } catch (NumberFormatException e) {
            return true; // not int
        }
    }

    private static void createGap(int spaces){
        for (int i = 0; i < spaces; i++) {
            System.out.println("");
        }
    }

    public static void purchaseMenu() {
        System.out.println("Select items to be purchased:");
        System.out.println("Complete!");
        // pretend something is happening here
        System.out.println("Press Enter to continue");
        String choice = scanner.nextLine();
        switch (choice) {
            case "":
                break;

            default:
                break;
        }
    }

    public static void inventoryMenu(Inventory inventory) {
        boolean isActive = true;
        while (isActive) {
            System.out.println("Current Money: " + inventory.money);
            System.out.println("INVENTORY");
            System.out.println("=========================");

            if (inventory.itemsList.size() <= 0) {
                System.out.println("");
                System.out.println("");
                System.out.println("");
            } else { // lists the items from the item arraylist. terrible implementation, refer to todo #2
                for (int i = 0; i < inventory.itemsList.size(); i++) {
                    Item item = inventory.itemsList.get(i);
                    item.displayDetails();
                }
            }

            System.out.println("=========================");
            System.out.println("1. Add items to inventory");
            System.out.println("2. Exit");
            System.out.println("-------------------------");
            System.out.print("Enter Command: ");
            String choice = scanner.nextLine();
            createGap(10);

            switch (choice) {
                case "1": // add item to inventory
                    System.out.print("Name: ");
                    String itemName = scanner.nextLine();
                    System.out.print("Price: ");
                    String itemPrice = scanner.nextLine();
                    
                    int itemPriceInt = Integer.parseInt(itemPrice); // converts string to int

                    if (isNotInt(itemPrice)) { // checks if string entered can be int. if true then its not
                        System.out.println("Enter a valid price.");
                        break;
                    }
                    if (itemPriceInt > inventory.money) { // checks if you have enough money to make the purchase
                        System.out.println("Insufficient funds.");
                        System.out.println("Entered item price: P" + itemPriceInt + " | Current funds: P" + inventory.money);

                        int missingFunds = itemPriceInt - inventory.money;
                        System.out.println("Missing P" + missingFunds);
                        break;
                    }
                    
                    Item newItem = new Item(itemName, itemPriceInt);

                    inventory.money -= newItem.itemPrice;
                    inventory.itemsList.add(newItem);

                    System.out.println("");
                    System.out.println("Purchased " + newItem.itemName);
                    System.out.println("Remaining money: " + inventory.money);
                    System.out.println("");
                    break;
                case "2":
                    System.out.println("> Exited inventory");
                    createGap(10);
                    isActive = false;
                    break;
                default:
                    System.out.println("INVENTORY: Something went wrong!");
                    break;
            }
        }

    }

    public static void transactionMenu() {
        System.out.println("Transaction history:");
        System.out.println("nothing.");
        // pretend something is happening here
        System.out.println("Press Enter to continue");
        String choice = scanner.nextLine();
        switch (choice) {
            case "":
                break;

            default:
                System.out.println("Something went wrong!");
                break;
        }
    }
}
