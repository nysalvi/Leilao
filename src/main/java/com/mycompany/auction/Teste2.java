/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.auction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;

/**
 *
 * @author Anton
 */
public class Teste2 {
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
            
            JSONObject objecto = new JSONObject();
            JSONObject object1 = new JSONObject();
            
            byte[] key = privateKey.getEncoded();
            
            byte[] messageCrypto = cipher.doFinal("Test Message".getBytes());
            
            objecto.put("chave", key);
            objecto.put("mensagem", messageCrypto);
            object1.put("chave", "isso isso isso");
            object1.put("mensagem", "fooi sem ");

            System.out.println(objecto);
            credenciamento.put(objecto);
            credenciamento.put(object1);
            
            File arquivo = new File("servidor.json");
            FileWriter writer = new FileWriter(arquivo);                        
            writer.write(objecto.toString());            
            writer.close();

            File arquivo1 = new File("servidor1.json");
            FileWriter writer1 = new FileWriter(arquivo1);                        
            //credenciamento.write(writer1);
        
            writer1.write(credenciamento.toString());
            /*
            credenciamento.forEach(x -> {                
                try {                
                    writer1.write(x.toString());
                } catch (IOException ex) {
                    Logger.getLogger(Teste2.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            */
            writer1.close();
        }
        catch (IOException ex) {
            Logger.getLogger(Teste2.class.getName()).log(Level.SEVERE, null, ex);
        }
            //writer1.write(credenciamento.);            
            //writer1.close();            
    }
}
