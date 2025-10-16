import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    String transactionType;
    Item item;
    double buyingPrice;
    int newQuantity;
    String dateCreated;

    public Transaction(String _transactionType, Item _item, double _buyingPrice, int _newQuantity) {
        transactionType = _transactionType;
        buyingPrice = _buyingPrice;
        newQuantity = _newQuantity;
        item = _item;
        dateCreated = getDate();
    }

    private String getDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return dateCreated = now.format(formatter);
    }

    public void displayDetails() {
      System.out.println("Type: " + transactionType);
        System.out.println("Item Name: " + item.itemName);
        System.out.println("Item Price: " + item.itemPrice);
        System.out.println("Item Current Quantity: " + item.quantity);
        System.out.println("Item Added Quantity: " + newQuantity);
        System.out.println("Buying Price: " + buyingPrice);
        System.out.println("Date created: " + dateCreated);
        System.out.println("----------------------------");
    }

}
