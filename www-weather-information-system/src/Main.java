import WeatherReceiver.WundergroundWeatherReceiver;
import database.Reading;

public class Main {
    public static void main(String[] args){
        System.out.println("Hello world");

        WundergroundWeatherReceiver test = new WundergroundWeatherReceiver("townsville");

        Reading testReading = test.getCurrentReading();

    }
}
