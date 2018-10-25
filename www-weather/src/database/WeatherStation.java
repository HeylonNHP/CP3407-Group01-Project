package database;

public class WeatherStation {
    int stationID;
    String stationName;
    int stationPowerStatus;

    public WeatherStation(int ID, String Name, int PowerStatus) {
        setStationID(ID);
        setStationName(Name);
        setStationPowerStatus(PowerStatus);
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

    @Override
    public String toString() {
        return getStationName();
    }
}
