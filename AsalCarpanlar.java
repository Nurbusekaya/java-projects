import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AsalCarpanlar {
    public static void main(String[] args) {
    }
    public static List<Integer> AsalCarpanlariBul(int sayi) {
        List<Integer> asalCarpanlar = new ArrayList<>();

        while (sayi % 2 == 0) {
            asalCarpanlar.add(2);
            sayi /= 2;
        }
       
        for (int i = 3; i <= Math.sqrt(sayi); i += 2) {
            while (sayi % i == 0) {
                asalCarpanlar.add(i);
                sayi /= i;
            }
        }
    
        if (sayi > 2) {
            asalCarpanlar.add(sayi);
        }

        return asalCarpanlar;
    }

    public static void add(int i) {
    }

    public Object getAsalCarpanlar() {
        List<Integer> AsalCarpanlar = null;
        List<Integer> asalCarpanlar = AsalCarpanlar;
        return asalCarpanlar;

    }
    System.out.println(sayi);

}



