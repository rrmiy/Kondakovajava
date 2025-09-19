public interface FlightAction {
    String getAircraftBrand();
    String getFlightNumber();
    String getDestination();
    double getLoadCapacity();
    double getCostPerUnit();
    double getTotalCost();
    void printInfo();
}


class Flight implements FlightAction {
    private String aircraftBrand;
    private String flightNumber;
    private String destination;
    private double loadCapacity;
    private double costPerUnit;
    private double totalCost;

    public Flight(String aircraftBrand, String flightNumber, String destination, double loadCapacity, double costPerUnit) {
        this.aircraftBrand = aircraftBrand;
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.loadCapacity = loadCapacity;
        this.costPerUnit = costPerUnit;
        this.totalCost = loadCapacity * costPerUnit; // Рассчитываем totalCost в конструкторе
    }

    @Override
    public String getAircraftBrand() {
        return aircraftBrand;
    }

    @Override
    public String getFlightNumber() {
        return flightNumber;
    }

    @Override
    public String getDestination() {
        return destination;
    }

    @Override
    public double getLoadCapacity() {
        return loadCapacity;
    }

    @Override
    public double getCostPerUnit() {
        return costPerUnit;
    }

    @Override
    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public void printInfo() {
        System.out.println("Марка: " + aircraftBrand);
        System.out.println("Номер рейса: " + flightNumber);
        System.out.println("Куда: " + destination);
        System.out.println("Грузоподъемность: " + loadCapacity);
        System.out.println("Стоимость тонны: " + costPerUnit);
        System.out.println("Общая стоимость: " + totalCost);
    }


    public class Main {
        public static void main(String[] args) {
            Flight[] flights = new Flight[]{
                    new Flight("Боинг", "AF123", "Москва", 100, 5000),
                    new Flight("Аэробус", "SU456", "Париж", 80, 6000),
                    new Flight("Боинг", "KL789", "Токио", 120, 5500),
                    new Flight("Аэробус", "AZ012", "Рим", 90, 6500),
            };

            // Вывод всех рейсов
            System.out.println("===Все рейсы===");
            for (Flight flight : flights) {
                flight.printInfo();
            }

            // Пример выборки: рейсы с грузоподъемностью больше 90
            System.out.println("\n===Рейсы с грузоподъемностью > 90===");
            for (Flight flight : flights) {
                if (flight.getLoadCapacity() > 90) {
                    flight.printInfo();
                }
            }
        }
    }
}