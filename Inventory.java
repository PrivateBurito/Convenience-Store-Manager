import java.util.ArrayList;

public class Inventory {
    ArrayList<Item> itemsList = new ArrayList<Item>();
    ArrayList<Transaction> transactionList = new ArrayList<>();
    int money;
    
    public Inventory(int _money){
        money = _money;
    }
}
