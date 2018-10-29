package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class databaseInterface {
    static String jdbcUrl = "jdbc:mysql://cp3407-www-weather-station.cjwpmslychx7.us-east-1.rds.amazonaws.com?user=CP3407&password=3MJLOpDV47IRHtrCA4Qg";
    static Connection con;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void connectToDatabase() {
        try {
            con = DriverManager.getConnection(jdbcUrl);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void closeDatabaseConnection() {
        try {
            con.close();
        } catch (java.sql.SQLException ex) {

        }
    }

    public static void testQuery() {
        try {
            String queryString = "select version()";
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(queryString);

            //Print query results
            while (rset.next()) {
                System.out.println("Version: " + rset.getString(1));
            }

            //Close
            rset.close();
            stmt.close();
        } catch (java.sql.SQLException ex) {

        }
    }

    public static WeatherStation[] getWeatherStationList() throws java.sql.SQLException {
        List<WeatherStation> weatherStations = new ArrayList<WeatherStation>();

        System.out.println("Printing weather station list:");
        String query = "SELECT * FROM weatherdata.Stations;";
        try (Statement stmt = con.createStatement()) {
            try (ResultSet rset = stmt.executeQuery(query)) {
                while (rset.next()) {
                    System.out.println("Weather station: " + rset.getString(2));
                    WeatherStation station = new WeatherStation(Integer.parseInt(rset.getString(1)), rset.getString(2),
                            Integer.parseInt(rset.getString(3)),rset.getDouble(4), rset.getDouble(5));
                    weatherStations.add(station);

                }
            }
        }
        return (WeatherStation[]) weatherStations.toArray(new WeatherStation[0]);
    }

    public static Reading[] getWeatherStationReadings(WeatherStation station) throws java.sql.SQLException {
        List<Reading> readings = new ArrayList<>();

        int stationID = station.getStationID();

        String query = "SELECT * FROM weatherdata.Readings where Station_ID = " + stationID + ";";

        try (Statement stmt = con.createStatement()) {
            try (ResultSet rset = stmt.executeQuery(query)) {
                while (rset.next()) {
                    Reading reading = new Reading(rset.getInt(1), rset.getInt(2), rset.getTimestamp(3),
                            rset.getInt(4), rset.getInt(5), rset.getInt(6), rset.getInt(7),
                            rset.getInt(8), rset.getInt(9), rset.getInt(10));
                    readings.add(reading);
                }
            }
        }
        return readings.toArray(new Reading[0]);
    }

    public static AdminUser[] getAdminUsers() throws java.sql.SQLException {
        List<AdminUser> adminUserList = new ArrayList<>();


        String query = "SELECT * FROM weatherdata.Admin_User;";
        try (Statement stmt = con.createStatement()) {
            try (ResultSet rset = stmt.executeQuery(query)) {
                while (rset.next()) {
                    AdminUser adminUser = new AdminUser(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4),
                            rset.getString(5), rset.getString(6), rset.getString(7));
                    adminUserList.add(adminUser);
                }
            }
        }

        return (AdminUser[]) adminUserList.toArray(new AdminUser[0]);
    }

    public static void submitMaintenanceSchedule(WeatherStation station, String notes, Date date) throws java.sql.SQLException{
        String query = "INSERT INTO `weatherdata`.`ScheduledMaintenance` (`Station_ID`, `ScheduledMaintenance_Notes`, `ScheduledMaintenance_Date` , " +
                "`ScheduledMaintenance_Completed`) VALUES (?, ?, ?, ?);";

        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1,station.getStationID());
        preparedStatement.setString(2,notes);
        preparedStatement.setDate(3,date);
        preparedStatement.setBoolean(4,false);

        preparedStatement.execute();
    }

    public static MaintenanceSchedule[] getMaintenanceSchedules(boolean excludeCompleted) throws java.sql.SQLException{
        List<MaintenanceSchedule> scheduleList = new ArrayList<>();

        String query = "SELECT * FROM weatherdata.ScheduledMaintenance;";

        if(excludeCompleted){
            query = "SELECT * FROM weatherdata.ScheduledMaintenance where ScheduledMaintenance_Completed != 1;";
        }

        try (Statement stmt = con.createStatement()) {
            try (ResultSet rset = stmt.executeQuery(query)) {
                while (rset.next()) {
                    scheduleList.add(new MaintenanceSchedule(rset.getInt(1),rset.getInt(2),rset.getString(3), rset.getDate(4),rset.getBoolean(5)));
                }
            }
        }

        return scheduleList.toArray(new MaintenanceSchedule[0]);
    }

}
