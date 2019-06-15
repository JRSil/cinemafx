package br.com.cinemafx.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {
    static String url = "jdbc:mysql://localhost:3306/cinema";
    static String usuario = "root";
    static String senha = "";
    static Connection con = null;
    
    public static Connection getConexao() throws SQLException {
        System.out.println("Conectando ao banco...");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            if(con == null){
                con = (Connection) DriverManager.getConnection(url, usuario, senha);
                System.out.println("Conectado.");
            }
            return con;
        }catch(ClassNotFoundException e){
            throw new SQLException(e.getMessage());
        }
    }
}
