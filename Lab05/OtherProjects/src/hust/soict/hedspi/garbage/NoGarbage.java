package hust.soict.hedspi.garbage;

public class NoGarbage {
    public static void main(String[] args) {
        // Sử dụng StringBuffer để nối mà không sinh thêm String tạm
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 50000; i++) {
            sb.append("some text ").append(i);
        }
        // Chuyển về String nếu cần
        String s = sb.toString();
        System.out.println("Created string length (using StringBuffer): " + s.length());
    }
}

