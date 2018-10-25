import WeatherReceiver.WundergroundWeatherReceiver;
import database.Reading;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jc300556 on 25/10/18.
 */
public class WeatherUpdater {
    private static Timer weatherUpdateTimer = new Timer();
    static {

        /*

        weatherUpdateTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.print("Timer tick");
                getWeatherUpdate();
            }
        },updateIntervalMinutes,updateIntervalMinutes);*/

    }

    public static void beginFetchingUpdates(){
        int updateIntervalMinutes = 5*60*1000;
        while (true){
            getWeatherUpdate();
            try{
                Thread.sleep(updateIntervalMinutes);
            }catch (Exception ex){

            }
        }
    }

    private static void getWeatherUpdate(){
        WundergroundWeatherReceiver test = new WundergroundWeatherReceiver("townsville");

        Reading testReading = test.getCurrentReading();

        try{
            database.databaseInterface.submitReading(1,testReading);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        WundergroundWeatherReceiver test2 = new WundergroundWeatherReceiver("brisbane");
        Reading test2Reading = test2.getCurrentReading();
        try{
            database.databaseInterface.submitReading(2,test2Reading);
        }catch (Exception ex){
            ex.printStackTrace();
        }




        WundergroundWeatherReceiver test4 = new WundergroundWeatherReceiver("ingham");
        Reading test4Reading = test4.getCurrentReading();
        try{
            database.databaseInterface.submitReading(4,test4Reading);
        }catch (Exception ex){
            ex.printStackTrace();
        }


        WundergroundWeatherReceiver test3 = new WundergroundWeatherReceiver("cairns");
        Reading test3Reading = test3.getCurrentReading();
        try{
            database.databaseInterface.submitReading(3,test3Reading);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
