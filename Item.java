public class Item {
    String itemName;
    double itemPrice;
    int quantity;

    public Item(String _itemName, double _itemPrice, int _quantity){
        itemName = _itemName;
        itemPrice = _itemPrice;
        quantity = _quantity;
    }

    public void displayDetails(){
        System.out.println("Name: " + itemName);
        System.out.println("Price: " + itemPrice);
        System.out.println("Quantity: " + quantity);
    }
}
