package model;

public class LivroAcervo {
    
    private int id_livro;
	private int id_acervo;
	private int quantidade;
	private int disponibilidade;
    private Livro livro;
    
    public LivroAcervo(int id_livro, int id_acervo, int quantidade, int disponibilidade) {
        this.id_livro = id_livro;
        this.id_acervo = id_acervo;
        this.quantidade = quantidade;
        this.disponibilidade = disponibilidade;
    }

    public LivroAcervo(int id_livro, int id_acervo, int quantidade, int disponibilidade, Livro livro) {
        this.id_livro = id_livro;
        this.id_acervo = id_acervo;
        this.quantidade = quantidade;
        this.disponibilidade = disponibilidade;
        this.livro = livro;
    }
    
    public int getId_livro() {
        return id_livro;
    }
    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }
    public int getId_acervo() {
        return id_acervo;
    }
    public void setId_acervo(int id_acervo) {
        this.id_acervo = id_acervo;
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
}
