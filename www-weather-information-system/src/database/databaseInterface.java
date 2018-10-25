package database;

import java.sql.*;

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
        String query = "INSERT INTO weatherdata.`Readings` (Station_ID, Reading_UVindex, Reading_Humidity, Reading_Temperature, Reading_WindSpeed, Reading_WindDirection, Reading_Pressure, Reading_RainfallTotal) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1,stationID);
        preparedStatement.setInt(2,reading.getReadingUVindex());
        preparedStatement.setInt(3,reading.getReadingHumidity());
        preparedStatement.setInt(4,reading.getReadingTemperature());
        preparedStatement.setInt(5, reading.getReadingWindSpeed());
        preparedStatement.setInt(6, reading.getReadingWindDirection());
        preparedStatement.setInt(7, reading.getReadingPressure());
        preparedStatement.setInt(8,reading.getReadingRainfall());

        preparedStatement.execute();
    }
}
