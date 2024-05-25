package model;

public class LivroUsuario {
    
    private int id_usuario;
	private int id_livro;
	private String prazo;
	private boolean expirado;
    
    public LivroUsuario(int id_usuario, int id_livro, String prazo, boolean expirado) {
        this.id_usuario = id_usuario;
        this.id_livro = id_livro;
        this.prazo = prazo;
        this.expirado = expirado;
    }
    
    public int getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    public int getId_livro() {
        return id_livro;
    }
    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }
    public String getPrazo() {
        return prazo;
    }
    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }
    public boolean isExpirado() {
        return expirado;
    }
    public void setExpirado(boolean expirado) {
        this.expirado = expirado;
    }
    
}
