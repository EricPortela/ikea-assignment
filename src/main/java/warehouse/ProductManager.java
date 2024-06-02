package warehouse;

import java.io.File;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductManager implements FileLoader<String, Product> {

    private Map<String, Product> productMap;
    private final ObjectMapper mapper;

    public ProductManager(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean load(String path) {
        try {
            Products products = mapper.readValue(new File(path), Products.class);

            this.productMap = products.getProducts().stream()
                    .collect(Collectors.toMap(Product::getProductName, product -> product));

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String get() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Product> entry: productMap.entrySet()) {
            sb.append("\n" + entry.getValue().toString()+ "\n");
        }
        return sb.toString();
    }

    @Override
    public Map<String, Product> getMap() {
        return productMap;
    }

    

    
    
}
