package Lab1;
import javax.swing.JOptionPane;
public class Ex225 {
    public static void main(String[] args) {
        String strNum1, strNum2;
        double num1, num2;
        String strNotification = "Results:\n";
        strNum1 = JOptionPane.showInputDialog(null,
                "Please input the first number:",
                "Input First Number",
                JOptionPane.INFORMATION_MESSAGE);
        num1 = Double.parseDouble(strNum1);
        strNum2 = JOptionPane.showInputDialog(null,
                "Please input the second number:",
                "Input Second Number",
                JOptionPane.INFORMATION_MESSAGE);
        num2 = Double.parseDouble(strNum2);

        double sum = num1 + num2;
        double difference = num1 - num2;
        double product = num1 * num2;

        strNotification += "Tổng: " + sum + "\n";
        strNotification += "Hiệu: " + difference + "\n";
        strNotification += "Tích: " + product + "\n";

        if (num2 == 0) {
            JOptionPane.showMessageDialog(null,
                    "Error: Không thể thực hiện phép chia cho 0",
                    "Division Error", JOptionPane.ERROR_MESSAGE);
        } else {
            double quotient = num1 / num2;
            strNotification += "Thương: " + quotient;
            JOptionPane.showMessageDialog(null, strNotification,
                    "Kết quả", JOptionPane.INFORMATION_MESSAGE);
        }
        System.exit(0);
    }
}
