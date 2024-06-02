package warehouse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;

public class Warehouse {

    private Map<String, Integer> availableProducts; //This represents current products that can be made from inventory - Format: {name: amount}
    private Map<Integer, Article> inventoryMap; //This represents current inventory - Format: {id: Article}
    private Map<String, Product> productMap; //This represents all products (doesnt take into account inv) - Format: {name: Product}

    private ProductManager productManager;
    private InventoryManager inventoryManager;

    public Warehouse() {
        this.availableProducts = new HashMap<>();
        this.inventoryMap = new HashMap<>();
        this.productMap = new HashMap<>();
        this.productManager = new ProductManager(new ObjectMapper());
        this.inventoryManager = new InventoryManager(new ObjectMapper());

    }

    public boolean loadInventory(String path) {
        if (inventoryManager.load(path)) {
            inventoryMap = inventoryManager.getMap();
            return true;
        }
        return false;
    }

    public String getInventory() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\nCurrent Inventory:\n");

        for (Map.Entry<Integer, Article> entry: inventoryMap.entrySet()) {
            sb.append("\n" + entry.getValue().toString()+ "\n");
        }
        return sb.toString();
    }

    public boolean loadProducts(String path) {
        if (productManager.load(path)) {
            productMap = productManager.getMap();
            updateAvailableProducts();
            return true;
        }
        return false;
    }

    public String getProducts() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Product> entry: productMap.entrySet()) {
            sb.append("\n" + entry.getValue().toString()+ "\n");
        }
        return sb.toString();
    }


    private void updateAvailableProducts() {
        availableProducts.clear();

        for (Product product: productMap.values()) {
            String productName = product.getProductName();
            int maxProductsForThisProduct = Integer.MAX_VALUE;

            for (ProductArticle part: product.getProductParts()) {
                int articleID = part.getArticleID();
                int requiredAmount = part.getAmount();

                if (inventoryMap.containsKey(articleID)) { // Basically if the part exists at all in the inventory
                    int stock = inventoryMap.get(articleID).getArticleStock();
                    if (stock < requiredAmount) {
                        maxProductsForThisProduct = 0;
                        break;
                    }
                    maxProductsForThisProduct = Math.min(maxProductsForThisProduct, stock / requiredAmount);
                } else { //Otherwise we cant create the product
                    maxProductsForThisProduct = 0;
                    break;
                }
            }
            availableProducts.put(productName, maxProductsForThisProduct);
        }
    }

    public String getAvailableProducts() {
        return "Available products: " + availableProducts.toString();
    }

    public String sellProduct(String productName, Integer quantity) {
        if (availableProducts.containsKey(productName)) {
            Integer amountOfProducts = availableProducts.get(productName);
            if (amountOfProducts >= quantity) {
                availableProducts.put(productName, amountOfProducts - quantity);
    
                Product product = productMap.get(productName);
                List<ProductArticle> parts = product.getProductParts();
    
                for (ProductArticle part : parts) {
                    Integer articleID = part.getArticleID();
                    Integer currentAmount = inventoryMap.get(articleID).getArticleStock();
                    Integer amountRequired = part.getAmount() * quantity;
    
                    inventoryMap.get(articleID).setArticleStock(currentAmount - amountRequired);
                }
    
                updateAvailableProducts();
                return "\nSold " + quantity + " " + productName;
            } else {
                return "\nThe amount of products ordered is not available. There are only " + amountOfProducts + " " + productName + ".";
            }
        }
        return "\nThe product is not available.";
    }
}