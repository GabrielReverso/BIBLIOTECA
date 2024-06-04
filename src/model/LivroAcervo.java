package model;

public class LivroAcervo implements Comparable<LivroAcervo> {
    
    private int id;
    private Livro livro;
	private Acervo acervo;
	private int quantidade;
	private int disponibilidade;

    public LivroAcervo(int id, Livro livro, Acervo acervo, int quantidade, int disponibilidade) {
        this.acervo = acervo;
        this.quantidade = quantidade;
        this.disponibilidade = disponibilidade;
        this.livro = livro;
        this.id = id;
    }

    public LivroAcervo(int id, Livro livro, Acervo acervo) {
        this.id = id;
        this.acervo = acervo;
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
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(LivroAcervo livroAcervo) {
        return this.livro.compareTo(livroAcervo.getLivro());
    }
}
