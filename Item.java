public class Item {
    String itemName;
    int itemPrice;

    public Item(String _itemName, int _itemPrice){
        itemName = _itemName;
        itemPrice = _itemPrice;
    }

    public void displayDetails(){
        System.out.println("Name: " + itemName);
        System.out.println("Price: " + itemPrice);
    }
}
