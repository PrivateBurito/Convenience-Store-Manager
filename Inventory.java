import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {
    ArrayList<Item> itemsList = new ArrayList<Item>();
    ArrayList<Transaction> transactionList = new ArrayList<>();
    int money;
    static Scanner scanner = new Scanner(System.in);
    
    public Inventory(int _money){
        money = _money;
    }

    public void sellItem(Inventory inventory, int invnetoryIndex){
        Item item = inventory.itemsList.get(invnetoryIndex);
        System.out.println("Item details:");
        item.displayDetails();
        System.out.println("-------------------------");
        System.out.print("Enter quantity to be bought: ");
        String quantitySold = scanner.nextLine();
        int quantitySoldInt = Integer.parseInt(quantitySold);

        item.quantity -= quantitySoldInt;
        double profit = item.itemPrice * quantitySoldInt;
        inventory.money += profit;

        Transaction transaction = new Transaction("Sell Item", item, profit, item.quantity);
        inventory.transactionList.add(transaction);
        
        System.out.println(quantitySoldInt + " " + item.itemName + " has been bought!");
        System.out.println("Made P" + profit + " of profit!");
        // System.out.println("Press any key to continue...");
        // scanner.nextLine();

    }
}
