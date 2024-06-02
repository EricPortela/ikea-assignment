package warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductPart {

    @JsonProperty("art_id")
    private int articleID;

    @JsonProperty("amount_of")
    private int amount;

    public ProductPart() {

    }

    public int getArticleID() {
        return articleID;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "\t{article_id: " + articleID + ", amount: " + amount + "}";
    }
}
