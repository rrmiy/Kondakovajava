import java.util.*;

public class Cache<K, V> {
    private final int maxSize;
    private final LinkedHashMap<K, V> cache;

    public Cache(int maxSize) {
        this.maxSize = maxSize;
        this.cache = new LinkedHashMap<K, V>(maxSize, 0.75f, true) { //порядок доступа
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > maxSize;
            }
        };
    }


    public void put(K key, V value) { // Добавление
        cache.put(key, value);
    }

    public V get(K key) {     // Получение
        return cache.get(key);
    }

    public V remove(K key) {     // Удаление
        return cache.remove(key);
    }

    public boolean containsKey(K key) {                  //наличие ключа
        return cache.containsKey(key);
    }

    public int size() {
        return cache.size();
    }

    public void printCache() {
        System.out.println("Cache contents (" + size() + "/" + maxSize + "):");
        for (Map.Entry<K, V> entry : cache.entrySet()) {
            System.out.println("  " + entry.getKey() + " → " + entry.getValue());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Cache<String, Integer> cache = new Cache<>(3);

        System.out.println("Тестирование LRU кеша String → Integer");

        System.out.println("Добавляем элементы:");
        cache.put("one", 1);
        cache.put("two", 2);
        cache.put("three", 3);
        cache.printCache();

        System.out.println("Обращаемся к элементу 'one': " + cache.get("one"));
        cache.printCache();

        System.out.println("Добавляем четвертый элемент 'four':");
        cache.put("four", 4);
        cache.printCache();

        System.out.println("Проверяем наличие 'two': " + cache.containsKey("two"));
        System.out.println("Проверяем наличие 'three': " + cache.containsKey("three"));
        System.out.println("Проверяем наличие 'one': " + cache.containsKey("one"));
        System.out.println("Проверяем наличие 'four': " + cache.containsKey("four"));

        System.out.println("\nДобавляем 'five' и 'six':");
        cache.put("five", 5);
        cache.printCache();
        cache.put("six", 6);
        cache.printCache();

        System.out.println("Обращаемся к 'four': " + cache.get("four"));
        cache.printCache();

    }
}