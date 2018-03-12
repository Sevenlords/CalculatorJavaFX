package calculator.model;

public class MainModel {

    public double calculated(double a, double b, String value) {
        switch (value) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "×":
                return a * b;
            case "÷":
                return a / b;
            case "x^y":
                return Math.pow(a, b);
            default:
                return 0.0;
        }
    }

    public double calculated(double a, String value) {
        switch (value) {
            case "√":
                return Math.sqrt(a);
            case "1/x":
                return 1 / a;
            default:
                return 0.0;
        }
    }

    public double percent(double a, double b, String value) {
        switch (value) {
            case "+":
                return a + (b / 100 * a);
            case "-":
                return a - (b / 100 * a);
            case "×":
                return b / 100 * a;
            case "÷":
                return b / 100 * a;
            default:
                return 0.0;
        }
    }
}
