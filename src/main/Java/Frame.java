import java.util.Calendar;
import java.util.Date;

/**
 * Created by roman on 27.10.16.
 */
public class Frame {
    private int frameNumber;
    private String type;
    private double deltaTime;
    private int frameLength;
    private double timeRelative;
    private String protocol;
    private Calendar receivingTime;

    public Frame() {
        receivingTime = Calendar.getInstance();
    }

    public Frame(int frameNumber, String type, double deltaTime, int frameLength, double timeRelative, String protocol) {
        this.frameNumber = frameNumber;
        this.type = type;
        this.deltaTime = deltaTime;
        this.frameLength = frameLength;
        this.timeRelative = timeRelative;
        this.protocol = protocol;
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getDeltaTime() {
        return deltaTime;
    }

    public void setDeltaTime(double deltaTime) {
        this.deltaTime = deltaTime;
    }

    public int getFrameLength() {
        return frameLength;
    }

    public void setFrameLength(int frameLength) {
        this.frameLength = frameLength;
    }

    public double getTimeRelative() {
        return timeRelative;
    }

    public void setTimeRelative(double timeRelative) {
        this.timeRelative = timeRelative;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }


    public Calendar getReceivingTime() {
        return receivingTime;
    }

    public void setReceivingTime(Calendar receivingTime) {
        this.receivingTime = receivingTime;
    }
}

