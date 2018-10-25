import WeatherReceiver.WundergroundWeatherReceiver;
import database.Reading;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world");
        database.databaseInterface.connectToDatabase();

        WeatherUpdater.beginFetchingUpdates();
        //new WundergroundWeatherReceiver("adelaide").getCurrentReading();


    }
}
