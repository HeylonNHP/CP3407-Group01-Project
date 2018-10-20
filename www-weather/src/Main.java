/**
 * Created by jc282222 on 19/10/18.
 */

public class Main {
    public static void main(String args[]){
        System.out.print("Hello world!");
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
