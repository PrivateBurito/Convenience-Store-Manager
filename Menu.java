import java.util.ArrayList;
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

    private static void createGap(int spaces) {
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
            } else { // lists the items from the item arraylist. terrible implementation, refer to
                     // todo #2
                for (int i = 0; i < inventory.itemsList.size(); i++) {
                    Item item = inventory.itemsList.get(i);
                    item.displayDetails();
                    System.out.println("");
                }
            }

            System.out.println("=========================");
            System.out.println("1. Add items to inventory");
            System.out.println("2. Edit Sell Price of Item");
            System.out.println("3. Exit");
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
                    System.out.print("Quantity: ");
                    String itemQuantity = scanner.nextLine();

                    if (isNotInt(itemPrice) || isNotInt(itemQuantity)) { // checks if string entered can be int. if true
                                                                         // then its not
                        System.out.println("Enter a valid price.");
                        break;
                    }

                    int itemPriceInt = Integer.parseInt(itemPrice); // converts string to int
                    int itemQuantityInt = Integer.parseInt(itemQuantity);
                    /*
                     * Q: Why bother converting a string to int if you can just take an int input?
                     * A: There's an issue where it gives a blank input after the first loop
                     * triggering the default case in the button without the user doing anything. So
                     * when
                     * trying to get an int input from a scanner, just get the string and convert it
                     * to int
                     */
                    if (itemPriceInt > inventory.money) { // checks if you have enough money to make the purchase
                        System.out.println("Insufficient funds.");
                        System.out.println(
                                "Entered item price: P" + itemPriceInt + " | Current funds: P" + inventory.money);

                        int missingFunds = itemPriceInt - inventory.money;
                        System.out.println("Missing P" + missingFunds);
                        break;
                    }
                    createGap(10);

                    ArrayList<Item> items = inventory.itemsList;
                    boolean itemExists = false;
                    for (Item item : items) { // checks if item already exists
                        if (itemName.equals(item.itemName)) {
                            System.out.println("Item already exists.");
                            System.out.println("Added [" + itemQuantity + "] " + itemName + " to the quantity.");
                            item.quantity += itemQuantityInt;
                            itemExists = true;

                            inventory.money -= itemPriceInt;

                            Transaction newTransaction = new Transaction("Inventory Purchase", item, itemPriceInt,
                                    itemQuantityInt);
                            inventory.transactionList.add(newTransaction);
                        }
                    }
                    if (!itemExists) { // if its a new item
                        double sellPrice = (itemQuantityInt / itemPriceInt) * 1.5;
                        Item newItem = new Item(itemName, sellPrice, itemQuantityInt);
                        Transaction newTransaction = new Transaction("Inventory Purchase", newItem, itemPriceInt,
                                itemQuantityInt);
                        inventory.transactionList.add(newTransaction);

                        inventory.money -= itemPriceInt;
                        inventory.itemsList.add(newItem);

                    }
                    System.out.println("Purchased [" + itemName + "] for P" + itemPrice);
                    System.out.println("Remaining money: P" + inventory.money);
                    System.out.println("");
                    break;
                case "2": // edit item prices
                    int maxIndex = inventory.itemsList.size();
                    System.out.println("Edit Item Prices");
                    System.out.println("=========================");
                    if (inventory.itemsList.size() <= 0) {
                        System.out.println("");
                        System.out.println("");
                        System.out.println("");
                    } else {
                        for (int i = 0; i < maxIndex; i++) {
                            Item item = inventory.itemsList.get(i);
                            System.out.println((i + 1) + ". Name: " + item.itemName);
                            System.out.println("Price: " + item.itemPrice);
                        }
                    }
                    System.out.println((maxIndex + 1) + ". Exit");
                    System.out.println("=========================");

                    System.out.println("Enter Command: ");
                    String editChoice = scanner.nextLine();
                    int editChoiceInt = (Integer.parseInt(editChoice) - 1);
                    if (editChoiceInt > maxIndex || editChoiceInt < 0) {
                        System.out.println("> Exit");
                        break;
                    } else {
                        System.out.println("> Editing...");
                        editItemPrice(inventory, editChoiceInt);
                        break;
                    }
                case "3":
                    System.out.println("> Exited inventory");
                    createGap(10);
                    isActive = false;
                    break;
                case "test": // idk how to fix this

                    // for (int i = 0; i < 50; i++) {
                    // int testPrice = (int)(Math.random() * ((21 - 10) + 1));
                    // int testQuantity = (int) (Math.random() * ((11 - 5) + 1));
                    // double testSellPrice = (testQuantity / testPrice) * 1.5;
                    // String testName = "TestItem" + i;
                    // Item testItem = new Item(testName, testSellPrice, testQuantity);
                    // Transaction testTransaction = new Transaction("Inventory Purchase", testItem,
                    // testPrice,
                    // testQuantity);
                    // inventory.transactionList.add(testTransaction);

                    // inventory.money -= testItem.itemPrice;
                    // inventory.itemsList.add(testItem);
                    // }
                    break;
                default:
                    System.out.println("INVENTORY: Something went wrong!");
                    break;
            }
        }

    }

    public static void transactionMenu(Inventory inventory) {
        int page = 1;
        int index = 0;
        int maxIndex = index + 5;
        boolean isActive = true;
        while (isActive) {
            System.out.println("Transaction history:");
            System.out.println("==================================");
            // checks if maxIndex is greater than the size of transaction list size
            if (maxIndex > inventory.transactionList.size()) {
                maxIndex = inventory.transactionList.size();
                System.out.println("!");
            }
            System.out.println("index: " + index + " | maxIndex: " + maxIndex);
            for (int i = index; i < maxIndex; i++) {
                Transaction transaction = inventory.transactionList.get(i);
                transaction.displayDetails();
                index++;
            }
            System.out.println("==================================");
            System.out.println("Page " + page);
            System.out.println("1. Previous Page");
            System.out.println("2. Next Page");
            System.out.println("3. Exit");
            System.out.print("Enter Command: ");
            String choice = scanner.nextLine();
            /*
             * BUG: If transaction list is NOT a multiple of 5, when going to page 1
             * and perform previous page command, program breaks
             */
            switch (choice) {
                case "1": // Previous Page
                    page--;
                    if (page <= 0) {
                        page = 1;
                        index -= maxIndex;
                    } else {
                        index -= 5 * 2;
                        maxIndex = maxIndex - 5;
                    }
                    break;
                case "2": // Next Page
                    page++;
                    maxIndex = index + 5;
                    break;
                case "3": // Exit
                    isActive = false;
                    break;
                default:
                    System.out.println("Something went wrong!");
                    break;
            }
        }

    }

    public static void editItemPrice(Inventory inventory, int index) {
        Item item = inventory.itemsList.get(index);
        System.out.println("Edit Item Price");
        System.out.println("=========================");
        System.out.println("Name: " + item.itemName);
        System.out.println("Current Price: " + item.itemPrice);
        System.out.println("=========================");
        System.out.print("\nEnter new item price: ");
        String choice = scanner.nextLine();
        double newPrice = Integer.parseInt(choice);

        if (isNotInt(choice)) {
            System.out.println("Enter valid price.");
            return;
        }
        item.itemPrice = newPrice;

        createGap(10);
        System.out.println("Edit Item Price");
        System.out.println("=========================");
        System.out.println("Name: " + item.itemName);
        System.out.println("Current Price: " + item.itemPrice);
        System.out.println("=========================");
        System.out.println("> Updated the price of [" + item.itemName + "] to P" + newPrice);
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

}
