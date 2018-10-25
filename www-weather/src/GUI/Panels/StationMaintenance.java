package GUI.Panels;

import GUI.*;
import GUI.Window;
import database.WeatherStation;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jc300556 on 23/10/18.
 */
public class StationMaintenance extends JPanel {

    JPanel stationListPanel = new JPanel(new GridLayout());

    public StationMaintenance(){
        super(null);

        Dimension size = GUI.Window.getSize();
        setPreferredSize(size);

        JLabel panelTitle = new JLabel("<html><div style='text-align: center;'>Station Maintenance</div></html>");
        panelTitle.setBounds(0,25,size.width,50);
        System.out.printf("Title width: %s", panelTitle.getWidth());
        add(panelTitle);

        JButton backButton = new JButton("< Back");
        backButton.setBounds(size.width - 125, 25, 100, 25);
        add(backButton);

        stationListPanel.setBackground(Color.cyan);
        JScrollPane scrollPanel = new JScrollPane(stationListPanel);
        stationListPanel.setAutoscrolls(true);
        scrollPanel.setBounds(100,150,400,300);
        add(scrollPanel);

        populateStationListPanel();
        //Event listeners
        backButton.addActionListener((e) -> GUI.Window.showStartScreen());
    }

    private void populateStationListPanel(){
        try{
            WeatherStation[] stations = database.databaseInterface.getWeatherStationList();

            for(WeatherStation station : stations){
                JPanel stationPanel = new JPanel(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();

                c.gridx = 0;
                c.gridy = 0;

                JLabel stationName = new JLabel(station.getStationName());
                stationPanel.add(stationName,c);

                JLabel statusLabel = new JLabel("Status: ");
                c.gridx = 1;
                stationPanel.add(statusLabel,c);

                JLabel statusDisplayLabel = new JLabel(station.getStationPowerStatus() == 1 ? "Online" : "Offline");
                c.gridx = 2;
                stationPanel.add(statusDisplayLabel,c);

                JButton scheduleMaintenance = new JButton("Schedule maintenance");
                c.gridx = 1;
                c.gridy = 1;
                c.gridwidth = 2;
                stationPanel.add(scheduleMaintenance,c);

                stationListPanel.add(stationPanel);

                //Event listeners
                scheduleMaintenance.addActionListener((e) ->{
                    Window.showMaintenanceScheduler(station);
                });
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
