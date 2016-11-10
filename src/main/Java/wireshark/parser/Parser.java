package wireshark.parser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wireshark.parser.entities.Frame;
import wireshark.parser.entities.SimpleMeasurement;
import wireshark.parser.utils.FrameConstants;
import wireshark.parser.utils.FrameUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

// TODO: 04.11.16 Написать тесты


/**
 * Created by Krasnikov Roman on 29.10.16 .
 */
class Parser {
    private static final Logger log = LoggerFactory.getLogger(Parser.class);
    private final String defaultFileName = getClass().getClassLoader().getResource("Network_1.json").getFile();

    private File jsonFile;
    private String fileName;

    public Parser() {
            jsonFile = new File(defaultFileName);
            log.debug("Created json file", jsonFile);
    }

    public Parser(String fileName) {
        this.fileName = fileName;
        log.debug("Get name of file", this.fileName);
        jsonFile = new File(fileName);
        log.debug("Created json file", this.jsonFile);
    }

    public SimpleMeasurement parse() {
        ArrayList<Frame> frames = new ArrayList<>();
        int countOpenBrackets = 0;
        int countCloseBrackets = 0;

        try {
            JsonFactory factory = new JsonFactory();
            JsonParser parser = factory.createParser(jsonFile);
            log.info("JsonParser created");

            Frame frame = null;
            while (true) {
                JsonToken token = parser.nextToken();
                if (token == JsonToken.END_ARRAY)
                    break;

                switch (token) {
                    case START_ARRAY:
                        break;
                    case START_OBJECT:
                        countOpenBrackets++;
                        if (countOpenBrackets == 1) {
                            frame = new Frame();

                        }
                        break;
                    case END_OBJECT:
                        countCloseBrackets++;
                        if (countOpenBrackets == countCloseBrackets) {
                            frames.add(frame);
                            countOpenBrackets = 0;
                            countCloseBrackets = 0;
                        }
                        break;
                    default:
                        String fieldName = parser.getCurrentName();
                        parseFrameIndex(frame, parser, fieldName);
                        parseFrameType(frame, parser, fieldName);
                        parseFrameTime(frame, parser, fieldName);
                        parseFrameDeltaTime(frame, parser, fieldName);
                        parseFrameTimeRelative(frame, parser, fieldName);
                        parseFrameNumber(frame, parser, fieldName);
                        parseFrameLength(frame, parser, fieldName);
                        parseFrameProtocol(frame, parser, fieldName);
                        break;
                }
            }
        } catch (IOException e) {
            log.error("Failed to create JsonParser", e);
            throw new IllegalStateException();
        }
        return new SimpleMeasurement(frames);
    }

    private void parseFrameIndex(Frame frame, JsonParser parser, String fieldName) throws IOException {
        if (fieldName.equals(FrameConstants.FRAME_INDEX)) {
            parser.nextToken();
            if (frame != null) {
                frame.getReceivingTime().set(Calendar.YEAR, FrameUtils.getYear(parser.getText()));
                frame.getReceivingTime().set(Calendar.MONTH, FrameUtils.getMonth(parser.getText()));
                frame.getReceivingTime().set(Calendar.DATE, FrameUtils.getDay(parser.getText()));
            }
        }
    }

    private void parseFrameType(Frame frame, JsonParser parser, String fieldName) throws IOException {
        if (fieldName.equals(FrameConstants.FRAME_TYPE)) {
            parser.nextToken();
            if (frame != null) {
                frame.setType(parser.getText());
            }
        }
    }

    private void parseFrameTime(Frame frame, JsonParser parser, String fieldName) throws IOException {
        if (fieldName.equals(FrameConstants.FRAME_TIME)) {
            parser.nextToken();
            if (frame != null) {
                frame.getReceivingTime().set(Calendar.HOUR, FrameUtils.getHours(parser.getText()));
                frame.getReceivingTime().set(Calendar.MINUTE, FrameUtils.getMinutes(parser.getText()));
                frame.getReceivingTime().set(Calendar.SECOND, FrameUtils.getSeconds(parser.getText()));
            }
        }
    }

    private void parseFrameDeltaTime(Frame frame, JsonParser parser, String fieldName) throws IOException {
        if (fieldName.equals(FrameConstants.FRAME_DELTA_TIME)) {
            parser.nextToken();
            if (frame != null) {
                frame.setDeltaTime(Double.parseDouble(parser.getText()));
            }
        }
    }

    private void parseFrameTimeRelative(Frame frame, JsonParser parser, String fieldName) throws IOException {
        if (fieldName.equals(FrameConstants.FRAME_TIME_RELATIVE)) {
            parser.nextToken();
            if (frame != null) {
                frame.setTimeRelative(Double.parseDouble(parser.getText()));
            }
        }
    }

    private void parseFrameNumber(Frame frame, JsonParser parser, String fieldName) throws IOException {
        if (fieldName.equals(FrameConstants.FRAME_NUMBER)) {
            parser.nextToken();
            if (frame != null) {
                frame.setFrameNumber(Integer.parseInt(parser.getText()));
            }
        }
    }

    private void parseFrameLength(Frame frame, JsonParser parser, String fieldName) throws IOException {
        if (fieldName.equals(FrameConstants.FRAME_LENGTH)) {
            parser.nextToken();
            if (frame != null) {
                frame.setFrameLength(Integer.parseInt(parser.getText()));
            }
        }
    }

    private void parseFrameProtocol(Frame frame, JsonParser parser, String fieldName) throws IOException {
        if (fieldName.equals(FrameConstants.FRAME_PROTOCOL)) {
            if (frame != null) {
                frame.setProtocol(parser.getText());
            }
        }
    }
}
