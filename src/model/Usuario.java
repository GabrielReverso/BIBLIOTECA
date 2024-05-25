/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author gabriel
 */
public class Usuario {
    
    private int id;
    private String email;
    private String senha;
    private String nome;
    private boolean possuiLivro;
    
    public Usuario(String nome ,String email, String senha) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
    }
    public Usuario(String nome ,String email, boolean possuiLivro) {
        this.email = email;
        this.nome = nome;
        this.possuiLivro = possuiLivro;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public boolean isPossuiLivro() {
        return possuiLivro;
    }
    public void setPossuiLivro(boolean possuiLivro) {
        this.possuiLivro = possuiLivro;
    }

    @Override
    public String toString() {
        return "\nNome: " + this.nome + "\nEmail: " + this.email + "\nID: " + this.id + "\n";
    }
}
