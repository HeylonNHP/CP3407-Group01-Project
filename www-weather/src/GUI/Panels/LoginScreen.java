package GUI.Panels;

import GUI.*;
import database.WeatherStation;

import javax.swing.*;
import java.awt.*;
import java.security.PublicKey;

/**
 * Created by AleCSilva on 23/10/18.
 */
public class LoginScreen extends JPanel {
    public LoginScreen(){
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;

        Dimension size = GUI.Window.getSize();
        setPreferredSize(size);


        //Label
        JLabel loginScreenLabel = new JLabel("Login: ");
        c.gridx = 0;
        c.gridy = 0;
        add(loginScreenLabel,c);

        //Back button
        JButton backButton = new JButton("< Back");
        c.gridx = 1;
        add(backButton,c);

    }
}
