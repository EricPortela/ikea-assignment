package warehouse;

import java.util.*;
import java.util.stream.Collectors;

public class Inventory {
    private List<Article> inventory;

    public Inventory() {
    }

    public List<Article> getInventory() {
        return inventory;
    }

    public void setInventory(List<Article> inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\nCurrent Inventory\n");
        
        for (Article article: inventory) {
            sb.append("\n" + article.toString() + "\n");
        }

        return sb.toString();
    }
}
