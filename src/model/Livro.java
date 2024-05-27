package model;

public class Livro implements Comparable<Livro>{
    
    private int id;
    private String titulo;
    private String autor;
    private String editora;
    private String descricao;
    private String pathImagem;
    
    public Livro(String titulo, String autor, String editora, String descricao) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.descricao = descricao;
    }

    public Livro(int id, String titulo, String autor, String editora, String descricao) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.descricao = descricao;
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getEditora() {
        return editora;
    }
    public void setEditora(String editora) {
        this.editora = editora;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getPathImagem() {
        return pathImagem;
    }
    public void setPathImagem(String pathImagem) {
        this.pathImagem = pathImagem;
    }

    @Override
    public int compareTo(Livro livro) {
        return this.titulo.compareTo(livro.getTitulo());
    }

    @Override
    public String toString() {
        return "Livro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", editora=" + editora + ", descricao="
                + descricao + "]";
    }

}
