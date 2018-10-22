package GUI;

import GUI.Panels.StartScreen;

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
        mainWindow.add(new StartScreen(),BorderLayout.CENTER);
        mainWindow.revalidate();
        mainWindow.repaint();
    }

    public static Dimension getSize(){
        return mainWindow.getSize();
    }
}
