package database;

/**
 * Created by jc300556 on 23/10/18.
 */
public class AdminUser {
    int userID;
    String firstName;
    String lastName;
    String userName;
    String password;
    String phonenumber;
    String emailAddress;

    public AdminUser(int userID, String firstName, String lastName, String userName, String password, String phonenumber, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.phonenumber = phonenumber;
        this.emailAddress = emailAddress;
    }

    public int getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
