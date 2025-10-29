//фильтрация по бренду, добавление товара, покупку, гарантию.

import java.util.*;
import java.time.LocalDate;

abstract class Product {
    protected String name;
    protected String brand;
    protected double price;
    protected int warrantyMonths;

    public Product(String name, String brand, double price, int warrantyMonths) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.warrantyMonths = warrantyMonths;
    }

    public abstract String getType();


    public boolean isUnderWarranty(LocalDate purchaseDate) { // Метод для проверки гарантии
        LocalDate warrantyEnd = purchaseDate.plusMonths(warrantyMonths);
        return LocalDate.now().isBefore(warrantyEnd) || LocalDate.now().isEqual(warrantyEnd);
    }

    @Override
    public String toString() {
        return String.format("%s: %s %s - %.2f руб. (гарантия: %d мес.)",
                getType(), brand, name, price, warrantyMonths);
    }


    public String getName() { return name; }
    public String getBrand() { return brand; }
    public double getPrice() { return price; }
    public int getWarrantyMonths() { return warrantyMonths; }
}


interface Electronics {
    String getOperatingSystem();
    void updateFirmware();
}


class Laptop extends Product implements Electronics {
    private int ram;
    private int storage;
    private String operatingSystem;

    public Laptop(String name, String brand, double price, int warrantyMonths,
                  int ram, int storage, String operatingSystem) {
        super(name, brand, price, warrantyMonths);
        this.ram = ram;
        this.storage = storage;
        this.operatingSystem = operatingSystem;
    }

    @Override
    public String getType() {
        return "Ноутбук";
    }

    @Override
    public String getOperatingSystem() {
        return operatingSystem;
    }

    @Override
    public void updateFirmware() {
        System.out.println("Обновление BIOS для ноутбука " + brand + " " + name);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [RAM: %dGB, SSD: %dGB, OS: %s]",
                ram, storage, operatingSystem);
    }

    public int getRam() { return ram; }
    public int getStorage() { return storage; }
}

// Класс Phone
class Phone extends Product implements Electronics {
    private double screenSize;
    private int batteryCapacity;
    private String operatingSystem;

    public Phone(String name, String brand, double price, int warrantyMonths,
                 double screenSize, int batteryCapacity, String operatingSystem) {
        super(name, brand, price, warrantyMonths);
        this.screenSize = screenSize;
        this.batteryCapacity = batteryCapacity;
        this.operatingSystem = operatingSystem;
    }

    @Override
    public String getType() {
        return "Смартфон";
    }

    @Override
    public String getOperatingSystem() {
        return operatingSystem;
    }

    @Override
    public void updateFirmware() {
        System.out.println("Обновление прошивки для смартфона " + brand + " " + name);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [Экран: %.1f\", Батарея: %dmAh, OS: %s]",
                screenSize, batteryCapacity, operatingSystem);
    }

    public double getScreenSize() { return screenSize; }
    public int getBatteryCapacity() { return batteryCapacity; }
}


class Order {
    private static int orderCounter = 0;
    private int orderId;
    private Product product;
    private LocalDate orderDate;
    private String customerName;

    public Order(Product product, String customerName) {
        this.orderId = ++orderCounter;
        this.product = product;
        this.orderDate = LocalDate.now();
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return String.format("Заказ #%d: %s - Клиент: %s, Дата: %s",
                orderId, product.getName(), customerName, orderDate);
    }

    // Геттеры
    public int getOrderId() { return orderId; }
    public Product getProduct() { return product; }
    public LocalDate getOrderDate() { return orderDate; }
    public String getCustomerName() { return customerName; }
}


class Store {
    private String storeName;
    private ArrayList<Product> products;
    private ArrayList<Order> orders;
    private HashMap<String, Integer> brandCount;

    public Store(String storeName) {
        this.storeName = storeName;
        this.products = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.brandCount = new HashMap<>();
    }


    public void addProduct(Product product) {
        try {
            if (product == null) {
                throw new IllegalArgumentException("Продукт не может быть null");
            }
            if (product.getPrice() <= 0) {
                throw new IllegalArgumentException("Цена продукта должна быть положительной");
            }

            products.add(product);

            // Обновляем счетчик брендов
            String brand = product.getBrand();
            brandCount.put(brand, brandCount.getOrDefault(brand, 0) + 1);

            System.out.println("Товар добавлен: " + product.getName());

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при добавлении товара: " + e.getMessage());
        }
    }


    public void purchaseProduct(String productName, String customerName) {
        try {
            Product productToPurchase = null;
            for (Product product : products) {
                if (product.getName().equalsIgnoreCase(productName)) {
                    productToPurchase = product;
                    break;
                }
            }

            if (productToPurchase == null) {
                throw new IllegalArgumentException("Товар '" + productName + "' не найден");
            }


            Order order = new Order(productToPurchase, customerName);
            orders.add(order);


            products.remove(productToPurchase);


            String brand = productToPurchase.getBrand();
            brandCount.put(brand, brandCount.get(brand) - 1);
            if (brandCount.get(brand) == 0) {
                brandCount.remove(brand);
            }

            System.out.println("Покупка оформлена: " + order);

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при покупке: " + e.getMessage());
        }
    }


    public ArrayList<Product> filterByBrand(String brand) {
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getBrand().equalsIgnoreCase(brand)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }


    public void checkWarranty(int orderId) { // Проверка гарантии для заказа
        try {
            Order order = null;
            for (Order o : orders) {
                if (o.getOrderId() == orderId) {
                    order = o;
                    break;
                }
            }

            if (order == null) {
                throw new IllegalArgumentException("Заказ с ID " + orderId + " не найден");
            }

            Product product = order.getProduct();
            boolean underWarranty = product.isUnderWarranty(order.getOrderDate());

            if (underWarranty) {
                System.out.println("Товар " + product.getName() + " находится на гарантии");
            } else {
                System.out.println("Гарантия на товар " + product.getName() + " истекла");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при проверке гарантии: " + e.getMessage());
        }
    }


    public void printBrandStatistics() { // Получение статистики по брендам
        System.out.println("\n Статистика по брендам ");
        if (brandCount.isEmpty()) {
            System.out.println("Нет товаров в магазине");
        } else {
            for (Map.Entry<String, Integer> entry : brandCount.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " товар(ов)");
            }
        }
    }

    public void displayProducts() {
        System.out.println("\n Товары в магазине '" + storeName + "' ");
        if (products.isEmpty()) {
            System.out.println("Магазин пуст");
        } else {
            for (int i = 0; i < products.size(); i++) {
                System.out.println((i + 1) + ". " + products.get(i));
            }
        }
    }


    public void displayOrders() {  // Вывод всех заказов
        System.out.println("\n История заказов ");
        if (orders.isEmpty()) {
            System.out.println("Заказов нет");
        } else {
            for (Order order : orders) {
                System.out.println(order);
            }
        }
    }
}


class Main {
    public static void main(String[] args) {
        // Создаем магазин
        Store electronicsStore = new Store("TechWorld");

        // Создаем товары
        Laptop laptop1 = new Laptop("MacBook Pro", "Apple", 199999.99, 12,
                16, 512, "macOS");
        Laptop laptop2 = new Laptop("ThinkPad X1", "Lenovo", 149999.99, 24,
                32, 1000, "Windows 11");
        Phone phone1 = new Phone("iPhone 15", "Apple", 89999.99, 12,
                6.1, 3349, "iOS");
        Phone phone2 = new Phone("Galaxy S24", "Samsung", 79999.99, 18,
                6.2, 4000, "Android");



        System.out.println(" Добавление товаров ");
        electronicsStore.addProduct(laptop1);
        electronicsStore.addProduct(laptop2);
        electronicsStore.addProduct(phone1);
        electronicsStore.addProduct(phone2);

        electronicsStore.addProduct(null);
        electronicsStore.addProduct(new Phone("Test", "Test", -100, 12, 6.0, 3000, "Test"));

        electronicsStore.displayProducts();

        System.out.println("\n Фильтрация по бренду Apple ");
        ArrayList<Product> appleProducts = electronicsStore.filterByBrand("Apple");
        for (Product product : appleProducts) {
            System.out.println(product);
        }

        System.out.println("\n Процесс покупки ");
        electronicsStore.purchaseProduct("MacBook Pro", "Иван Иванов");
        electronicsStore.purchaseProduct("iPhone 15", "Петр Петров");

        electronicsStore.purchaseProduct("Несуществующий товар", "Василий");

        electronicsStore.displayProducts();
        electronicsStore.displayOrders();

        System.out.println("\n Проверка гарантии ");
        electronicsStore.checkWarranty(1); // Существующий заказ
        electronicsStore.checkWarranty(999); // Несуществующий заказ (обработка исключения)

        electronicsStore.printBrandStatistics();

        System.out.println("\n Обновление прошивки ");
        Laptop remainingLaptop = (Laptop) electronicsStore.filterByBrand("Lenovo").get(0);
        remainingLaptop.updateFirmware();

        Phone remainingPhone = (Phone) electronicsStore.filterByBrand("Samsung").get(0);
        remainingPhone.updateFirmware();

        System.out.println("\n Демонстрация toString() ");
        System.out.println("Ноутбук: " + remainingLaptop);
        System.out.println("Смартфон: " + remainingPhone);
    }
}