package com.mycompany.auction;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONObject;

public class Criptografia {
    public static JSONObject decriptSimetrica(byte[] mensagemComCifraEncode, SecretKey chave, byte[] iv){
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKey = new SecretKeySpec(chave.getEncoded(), "AES");            

            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
            
            
            byte[] mensagemComCifra = Base64.getDecoder().decode(mensagemComCifraEncode);                
            
            byte[] mensagemBuffer = cipher.doFinal(mensagemComCifra);
                        
            return new JSONObject(new String(mensagemBuffer));
        }
        catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
        public static byte[] encriptSimetrica(JSONObject mensagem, SecretKey chave, byte[] iv){
            try {
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                SecretKeySpec secretKey = new SecretKeySpec(chave.getEncoded(), "AES");            

                cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
                byte[] mensagemBuffer = mensagem.toString().getBytes();
                byte[] mensagemComCifra = cipher.doFinal(mensagemBuffer);
                byte[] mensagemComCifraEncode = Base64.getEncoder().encode(mensagemComCifra);                

                return mensagemComCifraEncode;
            }
            catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e){
                System.out.println(e.getMessage());
            }
            return null;
    }
        public static byte[] encriptAssimetrica(JSONObject mensagem, Key chave){
            try {                                                
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");                                  

                cipher.init(Cipher.ENCRYPT_MODE, chave);                                    

                byte[] mensagemCifrada = cipher.doFinal(mensagem.toString().getBytes());

                return Base64.getEncoder().encode(mensagemCifrada);
            } 
            catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException ex) {
                Logger.getLogger(Criptografia.class.getName()).log(Level.SEVERE, null, ex);
            }        
            return null;
    }
    public static JSONObject decriptAssimetrica(byte[] mensagemCifrada, Key chave){
        try {                                                
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");                                                
            cipher.init(Cipher.DECRYPT_MODE, chave);                                    
            
            byte[] mensagemDecoded = Base64.getDecoder().decode(mensagemCifrada);
            byte[] mensagemByte = cipher.doFinal(mensagemDecoded);            
            String mensagem = new String(mensagemByte);
            
            return new JSONObject(mensagem);
        } 
        catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException ex) {
            Logger.getLogger(Criptografia.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return null;
    }        
}
