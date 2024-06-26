package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import controller.conexao.Conexao;
import model.Livro;

public class LivroDAO {
    
    private Connection con;
    private PreparedStatement cmd;

    public LivroDAO() {
        this.con = Conexao.conectar();
    }

    public List<Livro> obterLivros(){
        List<Livro> lista = new LinkedList<>();

        try {
            String SQL = "select * from tb_livro";
    
            cmd = con.prepareStatement(SQL);

            ResultSet rs = cmd.executeQuery();

            while (rs.next()){
                Livro livro = new Livro(
                            rs.getString("titulo"),
                            rs.getString("autor"),
                            rs.getString("editora"),
                            rs.getString("descricao")
                        );
                
                livro.setId(rs.getInt("id"));
                livro.setPathImagem(rs.getString("imagePath"));
                lista.add(livro);
            }

            Collections.sort(lista);
            return lista;

        } catch (Exception e) {
            System.err.println("Erro na execução do query: " + e.getMessage());
            return null;
        } finally {
            Conexao.desconectar(con);
        }
    }
}
