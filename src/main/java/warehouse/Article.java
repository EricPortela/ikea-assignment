package warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Article {

    @JsonProperty("art_id")
    private int articleID;

    @JsonProperty("name")
    private String name;

    @JsonProperty("stock")
    private int stock;

    public Article() {
    }

    public int getArticleId() {
        return this.articleID;
    }
    
    public String getArticleName() {
        return this.name;
    }

    public int getArticleStock() {
        return this.stock;
    }

    public void setArticleStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "\t{article_id: " + articleID + ", name: " + name + ", stock: " + stock + "}";
    }


    
}
