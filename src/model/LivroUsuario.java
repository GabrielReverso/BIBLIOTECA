package model;

public class LivroUsuario implements Comparable<LivroUsuario>{
    
    private Usuario usuario;
	private LivroAcervo livroAcervo;
    private String dataEmprestimo;
	private String prazo;
	private boolean expirado;
    
    public LivroUsuario(Usuario usuario, LivroAcervo livroAcervo, String dataEmprestimo ,String prazo, boolean expirado) {
        this.usuario = usuario;
        this.livroAcervo = livroAcervo;
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
    public LivroAcervo getLivroAcervo() {
        return livroAcervo;
    }
    public void setLivroAcervo(LivroAcervo livroAcervo) {
        this.livroAcervo = livroAcervo;
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
    
    @Override
    public int compareTo(LivroUsuario livroUsuario) {
        return this.livroAcervo.getLivro().compareTo(livroUsuario.getLivroAcervo().getLivro());
    }
}
