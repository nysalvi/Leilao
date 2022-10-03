package com.mycompany.auction;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.json.JSONArray;
import org.json.JSONObject;

public class Server{
    Leilao leilao;
    
    MulticastSocket socketLeilao;
    int portaLeilao;
    InetAddress ipLeilao;
    DatagramPacket packetLeilao;
        
    DatagramPacket packetEscuta;
    int portaEscuta;
    DatagramSocket socketEscuta;
    InetAddress ipEscuta;
    
    byte[] CHAVE_SECRETA;
    byte[] iv; 
    SecretKey chave;
    KeyPair par;
    
    PrivateKey chavePrivada;
    PublicKey chavePublica;

    //String salt = "pp3fa3dxg=d1";
    //String CHAVE_SECRETA = "Pd3Hz45Wq1mgzt421fa25d8e6t";
    
    public Server(){ 
        try {
            iv = new byte[256];
            
            portaLeilao = 6789;
            ipLeilao = InetAddress.getByName("228.5.6.7");
            socketLeilao = new MulticastSocket();
            
            portaEscuta = 4040;            
            ipEscuta = InetAddress.getByName("127.0.0.45");            
            socketEscuta = new DatagramSocket(portaEscuta, ipEscuta);
            
            
            socketLeilao.joinGroup(new InetSocketAddress(ipLeilao, portaLeilao), 
                    NetworkInterface.getByInetAddress(ipLeilao));            
            
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.nextBytes(iv);
            chave = keyGen.generateKey();
            
            System.out.println("127.0.0.45");
            System.out.println(portaEscuta);
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    JSONObject decriptAssimetrica(byte[] mensagem){
        
    }
    */        
    boolean autenticaUsuario(JSONObject login){
        JSONArray listaUsuariosJSON = Arquivo.carregarDados("servidor.json");            
        for (int i = 0; i < listaUsuariosJSON.length(); i++){                
            JSONObject obj = listaUsuariosJSON.getJSONObject(i);                
            boolean nomeEncontrado = login.get("nome").equals(obj.get("nome"));
            boolean senhaValida = login.get("senha").equals(obj.get("senha"));
            if (nomeEncontrado && senhaValida)
                return true;
        }               
        return false;
    }
    boolean validaAposta(JSONObject mensagem){
        int aposta = mensagem.getInt("aposta");
        return aposta % leilao.produtoAtual.multiplo == 0? true: false; 
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
            JSONObject apostaJSON = Criptografia.decriptSimetrica(buffer, chave, iv);     
            if (validaAposta(apostaJSON)){
                apostaJSON.append("data",  LocalTime.now(ZoneId.systemDefault()));
                apostaJSON.append("hora", System.currentTimeMillis());                            
                
                buffer = Criptografia.encriptSimetrica(apostaJSON, chave, iv);
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, ipLeilao, portaLeilao);
                socketLeilao.send(packet);
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    void receiveEscuta(){
        while (true){
            try {
                byte[] buffer = new byte[2000];
                packetEscuta = new DatagramPacket(buffer, buffer.length);
                socketEscuta.receive(packetEscuta);                                                               
                new Thread(this :: sendEscuta).start();                                
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }    
    void sendEscuta(){                
        try {            
            byte[] buffer = packetEscuta.getData();
            JSONObject object = Criptografia.decriptAssimetrica(buffer, chavePrivada);
            JSONObject resposta = new JSONObject();
            if (autenticaUsuario(object)){                
                resposta.append("STATUS", true);
                resposta.append("IP", ipLeilao.toString());
                resposta.append("porta", portaLeilao);
                resposta.append("mensagem", "Conectado com Sucesso no Leilão!");
                //resposta.append("salt", salt);
                resposta.append("chave", chave);
                resposta.append("IV", Base64.getEncoder().encodeToString(iv));
            }
            else {                
                resposta.append("STATUS", false);
                resposta.append("mensagem", "Usuário não cadastrado no sistema!");
            }            
            
            //byte[] chavePublicaByte = Base64.getDecoder().decode(object.getString("chave"));
            
            //X509EncodedKeySpec x509Encoded = new X509EncodedKeySpec(chavePublicaByte);            
            //PublicKey chavePublica = KeyFactory.getInstance("RSA").generatePublic(x509Encoded);
            
            PublicKey chavePublica = (PublicKey)object.get("chave");
            byte[] mensagemCripto = Criptografia.encriptAssimetrica(resposta, chavePublica);
            DatagramPacket packet = new DatagramPacket(mensagemCripto, mensagemCripto.length, packetEscuta.getSocketAddress());
            socketEscuta.send(packet);
            
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }        
    }
    public static void main(String[] args){        
        Server server = new Server();
        new Thread(server :: receiveEscuta).start();      
        new Thread(server :: receiveLeilao).start();                  
    }
}
