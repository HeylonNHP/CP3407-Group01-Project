package database;

import java.sql.Date;

public class MaintenanceSchedule {
    int maintenanceID;
    int stationID;
    String notes;
    Date date;

    public MaintenanceSchedule(int maintenanceID, int stationID, String notes, Date date) {
        this.maintenanceID = maintenanceID;
        this.stationID = stationID;
        this.notes = notes;
        this.date = date;
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
}
