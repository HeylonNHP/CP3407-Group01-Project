package GUI.Panels;

import GUI.Window;
import database.WeatherStation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class StartScreen extends JPanel {
    public StartScreen() {
        setLayout(null);
        System.out.println(Window.getSize());
        Dimension size = Window.getSize();
        //setSize(size);
        setPreferredSize(size);


        Dimension windowSize = Window.getSize();

        //Login button
        System.out.println(Window.isLoggedIn());
        String loginButtonText = "Administration login";
        if (Window.isLoggedIn()) {
            loginButtonText = "Logout";
        }

        JButton loginButton = new JButton(loginButtonText);
        loginButton.setBounds(windowSize.width - 250, 50, 200, 25);
        add(loginButton);

        //Menu panel
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridBagLayout());
        menuPanel.setBounds((windowSize.width / 2) - 150, 100, 300, 300);
        menuPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Menu"));

        GridBagConstraints c = new GridBagConstraints();

        JButton weatherStations = new JButton("Weather Stations");
        try {
            Image img = ImageIO.read(getClass().getResource("img/station.png"));
            weatherStations.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;
        c.weightx = 1;

        menuPanel.add(weatherStations, c);

        JButton viewMap = new JButton("View Map");
        try {
            Image img = ImageIO.read(getClass().getResource("img/view_map.png"));
            viewMap.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        c.gridy = 1;
        menuPanel.add(viewMap, c);

        JButton stationMaintenance = new JButton("Station maintenance");
        try {
            Image img = ImageIO.read(getClass().getResource("img/maintenance.png"));
            stationMaintenance.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        c.gridy = 2;
        menuPanel.add(stationMaintenance, c);

        add(menuPanel);

        //Event listeners
        weatherStations.addActionListener((e) -> {
            StationViewer.backToMap = false;
            Window.showStationViewer(new WeatherStation(1, "test", 1, 0, 0));
        });

        loginButton.addActionListener((e -> {
            if (!Window.isLoggedIn()) {
                Window.showLoginScreen();
            } else {
                Window.setLoggedIn(false);
                Window.showStartScreen();
            }


        }));

        stationMaintenance.addActionListener((e) -> {
            if (Window.isLoggedIn()) {
                Window.showStationMaintenance();
            } else {
                JOptionPane.showMessageDialog(null, "This requires administrator privileges");
            }
        });

        viewMap.addActionListener((e) -> {
            Window.showStationSelectorMap();
        });
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
