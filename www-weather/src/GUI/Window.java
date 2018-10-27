package GUI;

import GUI.Panels.*;
import database.WeatherStation;

import javax.swing.*;
import java.awt.*;

public class Window {
    private static JFrame mainWindow = new JFrame();
    private static boolean loggedIn = false;

    static {
        //initialise window
        LayoutManager layout = new BorderLayout();
        mainWindow.setLayout(layout);
        mainWindow.setTitle("Www-weather monitor");
        mainWindow.setSize(700, 500);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);


    }

    public static void showWindow() {
        showStartScreen();
        mainWindow.setVisible(true);
    }

    public static void showStartScreen() {
        mainWindow.getContentPane().removeAll();
        mainWindow.add(new StartScreen());
        mainWindow.revalidate();
        mainWindow.repaint();
    }

    public static void showLoginScreen() {
        mainWindow.getContentPane().removeAll();
        mainWindow.add(new LoginScreen());
        mainWindow.revalidate();
        mainWindow.repaint();
    }

    public static void showStationViewer(WeatherStation station) {
        mainWindow.getContentPane().removeAll();
        mainWindow.add(new StationViewer(station));
        mainWindow.revalidate();
        mainWindow.repaint();
    }

    public static void showStationSelectorMap() {
        mainWindow.getContentPane().removeAll();
        mainWindow.add(new StationSelectorMap());
        mainWindow.revalidate();
        mainWindow.repaint();
    }

    public static void showStationChartViewer(WeatherStation station) {
        mainWindow.getContentPane().removeAll();
        mainWindow.add(new StationChartViewer(station));
        mainWindow.revalidate();
        mainWindow.repaint();
    }

    public static void showStationPastWeather(WeatherStation station) {
        mainWindow.getContentPane().removeAll();
        mainWindow.add(new PastWeather(station));
        mainWindow.revalidate();
        mainWindow.repaint();
    }

    public static void showStationMaintenance() {
        mainWindow.getContentPane().removeAll();
        mainWindow.add(new StationMaintenance());
        mainWindow.revalidate();
        mainWindow.repaint();
    }

    public static void showMaintenanceScheduler(WeatherStation station) {
        mainWindow.getContentPane().removeAll();
        mainWindow.add(new MaintenanceScheduler(station));
        mainWindow.revalidate();
        mainWindow.repaint();
    }

    public static void requestRepaint(){
        mainWindow.revalidate();
        mainWindow.repaint();
    }

    public static Dimension getSize() {
        return mainWindow.getSize();
    }

    public static boolean isLoggedIn() {
        return loggedIn;
    }

    public static void setLoggedIn(boolean loggedInValue) {
        loggedIn = loggedInValue;
    }
}
