package warehouse;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

    @JsonProperty("name")
    private String name;

    @JsonProperty("contain_articles")
    private List<ProductPart> containArticles;

    public Product() {
    }

    public String getProductName() {
        return name;
    }

    public List<ProductPart> getProductParts() {
        return containArticles;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("\n" + name + "\n");

        for (ProductPart part : containArticles) {
            sb.append("\n" + part.toString() + "\n");
        }

        return sb.toString();
    }
    
}
