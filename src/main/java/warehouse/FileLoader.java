package warehouse;
import java.util.*;

public interface FileLoader<K, V> {
    boolean load(String path);
    String get();
    Map<K, V> getMap();
}
