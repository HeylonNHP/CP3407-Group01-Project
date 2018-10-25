package GUI.Panels;

import GUI.*;
import GUI.Window;
import database.AdminUser;
import database.WeatherStation;
import javafx.scene.control.PasswordField;

import javax.swing.*;
import javax.swing.tree.ExpandVetoException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.PublicKey;

/**
 * Created by AleCSilva on 23/10/18.
 */
public class LoginScreen extends JPanel {

    JTextField usernameField = new JTextField("");
    JPasswordField passwordField = new JPasswordField("");

    public LoginScreen(){
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;

        Dimension size = GUI.Window.getSize();
        setPreferredSize(size);


        //Title Label
        JLabel loginScreenLabel = new JLabel("<html><h1>Administration login </h1></html>");
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


        c.gridx = 1;
        add(usernameField,c);

        JLabel passwordLabel = new JLabel("Password: ");
        c.gridx = 0;
        c.gridy = 3;
        add(passwordLabel,c);


        c.gridx = 1;
        c.gridy = 3;
        add(passwordField,c);

        // login confirmation buttons
        JButton confirmButton = new JButton("Login");
        c.gridy = 4;
        add(confirmButton,c);
        confirmButton.registerKeyboardAction(confirmButton.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                JComponent.WHEN_IN_FOCUSED_WINDOW);

        confirmButton.registerKeyboardAction(confirmButton.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                JComponent.WHEN_IN_FOCUSED_WINDOW);

        //Event listeners
        backButton.addActionListener((e -> Window.showStartScreen()));

        confirmButton.addActionListener((e) -> {
            try{
                logIn();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });
    }

    private void logIn() throws java.sql.SQLException{
        AdminUser[] adminUsers = database.databaseInterface.getAdminUsers();

        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        System.out.printf("Username: %s Password: %s", username, password);

        for(AdminUser user : adminUsers){
            System.out.printf("");
            if(username.equals(user.getUserName())){
                if(password.equals(user.getPassword())){
                    JOptionPane.showMessageDialog(null, String.format("Logged in successfully, Welcome %s %s", user.getFirstName(),
                            user.getLastName()));
                    Window.setLoggedIn(true);
                    System.out.printf("Test1: %s", Window.isLoggedIn());
                    Window.showStartScreen();
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Log in failed");
    }
}
