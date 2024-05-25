package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import controller.conexao.Conexao;

public class LivroAcervoDAO {

    private Connection con;
    private PreparedStatement cmd;

    public LivroAcervoDAO() {
        this.con = Conexao.conectar();
    }
    
}
