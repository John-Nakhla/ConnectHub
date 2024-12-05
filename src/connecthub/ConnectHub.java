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
        User u1 = new User("waeljohn389@gmail.com","ayman","1456","25-6-2000",null,null,null);
        User u2 = new User("waeljohn38@gmail.com","john","1456","25-6-2000",null,null,null);
        User u3 = new User("waeljohn89@gmail.com","raef","1456","25-6-2000",null,null,null);
        User u4 = new User("waeljohn39@gmail.com","caro","1456","25-6-2000",null,null,null);
        User u5 = new User("waeljohn3289@gmail.com","maria","1456","25-6-2000",null,null,null);
        User u6 = new User("waeljohn289@gmail.com","ahmed","1456","25-6-2000",null,null,null);
        User u7 = new User("waeljohn329@gmail.com","jana","1456","25-6-2000",null,null,null);
        u1.addFriend(u2);
        u1.blockUser(u3);
        u1.blockUser(u4);
        u1.blockUser(u5);
        u1.blockUser(u6);
        u1.blockUser(u7);
        Blocked b = new Blocked(u1);
        b.setVisible(true);
    }
    
}
