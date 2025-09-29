import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static boolean isActive = true;

    public static void main(String[] args) {
        while (isActive) {
            startProgram();
        }
    }

    private static void startProgram() {
        System.out.println("|| Convinience Store Manager ||");
        System.out.println("-------------------------------");
        System.out.println("1. Make purchase");
        System.out.println("2. Check Inventory");
        System.out.println("3. Open Transaction History");
        System.out.println("4. Exit");
        System.out.println("-------------------------------");
        System.out.println("Enter command:");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1": // make purchase
                createGap();
                Menu.purchaseMenu();
                break;
            case "2": // check inventory
                createGap();
                Menu.inventoryMenu();
                break;
            case "3": // open transaction history
                createGap();
                Menu.transactionMenu();
                break;
            case "4":
                isActive = false;
                break;
            default:
                break;
        }
    }
    private static void createGap(){
        int spaces = 10;
        for (int i = 0; i < spaces; i++) {
            System.out.println("");
        }
    }
}
