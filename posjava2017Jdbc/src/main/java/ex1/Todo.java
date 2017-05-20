package ex1;

public class Todo {

    private int    id;
    private String descricao;
    private String resumo;

    public Todo() {

        super();
    }

    public Todo( int id, String descricao, String resumo ) {

        super();
        this.id = id;
        this.descricao = descricao;
        this.resumo = resumo;
    }

    public int getId() {

        return id;
    }

    public void setId( int id ) {

        this.id = id;
    }

    public String getDescricao() {

        return descricao;
    }

    public void setDescricao( String descricao ) {

        this.descricao = descricao;
    }

    public String getResumo() {

        return resumo;
    }

    public void setResumo( String resumo ) {

        this.resumo = resumo;
    }

    @Override
    public String toString() {

        return "Todo: {id=" + id + ", descricao=" + descricao + ", resumo=" + resumo + "}";
    }

    
}
