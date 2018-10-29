package GUI.Panels;

import GUI.*;
import GUI.Window;
import database.MaintenanceSchedule;
import database.WeatherStation;
import sun.font.TrueTypeFont;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jc300556 on 23/10/18.
 */
public class StationMaintenance extends JPanel {

    JPanel stationListPanel = new JPanel();

    public StationMaintenance() {
        super(null);

        Dimension size = GUI.Window.getSize();
        setPreferredSize(size);

        JLabel panelTitle = new JLabel("<html><div style='text-align: center;'>Station Maintenance</div></html>");
        panelTitle.setBounds(0, 25, size.width, 50);
        System.out.printf("Title width: %s", panelTitle.getWidth());
        add(panelTitle);

        JButton backButton = new JButton("< Back");
        backButton.setBounds(size.width - 125, 25, 100, 25);
        add(backButton);

        JButton notificationButton = new JButton("");
        notificationButton.setBounds(size.width - 50, 35, 50, 25);
        notificationButton.setLocation(12, 90);
        try {
            Image img = ImageIO.read(getClass().getResource("img/no_notifications.png"));
            notificationButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        add(notificationButton);
        final JPopupMenu menu = new JPopupMenu("Notification menu");

        try{
            MaintenanceSchedule[] scheduleList = database.databaseInterface.getMaintenanceSchedules(false);
            WeatherStation[] stations = database.databaseInterface.getWeatherStationList();

            for(MaintenanceSchedule schedule: scheduleList){
                for(WeatherStation station: stations){
                    if(schedule.getStationID() == station.getStationID()){
                        JMenuItem item = new JMenuItem(String.format("%s station requires repair on %s",station.getStationName(), schedule.getDate().toString()));
                        item.addActionListener((e)->{
                            String message = String.format("<html><h1>%s Station</h1><br><b>Repair notes: </b>%s<br><b>Scheduled date: </b>%s</html>",
                                    station.getStationName(),schedule.getNotes(),schedule.getDate().toString());
                            JOptionPane.showMessageDialog(null,message);
                        });
                        menu.add(item);
                    }
                }

            }
        }catch (Exception ex){

        }
        if (menu.getComponentCount() > 0) {
            try {
                Image img = ImageIO.read(getClass().getResource("img/notifications.png"));
                notificationButton.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        notificationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.show(notificationButton, notificationButton.getWidth() / 2, notificationButton.getHeight() / 2);
            }
        });

        stationListPanel.setLayout(new BoxLayout(stationListPanel, BoxLayout.Y_AXIS));
        stationListPanel.setBackground(Color.cyan);
        JScrollPane scrollPanel = new JScrollPane(stationListPanel);
        stationListPanel.setAutoscrolls(true);
        scrollPanel.setBounds(100, 150, 400, 300);
        add(scrollPanel);

        populateStationListPanel();
        //Event listeners
        backButton.addActionListener((e) -> GUI.Window.showStartScreen());
    }

    private void populateStationListPanel() {
        try {
            WeatherStation[] stations = database.databaseInterface.getWeatherStationList();

            for (WeatherStation station : stations) {
                JPanel stationPanel = new JPanel(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();

                c.gridx = 0;
                c.gridy = 0;

                JLabel stationName = new JLabel(station.getStationName());
                stationPanel.add(stationName, c);

                JLabel statusLabel = new JLabel("Status: ");
                c.gridx = 1;
                stationPanel.add(statusLabel, c);

                JLabel statusDisplayLabel = new JLabel(station.getStationPowerStatus() == 1 ? "Online" : "Offline");
                c.gridx = 2;
                stationPanel.add(statusDisplayLabel, c);

                JButton scheduleMaintenance = new JButton("Schedule maintenance");
                c.gridx = 1;
                c.gridy = 1;
                c.gridwidth = 2;
                stationPanel.add(scheduleMaintenance, c);

                stationListPanel.add(stationPanel);

                //Event listeners
                scheduleMaintenance.addActionListener((e) -> {
                    Window.showMaintenanceScheduler(station);
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
