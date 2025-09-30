import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);

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
            System.out.println("Current inventory:");

            if (inventory.itemsList.size() <= 0) {
                System.out.println("Nothing.");
            } else {
                for (int i = 0; i < inventory.itemsList.size(); i++) {
                    Item item = inventory.itemsList.get(i);
                    item.displayDetails();
                }
            }

            System.out.println("-------------------------");
            System.out.println("1. Add items to inventory");
            System.out.println("2. Exit");
            
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.print("Name: ");
                    String itemName = scanner.nextLine();
                    System.out.print("Price: ");
                    String itemPrice = scanner.nextLine();
                    int itemPriceInt = Integer.parseInt(itemPrice);
                    Item newItem = new Item(itemName, itemPriceInt);
                    inventory.money -= newItem.itemPrice;
                    inventory.itemsList.add(newItem);

                    System.out.println("");
                    System.out.println("Purchased " + newItem.itemName);
                    System.out.println("Remaining money: " + inventory.money);
                    break;
                case "2":
                    System.out.println("> Exited inventory");
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
