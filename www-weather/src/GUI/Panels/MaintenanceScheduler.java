package GUI.Panels;

import GUI.Window;
import database.WeatherStation;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MaintenanceScheduler extends JPanel {
    public MaintenanceScheduler(WeatherStation station){
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weighty = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        Dimension size = Window.getSize();
        setPreferredSize(size);

        JLabel titleLabel = new JLabel("Maintenance scheduler");
        c.gridx = 0;
        c.gridy = 0;
        add(titleLabel,c);

        JButton backButton = new JButton("< Back");
        c.gridx = 1;
        add(backButton,c);

        JLabel notesLabel = new JLabel("Maintenance notes: ");
        c.gridx = 0;
        c.gridy = 1;
        add(notesLabel,c);

        JTextArea notesInput = new JTextArea("");
        c.gridx = 1;
        add(notesInput,c);

        JLabel dateLabel = new JLabel("Schedule date: ");
        c.gridx = 0;
        c.gridy = 2;
        add(dateLabel,c);


        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
        c.gridx = 1;
        add(datePicker,c);

        JButton submitButton = new JButton("Submit");
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        add(submitButton,c);


        //Event listeners
        backButton.addActionListener((e) -> {
            Window.showStationMaintenance();
        });
    }

}