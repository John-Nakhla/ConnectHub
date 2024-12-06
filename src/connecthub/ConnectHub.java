/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package connecthub;
import FrontEnd.*;
import java.util.*;
/**
 *
 * @author waelj
 */
public class ConnectHub {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        User u1 = new User("john389@gmail.com","lydia","1456","25-6-2000",null,null,null);
        User u2 = new User("wael38@gmail.com","wael","1456","25-6-2000",null,null,null);
        u1.addFriend(u2);
        u2.addFriend(u1);
        NewsFeedWindow nf= new NewsFeedWindow(u1);
        nf.setVisible(true);
    }
    
}
