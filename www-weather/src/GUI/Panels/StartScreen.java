package GUI.Panels;

import GUI.Window;
import com.sun.glass.ui.Size;
import database.WeatherStation;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class StartScreen extends JPanel {
    public StartScreen(){
        setLayout(null);
        System.out.println(Window.getSize());
        Dimension size = Window.getSize();
        //setSize(size);
        setPreferredSize(size);


        Dimension windowSize = Window.getSize();

        //Login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(windowSize.width - 150, 50,100,25);
        add(loginButton);

        //Menu panel
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridBagLayout());
        menuPanel.setBounds((windowSize.width/2) - 150, 100,300, 300);
        menuPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Menu"));

        GridBagConstraints c = new GridBagConstraints();

        JButton weatherStations = new JButton("Weather Stations");
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;
        c.weightx = 1;

        menuPanel.add(weatherStations,c);

        JButton management = new JButton("Management");
        c.gridy = 1;
        menuPanel.add(management,c);

        JButton stationMaintenance = new JButton("Station maintenance");
        c.gridy = 2;
        menuPanel.add(stationMaintenance,c);

        add(menuPanel);

        //Event listeners
        weatherStations.addActionListener((e) -> {
            Window.showStationViewer(new WeatherStation(1,"test",1));
        });
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
