package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import controller.conexao.Conexao;

public class AcervoDAO {

    private Connection con;
    private PreparedStatement cmd;

    public AcervoDAO() {
        this.con = Conexao.conectar();
    }
    
}
