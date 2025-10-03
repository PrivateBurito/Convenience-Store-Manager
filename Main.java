import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static boolean isActive = true;

    public static void main(String[] args) {
        Inventory inventory = new Inventory(1000); // setups the initial money
        while (isActive) { // will always loop the program unless user selects Exit which terminates the loop
            startProgram(inventory);
        }
    }

    private static void startProgram(Inventory inventory) {
        System.out.println("|| Convinience Store Manager ||");
        System.out.println("-------------------------------");
        System.out.println("Money: " + inventory.money);
        System.err.println("");
        System.out.println("1. Make purchase");
        System.out.println("2. Check Inventory");
        System.out.println("3. Open Transaction History");
        System.out.println("4. Exit");
        System.out.println("-------------------------------");
        System.out.println("Enter command:");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1": // make purchase
                createGap(10);
                Menu.purchaseMenu();
                break;
            case "2": // check inventory
                createGap(10);
                Menu.inventoryMenu(inventory);
                break;
            case "3": // open transaction history
                createGap(10);
                Menu.transactionMenu(inventory);
                break;
            case "4":
                isActive = false;
                break;
            default:
                break;
        }
    }
    private static void createGap(int spaces){
        for (int i = 0; i < spaces; i++) {
            System.out.println("");
        }
    }
}
