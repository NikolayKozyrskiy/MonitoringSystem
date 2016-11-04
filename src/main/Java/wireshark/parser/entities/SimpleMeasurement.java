package wireshark.parser.entities;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by roman on 02.11.16.
 */

public class SimpleMeasurement {
    private final ArrayList<Frame> measure;
    private final Calendar date;

    public SimpleMeasurement(ArrayList<Frame> measure) {
        this.measure = measure;
        this.date = measure.get(0).getReceivingTime();
    }

    public ArrayList<Frame> getMeasure() {
        return measure;
    }

    public Calendar getDate() {
        return date;
    }
}
