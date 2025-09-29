import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);
    public static void purchaseMenu(){
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
    public static void inventoryMenu(){
        System.out.println("Current inventory:");
        System.out.println("nothing.");
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
    public static void transactionMenu(){
        System.out.println("Transaction history:");
        System.out.println("nothing.");
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
}
