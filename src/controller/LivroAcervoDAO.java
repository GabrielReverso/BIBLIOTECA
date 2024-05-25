package controller;

import java.util.LinkedList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import controller.conexao.Conexao;
import model.Livro;

public class LivroAcervoDAO {

    private Connection con;
    private PreparedStatement cmd;

    public LivroAcervoDAO() {
        this.con = Conexao.conectar();
    }

    public List<Livro> obterLivrosDoAcervo(String local){
        List<Livro> lista = new LinkedList<>();

        try {
            String SQL = "select * " +
                         "FROM tb_livro tl " + 
                         "INNER JOIN tb_livro_acervo tla ON tl.id = tla.id_livro " +
                         "INNER JOIN tb_acervo ta ON ta.id = tla.id_acervo  " +
                         "WHERE ta.localidade = ?";

            cmd = con.prepareStatement(SQL);
            cmd.setString(1, local);

            ResultSet rs = cmd.executeQuery();

            while (rs.next()){
                Livro livro = new Livro(
                            rs.getString("titulo"),
                            rs.getString("autor"),
                            rs.getString("editora"),
                            rs.getString("descricao")
                        );
                
                livro.setId(rs.getInt("id"));
                lista.add(livro);
            }

            return lista;

        } catch (Exception e) {
            System.err.println("Erro na execução do query: " + e.getMessage());
            return lista;
        }
    }
    
}
