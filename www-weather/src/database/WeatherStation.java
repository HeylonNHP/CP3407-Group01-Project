package database;

public class WeatherStation {
    int stationID;
    String stationName;
    int stationPowerStatus;

    double latitude;
    double longitude;

    public WeatherStation(int ID, String Name, int PowerStatus, double latitude, double longitude) {
        setStationID(ID);
        setStationName(Name);
        setStationPowerStatus(PowerStatus);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getStationPowerStatus() {
        return stationPowerStatus;
    }

    public void setStationPowerStatus(int stationPowerStatus) {
        this.stationPowerStatus = stationPowerStatus;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return getStationName();
    }
}
