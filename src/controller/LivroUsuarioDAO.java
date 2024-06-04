package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.sql.Date;

import javax.swing.JOptionPane;

import controller.conexao.Conexao;
import model.Acervo;
import model.Livro;
import model.LivroAcervo;
import model.LivroUsuario;
import model.Usuario;

public class LivroUsuarioDAO {

    private Connection con;
    private PreparedStatement cmd;
    private final long SETE_DIAS_EM_MILISEGUNDOS = (long) 7 * 24 * 60 * 60 * 1000;

    public LivroUsuarioDAO() {
        this.con = Conexao.conectar();
    }

    public void emprestimoLivro(Usuario u, LivroAcervo la){
        
        try {
            int disponibilidade = 0;

            String SQL = "select disponibilidade from tb_livro_acervo tla " +
                         "where id_livro = ? and id_acervo = ?";

            cmd = con.prepareStatement(SQL);
            cmd.setInt(1, la.getLivro().getId());
            cmd.setInt(2, la.getAcervo().getId());

            ResultSet rs = cmd.executeQuery();

            if(rs.next()){
                disponibilidade = rs.getInt("disponibilidade");
                if(disponibilidade <= 0){
                    JOptionPane.showMessageDialog(null, "Nenhum livro disponivel para esse acervo!");
                    return;
                }
            }

            if(u.isPossuiLivro()){
                int quantidadeLivroPosse = 0;
    
                SQL = "select * from tb_livro_usuario where id_usuario = ?";

                cmd = con.prepareStatement(SQL);
                cmd.setInt(1, u.getId());

                rs = cmd.executeQuery();

                while (rs.next()) {
                    quantidadeLivroPosse ++;
                }

                if (quantidadeLivroPosse >= 3){
                    JOptionPane.showMessageDialog(null, "Número máximo de livros emprestados!");
                    return;
                }

            }

            SQL = "insert into tb_livro_usuario (id_usuario, id_livroAcervo, dataEmprestimo, prazo, expirado) values (?,?,?,?,?)";

            cmd = con.prepareStatement(SQL);
            cmd.setInt(1, u.getId());
            cmd.setInt(2, la.getId());
            cmd.setDate(3, new Date(System.currentTimeMillis()));
            cmd.setDate(4, new Date(System.currentTimeMillis() + SETE_DIAS_EM_MILISEGUNDOS));
            cmd.setBoolean(5, false);

            int rowsAffected = cmd.executeUpdate();

            if(rowsAffected == 0){
                JOptionPane.showMessageDialog(null, "Erro no banco de dados");
                return;
            }

            u.setPossuiLivro(true);

            SQL = "update tb_usuario set possuiLivro = ? where id = ?";

            cmd = con.prepareStatement(SQL);
            cmd.setBoolean(1, true);
            cmd.setInt(2, u.getId());

            rowsAffected = cmd.executeUpdate();

            if(rowsAffected == 0){
                JOptionPane.showMessageDialog(null, "Erro no banco de dados");
                return;
            } 

            SQL = "update tb_livro_acervo set disponibilidade = ? where id_livro = ? and id_acervo = ?";

            cmd = con.prepareStatement(SQL);
            cmd.setInt(1, disponibilidade - 1);
            cmd.setInt(2, la.getLivro().getId());
            cmd.setInt(3, la.getAcervo().getId());

            rowsAffected = cmd.executeUpdate();

            if(rowsAffected == 0){
                JOptionPane.showMessageDialog(null, "Erro no banco de dados");
            } else {
                JOptionPane.showMessageDialog(null, "Operação bem sucedida!");
            }

        } catch (Exception e) {
            System.err.println("Erro no banco de dados: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro no banco de dados");
            Conexao.desconectar(con);
            return;
        } finally {
            Conexao.desconectar(con);
        }
        
    }

    public List<LivroUsuario> obterLivrosUsuario(Usuario u){

        List<LivroUsuario> lista = new LinkedList<>();

        try {
            String SQL = "select *, tla.id as id_livroAcervo "+
                         "from tb_livro_usuario tlu " +
                         "inner join tb_livro_acervo tla on tlu.id_livroacervo = tla.id " +
                         "inner join tb_livro tl on tl.id = tla.id_livro " +
                         "where tlu.id_usuario = ?";

            cmd = con.prepareStatement(SQL);
            cmd.setInt(1, u.getId());

            ResultSet rs = cmd.executeQuery();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            while (rs.next()) {
                Livro livro = new Livro(rs.getInt("id_livro"), 
                                        rs.getString("titulo"), 
                                        rs.getString("autor"), 
                                        rs.getString("editora"),
                                        rs.getString("descricao"));
                livro.setPathImagem(rs.getString("imagePath"));

                Acervo acervo = new Acervo(rs.getInt("id_acervo"));

                LivroAcervo livroAcervo = new LivroAcervo(rs.getInt("id_livroAcervo"),livro, acervo);
                
                lista.add(new LivroUsuario(u, 
                                           livroAcervo, 
                                           dateFormat.format(rs.getDate("dataEmprestimo")), 
                                           dateFormat.format(rs.getDate("prazo")), 
                                           rs.getBoolean("expirado")));
            }

            Collections.sort(lista);
            return lista;

        } catch (Exception e) {
            System.err.println("Erro ao executar query: " + e.getMessage());
            return lista;
        } finally {
            Conexao.desconectar(con);
        }
    }

    public void RenovarEmprestimo(Usuario u, LivroAcervo l, Date prazoAtual){

        try {
            String SQL = "update tb_livro_usuario set prazo = ? where id_usuario = ? and id_livroAcervo = ?";

            cmd = con.prepareStatement(SQL);
            cmd.setDate(1, new Date(prazoAtual.getTime() + SETE_DIAS_EM_MILISEGUNDOS));
            cmd.setInt(2, u.getId());
            cmd.setInt(3, l.getId());

            int rowsAffected = cmd.executeUpdate();

            if(rowsAffected == 0){
                JOptionPane.showMessageDialog(null, "Erro no banco de dados");
            } else {
                VerificarLivrosExpirados(u, l);
                JOptionPane.showMessageDialog(null, "Foram acrescentados mais 7 dias no seu empréstimo");
            }

        } catch (Exception e) {
            System.err.println("Erro ao executar update: " + e.getMessage());
        } finally {
            Conexao.desconectar(con);
        }
    }

    public void DevolverLivro(Usuario u, LivroAcervo l){

        try {
            int disponibilidade = 0;

            String SQL = "select disponibilidade from tb_livro_acervo tla " +
                         "inner join tb_livro_usuario tlu on tla.id = tlu.id_livroAcervo " +
                         "where tlu.id_usuario = ? and tlu.id_livroAcervo = ?";
            
            cmd = con.prepareStatement(SQL);
            cmd.setInt(1, u.getId());
            cmd.setInt(2, l.getId());

            ResultSet rs = cmd.executeQuery();

            if(rs.next()){
                disponibilidade = rs.getInt("disponibilidade");
            } else {
                System.err.println("Erro na obtenção da disponibilidade");
                JOptionPane.showMessageDialog(null, "Erro no banco de dados");
                return;
            }

            SQL = "update tb_livro_acervo set disponibilidade = ? where id_livro = ? and id_acervo = ?";

            cmd = con.prepareStatement(SQL);
            cmd.setInt(1, disponibilidade + 1);
            cmd.setInt(2, l.getLivro().getId());
            cmd.setInt(3, l.getAcervo().getId());

            int rowsAffected = cmd.executeUpdate();

            if(rowsAffected == 0){
                System.err.println("Erro no update da disponibilidade");
                JOptionPane.showMessageDialog(null, "Erro no banco de dados");
                return;
            }

            SQL = "delete from tb_livro_usuario where id_usuario = ? and id_livroAcervo = ?";

            cmd = con.prepareStatement(SQL);
            cmd.setInt(1, u.getId());
            cmd.setInt(2, l.getId());

            rowsAffected = cmd.executeUpdate();

            if(rowsAffected == 0){
                System.err.println("Erro ao deletar da tabela");
                JOptionPane.showMessageDialog(null, "Erro no banco de dados");
                return;
            } else {
                JOptionPane.showMessageDialog(null, "Livro devolvido!");
            }

            int quantidadeLivroPosse = 0;

            SQL = "select * from tb_livro_usuario where id_usuario = ?";

            cmd = con.prepareStatement(SQL);
            cmd.setInt(1, u.getId());

            rs = cmd.executeQuery();

            while (rs.next()) {
                quantidadeLivroPosse ++;
            }

            if (quantidadeLivroPosse == 0){
                u.setPossuiLivro(false);

                SQL = "update tb_usuario set possuiLivro = ? where id = ?";

                cmd = con.prepareStatement(SQL);
                cmd.setBoolean(1, false);
                cmd.setInt(2, u.getId());

                rowsAffected = cmd.executeUpdate();

                if(rowsAffected == 0){
                    System.err.println("Erro ao fazer update no possuiLivro");
                    JOptionPane.showMessageDialog(null, "Erro no banco de dados");
                    return;
                }
            }

        } catch (Exception e) {
            System.err.println("Erro ao executar update: " + e.getMessage());
        } finally {
            Conexao.desconectar(con);
        }
    }

    public void VerificarLivrosExpirados(Usuario u){

        if (u.isPossuiLivro()) {
            try {
                List<Integer> lista = new ArrayList<>();
                String SQL = "select prazo, id_livroAcervo from tb_livro_usuario where id_usuario = ?";
    
                cmd = con.prepareStatement(SQL);
                cmd.setInt(1, u.getId());

                ResultSet rs = cmd.executeQuery();

                java.util.Date dataAtual = new Date(System.currentTimeMillis());

                while (rs.next()) {
                    if(rs.getDate("prazo").compareTo(dataAtual) <= 0) {
                        lista.add(rs.getInt("id_livroAcervo"));
                    }
                }

                if (!lista.isEmpty()){
                    SQL = "update tb_livro_usuario set expirado = ? where id_usuario = ? and id_livroAcervo = ?";

                    for (Integer id_livroAcervo : lista){
                        cmd = con.prepareStatement(SQL);
                        cmd.setBoolean(1, true);
                        cmd.setInt(2, u.getId());
                        cmd.setInt(3, id_livroAcervo);

                        int rowsAffected = cmd.executeUpdate();

                        if(rowsAffected == 0) {
                            System.err.println("Erro ao fazer update");
                        }
                    }

                    JOptionPane.showMessageDialog(null, 
                                                 "Você possui livros expirados!\nDevolva ou extenda o prazo", 
                                                 "Alerta", 
                                                 JOptionPane.WARNING_MESSAGE);
                }
    
            } catch (Exception e) {
                System.err.println("Erro ao executar update: " + e.getMessage());
            } finally {
                Conexao.desconectar(con);
            }
        }
    }

    public void VerificarLivrosExpirados(Usuario u, LivroAcervo l){

        try {
            String SQL = "select prazo, id_livroAcervo from tb_livro_usuario where id_usuario = ? and id_livroAcervo = ?";

            cmd = con.prepareStatement(SQL);
            cmd.setInt(1, u.getId());
            cmd.setInt(2, l.getId());

            ResultSet rs = cmd.executeQuery();

            java.util.Date dataAtual = new Date(System.currentTimeMillis());

            if(rs.next()) {
                if(rs.getDate("prazo").compareTo(dataAtual) > 0) {
                    SQL = "update tb_livro_usuario set expirado = ? where id_usuario = ? and id_livroAcervo = ?";

                    cmd = con.prepareStatement(SQL);
                    cmd.setBoolean(1, false);
                    cmd.setInt(2, u.getId());
                    cmd.setInt(3, l.getId());

                    int rowsAffected = cmd.executeUpdate();

                    if(rowsAffected == 0) {
                        System.err.println("Erro ao fazer update");
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Erro ao executar update: " + e.getMessage());
        } finally {
            Conexao.desconectar(con);
        }
    }
    
}
