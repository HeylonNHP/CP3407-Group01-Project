package WeatherReceiver;

import database.Reading;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WundergroundWeatherReceiver extends WeatherReceiver {
    public WundergroundWeatherReceiver(String stationName) {
        super(stationName);
    }

    @Override
    public Reading getCurrentReading() {
        String pageData = getPageData(stationName);
        System.out.println(pageData);

        boolean imperialUnits = false;

        float temperatureF = -1;
        float pressureInches = -1;
        int uvIndex = -1;
        float rainfall = -1;
        float windSpeed = -1;
        int windDirection = -1;
        int humidity = -1;

        //Check units
        Pattern MY_PATTERN = Pattern.compile("\"units_nosymbol\":\"([CF])\"");
        Matcher m = MY_PATTERN.matcher(pageData);
        while (m.find()) {
            String s = m.group(1);
            System.out.printf("Found Units: %s\n", s);
            if (s.equals("F")) {
                imperialUnits = true;
            }
            // s now contains "BAR"
        }

        //Temperature
        MY_PATTERN = Pattern.compile("\"temperature\":([0-9]+[.][0-9]+|[0-9]+)");
        m = MY_PATTERN.matcher(pageData);
        while (m.find()) {
            String s = m.group(1);
            System.out.printf("Found temp: %s\n", s);
            temperatureF = Float.parseFloat(s);
            // s now contains "BAR"
        }

        //Pressure
        MY_PATTERN = Pattern.compile("\"pressure\":([0-9]+[.][0-9]+)");
        m = MY_PATTERN.matcher(pageData);
        while (m.find()) {
            String s = m.group(1);
            System.out.printf("Found pressure: %s\n", s);
            pressureInches = Float.parseFloat(s);
            // s now contains "BAR"
        }

        //UV Index
        MY_PATTERN = Pattern.compile("\"uv_index\":([0-9]+)");
        m = MY_PATTERN.matcher(pageData);
        int uvCount = 0;
        while (m.find()) {
            String s = m.group(1);
            System.out.printf("Found UV: %s\n", s);
            if (uvCount == 3) {
                uvIndex = Integer.parseInt(s);
            }
            uvCount++;
            // s now contains "BAR"
        }

        //Rainfall
        MY_PATTERN = Pattern.compile("\"precipTotal\":([0-9]+)");
        m = MY_PATTERN.matcher(pageData);
        while (m.find()) {
            String s = m.group(1);
            System.out.printf("Found Rain: %s\n", s);
            rainfall = Float.parseFloat(s);
            // s now contains "BAR"
        }

        //Wind speed
        MY_PATTERN = Pattern.compile("\"wind_speed\":([0-9]+[.][0-9]+|[0-9]+)");
        m = MY_PATTERN.matcher(pageData);
        while (m.find()) {
            String s = m.group(1);
            System.out.printf("Found Wind speed: %s\n", s);
            windSpeed = Float.parseFloat(s);
            // s now contains "BAR"
        }

        //Wind direction
        MY_PATTERN = Pattern.compile("\"wind_dir_degrees\":([0-9]+)");
        m = MY_PATTERN.matcher(pageData);
        while (m.find()) {
            String s = m.group(1);
            System.out.printf("Found Wind direction: %s\n", s);
            windDirection = Integer.parseInt(s);
            // s now contains "BAR"
        }

        //Humidity
        MY_PATTERN = Pattern.compile("\"humidity\":([0-9]+)");
        m = MY_PATTERN.matcher(pageData);
        while (m.find()) {
            String s = m.group(1);
            System.out.printf("Found humidity: %s\n", s);
            humidity = Integer.parseInt(s);
            // s now contains "BAR"
        }

        //Unit conversions
        int temperatureC = (int) temperatureF;
        int pressurePascals = (int) pressureInches;
        if (imperialUnits) {
            temperatureC = (int) farenheitToCelcius(temperatureF);
            pressurePascals = (int) mercuryInchesToPascals(pressureInches) / 100;
        }


        Reading reading = new Reading(uvIndex, humidity, temperatureC, (int) windSpeed, windDirection, pressurePascals, (int) rainfall);


        String testOutput = String.format("UV: %s Humidity: %s Temperature: %s Wind speed: %s Wind direction: %s Pressure: %s Rainfall: %s",
                reading.getReadingUVindex(), reading.getReadingHumidity(), reading.getReadingTemperature(), reading.getReadingWindSpeed(),
                reading.getReadingWindDirection(), reading.getReadingPressure(), reading.getReadingRainfall());

        System.out.println(testOutput);

        return reading;
    }

    private String getPageData(String stationName) {
        String url = String.format("https://www.wunderground.com/weather/au/%s", stationName);

        return readPage(url, "\n");
    }
}
