public class Item {
    static String itemName;
    static int itemPrice;

    public Item(String _itemName, int _itemPrice){
        itemName = _itemName;
        itemPrice = _itemPrice;
    }

    public void displayDetails(){
        System.out.println("Name: " + itemName);
        System.out.println("Price: " + itemPrice);
    }
}
