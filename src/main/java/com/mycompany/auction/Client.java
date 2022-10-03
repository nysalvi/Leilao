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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.spec.IvParameterSpec;
import org.json.JSONObject;

public class Client{
    
    DatagramSocket socket;    
    InetAddress group;
    int porta;
    
    String CHAVE_SECRETA;
    String salt;
    byte[] iv;
    byte[] seed;
    
    String nome;
    String senha;
    
    public Client(JSONObject cliente){
        this.nome = cliente.get("nome").toString();
        this.senha = cliente.get("senha").toString();
        
    }
    public Client(){       
        try {            
            porta = 4423;
            socket = new MulticastSocket(4423);
            group = InetAddress.getByName("227.15.3.75");
            InetSocketAddress address = new InetSocketAddress(group, porta);
            socket.joinGroup(address, NetworkInterface.getByInetAddress(group));
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    void recieveLogin(){
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
    void sendLogin(JSONObject mensagem){
        while (true){
            try {
                String mensagemToString = mensagem.toString();
                byte[] buffer = mensagemToString.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, porta);
                socket.send(packet);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    public static void main(String[] args){
        Client cliente = new Client();      
                
        //new Thread(cliente:: sendLogin).start();
    }
    
}
    


//TheDocumentFoundation.LibreOffice