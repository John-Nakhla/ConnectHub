/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package connecthub;


public interface Notification {
    void send(User owner, String sender, String message, String type); // To send the notification
    void handleAction(String action); // To handle type-specific actions
}
