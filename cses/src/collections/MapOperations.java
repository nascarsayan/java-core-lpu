package collections;

import java.util.*;

public class MapOperations {
    public static void main(String[] args) {
        // 1. Common Map Implementations

        // Unordered Map
        HashMap<String, Integer> hashMap = new HashMap<>();
        var hashMap2 = new HashMap<String, Integer>();

        // Ordered Map
        TreeMap<String, Integer> treeMap = new TreeMap<>();


        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();

        // 2. Basic Operations
        // Adding elements
        hashMap.put("key1", 100);
        hashMap.putIfAbsent("key2", 200);      // Only if key doesn't exist

        // Retrieving elements
        Integer value = hashMap.get("key11");       // Returns null if not found
        int defaultValue = hashMap.getOrDefault("key3", 0);  // Returns default if not found

        // Removing elements
        hashMap.remove("key1");                // Remove by key
        hashMap.remove("key1", 100);           // Remove only if key-value matches

        // Checking existence
        boolean hasKey = hashMap.containsKey("key1");
        boolean hasValue = hashMap.containsValue(100);

        // Size operations
        int size = hashMap.size();
        boolean isEmpty = hashMap.isEmpty();

        var anotherMap = new HashMap<String, Integer>();

        // 3. Bulk Operations
        hashMap.putAll(anotherMap);            // Add all from another map
        // hashMap.clear();                       // Remove all entries

        // 4. Views of the Map
        Set<String> keys = hashMap.keySet();   // Set of keys
        Collection<Integer> values = hashMap.values(); // Collection of values
        Set<Map.Entry<String, Integer>> entries = hashMap.entrySet(); // Set of entries

        // 5. Modern API Operations (Java 8+)
        // Compute values
        hashMap.compute("key1", (k, v) -> (v == null) ? 1 : v + 1);
        hashMap.computeIfAbsent("key2", k -> k.length());
        hashMap.computeIfPresent("key3", (k, v) -> v * 2);

        // Merge values
        hashMap.merge("key1", 1, Integer::sum);  // Add if exists, put if doesn't
    }
}