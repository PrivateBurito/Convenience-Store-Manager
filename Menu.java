import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    static Scanner scanner = Main.scanner;

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

    private static Integer tryParseInt(String str) { // converts string to int but returns null if not possible
        if (str == null || str.trim().isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static Double tryParseDouble(String str) { // converts string to double but returns null if not possible
        if (str == null || str.trim().isEmpty()) {
            return null;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static void createGap(int spaces) {
        for (int i = 0; i < spaces; i++) {
            System.out.println("");
        }
    }

    public static void purchaseMenu(Inventory inventory) {
        int page = 1;
        int itemsBeDisplayed = 5;
        while (true) {
            int minDisplayed = 0;
            int maxDisplayed = 5;
            if (inventory.itemsList.size() <= itemsBeDisplayed) {
                maxDisplayed = inventory.itemsList.size();
            }
            if (inventory.itemsList.size() <= 0) {
                System.out.println("No items available for purchase.");
                return;
            }
            for (int i = 1; i < page; i++) {
                minDisplayed += itemsBeDisplayed;
                maxDisplayed += itemsBeDisplayed;
            }
            
            displayPage(displayType.Item, inventory, page, minDisplayed, maxDisplayed);
            for (int i = minDisplayed; i < maxDisplayed; i++) {
                Item item = inventory.itemsList.get(i);
                System.out.println((i + 1) + ". " + item.itemName);
            }

            int prevPage = maxDisplayed + 1, nextPage = maxDisplayed + 2, exitOption = maxDisplayed + 3;
            String preString = String.valueOf(prevPage), nextString = String.valueOf(nextPage),
                    exitString = String.valueOf(exitOption);
            System.out.println(prevPage + ". Previous Page");
            System.out.println(nextPage + ". Next Page");
            System.out.println(exitOption + ". Exit");
            System.out.print("Enter Command: ");
            String choice = scanner.nextLine();
            if (choice.equals(preString)) {
                page--;
                if (page <= 0) {
                    page = 1;
                }
            } else if (choice.equals(nextString)) {
                page++;
            } else if (choice.equals(exitString)) {
                return;
            } else {
                int choiceInt = tryParseInt(choice) - 1;
                if (choiceInt >= minDisplayed && choiceInt < maxDisplayed) {
                    inventory.sellItem(inventory, choiceInt);
                    System.out.println("Press Enter to continue");
                    scanner.nextLine();
                } else {
                    System.out.println("Something went wrong!");
                }
            }
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

                    double itemPriceInt = tryParseDouble(itemPrice);
                    int itemQuantityInt = tryParseInt(itemQuantity);
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

                        double missingFunds = itemPriceInt - inventory.money;
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
                        double sellPrice = (double) (itemQuantityInt / itemPriceInt) * 1.5;
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
                    int editChoiceInt = (tryParseInt(editChoice) - 1);
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

                    for (int i = 0; i < 50; i++) {
                        int testPrice = 10;
                        int testQuantity = 50;
                        double testSellPrice = (double) (testQuantity / testPrice) * 1.5;
                        String testName = "TestItem" + i;
                        Item testItem = new Item(testName, testSellPrice, testQuantity);
                        Transaction testTransaction = new Transaction("Inventory Purchase", testItem,
                                testPrice,
                                testQuantity);
                        inventory.transactionList.add(testTransaction);

                        inventory.money -= testItem.itemPrice;
                        inventory.itemsList.add(testItem);
                    }
                    break;
                default:
                    System.out.println("INVENTORY: Something went wrong!");
                    break;
            }
        }

    }

    public static void transactionMenu(Inventory inventory) {
        int page = 1;
        int itemsBeDisplayed = 5;
        while (true) {
            int minDisplayed = 0;
            int maxDisplayed = 5;
            for (int i = 1; i < page; i++) {
                minDisplayed += itemsBeDisplayed;
                maxDisplayed += itemsBeDisplayed;
            }
            displayPage(displayType.Transaction, inventory, page, minDisplayed, maxDisplayed);
            System.out.println("1. Previous Page");
            System.out.println("2. Next Page");
            System.out.println("3. Exit");
            System.out.print("Enter Command: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1": // Previous Page
                    page--;
                    if (page <= 0) {
                        page = 1;
                    }
                    break;
                case "2": // Next Page
                    page++;
                    break;
                case "3": // Exit
                    return;
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
        String enteredPrice = scanner.nextLine();

        if (isNotInt(enteredPrice)) {
            System.out.println("Enter valid price.");
            return;
        }

        double price = tryParseDouble(enteredPrice);
        item.itemPrice = price;

        createGap(10);
        System.out.println("Edit Item Price");
        System.out.println("=========================");
        System.out.println("Name: " + item.itemName);
        System.out.println("Current Price: " + item.itemPrice);
        System.out.println("=========================");
        System.out.println("> Updated the price of [" + item.itemName + "] to P" + price);
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    public enum displayType {
        Transaction, Item
    }

    public static void displayPage(displayType type, Inventory inventory, int page, int minumumDisplayed,
            int maxDisplayed) {

        System.out.println(type + ":");
        System.out.println("==================================");
        if (type == displayType.Transaction) {
            // checks if maxIndex is greater than the size of transaction list size
            if (maxDisplayed > inventory.transactionList.size()) {
                maxDisplayed = inventory.transactionList.size();
            }
            for (int i = minumumDisplayed; i < maxDisplayed; i++) { // display the individual transaction
                Transaction transaction = inventory.transactionList.get(i);
                transaction.displayDetails();
                minumumDisplayed++;
            }
        } else if (type == displayType.Item) {
            if (maxDisplayed > inventory.itemsList.size()) {
                maxDisplayed = inventory.itemsList.size();
            }
            for (int i = minumumDisplayed; i < maxDisplayed; i++) {
                Item item = inventory.itemsList.get(i);
                item.displayDetails();
                System.out.println("----------------------------------");
                minumumDisplayed++;
            }
        }

        System.out.println("==================================");
        System.out.println("Page " + page);
    }
}
