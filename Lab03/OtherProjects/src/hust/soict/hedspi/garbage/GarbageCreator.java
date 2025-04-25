package hust.soict.hedspi.garbage;

public class GarbageCreator {
    public static void main(String[] args) {
        // Khởi tạo chuỗi rỗng
        String s = "";
        // Tạo rất nhiều “garbage” bằng phép nối String
        for (int i = 0; i < 50000; i++) {
            s += "some text " + i;   // mỗi lần nối tạo ra một String mới
        }
        // In ra độ dài cuối cùng của chuỗi
        System.out.println("Created string length (using +): " + s.length());
    }
}
