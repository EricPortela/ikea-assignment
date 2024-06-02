package warehouse;

public class main {
    public static void main(String[] args) {
        
        Warehouse wh = new Warehouse();

        wh.loadInventory("data/inventory.json");
        System.out.println(wh.getInventory());

        wh.loadProducts("data/products.json");
        System.out.println(wh.getProducts());

        System.out.println(wh.getAvailableProducts());

        System.out.println(wh.sellProduct("Dinning Table", 1));
        System.out.println(wh.sellProduct("Dining Chair", 1));
        System.out.println(wh.sellProduct("Dining Chair", 2));

        System.out.println(wh.getAvailableProducts());
        System.out.println(wh.sellProduct("Dining Chair", 1));
        System.out.println(wh.getInventory());
    }
}
