package Lab1;
import javax.swing.JOptionPane;

public class Ex63 {
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Nhap n:"); //nhập n
        int n = Integer.parseInt(input); // 

        StringBuilder Dot = new StringBuilder();
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= 2 * n - 1; ++j) {
                if (j >= n - i + 1 && j <= n + i - 1) {
                    Dot.append("*");} 
                else {
                    Dot.append(" ");}
            }
            Dot.append("\n"); 
        }
        JOptionPane.showMessageDialog(null, Dot.toString(), "Triangle Output", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
