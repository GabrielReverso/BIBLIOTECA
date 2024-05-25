package model;

public class Acervo {
    
    private int id;
    private String localidade;
    
    public Acervo(int id, String localidade) {
        this.id = id;
        this.localidade = localidade;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLocalidade() {
        return localidade;
    }
    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    @Override
    public String toString() {
        return "Acervo [id=" + id + ", localidade=" + localidade + "]";
    }
}
