/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.auction;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.SecretKey;
import javax.swing.JOptionPane;
import org.json.JSONObject;

public class TelaMain extends javax.swing.JFrame {
    InetAddress ipLeilao;
    int portaLeilao;
    
    Product produto;
    Client cliente;
    SecretKey key;
    PublicKey serverKey;
    byte[] iv;
    
    public TelaMain(){}
    public TelaMain(InetAddress ipLeilao, int portaLeilao, byte[] iv, SecretKey key) {        
        this.ipLeilao = ipLeilao;        
        this.portaLeilao = portaLeilao;        
        this.iv = iv;
        this.key = key;
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaConsole = new javax.swing.JTextArea();
        jLabelAposta = new javax.swing.JLabel();
        jTextFieldAposta = new javax.swing.JTextField();
        jButtonEnviar = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();
        jLabelItemAtual = new javax.swing.JLabel();
        jLabelItem = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextAreaConsole.setColumns(20);
        jTextAreaConsole.setRows(5);
        jScrollPane1.setViewportView(jTextAreaConsole);

        jLabelAposta.setText("Aposta: R$");

        jButtonEnviar.setText("Enviar");
        jButtonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviarActionPerformed(evt);
            }
        });

        jButtonSair.setText("Sair");
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });

        jLabelItemAtual.setText("Item Atual:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelItemAtual)
                        .addGap(27, 27, 27)
                        .addComponent(jLabelItem, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jButtonSair, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelAposta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldAposta, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(122, 122, 122)
                                .addComponent(jButtonEnviar)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelItemAtual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAposta)
                    .addComponent(jTextFieldAposta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEnviar))
                .addGap(28, 28, 28)
                .addComponent(jButtonSair, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviarActionPerformed
        String apostaString = jTextFieldAposta.getText();
        try {
            int aposta = Integer.valueOf(apostaString);            
            
            if (aposta % produto.multiplo != 0){
                JOptionPane.showMessageDialog(this, "A aposta deve ser em m??ltiplos de " + produto.multiplo, "AVISO!!!", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (aposta < produto.aposta * 1.1){
                JOptionPane.showMessageDialog(this, "O Valor precisa ser 10% maior que o lance atual", "AVISO!!!", JOptionPane.WARNING_MESSAGE);
                return;
            }
                                 
            byte[] buffer;
            JSONObject apostaJSON = new JSONObject();
            apostaJSON.append("aposta", aposta);
            apostaJSON.append("cliente", cliente);
            //Criptografia.decriptSimetrica(buffer, CHAVE_SECRETA, salt, iv);            
            //buffer = Base64.getEncoder().encode(apostaJSON.toString().getBytes());
            buffer = Criptografia.encriptSimetrica(apostaJSON, key, iv);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, cliente.group, cliente.porta);                       
            cliente.socket.send(packet);
            
        } 
        catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }                       
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Digite Apenas N??meros", "ERRO!!!", JOptionPane.ERROR_MESSAGE);            
        }                
    }//GEN-LAST:event_jButtonEnviarActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonSairActionPerformed
    public static void main(String args[]) {
        /*
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true);                
            }
        });    
        */
        Login login = new Login();      
        while(!login.valido);                        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaMain(login.ipLeilao, login.portaLeilao, login.iv, login.key).setVisible(true);                                
            }
        });
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEnviar;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JLabel jLabelAposta;
    private javax.swing.JLabel jLabelItem;
    private javax.swing.JLabel jLabelItemAtual;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaConsole;
    private javax.swing.JTextField jTextFieldAposta;
    // End of variables declaration//GEN-END:variables
}
