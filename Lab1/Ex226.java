package Lab1;
import javax.swing.*;

public class Ex226 {
    private static void solveLinearEquation(double coefficient, double constant) {
        if (coefficient == 0 && constant == 0) {
            JOptionPane.showMessageDialog(null, "Vô số nghiệm", "Kết quả", JOptionPane.INFORMATION_MESSAGE);
        } else if (coefficient == 0) {
            JOptionPane.showMessageDialog(null, "Không có nghiệm", "Kết quả", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Nghiệm x = " + (-constant / coefficient), "Kết quả", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        double coefficient = Double.parseDouble(JOptionPane.showInputDialog("Nhập hệ số a của phương trình bậc nhất"));
        double constant = Double.parseDouble(JOptionPane.showInputDialog("Nhập hệ số b của phương trình bậc nhất"));
        
        solveLinearEquation(coefficient, constant);
        
        double a11 = Double.parseDouble(JOptionPane.showInputDialog("Nhập hệ số a11 của hệ phương trình bậc nhất hai ẩn"));
        double a12 = Double.parseDouble(JOptionPane.showInputDialog("Nhập hệ số a12 của hệ phương trình bậc nhất hai ẩn"));
        double b1 = Double.parseDouble(JOptionPane.showInputDialog("Nhập hệ số b1 của hệ phương trình bậc nhất hai ẩn"));
        double a21 = Double.parseDouble(JOptionPane.showInputDialog("Nhập hệ số a21 của hệ phương trình bậc nhất hai ẩn"));
        double a22 = Double.parseDouble(JOptionPane.showInputDialog("Nhập hệ số a22 của hệ phương trình bậc nhất hai ẩn"));
        double b2 = Double.parseDouble(JOptionPane.showInputDialog("Nhập hệ số b2 của hệ phương trình bậc nhất hai ẩn"));
        
        double determinant = a11 * a22 - a12 * a21;
        double determinantX = b1 * a22 - b2 * a12;
        double determinantY = a11 * b2 - a21 * b1;
        
        String result;
        if (determinant != 0) {
            result = "x1 = " + (determinantX / determinant) + ", x2 = " + (determinantY / determinant);
        } else if (determinantX == 0 && determinantY == 0) {
            result = "Vô số nghiệm";
        } else {
            result = "Không có nghiệm";
        }
        JOptionPane.showMessageDialog(null, result, "Kết quả", JOptionPane.INFORMATION_MESSAGE);
        
        coefficient = Double.parseDouble(JOptionPane.showInputDialog("Nhập hệ số a của phương trình bậc hai"));
        constant = Double.parseDouble(JOptionPane.showInputDialog("Nhập hệ số b của phương trình bậc hai"));
        double quadraticConstant = Double.parseDouble(JOptionPane.showInputDialog("Nhập hệ số c của phương trình bậc hai"));
        
        double discriminant = Math.pow(constant, 2) - 4 * coefficient * quadraticConstant;
        
        if (coefficient == 0) {
            solveLinearEquation(constant, quadraticConstant);
        } else {
            if (discriminant < 0) {
                result = "Không có nghiệm thực";
            } else if (discriminant == 0) {
                result = "Nghiệm kép x1 = x2 = " + (-constant / (2 * coefficient));
            } else {
                result = "x1 = " + ((-constant + Math.sqrt(discriminant)) / (2 * coefficient)) + "\n" + "x2 = " + ((-constant - Math.sqrt(discriminant)) / (2 * coefficient));
            }
            JOptionPane.showMessageDialog(null, result, "Kết quả", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
