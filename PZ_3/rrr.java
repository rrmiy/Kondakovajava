public class rrr {
    static class Person {
        private String firstName;
        private String lastName;


        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }


        public String getLastName() {
            return lastName;
        }


        public void printInfo() {
            System.out.println("Имя: " + firstName);
            System.out.println("Фамилия: " + lastName);
        }
    }


    static class Reis extends Person {
        private String markaAvto;
        private String nomerAvto;
        private String punktNaznacheniya;
        private double gruzopodemnost;
        private double stoimostEdinitsy;
        private double obshayaStoimost;


        public Reis(String firstName, String lastName, String markaAvto, String nomerAvto, String punktNaznacheniya, double gruzopodemnost, double stoimostEdinitsy) {
            super(firstName, lastName);
            this.markaAvto = markaAvto;
            this.nomerAvto = nomerAvto;
            this.punktNaznacheniya = punktNaznacheniya;
            this.gruzopodemnost = gruzopodemnost;
            this.stoimostEdinitsy = stoimostEdinitsy;
            this.obshayaStoimost = gruzopodemnost * stoimostEdinitsy;
        }


        public String getMarkaAvto() {
            return markaAvto;
        }


        public String getNomerAvto() {
            return nomerAvto;
        }


        public String getPunktNaznacheniya() {
            return punktNaznacheniya;
        }


        public double getGruzopodemnost() {
            return gruzopodemnost;
        }


        public double getStoimostEdinitsy() {
            return stoimostEdinitsy;
        }


        public double getObshayaStoimost() {
            return obshayaStoimost;
        }


        @Override
        public void printInfo() {
            System.out.println(getFirstName() + " " + getLastName() + " " + getMarkaAvto() +
                    ", Марка авто: " + markaAvto +
                    ", Номер авто: " + nomerAvto +
                    ", Пункт назначения: " + punktNaznacheniya +
                    ", Грузоподъемность: " + gruzopodemnost + " тонн" +
                    ", Стоимость за тонну: " + stoimostEdinitsy +
                    ", Общая стоимость груза: " + obshayaStoimost);


        }
    }


    public static void main(String[] args) {
        Reis[] reis = {
                new Reis("Иван", "Иванов", "Volvo", "BE876K", "Москва", 20, 5000),
                new Reis("Петр", "Петров", "Mercedes", "ХХ45В", "Санкт-Петербург", 15, 6000),
                new Reis("Сидор", "Сидоров", "КАМАЗ", "HO689C", "Казань", 25, 4000),
                new Reis("Анна", "Смирнова", "MAN", "PP888P", "Екатеринбург", 18, 5500),
                new Reis("Елена", "Кузнецова", "Scania", "AE273Y", "Новосибирск", 22, 4800)
        };




        System.out.println("=== ВСЕ РЕЙСЫ ===");
        for (Reis flight : reis) {
            flight.printInfo();
        }


        System.out.println("\n=== ВЫБОРКА: Рейсы с грузоподъемностью > 20 тонн ===");
        for (Reis flight : reis) {
            if (flight.getGruzopodemnost() > 20) {
                flight.printInfo();
            }
        }


        System.out.println("\n=== ВЫБОРКА: Рейсы с общей стоимостью > 180000 ===");
        for (Reis flight : reis) {
            if (flight.getObshayaStoimost() > 5000) {
                flight.printInfo();
            }
        }
    }
}
