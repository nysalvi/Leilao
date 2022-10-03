/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.auction;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Anton
 */
public class Teste {
    public static void main(String[] args) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
        try {
            JSONArray credenciamento = new JSONArray();                                                            
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");            
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);                        
            KeyPair keyPair = generator.genKeyPair();            
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();  
            
            
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            
            String nomeJoao = "Joao";
            String senhaJoao = "joao123";
            JSONObject joao = new JSONObject();
            File joaoArquivo = new File("joao.txt");                        
            byte[] nomeJoaoByte = nomeJoao.getBytes();
            byte[] senhaJoaoByte = senhaJoao.getBytes();
            byte[] nomeJoaoCrip = cipher.doFinal(nomeJoaoByte);            
            byte[] senhaJoaoCrip = cipher.doFinal(senhaJoaoByte);            
            String nomeEncodedJoao = Base64.getEncoder().encodeToString(nomeJoaoCrip);
            String senhaEncodedJoao = Base64.getEncoder().encodeToString(senhaJoaoCrip);
            joao.put("publica", publicKey.toString());
            joao.put("nome", nomeEncodedJoao);
            joao.put("senha", senhaEncodedJoao);      
            joao.put("adasd", new byte[4]);
            byte[] t;
            ////////////////////////////////////////////////////////////////////
            
            ObjectInputStream d;
            
            
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");            
            generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);                        
            keyPair = generator.genKeyPair();            
            
            
            //FileOutputStream lucasStream = new FileOutputStream(lucasArquivo);            
            //lucasStream.write(b);
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            
            
            String nomeLucas = "Lucas";
            String senhaLucas = "luc@s";
            JSONObject lucas = new JSONObject();
            File lucasArquivo = new File("lucas.txt");
            byte[] nomeLucasByte = nomeLucas.getBytes();
            byte[] senhaLucasByte = senhaLucas.getBytes();
            byte[] nomeLucasCrip = cipher.doFinal(nomeLucasByte);
            byte[] senhaLucasCrip = cipher.doFinal(senhaLucasByte);
            String nomeEncodedLucas = Base64.getEncoder().encodeToString(nomeLucasCrip);
            String senhaEncodedLucas = Base64.getEncoder().encodeToString(senhaLucasCrip);
            lucas.put("publica", publicKey.toString());
            lucas.put("nome", nomeEncodedLucas);
            lucas.put("senha", senhaEncodedLucas);
            
            
            ////////////////////////////////////////////////////////////////////
            
            
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
            keyPair = generator.genKeyPair();
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            
            
            String nomeFernando = "Fernando";
            String senhaFernando = "fernan0";
            JSONObject fernando = new JSONObject();
            File fernandoArquivo = new File("fernando.txt");
            byte[] nomeFernandoByte = nomeFernando.getBytes();
            byte[] senhaFernandoByte = senhaFernando.getBytes();
            byte[] nomeFernandoCrip = cipher.doFinal(nomeFernandoByte);
            byte[] senhaFernandoCrip = cipher.doFinal(senhaFernandoByte);
            String nomeEncodedFernando = Base64.getEncoder().encodeToString(nomeFernandoCrip);
            String senhaEncodedFernando = Base64.getEncoder().encodeToString(senhaFernandoCrip);
            fernando.put("publica", publicKey.toString());
            
            String chavePublica = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            String chavePrivada = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            
            fernando.put("nome", nomeEncodedFernando);
            fernando.put("senha", senhaEncodedFernando);
            
            JSONObject chaveFernando = new JSONObject();
            chaveFernando.put("publica", publicKey.toString());
            chaveFernando.put("privada", privateKey.toString());
            
            ////////////////////////////////////////////////////////////////////
            
            
            JSONObject[] usuarios = new JSONObject[]{joao, lucas, fernando};
            credenciamento.put(usuarios);
            
            //FileOutputStream joaoStream = new FileOutputStream(joaoArquivo);
            //joaoStream.write();
            //FileWriter joaoWrite = new FileWriter(joaoArquivo);
            //FileWriter lucasWrite = new FileWriter(lucasArquivo);
            
            //FileOutputStream fernandoStream = new FileOutputStream(fernandoArquivo);                                    
            //fernandoStream.write(b);                                                         
            //FileWriter fernandoWrite = new FileWriter(fernandoArquivo);                                                                                                                                                                                          
                                    
            //byte[] decodedByteMessage = Base64.getDecoder().decode(senhaJoaoEncoded);                         
            //String decodedStringMessage = new String(encryptedByte,  StandardCharsets.UTF_8);                                    
            //cipher.init(Cipher.DECRYPT_MODE, privateKey);            
            //byte[] decryptedByte = cipher.doFinal(decodedByteMessage);            
            //message = new String(decryptedByte);
            
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
