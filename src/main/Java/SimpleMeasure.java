import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by roman on 02.11.16.
 */

/**
 * Это сущность одного измерения. Будет храниться в БД.
 */

// TODO: 02.11.16 Прописать аннотации для хранения в БД
public class SimpleMeasure {
    private final ArrayList<Frame> measure;
    private final Calendar date;

    public SimpleMeasure(ArrayList<Frame> measure) {
        this.measure = measure;
        this.date = measure.get(0).getReceivingTime();
    }

    public ArrayList<Frame> getMeasure() {
        return measure;
    }
}
