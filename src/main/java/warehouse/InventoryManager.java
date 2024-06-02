package warehouse;

import java.io.File;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

public class InventoryManager implements FileLoader<Integer, Article> {

    private Map<Integer, Article> inventoryMap;
    private final ObjectMapper mapper;

    public InventoryManager(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean load(String path) {
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

    @Override
    public String get() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\nCurrent Inventory:\n");

        for (Map.Entry<Integer, Article> entry: inventoryMap.entrySet()) {
            sb.append("\n" + entry.getValue().toString()+ "\n");
        }
        return sb.toString();
    }

    @Override
    public Map<Integer, Article> getMap() {
        return inventoryMap;
    }
    
}
