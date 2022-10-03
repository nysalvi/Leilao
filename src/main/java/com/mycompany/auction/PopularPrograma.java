package com.mycompany.auction;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
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



public class PopularPrograma {
    public static void main(String[] args){
        try { 
                                    
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");            
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);                        
            KeyPair keyPair = generator.genKeyPair();            
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic(); 
            
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);            
            
            //String m = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            //byte[] array = Base64.getDecoder().decode(m);
            //X509EncodedKeySpec x509Encoded = new X509EncodedKeySpec(array);            
            //PublicKey chaee = KeyFactory.getInstance("RSA").generatePublic(x509Encoded);
            
            
            JSONObject joao = new JSONObject();
            File joaoArquivo = new File("joao.json");                                    
            /*
                byte[] nomeJoaoByte = "Joao".getBytes();
                byte[] senhaJoaoByte = "joao123".getBytes();
                byte[] nomeJoaoCrip = cipher.doFinal(nomeJoaoByte);            
                byte[] senhaJoaoCrip = cipher.doFinal(senhaJoaoByte);            
                String nomeEncodedJoao = Base64.getEncoder().encodeToString(nomeJoaoCrip);
                String senhaEncodedJoao = Base64.getEncoder().encodeToString(senhaJoaoCrip);            
            */
            byte[] publicKeyByte = publicKey.getEncoded();
            byte[] privateKeyByte = privateKey.getEncoded();
            
            String publicKeyString = Base64.getEncoder().encodeToString(publicKeyByte);
            String privateKeyString = Base64.getEncoder().encodeToString(privateKeyByte);
            
            joao.put("nome", "Joao");
            joao.put("senha", "joao123");            
            joao.put("publica", publicKeyString);
            joao.put("privada", privateKeyString);
            
            FileWriter joaoWriter = new FileWriter(joaoArquivo);
            joaoWriter.write(joao.toString());
            joaoWriter.close();
            ////////////////////////////////////////////////////////////////////
            
            ObjectInputStream d;
            
            
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");            
            generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);                        
            keyPair = generator.genKeyPair();                                    
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
            
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            
            
            JSONObject lucas = new JSONObject();
            File lucasArquivo = new File("lucas.json");
            /*
                String nomeLucas = "Lucas";
                String senhaLucas = "luc@s";
                byte[] nomeLucasByte = nomeLucas.getBytes();
                byte[] senhaLucasByte = senhaLucas.getBytes();
                byte[] nomeLucasCrip = cipher.doFinal(nomeLucasByte);
                byte[] senhaLucasCrip = cipher.doFinal(senhaLucasByte);
                String nomeEncodedLucas = Base64.getEncoder().encodeToString(nomeLucasCrip);
                String senhaEncodedLucas = Base64.getEncoder().encodeToString(senhaLucasCrip);
            */
            publicKeyByte = publicKey.getEncoded();
            privateKeyByte = privateKey.getEncoded();            
            publicKeyString = Base64.getEncoder().encodeToString(publicKeyByte);
            privateKeyString = Base64.getEncoder().encodeToString(privateKeyByte);
            
            lucas.put("nome", "Lucas");
            lucas.put("senha", "luc@s");
            lucas.put("publica", publicKeyString);
            lucas.put("privada", privateKeyString);
            
            FileWriter lucasWriter = new FileWriter(lucasArquivo);
            lucasWriter.write(lucas.toString());
            lucasWriter.close();
            ////////////////////////////////////////////////////////////////////
            
            
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
            keyPair = generator.genKeyPair();
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            
            
            JSONObject fernando = new JSONObject();
            File fernandoArquivo = new File("fernando.json");
            /*
                String nomeFernando = "Fernando";
                String senhaFernando = "fernan0";
                byte[] nomeFernandoByte = nomeFernando.getBytes();
                byte[] senhaFernandoByte = senhaFernando.getBytes();
                byte[] nomeFernandoCrip = cipher.doFinal(nomeFernandoByte);
                byte[] senhaFernandoCrip = cipher.doFinal(senhaFernandoByte);
                String nomeEncodedFernando = Base64.getEncoder().encodeToString(nomeFernandoCrip);
                String senhaEncodedFernando = Base64.getEncoder().encodeToString(senhaFernandoCrip);
            */
            publicKeyByte = publicKey.getEncoded();
            privateKeyByte = privateKey.getEncoded();            
            publicKeyString = Base64.getEncoder().encodeToString(publicKeyByte);
            privateKeyString = Base64.getEncoder().encodeToString(privateKeyByte);
            
            fernando.put("nome", "Fernando");
            fernando.put("senha", "fernan0");
            fernando.put("publica", publicKeyString);
            fernando.put("privada", privateKeyString);
            
            FileWriter fernandoWriter = new FileWriter(fernandoArquivo);
            fernandoWriter.write(fernando.toString());
            fernandoWriter.close();
            //String chavePublica = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            //String chavePrivada = Base64.getEncoder().encodeToString(publicKey.getEncoded());                        
            
            ////////////////////////////////////////////////////////////////////
            
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");            
            generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);                        
            keyPair = generator.genKeyPair();            
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic(); 
            
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);           
           
            
            JSONObject servidorDados = new JSONObject();
            
            publicKeyByte = publicKey.getEncoded();
            privateKeyByte = privateKey.getEncoded();            
            publicKeyString = Base64.getEncoder().encodeToString(publicKeyByte);
            privateKeyString = Base64.getEncoder().encodeToString(privateKeyByte);
            
            servidorDados.put("publica", publicKeyString);
            servidorDados.put("private", privateKeyString);
            
            JSONArray servidor = new JSONArray();                                   
            JSONObject[] usuarios = new JSONObject[]{joao, lucas, fernando, servidorDados};
            servidor.put(usuarios);
            
            File servidorArquivo = new File("servidor.json");
            FileWriter servidorWriter = new FileWriter(servidorArquivo);
            servidorWriter.write(servidor.toString());
            
            JSONObject chavePublicaJson = new JSONObject();
            publicKey = keyPair.getPublic();
            
            publicKeyByte = publicKey.getEncoded();
            publicKeyString = Base64.getEncoder().encodeToString(publicKeyByte);


            chavePublicaJson.put("chave", publicKeyString);
            File chave_publica = new File("chave_publica.json");
            FileWriter chave_publicaWriter = new FileWriter(chave_publica);
            chave_publicaWriter.write(servidor.toString());
            chave_publicaWriter.close();
            
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PopularPrograma.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(PopularPrograma.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PopularPrograma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
