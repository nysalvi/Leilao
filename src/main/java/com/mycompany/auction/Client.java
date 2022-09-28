/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.auction;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client{
    DatagramSocket socket;    
    InetAddress group;
    int port;
    
    public Client(){       
        try {            
            port = 4423;
            socket = new MulticastSocket(4423);
            group = InetAddress.getByName("227.15.3.75");
            InetSocketAddress address = new InetSocketAddress(group, port);
            socket.joinGroup(address, NetworkInterface.getByInetAddress(group));
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    void receive(){
        while (true){
            try {
                byte[] buffer = new byte[2000];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                
                System.out.println("Message: " + new String(packet.getData()));
            } 
            catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
    void send(){
        Scanner input = new Scanner(System.in);
        while (true){
            try {
                System.out.print("Type in the message: ");
                String message = input.nextLine();
                byte[] buffer = message.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, port);
                socket.send(packet);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    public static void main(String[] args){
        Client cliente = new Client();      
        
        new Thread(cliente:: receive).start();
        new Thread(cliente:: send).start();
    }
    
}
    


//TheDocumentFoundation.LibreOffice