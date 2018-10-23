package GUI;

import GUI.Panels.StartScreen;
import GUI.Panels.StationViewer;
import database.WeatherStation;

import javax.swing.*;
import java.awt.*;

public class Window {
    private static JFrame mainWindow = new JFrame();
    static {
        //initialise window
        LayoutManager layout = new BorderLayout();
        mainWindow.setLayout(layout);
        mainWindow.setTitle("Www-weather monitor");
        mainWindow.setSize(700,500);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);


    }

    public static void showWindow(){
        showStartScreen();
        mainWindow.setVisible(true);
    }

    public static void showStartScreen(){
        mainWindow.getContentPane().removeAll();
        mainWindow.add(new StartScreen());
        mainWindow.revalidate();
        mainWindow.repaint();
    }

    public static void showStationViewer(WeatherStation station){
        mainWindow.getContentPane().removeAll();
        mainWindow.add(new StationViewer(station));
        mainWindow.revalidate();
        mainWindow.repaint();
    }

    public static Dimension getSize(){
        return mainWindow.getSize();
    }
}
