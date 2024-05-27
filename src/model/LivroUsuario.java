package model;

public class LivroUsuario {
    
    private Usuario usuario;
	private Livro livro;
    private String dataEmprestimo;
	private String prazo;
	private boolean expirado;
    
    public LivroUsuario(Usuario usuario, Livro livro, String dataEmprestimo ,String prazo, boolean expirado) {
        this.usuario = usuario;
        this.livro = livro;
        this.prazo = prazo;
        this.expirado = expirado;
        this.dataEmprestimo = dataEmprestimo;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Livro getLivro() {
        return livro;
    }
    public void setLivro(Livro livro) {
        this.livro = livro;
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
    public String getDataEmprestimo() {
        return dataEmprestimo;
    }
    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }    
}
