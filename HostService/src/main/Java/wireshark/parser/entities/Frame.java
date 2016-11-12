package wireshark.parser.entities;

import java.util.Calendar;

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
        receivingTime.set(Calendar.MILLISECOND, 0);
    }

    public Frame(int frameNumber, String type, double deltaTime, int frameLength, double timeRelative, String protocol) {
        this.frameNumber = frameNumber;
        this.type = type;
        this.deltaTime = deltaTime;
        this.frameLength = frameLength;
        this.timeRelative = timeRelative;
        this.protocol = protocol;
        receivingTime = Calendar.getInstance();
        receivingTime.set(Calendar.MILLISECOND, 0);
    }

    public Frame(int frameNumber, String type, double deltaTime, int frameLength, double timeRelative, String protocol, Calendar receivingTime) {
        this.frameNumber = frameNumber;
        this.type = type;
        this.deltaTime = deltaTime;
        this.frameLength = frameLength;
        this.timeRelative = timeRelative;
        this.protocol = protocol;
        this.receivingTime = receivingTime;
        this.receivingTime.set(Calendar.MILLISECOND, 0);
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

    @Override
    public String toString() {
        return "Frame{" +
                "frameNumber=" + frameNumber +
                ", type='" + type + '\'' +
                ", deltaTime=" + deltaTime +
                ", frameLength=" + frameLength +
                ", timeRelative=" + timeRelative +
                ", protocol='" + protocol + '\'' +
                ", receivingTime=" + receivingTime.getTime() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Frame frame = (Frame) o;

        if (frameNumber != frame.frameNumber) return false;
        if (Double.compare(frame.deltaTime, deltaTime) != 0) return false;
        if (frameLength != frame.frameLength) return false;
        if (Double.compare(frame.timeRelative, timeRelative) != 0) return false;
        if (type != null ? !type.equals(frame.type) : frame.type != null) return false;
        return protocol != null ? protocol.equals(frame.protocol) : frame.protocol == null;

    }

}

