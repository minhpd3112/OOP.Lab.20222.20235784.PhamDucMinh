package Lab1;
import javax.swing.JOptionPane;

public class Ex66{
    public static void main(String[] args) {
        int m= NhapKichThuoc("Nhập số hàng của ma trận:");
        int n= NhapKichThuoc("Nhập số cột của ma trận:");
        double[][] matran1= new double[m][n];
        double[][] matran2= new double[m][n];
        double[][] result= new double[m][n];

        nhapmatrix(matran1, "Nhập phần tử cho matrix1:");
        nhapmatrix(matran2, "Nhập phần tử cho matrix2:");

        Tong(matran1, matran2,result);
        hienThiKetQua(result);
    }
    public static int NhapKichThuoc(String thongBao) {
        int k;
        while (true){
            try{
                String input = JOptionPane.showInputDialog(thongBao);
                k = Integer.parseInt(input.trim());
                if (k > 0) return k;
                JOptionPane.showMessageDialog(null,"Invalid");
            } 
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Invalid");
            }
        }
    }
    public static double NhapPhanTu(String thongBao){
        double k;
        while (true){
            try{
                String input = JOptionPane.showInputDialog(thongBao);
                k = Double.parseDouble(input.trim());
                return k;
            } 
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Invalid");
            }
        }}
    public static void nhapmatrix(double[][] maTran, String message){
        for (int i= 0;i < maTran.length; i++){
            for (int j= 0;j < maTran[i].length; j++){
                maTran[i][j]= NhapPhanTu(message + "\nNhập phần tử [" +(i + 1)+ "][" +(j + 1)+ "]:");
            }
        }
    }
    public static void Tong(double[][] maTran1, double[][] maTran2, double[][] tongMaTran){
        for (int i= 0;i < maTran1.length; i++) {
            for (int j= 0;j < maTran1[i].length; j++) {
                tongMaTran[i][j]= maTran1[i][j] + maTran2[i][j];
            }
        }
    }
    public static void hienThiKetQua(double[][] tongMaTran) {
        StringBuilder result = new StringBuilder("Tổng hai ma trận là:\n");
        for (double[] row : tongMaTran) {
            for (double value : row) {
                result.append(value).append(" ");
            }
            result.append("\n");
        }
        JOptionPane.showMessageDialog(null, result.toString(), "Kết quả", JOptionPane.INFORMATION_MESSAGE);
    }
}
