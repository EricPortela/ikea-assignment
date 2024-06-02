package warehouse;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

    @JsonProperty("name")
    private String name;

    @JsonProperty("contain_articles")
    private List<ProductArticle> containArticles;

    public Product() {
    }

    public String getProductName() {
        return name;
    }

    public List<ProductArticle> getProductParts() {
        return containArticles;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("\n" + name + "\n");

        for (ProductArticle part : containArticles) {
            sb.append("\n" + part.toString() + "\n");
        }

        return sb.toString();
    }
    
}
