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
    public static void main(String[] args) throws InterruptedException {
        User u1 = new User("waeljohn30@gmail.com","john","12345678","30-8-2004",null,null,null);
        User u2 = new User("waeljohn06@gmail.com","ahmed","12345678","30-8-2004",null,null,null);
        User u3 = new User("waeljohn36@gmail.com","mina","12345678","30-8-2004",null,null,null);
        User u4 = new User("waeljohn06@gmail.com","raef","12345678","30-8-2004",null,null,null);
        User u5 = new User("waeljohn6@gmail.com","maria","12345678","30-8-2004",null,null,null);
        u1.addFriend(u2);
        u1.addFriend(u3);
        u1.addFriend(u4);
        u1.addFriend(u5);
        Friends f = new Friends(u1);
        f.setVisible(true);
    }
}
