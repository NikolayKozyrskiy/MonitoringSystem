package wireshark.parser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.log4j.Logger;
import wireshark.parser.entities.Frame;
import wireshark.parser.entities.SimpleMeasurement;
import wireshark.parser.utils.FrameConstants;
import wireshark.parser.utils.FrameUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

// TODO: 04.11.16 Написать тесты к этому классу
// TODO: 04.11.16 Пофиксить баги
// TODO: 04.11.16 Прогнать устно весь алгоритм


// Замечания для тестов:

/**
 * Created by Krasnikov Roman on 29.10.16 .
 */
class Parser {
    //private static Logger logger = Logger.getLogger(Parser.class);

    private File jsonFile;
    private String fileName;

    public Parser() {
        try {
            fileName = getClass().getClassLoader().getResource("Network_1.json").getFile();
            //logger.info("Got an absolute path of Network_1.json file");
            jsonFile = new File(fileName);
            //logger.info("Created jsonFile");
        } catch (NullPointerException e) {
            //logger.fatal(e.getMessage(), e);
        }
    }

    public Parser(String fileName) {
        this.fileName = fileName;
        //logger.info("Got an absolute path of Network_1.json file");
        jsonFile = new File(fileName);
        //logger.info("Created jsonFile");
    }

    public SimpleMeasurement parse() {
        ArrayList<Frame> frames = new ArrayList<Frame>();

        try {
            JsonFactory factory = new JsonFactory();
            JsonParser parser = factory.createParser(jsonFile);

            Frame frame = null;
            while (true) {
                if (parser.nextToken() == JsonToken.START_OBJECT) {
                    frame = new Frame();

                    //logger.info("Created new wireshark.parser.entities.Frame object");
                }
                else if (parser.currentToken() == JsonToken.END_OBJECT) {
                    frames.add(frame);
                    //logger.info("Added wireshark.parser.entities.Frame object into list");
                    continue;
                }
                else {
                    String fieldName = parser.getCurrentName();
                    isFrameIndex(frame, parser, fieldName);
                    isFrameType(frame, parser, fieldName);
                    isFrameTime(frame, parser, fieldName);
                    isFrameDeltaTime(frame, parser, fieldName);
                    isFrameTimeRelative(frame, parser, fieldName);
                    isFrameNumber(frame, parser, fieldName);
                    isFrameLength(frame, parser, fieldName);
                    isFrameProtocol(frame, parser, fieldName);
                }
            }
        } catch (IOException e) {
            //logger.fatal(e.getMessage(), e);
        }
        return new SimpleMeasurement(frames);
    }

    private void isFrameIndex(Frame frame, JsonParser parser, String fieldName) throws IOException {
        if (fieldName.equals(FrameConstants.FRAME_INDEX)) {
            parser.nextToken();
            if (frame != null) {
                frame.getReceivingTime().set(Calendar.YEAR, FrameUtils.getYear(parser.getText()));
                frame.getReceivingTime().set(Calendar.MONTH, FrameUtils.getMonth(parser.getText()));
                frame.getReceivingTime().set(Calendar.DATE, FrameUtils.getDay(parser.getText()));
            }
        }
    }

    private void isFrameType(Frame frame, JsonParser parser, String fieldName) throws IOException {
        if (fieldName.equals(FrameConstants.FRAME_TYPE)) {
            parser.nextToken();
            if (frame != null) {
                frame.setType(parser.getText());
            }
        }
    }

    private void isFrameTime(Frame frame, JsonParser parser, String fieldName) throws IOException {
        if (fieldName.equals(FrameConstants.FRAME_TIME)) {
            parser.nextToken();
            if (frame != null) {
                frame.getReceivingTime().set(Calendar.HOUR, FrameUtils.getHours(parser.getText()));
                frame.getReceivingTime().set(Calendar.MINUTE, FrameUtils.getMinutes(parser.getText()));
                frame.getReceivingTime().set(Calendar.SECOND, FrameUtils.getSeconds(parser.getText()));
            }
        }
    }

    private void isFrameDeltaTime(Frame frame, JsonParser parser, String fieldName) throws IOException {
        if (fieldName.equals(FrameConstants.FRAME_DELTA_TIME)) {
            parser.nextToken();
            if (frame != null) {
                frame.setDeltaTime(Double.parseDouble(parser.getText()));
            }
        }
    }

    private void isFrameTimeRelative(Frame frame, JsonParser parser, String fieldName) throws IOException {
        if (fieldName.equals(FrameConstants.FRAME_TIME_RELATIVE)) {
            parser.nextToken();
            if (frame != null) {
                frame.setTimeRelative(Double.parseDouble(parser.getText()));
            }
        }
    }

    private void isFrameNumber(Frame frame, JsonParser parser, String fieldName) throws IOException {
        if (fieldName.equals(FrameConstants.FRAME_NUMBER)) {
            parser.nextToken();
            if (frame != null) {
                frame.setFrameNumber(Integer.parseInt(parser.getText()));
            }
        }
    }

    private void isFrameLength(Frame frame, JsonParser parser, String fieldName) throws IOException {
        if (fieldName.equals(FrameConstants.FRAME_LENGTH)) {
            parser.nextToken();
            if (frame != null) {
                frame.setFrameLength(Integer.parseInt(parser.getText()));
            }
        }
    }

    private void isFrameProtocol(Frame frame, JsonParser parser, String fieldName) throws IOException {
        if (fieldName.equals(FrameConstants.FRAME_PROTOCOL)) {
            if (frame != null) {
                frame.setProtocol(parser.getText());
            }
        }
    }
}
