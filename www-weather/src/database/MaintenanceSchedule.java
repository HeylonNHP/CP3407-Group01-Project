package database;

import java.sql.Date;

public class MaintenanceSchedule {
    int maintenanceID;
    int stationID;
    String notes;
    Date date;
    boolean completed;

    public MaintenanceSchedule(int maintenanceID, int stationID, String notes, Date date, boolean completed) {
        this.maintenanceID = maintenanceID;
        this.stationID = stationID;
        this.notes = notes;
        this.date = date;
        this.completed = completed;
    }

    public int getMaintenanceID() {
        return maintenanceID;
    }

    public int getStationID() {
        return stationID;
    }

    public String getNotes() {
        return notes;
    }

    public Date getDate() {
        return date;
    }

    public boolean isCompleted() {
        return completed;
    }
}
