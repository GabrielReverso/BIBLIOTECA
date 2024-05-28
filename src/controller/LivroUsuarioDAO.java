package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.sql.Date;

import javax.swing.JOptionPane;

import controller.conexao.Conexao;
import model.Acervo;
import model.Livro;
import model.LivroUsuario;
import model.Usuario;

public class LivroUsuarioDAO {

    private Connection con;
    private PreparedStatement cmd;

    public LivroUsuarioDAO() {
        this.con = Conexao.conectar();
    }

    public void emprestimoLivro(Usuario u, Livro l, Acervo a){
        
        int disponibilidade = 0;

        try {
            String SQL = "select disponibilidade from tb_livro_acervo tla " +
                         "where id_livro = ? and id_acervo = ?";

            cmd = con.prepareStatement(SQL);
            cmd.setInt(1, l.getId());
            cmd.setInt(2, a.getId());

            ResultSet rs = cmd.executeQuery();

            if(rs.next()){
                disponibilidade = rs.getInt("disponibilidade");
                if(disponibilidade <= 0){
                    JOptionPane.showMessageDialog(null, "Nenhum livro disponivel para esse acervo!");
                    return;
                }
            }

        } catch (Exception e) {
            System.err.println("Erro ao executar query: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro no banco de dados");
            Conexao.desconectar(con);
            return;
        }
        
        if(u.isPossuiLivro()){
            int quantidadeLivroPosse = 0;

            try {
                String SQL = "select * from tb_livro_usuario where id_usuario = ?";

                cmd = con.prepareStatement(SQL);
                cmd.setInt(1, u.getId());

                ResultSet rs = cmd.executeQuery();

                while (rs.next()) {
                    quantidadeLivroPosse ++;
                }

                if (quantidadeLivroPosse >= 3){
                    JOptionPane.showMessageDialog(null, "Número máximo de livros emprestados!");
                    return;
                }
            } catch (Exception e) {
                System.err.println("Erro ao executar query: " + e.getMessage());
                JOptionPane.showMessageDialog(null, "Erro no banco de dados");
                Conexao.desconectar(con);
                return;
            }
        }

        try {
            String SQL = "insert into tb_livro_usuario (id_usuario, id_livro, dataEmprestimo, prazo, expirado) values (?,?,?,?,?)";

            cmd = con.prepareStatement(SQL);
            cmd.setInt(1, u.getId());
            cmd.setInt(2, l.getId());
            cmd.setDate(3, new Date(System.currentTimeMillis()));
            cmd.setDate(4, new Date(System.currentTimeMillis() + ((long)7 * 24 * 60 * 60 * 1000)));
            cmd.setBoolean(5, false);

            int rowsAffected = cmd.executeUpdate();

            if(rowsAffected == 0){
                JOptionPane.showMessageDialog(null, "Erro no banco de dados");
            }

            u.setPossuiLivro(true);

            SQL = "update tb_livro_acervo set disponibilidade = ? where id_livro = ? and id_acervo = ?";

            cmd = con.prepareStatement(SQL);
            cmd.setInt(1, disponibilidade - 1);
            cmd.setInt(2, l.getId());
            cmd.setInt(3, a.getId());

            rowsAffected = cmd.executeUpdate();

            if(rowsAffected == 0){
                JOptionPane.showMessageDialog(null, "Erro no banco de dados");
            }

        } catch (Exception e) {
            System.err.println("Erro ao executar query: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro no banco de dados");
            return;
        } finally {
            Conexao.desconectar(con);
        }
        
    }

    public List<LivroUsuario> obterLivrosUsuario(Usuario u){

        List<LivroUsuario> lista = new LinkedList<>();

        try {
            String SQL = "select * "+
                         "from tb_livro_usuario tlu " +
                         "inner join tb_livro tl on tl.id = tlu.id_livro " +
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
                
                lista.add(new LivroUsuario(u, 
                                           livro, 
                                           dateFormat.format(rs.getDate("dataEmprestimo")), 
                                           dateFormat.format(rs.getDate("prazo")), 
                                           rs.getBoolean("expirado")));
            }

            return lista;

        } catch (Exception e) {
            System.err.println("Erro ao executar query: " + e.getMessage());
            return lista;
        } finally {
            Conexao.desconectar(con);
        }
    }
    
}
