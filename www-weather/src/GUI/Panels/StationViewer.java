package GUI.Panels;

import GUI.Window;
import database.WeatherStation;

import javax.swing.*;
import java.awt.*;

public class StationViewer extends JPanel{

    JComboBox<WeatherStation> stationSelector = new JComboBox<>();
    JTextField tempText = new JTextField("69");
    JTextField pressText = new JTextField("6969");
    JTextField sunText = new JTextField("69");
    JTextField rainText = new JTextField("69");
    JTextField windSpeedText = new JTextField("69");
    JTextField windDirectionText = new JTextField("69");

    public StationViewer(WeatherStation station){
        setLayout(null);
        Dimension size = Window.getSize();
        setPreferredSize(size);

        //Station selector combobox

        stationSelector.setBounds(25,25,200,25);
        add(stationSelector);

        //Back button
        JButton backButton = new JButton("< Back");
        backButton.setBounds(size.width - 125,25,100,25);
        add(backButton);

        //Weather stats panel
        JPanel weatherStats = new JPanel(new GridBagLayout());
        weatherStats.setBackground(Color.lightGray);
        weatherStats.setBounds((size.width/2) - 200,100,400,250);
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        //Temperature
        JLabel tempLabel = new JLabel("Temperature");
        weatherStats.add(tempLabel,c);


        tempText.setEditable(false);
        c.gridx = 1;
        weatherStats.add(tempText,c);

        //Pressure
        JLabel pressLabel = new JLabel("Pressure");
        c.gridx = 0;
        c.gridy = 1;
        weatherStats.add(pressLabel,c);


        pressText.setEditable(false);
        c.gridx = 1;
        weatherStats.add(pressText,c);

        //Sunshine
        JLabel sunLabel = new JLabel("Sunshine");
        c.gridx = 0;
        c.gridy = 2;
        weatherStats.add(sunLabel,c);


        sunText.setEditable(false);
        c.gridx = 1;
        weatherStats.add(sunText,c);

        //Rainfall
        JLabel rainLabel = new JLabel("Rainfall");
        c.gridx = 0;
        c.gridy = 3;
        weatherStats.add(rainLabel,c);


        rainText.setEditable(false);
        c.gridx = 1;
        weatherStats.add(rainText,c);

        //Wind speed
        JLabel windSpeedLabel = new JLabel("Wind Speed");
        c.gridx = 0;
        c.gridy = 4;
        weatherStats.add(windSpeedLabel,c);


        windSpeedText.setEditable(false);
        c.gridx = 1;
        weatherStats.add(windSpeedText,c);

        //Wind direction
        JLabel windDirectionLabel = new JLabel("Wind direction");
        c.gridx = 0;
        c.gridy = 5;
        weatherStats.add(windDirectionLabel,c);


        windDirectionText.setEditable(false);
        c.gridx = 1;
        weatherStats.add(windDirectionText,c);

        add(weatherStats);

        //Past weather
        JButton pastWeather = new JButton("Past Weather");
        pastWeather.setBounds(25,400,200,25);
        add(pastWeather);

        populateStationList();
        //Event listeners
        backButton.addActionListener((e) -> {
            Window.showStartScreen();
        });
    }

    private void populateStationList(){
        try {
            WeatherStation[] stationList = database.databaseInterface.getWeatherStationList();
            stationSelector.removeAllItems();
            for(WeatherStation station : stationList) {
                stationSelector.insertItemAt(station, stationSelector.getItemCount());
            }
        }
        catch (Exception ex){
            System.out.println("Exception error!");
        }
    }
}
