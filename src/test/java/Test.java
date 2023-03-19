import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Test {
    public static void main(String[] args) {
        Timestamp timestamp=Timestamp.valueOf("2022-08-19 09:00:00");
        System.out.println(timestamp.toString());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String stringTimestamp = dateFormat.format(timestamp);
        System.out.println(stringTimestamp);
    }
}
