package controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    private static final String URL = "jdbc:postgresql://localhost:5432/biblioteca";
    private static final String USR = "postgres";
    private static final String PWD = "123";
    private static final String DRIVER = "org.postgresql.Driver";
    
    public static Connection conectar(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USR, PWD);
        } catch (Exception e) {
            System.err.println("ERRO: " + e.getMessage());
            return null;
        }
    }
    
    public static void desconectar(Connection con){
        try {
            if (con != null){
                con.close();
            }
            con.close();
        } catch (Exception e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        Connection con = Conexao.conectar();
        if (con != null){
            System.out.println("Conectado");
        }
        Conexao.desconectar(con);
    }

}
