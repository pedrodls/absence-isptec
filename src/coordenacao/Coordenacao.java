package coordenacao;

import curso.CursoPersistente;

public class Coordenacao {

    private long id;
    private long cursoId;
    
    public Coordenacao() {

    }

    public Coordenacao(long id, long cursoId) {
        setId(id);
        setCursoId(cursoId);
    }

    public long getCursoId() {
        return cursoId;
    }

    public void setCursoId(long cursoId) {
        this.cursoId = cursoId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + ", Coordenacao de : " + CursoPersistente.findOne(this.getCursoId()).getNome();
    }

}
