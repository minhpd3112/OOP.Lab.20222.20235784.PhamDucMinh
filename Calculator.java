import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập hai số từ người dùng
        System.out.print("Enter first number: ");
        double num1 = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter second number: ");
        double num2 = Double.parseDouble(scanner.nextLine());

        // Tính toán các phép toán
        double sum = num1 + num2;
        double difference = num1 - num2;
        double product = num1 * num2;

        // Kiểm tra mẫu số trước khi chia
        Double quotient = null;
        if (num2 != 0) {
            quotient = num1 / num2;
        } else {
            System.out.println("Cannot divide by zero.");
        }

        // In kết quả
        System.out.println("Sum: " + sum);
        System.out.println("Difference: " + difference);
        System.out.println("Product: " + product);

        if (quotient != null) {
            System.out.println("Quotient: " + quotient);
        }

        // Đóng scanner
        scanner.close();
    }
}
