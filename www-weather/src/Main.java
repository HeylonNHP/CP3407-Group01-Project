import database.Reading;
import database.WeatherStation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by AlexCSilva && HeylonNHP on 19/10/18.
 */

public class Main {
    public static void main(String args[]) {
        System.out.print("Hello world!");

        database.databaseInterface.connectToDatabase();
        /*
        database.databaseInterface.testQuery();

        try{
            WeatherStation[] stations = database.databaseInterface.getWeatherStationList();
            System.out.println("Station count: " + stations.length);

            Reading[] readings = database.databaseInterface.getWeatherStationReadings(stations[0]);
            System.out.println("Readings count: " + readings.length);

            System.out.println(readings[0].getReadingDate().toString());
        }catch (java.sql.SQLException ex){
            ex.printStackTrace();
        }*/

        GUI.Window.showWindow();
    }
}
