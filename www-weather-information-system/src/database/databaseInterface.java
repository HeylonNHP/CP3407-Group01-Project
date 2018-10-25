package database;

import java.sql.Connection;
import java.sql.DriverManager;

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
}
