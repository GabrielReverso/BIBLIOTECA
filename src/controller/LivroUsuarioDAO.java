package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import controller.conexao.Conexao;

public class LivroUsuarioDAO {

    private Connection con;
    private PreparedStatement cmd;

    public LivroUsuarioDAO() {
        this.con = Conexao.conectar();
    }
    
}
