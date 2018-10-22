package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class databaseInterface {
    static String jdbcUrl = "jdbc:mysql://cp3407-www-weather-station.cjwpmslychx7.us-east-1.rds.amazonaws.com?user=CP3407&password=3MJLOpDV47IRHtrCA4Qg";
    static Connection con;

    static {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void connectToDatabase(){
        try{
            con = DriverManager.getConnection(jdbcUrl);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void closeDatabaseConnection(){
        try{
            con.close();
        }catch (java.sql.SQLException ex){

        }
    }

    public static void testQuery(){
        try{
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
        }catch (java.sql.SQLException ex){

        }
    }

    public static WeatherStation[] getWeatherStationList() throws java.sql.SQLException{
        List<WeatherStation> weatherStations = new ArrayList<WeatherStation>();

        System.out.println("Printing weather station list:");
        String query = "SELECT * FROM weatherdata.Stations;";
            try(Statement stmt = con.createStatement()){
                try(ResultSet rset = stmt.executeQuery(query)){
                    while (rset.next()){
                        System.out.println("Weather station: " + rset.getString(2));
                        WeatherStation station = new WeatherStation(Integer.parseInt(rset.getString(1)), rset.getString(2),
                                Integer.parseInt(rset.getString(3)));
                        weatherStations.add(station);

                    }
                }
            }
        return (WeatherStation[])weatherStations.toArray(new WeatherStation[0]);
    }

    public static Reading[] getWeatherStationReadings(WeatherStation station) throws java.sql.SQLException{
        List<Reading> readings = new ArrayList<>();

        String query = "SELECT * FROM weatherdata.Readings;";

        try(Statement stmt = con.createStatement()){
            try(ResultSet rset = stmt.executeQuery(query)){
                while (rset.next()){
                    Reading reading = new Reading(rset.getInt(1), rset.getInt(2), rset.getTimestamp(3),
                            rset.getInt(4), rset.getInt(5), rset.getInt(6), rset.getInt(7),
                            rset.getInt(8), rset.getInt(9), rset.getInt(10));
                    readings.add(reading);
                }
            }
        }
        return (Reading[])readings.toArray(new Reading[0]);
    }

}
