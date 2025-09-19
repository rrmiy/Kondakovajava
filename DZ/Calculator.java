public class Calculator {

    public int calculate(int a, int b, char op) {
        if (op == '+') return a + b;
        if (op == '-') return a - b;
        if (op == '*') return a * b;
        if (op == '/') {
            if (b == 0) throw new ArithmeticException("Деление на ноль");
            return a / b;
        }
        throw new IllegalArgumentException("Недопустимая операция: " + op);
    }

    public double calculate(double a, double b) {
        return a + b;
    }

    public int calculate(int... values) {
        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        return sum;
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();

        System.out.println("5 + 3 = " + calc.calculate(5, 3, '+')); //Базовая опер
        System.out.println("10 - 4 = " + calc.calculate(10, 4, '-'));
        System.out.println("6 * 7 = " + calc.calculate(6, 7, '*'));
        System.out.println("15 / 3 = " + calc.calculate(15, 3, '/'));

        System.out.println("3.5 + 2.1 = " + calc.calculate(3.5, 2.1)); //Сложение дабл

        System.out.println("Sum 1,2,3,4,5 = " + calc.calculate(1, 2, 3, 4, 5)); //Сумма произвольного
        System.out.println("Sum 10,20,30 = " + calc.calculate(10, 20, 30));
    }
}