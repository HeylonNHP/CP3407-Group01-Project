package GUI.Panels;

import GUI.*;
import database.WeatherStation;

import javax.swing.*;
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
        String[] columnNames = { "Reading time", "UV index", "Humidity", "Temperature", "Wind speed", "Wind direction"};

        JTable weatherDataTable = new JTable();



        c.gridx = 0;
        c.gridwidth = 2;
        add(weatherDataTable,c);
    }

    private String[][] getWeatherData(WeatherStation station){

        return new String[0][0];
    }
}
