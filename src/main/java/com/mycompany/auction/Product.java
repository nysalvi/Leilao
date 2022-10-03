/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.auction;

import org.json.JSONObject;

public class Product{
    String descricao;
    int aposta;
    int multiplo;    
    
    public Product(){        
        
    }
    public Product(String descricao, int aposta, int multiplo){
        this.descricao = descricao;
        this.aposta = aposta;
        this.multiplo = multiplo;
    }
    public static Product converteProduto(JSONObject produto){
        String descricao = produto.getString("descricao");
        int aposta = produto.getInt("aposta");
        int multiplo = produto.getInt("multiplo");
        return new Product(descricao, aposta, multiplo);                   
    }
}
