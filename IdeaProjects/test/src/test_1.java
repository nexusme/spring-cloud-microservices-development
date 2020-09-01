import java.text.SimpleDateFormat;
import java.util.Date;

public class test_1 {
    public static void main(String[] args) {
        StringBuffer sBuffer = new StringBuffer("What\'s wrong: ");
        sBuffer.append("YOU");
        sBuffer.append(" DEAD");
        System.out.println(sBuffer);

        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        System.out.println(ft.format(date));

    }
}