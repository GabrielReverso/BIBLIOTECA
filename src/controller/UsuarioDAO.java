package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import controller.conexao.Conexao;
import model.Usuario;

public class UsuarioDAO {
    
    private Connection con;
    private PreparedStatement cmd;

    public UsuarioDAO() {
        this.con = Conexao.conectar();
    }

    public boolean login(Usuario u) {
        try {
            return true;
        } catch (Exception e){
            System.err.println("Erro: " + e.getMessage());
            return false;
        } finally {
            Conexao.desconectar(con);
        }
    }

    public int inserirNovoUsuario(Usuario u){
        try {
            String SQL = "select id from tb_usuario where nome = ?";
            cmd = con.prepareStatement(SQL);
            cmd.setString(1, u.getNome());

            ResultSet rs = cmd.executeQuery();

            if(rs.next()){
                System.out.println("Nome ja cadastrado");
                return -2;
            }

            SQL = "select id from tb_usuario where email = ?";
            cmd = con.prepareStatement(SQL);
            cmd.setString(1, u.getEmail());

            rs = cmd.executeQuery();

            if(rs.next()){
                System.out.println("Email ja cadastrado");
                return -3;
            }

            SQL = "insert into tb_usuario (nome, email, senha, possuiLivro) values (?,?,MD5(?), ?)";

            cmd = con.prepareStatement(SQL);
            cmd.setString(1, u.getNome());
            cmd.setString(2, u.getEmail());
            cmd.setString(3, u.getSenha());
            cmd.setBoolean(4, false);

            int rowsAffected = cmd.executeUpdate();

            if (rowsAffected > 1) {
                return 1;
            } else {
                return 0;
            }
        } catch(Exception e) {
            System.err.println("Erro: " + e.getMessage());
            return -1;
        } finally {
            Conexao.desconectar(con);
        }
    }

    public Usuario acessarUsuario(String nomeEmail, String senha){
        try {

            String SQL;
            if(nomeEmail.contains("@")){
                SQL = "select * from tb_usuario where email=? and senha=MD5(?)";
            } else {
                SQL = "select * from tb_usuario where nome=? and senha=MD5(?)";
            }

            cmd = con.prepareStatement(SQL);
            cmd.setString(1, nomeEmail);
            cmd.setString(2, senha);

            ResultSet rs = cmd.executeQuery();

            if(rs.next()) {
                Usuario u = new Usuario(rs.getString("nome"), rs.getString("email"), rs.getBoolean("possuiLivro"));
                u.setId(rs.getInt("id"));
                return u;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.err.println("ERRO: " + e.getMessage());
            return null;
        } finally{
            Conexao.desconectar(con);
        }
    }  

    public void alterarNomeUsuario(Usuario u, String novoNome){
        try {

            String SQL = "update tb_usuario set nome = ? where id = ?";

            cmd = con.prepareStatement(SQL);
            cmd.setString(1, novoNome);
            cmd.setInt(2, u.getId());

            int rowsAffected = cmd.executeUpdate();

            if (rowsAffected == 0){
                JOptionPane.showMessageDialog(null, "Ocorreu um erro no banco de dados");
            }

        } catch (Exception e) {
            System.err.println("ERRO: " + e.getMessage());
        } finally{
            Conexao.desconectar(con);
        }
    }  

    public void alterarEmailUsuario(Usuario u, String novoEmail){
        try {

            String SQL = "update tb_usuario set email = ? where id = ?";

            cmd = con.prepareStatement(SQL);
            cmd.setString(1, novoEmail);
            cmd.setInt(2, u.getId());

            int rowsAffected = cmd.executeUpdate();

            if (rowsAffected == 0){
                JOptionPane.showMessageDialog(null, "Ocorreu um erro no banco de dados");
            }

        } catch (Exception e) {
            System.err.println("ERRO: " + e.getMessage());
        } finally{
            Conexao.desconectar(con);
        }
    }  

    public void alterarSenhaUsuario(Usuario u, String novaSenha){
        try {

            String SQL = "update tb_usuario set senha = MD5(?) where id = ?";

            cmd = con.prepareStatement(SQL);
            cmd.setString(1, novaSenha);
            cmd.setInt(2, u.getId());

            int rowsAffected = cmd.executeUpdate();

            if (rowsAffected == 0){
                JOptionPane.showMessageDialog(null, "Ocorreu um erro no banco de dados");
            }

        } catch (Exception e) {
            System.err.println("ERRO: " + e.getMessage());
        } finally{
            Conexao.desconectar(con);
        }
    }  
}
