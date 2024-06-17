package coordenacao;

import utils.Defs;

public class Coordenacao {

    private long id;
    private StringBuilder sb_nome = new StringBuilder(Defs.NAME_SIZE);

    public Coordenacao() {

    }

    public Coordenacao(long id, String nome) {
        setId(id);
        setNome(nome);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return sb_nome.toString();
    }

    public void setNome(String nome) {
        this.sb_nome = new StringBuilder(nome);
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + ", Nome: " + this.getNome();
    }

}
