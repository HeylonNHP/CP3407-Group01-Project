package WeatherReceiver;

import database.Reading;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

abstract public class WeatherReceiver {
    String stationName;

    public WeatherReceiver(String stationName) {
        this.stationName = stationName;
    }

    abstract public Reading getCurrentReading();

    public static String readPage(String url, String delimeter) {
        try {
            URL URL = new URL(url);
            URLConnection connection = URL.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line, lines = "";
            while ((line = reader.readLine()) != null) {
                if (lines != "") {
                    lines += delimeter;
                }
                lines += line;
            }
            return lines;
        } catch (Exception e) {
            return null;
        }
    }

    protected float farenheitToCelcius(float temperature) {
        return (temperature - 32) * 5 / 9;
    }

    protected float mercuryInchesToPascals(float mercuryInches) {
        return mercuryInches / (float) 0.00029530;
    }
}
