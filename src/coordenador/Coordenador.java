package coordenador;

public class Coordenador {

    private long id;
    private long idProfessor;
    private long idCurso;
    

    public Coordenador() {

    }

    public Coordenador(long id, long idProfessor, long idCurso) {
        setId(id);
        setIdCurso(idCurso);
        setIdProfessor(idProfessor);
    }

    public long getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(long idProfessor) {
        this.idProfessor = idProfessor;
    }

    public long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(long idCurso) {
        this.idCurso = idCurso;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return "ID: " + this.getId() + ", Nome: ";
    }

}
