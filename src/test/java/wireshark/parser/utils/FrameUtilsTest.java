package wireshark.parser.utils;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Krasnikov Roman on 04.11.16.
 */
public class FrameUtilsTest {
    private static final String testForDate = "packets-2016-11-04";
    private static final String testForTime = "Nov  4, 2016 23:16:32.706594000 MSK";

    @Test
    public void getYear() throws Exception {
        assertEquals(2016, FrameUtils.getYear(testForDate));
    }

    @Test
    public void getMonth() throws Exception {
        assertEquals(11, FrameUtils.getMonth(testForDate));
    }

    @Test
    public void getDay() throws Exception {
        assertEquals(4, FrameUtils.getDay(testForDate));
    }

    @Test
    public void getHours() throws Exception {
        assertEquals(23, FrameUtils.getHours(testForTime));
    }

    @Test
    public void getMinutes() throws Exception {
        assertEquals(16, FrameUtils.getMinutes(testForTime));
    }

    @Test
    public void getSeconds() throws Exception {
        assertEquals(32, FrameUtils.getSeconds(testForTime));
    }

}