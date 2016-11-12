package wireshark.parser.utils;

/**
 * Created by Krasnikov Roman on 03.11.16.
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
        return Integer.parseInt(dateTmp[3]);
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

    private static String[] getTimeFromString(String time) {
        String[] tmp = time.split(" ");
        String[] time_tmp = tmp[4].split(":");
        return time_tmp;
    }

}
