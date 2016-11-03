import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by roman on 03.11.16.
 */
public class FrameUtils {

    public static int getYear(String index) {
        String[] dateTmp = index.split("-");
        return Integer.parseInt(dateTmp[1]);
    }

    public static int getMonth(String index) {
        String[] dateTmp = index.split("-");
        return Integer.parseInt(dateTmp[2]);
    }

    public static int getDay(String index) {
        String[] dateTmp = index.split("-");
        return Integer.parseInt(dateTmp[2]);
    }

    private static String[] getTimeFromString(String time) {
        String[] tmp = time.split(" ");
        String[] time_tmp = tmp[4].split(":");
        return time_tmp;
    }

    public static int getHours(String time) {
        String[] timeTmp = getTimeFromString(time);
        return Integer.parseInt(timeTmp[0]);
    }

    public static int getMinutes(String time) {
        String[] timeTmp = getTimeFromString(time);
        return Integer.parseInt(timeTmp[1]);
    }

    public static int getSeconds(String time) {
        String[] timeTmp = getTimeFromString(time);
        return Integer.parseInt(timeTmp[2].substring(0, timeTmp[2].indexOf(".")));
    }

}
