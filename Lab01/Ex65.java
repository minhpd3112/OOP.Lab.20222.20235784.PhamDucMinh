package Lab1;
import javax.swing.JOptionPane;
import java.util.Arrays;

public class Ex65 {
    public static void main(String[] args) {
        int n;
        while(true) {
            try{
                String nhap_n = JOptionPane.showInputDialog("Nhập số phần tử :");
                n = Integer.parseInt(nhap_n.trim());
                if (n <= 0) throw new NumberFormatException();
                break;
            } 
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Invalid");
            }
        }
        int[] mang = new int[n];
        int tong = 0;
        for(int i = 0; i < n; i++){
            while(true) {
                try{
                    String phanTu = JOptionPane.showInputDialog("Nhập phần tử thứ "+(i + 1)+" của mảng:");
                    mang[i] = Integer.parseInt(phanTu.trim());
                    tong += mang[i];
                    break;
                } 
                catch(NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,"Invalid");}
            }
        }
        Arrays.sort(mang);
        String ketQua = "Mảng sau khi sắp xếp: "+Arrays.toString(mang)+"\n"
                      +"Tổng giá trị: "+ tong+ "\n"
                      +"Giá trị trung bình: "+ (double)tong / n;
        JOptionPane.showMessageDialog(null, ketQua, "Kết quả mảng", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
