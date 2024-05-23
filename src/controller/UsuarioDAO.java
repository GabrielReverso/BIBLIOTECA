/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Usuario;

/**
 *
 * @author gabriel
 */
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
            String SQL = "insert into tb_usuario (nome, email, senha) values (?,?,MD5(?))";

            cmd = con.prepareStatement(SQL);
            cmd.setString(1, u.getNome());
            cmd.setString(2, u.getEmail());
            cmd.setString(3, u.getSenha());

            int rowsAffected = cmd.executeUpdate();

            if (rowsAffected > 1) {
                return 1;
            } else {
                return 0;
            }
        } catch(Exception e) {
            System.err.println("Erro: " + e.getMessage());
            return -1;
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
                Usuario u = new Usuario(rs.getString("nome"), rs.getString("email"));
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
}
