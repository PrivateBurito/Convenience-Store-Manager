import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    String transactionType;
    Item item;
    String dateCreated;

    public Transaction(String _transactionType, Item _item) {
        transactionType = _transactionType;
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
        item.displayDetails();
        System.out.println("Date created: " + dateCreated);
        System.out.println("----------------------------");
    }

}
