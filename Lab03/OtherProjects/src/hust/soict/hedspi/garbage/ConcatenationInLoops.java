package hust.soict.hedspi.garbage;

public class ConcatenationInLoops {
    public static void main(String[] args) {
        int n = 10000;
        long start, end;

        start = System.currentTimeMillis();
        String s = "";
        for (int i=0; i<n; i++) s += i;
        end = System.currentTimeMillis();
        System.out.println("String: "+(end-start)+"ms");

        start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<n; i++) sb.append(i);
        end = System.currentTimeMillis();
        System.out.println("StringBuilder: "+(end-start)+"ms");

        start = System.currentTimeMillis();
        StringBuffer sbf = new StringBuffer();
        for (int i=0; i<n; i++) sbf.append(i);
        end = System.currentTimeMillis();
        System.out.println("StringBuffer: "+(end-start)+"ms");
    }
}