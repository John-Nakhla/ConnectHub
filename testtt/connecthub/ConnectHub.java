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
          AccountManagement admin = new AccountManagement();
          //admin.signup("john3589@gmail.com","tony","11112222","25-6-2000",null,null,null);
          admin.signup("waelnakhla38@gmail.com","kamal","13572468","25-6-2000",null,null,null);
//        u1.addFriend(u2);
//        u2.addFriend(u1);
//        UsersDatabase database= new UsersDatabase();
//        List<User> users = database.loadUsers();
//        for(User k : users)
//        {
//            System.out.println(k.getUserId());
//            if("User10005".equals(k.getUserId()))
//            {
//                System.out.println("here");
//                k.setUsername("kamolya");
//                System.out.println(k.getUsername());
//                database.refresh(k);
//            }
//        }
//        User u1 = new User("j389@gmail.com","dabdoob","1456","25-6-2000",null,null,null); 
//        NewsFeedWindow nf= new NewsFeedWindow(u1);
//        nf.setVisible(true);
          LogInPage l = new LogInPage(admin);
          l.setVisible(true);
    }
    
}