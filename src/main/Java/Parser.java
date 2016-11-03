import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

// TODO: 04.11.16 Разбить все проверки на приватные методы
// TODO: 04.11.16 Переделать конструктор, так чтобы на вход приходил путь файла
// TODO: 04.11.16 Написать тесты к этому классу
// TODO: 04.11.16 Подумать о том, что он должен передавать на выходе другому модулю
// TODO: 04.11.16 Добавить маленький Json файл для тестов
// TODO: 04.11.16 Пофиксить баги
// TODO: 04.11.16 Навести в коде порядок

/**
 * Created by roman on 29.10.16.
 */
public class Parser {
    private static final Logger logger = Logger.getLogger(Parser.class);

    private File jsonFile;
    private String fileName;

    public Parser() {
        try {
            fileName = getClass().getClassLoader().getResource("Network_1.json").getFile();
            jsonFile = new File(fileName);
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
        }

    }

    public SimpleMeasure parse() {
        ArrayList<Frame> frames = new ArrayList<Frame>();

        try {
            JsonFactory factory = new JsonFactory();
            JsonParser parser = factory.createParser(jsonFile);

            Frame frame = null;
            while (true) {
                if (parser.nextToken() == JsonToken.START_OBJECT) {
                    frame = new Frame();
                }
                else if (parser.currentToken() == JsonToken.END_OBJECT) {
                    frames.add(frame);
                    continue;
                }
                else {
                    String fieldName = parser.getCurrentName();
                    if (fieldName.equals(FrameConstants.FRAME_INDEX)) {
                        parser.nextToken();
                        if (frame != null) {
                            frame.getReceivingTime().set(Calendar.YEAR, FrameUtils.getYear(parser.getText()));
                            frame.getReceivingTime().set(Calendar.MONTH, FrameUtils.getMonth(parser.getText()));
                            frame.getReceivingTime().set(Calendar.DATE, FrameUtils.getDay(parser.getText()));
                        }
                    }

                    if (fieldName.equals(FrameConstants.FRAME_TYPE)) {
                        parser.nextToken();
                        if (frame != null) {
                            frame.setType(parser.getText());
                        }
                    }

                    if (fieldName.equals(FrameConstants.FRAME_TIME)) {
                        parser.nextToken();
                        if (frame != null) {
                            frame.getReceivingTime().set(Calendar.HOUR, FrameUtils.getHours(parser.getText()));
                            frame.getReceivingTime().set(Calendar.MINUTE, FrameUtils.getMinutes(parser.getText()));
                            frame.getReceivingTime().set(Calendar.SECOND, FrameUtils.getSeconds(parser.getText()));
                        }
                    }

                    if (fieldName.equals(FrameConstants.FRAME_DELTA_TIME)) {
                        parser.nextToken();
                        if (frame != null) {
                            frame.setDeltaTime(Double.parseDouble(parser.getText()));
                        }
                    }

                    if (fieldName.equals(FrameConstants.FRAME_TIME_RELATIVE)) {
                        parser.nextToken();
                        if (frame != null) {
                            frame.setTimeRelative(Double.parseDouble(parser.getText()));
                        }
                    }

                    if (fieldName.equals(FrameConstants.FRAME_NUMBER)) {
                        parser.nextToken();
                        if (frame != null) {
                            frame.setFrameNumber(Integer.parseInt(parser.getText()));
                        }
                    }

                    if (fieldName.equals(FrameConstants.FRAME_LENGTH)) {
                        parser.nextToken();
                        if (frame != null) {
                            frame.setFrameLength(Integer.parseInt(parser.getText()));
                        }
                    }

                    if (fieldName.equals(FrameConstants.FRAME_PROTOCOL)) {
                        if (frame != null) {
                            frame.setProtocol(parser.getText());
                        }
                    }
                }
            }
        } catch (IOException e) {

        }


        return new SimpleMeasure(frames);
    }


}
