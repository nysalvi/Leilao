/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.auction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Anton
 */
public class Arquivo {
    public static JSONArray carregarDados(String arquivo){
        File arquivoArray = new File(arquivo);
        byte[] arquivoBytes;
        try (FileInputStream readerArray = new FileInputStream(arquivoArray)){
            //arquivoBytes = Base64.getDecoder().decode(readerArray.readAllBytes());
            arquivoBytes = readerArray.readAllBytes();
            String arquivoJson = new String(arquivoBytes);
            return new JSONArray(arquivoJson);   
        } catch (IOException ex) {
            Logger.getLogger(Arquivo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static JSONObject carregar(String arquivo){
        File arquivoArray = new File(arquivo);
        byte[] arquivoBytes;
        try (FileInputStream readerArray = new FileInputStream(arquivoArray)){
            //arquivoBytes = Base64.getDecoder().decode(readerArray.readAllBytes());
            arquivoBytes = readerArray.readAllBytes();
            String arquivoJson = new String(arquivoBytes);
            return new JSONObject(arquivoJson);   
        } catch (IOException ex) {
            Logger.getLogger(Arquivo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }    
    public static PublicKey carregarChavePublicaServidor(String arquivo){
        File arquivoChave = new File(arquivo);        
        try (FileInputStream inputStream = new FileInputStream(arquivoChave)){
            //byte[] chave = Base64.getDecoder().decode(inputStream.readAllBytes());
            byte[] chave = inputStream.readAllBytes();
            X509EncodedKeySpec x509Encoded = new X509EncodedKeySpec(chave);            
            return KeyFactory.getInstance("RSA").generatePublic(x509Encoded);            
        }
        catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(Arquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
