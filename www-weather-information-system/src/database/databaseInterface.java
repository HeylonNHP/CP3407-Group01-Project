package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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

    public static void submitReading(int stationID, Reading reading) throws java.sql.SQLException{
        String query = String.format("INSERT INTO weatherdata.`Readings` (Station_ID, Reading_UVindex, Reading_Humidity, Reading_Temperature, Reading_WindSpeed, Reading_WindDirection, Reading_Pressure, Reading_RainfallTotal) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                2, reading.getReadingUVindex(), reading.getReadingHumidity(), reading.getReadingTemperature(), reading.getReadingWindSpeed(),
                reading.getReadingWindDirection(), reading.getReadingPressure(),reading.getReadingRainfall());

        try(Statement stmt = con.createStatement()){
            try(ResultSet rset = stmt.executeQuery(query)){
                while (rset.next()){

                }
            }
        }
    }
}
