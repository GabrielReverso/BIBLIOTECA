package controller;

import java.util.LinkedList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import controller.conexao.Conexao;
import model.Acervo;
import model.Livro;
import model.LivroAcervo;

public class LivroAcervoDAO {

    private Connection con;
    private PreparedStatement cmd;

    public LivroAcervoDAO() {
        this.con = Conexao.conectar();
    }

    public List<LivroAcervo> obterLivrosDoAcervo(String local){
        List<LivroAcervo> lista = new LinkedList<>();

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
                
                Acervo acervo = new Acervo(rs.getInt("id_acervo"), rs.getString("localidade"));
                
                livro.setId(rs.getInt("id"));
                LivroAcervo livroAcervo = new LivroAcervo(livro, 
                                                          acervo, 
                                                          rs.getInt("quantidade"), 
                                                          rs.getInt("disponibilidade"));
                lista.add(livroAcervo);
            }

            return lista;

        } catch (Exception e) {
            System.err.println("Erro na execução do query: " + e.getMessage());
            return lista;
        } finally {
            Conexao.desconectar(con);
        }
    }

    public List<LivroAcervo> buscarLivrosDoAcervo(String local, String busca){
        List<LivroAcervo> lista = new LinkedList<>();

        try {
            String SQL = "select * " +
                         "FROM tb_livro tl " + 
                         "INNER JOIN tb_livro_acervo tla ON tl.id = tla.id_livro " +
                         "INNER JOIN tb_acervo ta ON ta.id = tla.id_acervo  " +
                         "WHERE ta.localidade = ? and tl.titulo ILIKE ?";

            cmd = con.prepareStatement(SQL);
            cmd.setString(1, local);
            cmd.setString(2, "%" + busca + "%");

            ResultSet rs = cmd.executeQuery();

            while (rs.next()){
                Livro livro = new Livro(
                            rs.getString("titulo"),
                            rs.getString("autor"),
                            rs.getString("editora"),
                            rs.getString("descricao")
                        );
                livro.setId(rs.getInt("id"));

                Acervo acervo = new Acervo(rs.getInt("id_acervo"), rs.getString("localidade"));

                LivroAcervo livroAcervo = new LivroAcervo(livro, 
                                                          acervo, 
                                                          rs.getInt("quantidade"), 
                                                          rs.getInt("disponibilidade"));
                lista.add(livroAcervo);
            }

            return lista;

        } catch (Exception e) {
            System.err.println("Erro na execução do query: " + e.getMessage());
            return lista;
        } finally {
            Conexao.desconectar(con);
        }
    }   
    
    public List<Livro> buscarLivrosDoAcervo(String busca){
        List<Livro> lista = new LinkedList<>();

        try {
            String SQL = "select * " +
                         "FROM tb_livro tl " + 
                         "WHERE tl.titulo ILIKE ?";

            cmd = con.prepareStatement(SQL);
            cmd.setString(1, "%" + busca + "%");

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
        } finally {
            Conexao.desconectar(con);
        }
    }  
}
