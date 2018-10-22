import database.Reading;
import database.WeatherStation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by jc282222 on 19/10/18.
 */

public class Main {
    public static void main(String args[]){
        System.out.print("Hello world!");

        database.databaseInterface.connectToDatabase();
        database.databaseInterface.testQuery();

        try{
            WeatherStation[] stations = database.databaseInterface.getWeatherStationList();
            System.out.println("Station count: " + stations.length);

            Reading[] readings = database.databaseInterface.getWeatherStationReadings(stations[0]);
            System.out.println("Readings count: " + readings.length);
        }catch (java.sql.SQLException ex){
            ex.printStackTrace();
        }


        return;

        /*
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        try{
            String jdbcUrl = "jdbc:mysql://cp3407-www-weather-station.cjwpmslychx7.us-east-1.rds.amazonaws.com?user=CP3407&password=3MJLOpDV47IRHtrCA4Qg";
            Connection con = DriverManager.getConnection(jdbcUrl);
            System.out.println("Connected!");

            //Test query
            String queryString = "select version()";
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(queryString);

            //Print query results
            while ( rset.next()) {
                System.out.println("Version: " + rset.getString(1));
            }

            //Close
            rset.close();
            stmt.close();
            con.close();
        }catch (Exception e){
            System.out.println("Ouch");
            e.printStackTrace();
        }
        */

    }
}
