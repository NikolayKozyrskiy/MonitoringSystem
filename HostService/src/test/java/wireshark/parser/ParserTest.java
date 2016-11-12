package wireshark.parser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import wireshark.parser.entities.Frame;
import wireshark.parser.entities.SimpleMeasurement;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by Krasnikov Roman on 10.11.16.
 */
public class ParserTest {
    private final String fileNameForTest = getClass().getClassLoader().getResource("Network_test.json").getFile();
    private Parser parser;
    private SimpleMeasurement actualMeasurement;
    private Frame frame1, frame2, frame3, frame4, frame5, frame6;


    @Before
    public void setUp() throws Exception {
        parser = new Parser(fileNameForTest);
        actualMeasurement = parser.parse();
        frame1 = new Frame(1, "pcap_file", 0.000000000, 1358, 0.000000000, "eth:ethertype:ip:tcp:ssl");
        frame2 = new Frame(2, "pcap_file", 0.053264000, 66, 0.053264000, "eth:ethertype:ip:tcp");
        frame3 = new Frame(3, "pcap_file", 1.107622000, 42, 1.160886000, "eth:ethertype:arp");
        frame4 = new Frame(4, "pcap_file", 0.000098000, 42, 1.160984000, "eth:ethertype:arp");
        frame5 = new Frame(5, "pcap_file", 0.610291000, 511, 1.771275000, "eth:ethertype:ip:tcp:ssl");
        frame6 = new Frame(6, "pcap_file", 0.000087000, 66, 1.771362000, "eth:ethertype:ip:tcp");
        frame1.getReceivingTime().set(Calendar.YEAR, 2016);
        frame1.getReceivingTime().set(Calendar.MONTH, 11);
        frame1.getReceivingTime().set(Calendar.DATE, 4);
        frame1.getReceivingTime().set(Calendar.HOUR, 23);
        frame1.getReceivingTime().set(Calendar.MINUTE, 16);
        frame1.getReceivingTime().set(Calendar.SECOND, 32);
    }

    @After
    public void tearDown() throws Exception {
        actualMeasurement = null;
        parser = null;
        frame1 = null;
        frame2 = null;
        frame3 = null;
        frame4 = null;
        frame5 = null;
        frame6 = null;
    }

    @Test
    public void comparingFramesTest1() throws Exception {
        assertEquals(frame1, actualMeasurement.getMeasure().get(0));
    }

    @Test
    public void comparingFramesTest2() throws Exception {
        assertEquals(frame2, actualMeasurement.getMeasure().get(1));
    }

    @Test
    public void comparingFramesTest3() throws Exception {
        assertEquals(frame3, actualMeasurement.getMeasure().get(2));
    }

    @Test
    public void comparingFramesTest4() throws Exception {
        assertEquals(frame4, actualMeasurement.getMeasure().get(3));
    }

    @Test
    public void comparingFramesTest5() throws Exception {
        assertEquals(frame5, actualMeasurement.getMeasure().get(4));
    }

    @Test
    public void comparingFramesTest6() throws Exception {
        assertEquals(frame6, actualMeasurement.getMeasure().get(5));
    }

    @Test
    public void comparingDates() throws Exception {
        assertEquals(frame1.getReceivingTime(), actualMeasurement.getMeasure().get(0).getReceivingTime());
    }
    
}