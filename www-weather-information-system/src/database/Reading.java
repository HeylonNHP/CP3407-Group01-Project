package database;

import java.sql.Timestamp;

public class Reading {
    int readingUVindex;
    int readingHumidity;
    int readingTemperature;
    int readingWindSpeed;
    int readingWindDirection;
    int readingPressure;
    int readingRainfall;

    public Reading(int readingUVindex, int readingHumidity, int readingTemperature, int readingWindSpeed, int readingWindDirection, int readingPressure, int readingRainfall) {
        this.readingUVindex = readingUVindex;
        this.readingHumidity = readingHumidity;
        this.readingTemperature = readingTemperature;
        this.readingWindSpeed = readingWindSpeed;
        this.readingWindDirection = readingWindDirection;
        this.readingPressure = readingPressure;
        this.readingRainfall = readingRainfall;
    }

    public int getReadingUVindex() {
        return readingUVindex;
    }

    public int getReadingHumidity() {
        return readingHumidity;
    }

    public int getReadingTemperature() {
        return readingTemperature;
    }

    public int getReadingWindSpeed() {
        return readingWindSpeed;
    }

    public int getReadingWindDirection() {
        return readingWindDirection;
    }

    public int getReadingPressure() {
        return readingPressure;
    }

    public int getReadingRainfall() {
        return readingRainfall;
    }
}
