package GUI.Panels;

import GUI.*;
import GUI.Window;
import database.Reading;
import database.WeatherStation;

import javax.swing.*;
import javax.swing.tree.ExpandVetoException;
import java.awt.*;
import java.security.PublicKey;

/**
 * Created by jc300556 on 23/10/18.
 */
public class PastWeather extends JPanel {
    public PastWeather(WeatherStation station){
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;

        Dimension size = GUI.Window.getSize();
        setPreferredSize(size);


        //Label
        JLabel pastWeatherLabel = new JLabel("Past weather: ");
        c.gridx = 0;
        c.gridy = 0;
        add(pastWeatherLabel,c);

        //Back button
        JButton backButton = new JButton("< Back");
        c.gridx = 1;
        add(backButton,c);

        //Table of weather data
        String[] columnNames = { "Reading time", "UV index", "Humidity", "Temperature","Pressure","Rainfall", "Wind speed", "Wind direction"};
        String[][] rowData = new String[0][0];
        try{
            rowData = getWeatherData(station);
        }catch (Exception ex){

        }
        JTable weatherDataTable = new JTable(rowData,columnNames);



        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        //Nest inside a JScrollPane so columnNames are displayed
        add(new JScrollPane(weatherDataTable),c);

        //Event listeners
        backButton.addActionListener((e -> Window.showStationViewer(station)));
    }

    private String[][] getWeatherData(WeatherStation station) throws java.sql.SQLException{
        Reading[] readings = database.databaseInterface.getWeatherStationReadings(station);

        String[][] readings2D = new String[readings.length][8];

        for(int i = 0; i < readings.length; ++i){
            Reading reading = readings[i];
            String[] readingArray = new String[] {reading.getReadingDate().toString(), String.valueOf(reading.getReadingUVindex()),
            String.valueOf(reading.getReadingHumidity()),String.valueOf(reading.getReadingTemperature()),
            String.valueOf(reading.getReadingPressure()),String.valueOf(reading.getReadingRainfall()),
            String.valueOf(reading.getReadingWindSpeed()), String.valueOf(reading.getReadingWindDirection())};

            readings2D[i] = readingArray;
        }

        return readings2D;
    }
}
