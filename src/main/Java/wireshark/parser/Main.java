package wireshark.parser;

import wireshark.parser.entities.SimpleMeasurement;

/**
 * Created by Krasnikov Roman on 04.11.16.
 */
public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();

        SimpleMeasurement measurement = parser.parse();

        System.out.println(measurement.getMeasure());
    }
}
