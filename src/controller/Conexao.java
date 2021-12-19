
package controller;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexao {
        public Connection connect; // faz coneção com o BD
        public Statement stmt; // passa os comandos para o BD
        public ResultSet result; // quarda os resultados das consultas 
        
        private String emailUser;

        
        
        // codigo pra fazer a conexao com o banco
        public void conectar(){
            
            try {
                connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/restaurante", "root", "");


            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro de conecxao com o banco de dados. ERRO:"+ex);
            }
        }
        
        
        
        // codigo pra fazer a consulta no banco de dados
        public void consultaSQL(String sql){
            try {
                stmt = (Statement) connect.createStatement(
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
            
            ResultSet consulta = stmt.executeQuery(sql);
            
            while(consulta.next()){
                String resultEmail = consulta.getString("email");
                String resulSenha = consulta.getString("cadeiras");
                String resultNome = consulta.getString("status");
                



            }
            
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro na consulta SQL.  ERRO:"+ex);
            }finally{
            // codigo pra fechar a conexao com o banco
                try {
                    connect.close();
                    System.out.println("conexao fechada!");
                } catch (SQLException ex) {
                    Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
        }
        
        

    
    
        
        //manipula os dados do banco. basicamente faz as consultas que alteram o banco
        public void manipulaSQL(String sql){
            try {
                stmt = (Statement) connect.createStatement(
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                
                stmt.executeUpdate(sql);
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro. dados nao inseridos.  ERRO:"+ex);
            }
        }
        
        
        
        
        public boolean verificaEmail(String sql){
            try {
                stmt = (Statement) connect.createStatement(
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
            ResultSet consulta = stmt.executeQuery(sql);
            
            if(consulta.next()){
//                JOptionPane.showMessageDialog(null, "Esse email ja esta cadastrado");
                return true;
            }
            
            } catch (SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
            
        
        }
            
        public boolean verificaUsuario(String sql){
            try {
                stmt = (Statement) connect.createStatement(
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
            ResultSet consulta = stmt.executeQuery(sql);
            
            if(consulta.next()){
//                JOptionPane.showMessageDialog(null, "Esse email ja esta cadastrado");
                this.emailUser = consulta.getString("email");
                return true;
            }
            
            } catch (SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
            
        
        }
        
        

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

        
    
    
    
    
        
        
}
