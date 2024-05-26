package controller;

import java.util.LinkedList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import controller.conexao.Conexao;
import model.Acervo;

public class AcervoDAO {

    private Connection con;
    private PreparedStatement cmd;

    public AcervoDAO() {
        this.con = Conexao.conectar();
    }

    public List<Acervo> obterAcervos(){

        List<Acervo> lista = new LinkedList<>();

        try {
            String SQL = "select * from tb_acervo";

            cmd = con.prepareStatement(SQL);

            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                lista.add(new Acervo(rs.getInt("id"), rs.getString("localidade")));
            }

            return lista;
        } catch (Exception e) {
            System.err.println("Erro na execução do query: " + e.getMessage());
            return lista;
        }
    }
    
}
