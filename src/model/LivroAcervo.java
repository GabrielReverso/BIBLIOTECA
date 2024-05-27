package model;

public class LivroAcervo {
    
    private Livro livro;
	private Acervo acervo;
	private int quantidade;
	private int disponibilidade;
    
    public LivroAcervo(Livro livro, Acervo acervo, int quantidade, int disponibilidade) {
        this.acervo = acervo;
        this.quantidade = quantidade;
        this.disponibilidade = disponibilidade;
        this.livro = livro;
    }
    
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public int getDisponibilidade() {
        return disponibilidade;
    }
    public void setDisponibilidade(int disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
    public Livro getLivro() {
        return livro;
    }
    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    public Acervo getAcervo() {
        return acervo;
    }
    public void setAcervo(Acervo acervo) {
        this.acervo = acervo;
    }
}
