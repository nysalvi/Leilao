/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.auction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Anton
 */
public class Teste3 {
    public static void main(String[] args) {
        try {
                        
            File arquivo = new File("servidor.json");
            byte[] charac;
            try (FileInputStream reader = new FileInputStream(arquivo)) {
                charac = reader.readAllBytes();
            }
                        
            File arquivoArray = new File("servidor1.json");
            byte[] fff;
            try (FileInputStream readerArray = new FileInputStream(arquivoArray)){
                fff = readerArray.readAllBytes();
            }
            String arrayJson = new String(fff);
            JSONArray credenciamento = new JSONArray(arrayJson);                        
                        
            //InputStreamReader x = new InputStreamReader();
            //reader.transferTo(t);
            
            String qw = new String(charac);
            
            JSONObject objecto = new JSONObject(qw);            
            
            
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        } 
    }
    
}
