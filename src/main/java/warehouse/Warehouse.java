// ## The Task
// The assignment is to implement a warehouse software. This software should hold articles, 
// and the articles should contain an identification number, a name and available stock. 
// It should be possible to load articles into the software from a file, see the attached
// inventory.json.
// The warehouse software should also have products, products are made of different articles. 
// Products should have a name, price and a list of articles of which they are made from with 
// a quantity. 
// The products should also be loaded from a file, see the attached products.json. 


// The warehouse should have at least the following functionality;
// * Get all products and quantity of each that is an available with the current inventory
// * Remove(Sell) a product and update the inventory accordingly


// Definitions
// warehouse should have (articles + products)
//
// articles should have (id + name + available stock)
//
// products = different articles
// products should have (name + price + list of articles + quantity)

package warehouse;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {

    private Map<String, Integer> availableProducts; //This represents current products that can be made from inventory - Format: {name: amount}
    private Map<Integer, Article> inventoryMap; //This represents current inventory - Format: {id: Article}
    private Map<String, Product> productMap; //This represents all products (doesnt take into account inv) - Format: {name: Product}

    public Warehouse() {
        this.availableProducts = new HashMap<>();
        this.inventoryMap = new HashMap<>();
        this.productMap = new HashMap<>();
    }

    public boolean loadInventory(String path) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            Inventory inventory = mapper.readValue(new File(path), Inventory.class);

            this.inventoryMap = inventory.getInventory().stream()
                    .collect(Collectors.toMap(Article::getArticleId, article -> article));
            
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
        ObjectMapper mapper = new ObjectMapper();

        try {
            Products products = mapper.readValue(new File(path), Products.class);

            this.productMap = products.getProducts().stream()
                    .collect(Collectors.toMap(Product::getProductName, product -> product));

            updateAvailableProducts();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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

            for (ProductPart part: product.getProductParts()) {
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
                List<ProductPart> parts = product.getProductParts();
    
                for (ProductPart part : parts) {
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