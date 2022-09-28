/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.auction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.security.spec.KeySpec;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Anton
 */
public class Server{
    MulticastSocket socketLeilao;
    InetAddress ipLeilao;
    int portaLeilao;
    DatagramPacket packetLeilao;
    
    InetSocketAddress ip;
    
    DatagramSocket socketEscuta;
    InetAddress ipEscuta;
    int portaEscuta;
    DatagramPacket packetEscuta;
    
    String CHAVE_SECRETA = "Pd3Hz45Wq1mgzt421fa25d8e6t";
    String salt = "pp3fa3dxg=d1";
    IvParameterSpec iv;
    byte[] seed;
    public Server(){ 
        try {
            socketLeilao = new MulticastSocket(portaLeilao);
            ipLeilao = InetAddress.getByName("227.15.3.75");
            portaLeilao = 4423;
            
            socketEscuta = new DatagramSocket(portaEscuta, ipEscuta);
            ipEscuta = InetAddress.getByName("235.0.5.25");
            portaEscuta = 4000;
            
            ip = new InetSocketAddress(ipLeilao, portaLeilao);            
            socketLeilao.joinGroup(ip, NetworkInterface.getByInetAddress(ipLeilao));
            
            iv = new IvParameterSpec(seed);
            
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    /*
    JSONObject decriptAssimetrica(byte[] mensagem){
        
    }
    */
    JSONObject decriptSimetrica(byte[] mensagem){
        try {
            //SecureRandom random = new SecureRandom();            
            
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            
            KeySpec spec = new PBEKeySpec(CHAVE_SECRETA.toCharArray(), salt.getBytes(), 65536, 256);
            
                SecretKey tmp = factory.generateSecret(spec);
                SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
                //random.nextBytes(seed);
                
                //KeyGenerator key = KeyGenerator.getInstance("AES");
                //key.init(256, random);   
                
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                
                cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
                
                byte[] mensagemSemCifraDecode = Base64.getDecoder().decode(cipher.doFinal(mensagem));
                String mensagemString = new String(mensagemSemCifraDecode);
                
                return new JSONObject(mensagemString);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    byte[] encriptSimetrica(JSONObject mensagem){
        try {
            //SecureRandom random = new SecureRandom();            
            
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            
            KeySpec spec = new PBEKeySpec(CHAVE_SECRETA.toCharArray(), salt.getBytes(), 65536, 256);
            
                SecretKey tmp = factory.generateSecret(spec);
                SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
                //random.nextBytes(seed);
                
                //KeyGenerator key = KeyGenerator.getInstance("AES");
                //key.init(256, random);   
                
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
                
                byte[] mensagemBuffer = mensagem.toString().getBytes();
                byte[] mensagemComCifraEncode = Base64.getEncoder().encode(cipher.doFinal(mensagemBuffer));                
                                
                return mensagemComCifraEncode;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }    
    
    boolean autenticaUsuario(JSONObject login){
        File arquivo = new File("servidor.json");
        byte[] usuarios;                     
        try (FileInputStream reader = new FileInputStream(arquivo)) {
            usuarios = reader.readAllBytes();
            String listaUsuarios = new String(usuarios);
            JSONArray listaUsuariosJSON = new JSONArray(listaUsuarios);     
            
            for (int i = 0; i < listaUsuariosJSON.length(); i++){                
                JSONObject obj = listaUsuariosJSON.getJSONObject(i);                
                boolean nomeEncontrado = login.get("nome").equals(obj.get("nome"));
                boolean senhaValida = login.get("senha").equals(obj.get("senha"));
                if (nomeEncontrado && senhaValida)
                    return true;
            }
        }        
        catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return false;
    }
    void receiveLeilao() {        
        while (true){
            try {
                byte[] buffer = new byte[2000];
                packetLeilao = new DatagramPacket(buffer, buffer.length);
                socketLeilao.receive(packetLeilao);                
                new Thread(this :: sendLeilao).start();
                
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        
    }
    void sendLeilao(){        
        try {
            byte[] buffer = packetLeilao.getData();
            JSONObject apostaJSON = decriptSimetrica(buffer);            
            apostaJSON.append("data",  LocalTime.now(ZoneId.systemDefault()));
            apostaJSON.append("hora", System.currentTimeMillis());            
            buffer = encriptSimetrica(apostaJSON);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, ipLeilao, portaLeilao);
            socketLeilao.send(packet);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }
    void receiveEscuta(){
        while (true){
            try {
                byte[] buffer = new byte[2000];
                this.packetEscuta = new DatagramPacket(buffer, buffer.length);
                socketEscuta.receive(packetEscuta);                                                               
                new Thread(this :: sendEscuta).start();                                
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }    
    void sendEscuta(){                
        try {            
            JSONObject object = new JSONObject(packetEscuta.getData());
            JSONObject resposta = new JSONObject();
            if (autenticaUsuario(object)){                
                resposta.append("STATUS", true);
                resposta.append("IP", ipLeilao.toString());
                resposta.append("porta", portaLeilao);
                resposta.append("mensagem", "Conectado com Sucesso no Leilão!");
                resposta.append("salt", salt);
                resposta.append("chave", CHAVE_SECRETA);
                resposta.append("IV", iv);
            }
            else {
                resposta.append("STATUS", false);
                resposta.append("mensagem", "Usuário não cadastrado no sistema!");
            }
            
            byte[] buffer = resposta.toString().getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, packetEscuta.getSocketAddress());
            socketEscuta.send(packet);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }        
    }
    public static void main(String[] args){        
        Server server = new Server();
        new Thread(server :: receiveLeilao).start();                  
        new Thread(server :: receiveEscuta).start();      
    }
}
