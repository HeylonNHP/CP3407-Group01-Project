package GUI;

import javax.swing.*;
import java.awt.*;

public class Window {
    private static JFrame mainWindow = new JFrame();
    static {
        //initialise window
        LayoutManager layout = new FlowLayout();
        mainWindow.setLayout(layout);
        mainWindow.setTitle("Www-weather monitor");
        mainWindow.setSize(700,500);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setResizable(true);


    }

    public static void showWindow(){
        mainWindow.setVisible(true);
    }
}
