package GUI.Panels;

import GUI.*;
import GUI.Window;
import database.WeatherStation;
import javafx.scene.control.PasswordField;

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
        c.fill = GridBagConstraints.BOTH;

        Dimension size = GUI.Window.getSize();
        setPreferredSize(size);


        //Title Label
        JLabel loginScreenLabel = new JLabel("Administration login ");
        c.gridx = 0;
        c.gridy = 0;
        add(loginScreenLabel,c);

        //Back button
        JButton backButton = new JButton("< Back");
        c.gridx = 1;
        add(backButton,c);

        // Username fields
        JLabel usernameLabel = new JLabel("Username: ");
        c.gridx = 0;
        c.gridy = 2;
        add(usernameLabel,c);

        JTextField usernameField = new JTextField("");
        c.gridx = 1;
        add(usernameField,c);

        JLabel passwordLabel = new JLabel("Password: ");
        c.gridx = 0;
        c.gridy = 3;
        add(passwordLabel,c);

        JPasswordField passwordField = new JPasswordField("");
        c.gridx = 1;
        c.gridy = 3;
        add(passwordField,c);

        // login confirmation buttons
        JButton confirmButton = new JButton("Login");
        c.gridy = 4;
        add(confirmButton,c);


        //Event listeners
        backButton.addActionListener((e -> Window.showStartScreen()));
    }
}
