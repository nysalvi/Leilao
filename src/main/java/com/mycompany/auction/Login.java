/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.auction;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.swing.JOptionPane;
import org.json.JSONObject;

public class Login extends javax.swing.JFrame {

    private String nome;
    private String senha;
    
    private InetAddress ip;
    private int porta;
    
    //String caminhoChave;
    boolean valido;
    InetAddress ipLeilao;
    int portaLeilao;
    InetSocketAddress address;
    SecretKey key;
    //Client cliente;
    private DatagramSocket socket;
    
    private PrivateKey chavePrivada;
    private PublicKey chavePublica;
    byte[] iv;
    
    public Login() {        
        //cliente = new Client();
        /*
        new Thread(() -> {
            initComponents();  
            this.setVisible(true);
            try {
                //KeyStore.
                socket = new DatagramSocket();
            } catch (SocketException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }        
        });
        */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                initComponents();
                setVisible(true);                                
            }
        });
        
        
        //KeyStore.getInstance(file, password);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelLogin = new javax.swing.JPanel();
        jLabelNome = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jTextFieldSenha = new javax.swing.JTextField();
        jLabelSenha = new javax.swing.JLabel();
        jPanelBotao = new javax.swing.JPanel();
        jButtonLogar = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();
        jPanelConexao = new javax.swing.JPanel();
        jLabelIP = new javax.swing.JLabel();
        jLabelPorta = new javax.swing.JLabel();
        jLabelChave = new javax.swing.JLabel();
        jTextFieldIP = new javax.swing.JTextField();
        jTextFieldPorta = new javax.swing.JTextField();
        jTextFieldChave = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelNome.setText("Nome: ");

        jLabelSenha.setText("Senha:");

        javax.swing.GroupLayout jPanelLoginLayout = new javax.swing.GroupLayout(jPanelLogin);
        jPanelLogin.setLayout(jPanelLoginLayout);
        jPanelLoginLayout.setHorizontalGroup(
            jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNome)
                    .addComponent(jLabelSenha))
                .addGap(18, 18, 18)
                .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(jTextFieldNome))
                .addContainerGap())
        );
        jPanelLoginLayout.setVerticalGroup(
            jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLoginLayout.createSequentialGroup()
                .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNome)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSenha))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jButtonLogar.setText("Logar");
        jButtonLogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogarActionPerformed(evt);
            }
        });

        jButtonSair.setText("Sair");
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBotaoLayout = new javax.swing.GroupLayout(jPanelBotao);
        jPanelBotao.setLayout(jPanelBotaoLayout);
        jPanelBotaoLayout.setHorizontalGroup(
            jPanelBotaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotaoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jButtonLogar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jButtonSair, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanelBotaoLayout.setVerticalGroup(
            jPanelBotaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBotaoLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanelBotaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLogar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSair, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        jLabelIP.setText("IP: ");

        jLabelPorta.setText("Porta:");

        jLabelChave.setText("Chave: ");

        javax.swing.GroupLayout jPanelConexaoLayout = new javax.swing.GroupLayout(jPanelConexao);
        jPanelConexao.setLayout(jPanelConexaoLayout);
        jPanelConexaoLayout.setHorizontalGroup(
            jPanelConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConexaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelConexaoLayout.createSequentialGroup()
                            .addComponent(jLabelChave)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                        .addGroup(jPanelConexaoLayout.createSequentialGroup()
                            .addComponent(jLabelPorta)
                            .addGap(20, 20, 20)))
                    .addGroup(jPanelConexaoLayout.createSequentialGroup()
                        .addComponent(jLabelIP)
                        .addGap(35, 35, 35)))
                .addGroup(jPanelConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldIP, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(jTextFieldPorta, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(jTextFieldChave))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanelConexaoLayout.setVerticalGroup(
            jPanelConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConexaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIP)
                    .addComponent(jTextFieldIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPorta)
                    .addComponent(jTextFieldPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelChave)
                    .addComponent(jTextFieldChave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanelBotao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelConexao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanelConexao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelBotao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonSairActionPerformed
    void receiveLogin(){
        while (true){
            try {                       
                byte[] buffer = new byte[2000];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                
                byte[] mensagemComCifraEncoded = packet.getData();
                
                JSONObject mensagem = Criptografia.decriptAssimetrica(mensagemComCifraEncoded, chavePrivada);
                
                JOptionPane.showMessageDialog(this, mensagem.get("mensagem"), "AVISO!!!", JOptionPane.INFORMATION_MESSAGE);
                if (mensagem.getBoolean("STATUS")){
                    /*
                resposta.append("STATUS", true);
                resposta.append("IP", ipLeilao.toString());
                resposta.append("porta", portaLeilao);
                resposta.append("mensagem", "Conectado com Sucesso no Leilão!");
                resposta.append("salt", salt);
                resposta.append("chave", CHAVE_SECRETA);
                resposta.append("IV", iv);
                    */
                portaLeilao = mensagem.getInt("porta");
                ipLeilao = InetAddress.getByName(mensagem.getString("IP"));
                address = new InetSocketAddress(ip, porta);                    
                iv = Base64.getDecoder().decode(mensagem.getString("iv"));
                key = (SecretKey)mensagem.get("chave");
                this.dispose();
                }
                else return;                
            } 
            catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "ERRO!!!", JOptionPane.ERROR_MESSAGE);
            }
        }        
    }
    void sendLogin(JSONObject mensagem, PublicKey chavePublicaServidor){
            try {
                byte[] mensagemCifradaEncoded = Criptografia.encriptAssimetrica(mensagem, chavePublicaServidor);
                DatagramPacket packet = new DatagramPacket(mensagemCifradaEncoded, mensagemCifradaEncoded.length, ip , porta);
                socket.send(packet);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "ERRO!!!", JOptionPane.ERROR_MESSAGE);
            }        
    }
    private void jButtonLogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogarActionPerformed
        try {
            nome = jTextFieldNome.getText();
            senha = jTextFieldSenha.getText();
            
            ip = InetAddress.getByName(jTextFieldIP.getText());
            porta = Integer.valueOf(jTextFieldPorta.getText());
            String caminhoChave = jTextFieldChave.getText();  
            
            carregarLoginUsuario(caminhoChave);            
            
            valido = fazerLogin();
            if (valido)this.dispose();
            
        }
        catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(this, "Digite um endereço de IP Válido", "ERRO!!!", JOptionPane.ERROR_MESSAGE);
        }
        catch (NumberFormatException ne){
            JOptionPane.showMessageDialog(this, "Digite Apenas Números", "ERRO!!!", JOptionPane.ERROR_MESSAGE);
        } 
    }//GEN-LAST:event_jButtonLogarActionPerformed
    public boolean fazerLogin(){
        JSONObject mensagem = new JSONObject();
        mensagem.append("nome", nome);
        mensagem.append("senha", senha);    
        mensagem.append("chave", chavePublica);
        PublicKey chavePublicaServidor = (PublicKey)Arquivo.carregar("chave_publica_servidor").get("chave");
        sendLogin(mensagem, chavePublicaServidor);
        receiveLogin();
        return true;
    }   
    public void carregarLoginUsuario(String caminhoChave){
        //File arquivoChave = new File(caminhoChave);

        //byte[] chavePrivadaBytes = new FileInputStream(arquivoChave).readAllBytes();

        JSONObject clienteJSON = Arquivo.carregar(caminhoChave);

        chavePrivada = (PrivateKey)clienteJSON.get("privada");
        chavePublica = (PublicKey)clienteJSON.get("publica");
        
        
        //X509EncodedKeySpec
        //PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(chavePrivadaBytes);
        //KeyFactory keyFactory;            
        //try {
        //keyFactory = KeyFactory.getInstance("RSA");
        //} catch (NoSuchAlgorithmException ex) {
        //JOptionPane.showMessageDialog(this, ex.getMessage(), "ERRO!!!", JOptionPane.ERROR_MESSAGE);
        //}
        
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLogar;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JLabel jLabelChave;
    private javax.swing.JLabel jLabelIP;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelPorta;
    private javax.swing.JLabel jLabelSenha;
    private javax.swing.JPanel jPanelBotao;
    private javax.swing.JPanel jPanelConexao;
    private javax.swing.JPanel jPanelLogin;
    private javax.swing.JTextField jTextFieldChave;
    private javax.swing.JTextField jTextFieldIP;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldPorta;
    private javax.swing.JTextField jTextFieldSenha;
    // End of variables declaration//GEN-END:variables
}
