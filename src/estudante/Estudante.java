package estudante;

public class Estudante {

    private long id;
    private long idCurso;
    private StringBuilder name;

    public Estudante() {

    }

    public Estudante(long id, long idCurso) {
        setId(id);
        setIdCurso(idCurso);
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

    public StringBuilder getName() {
        return name;
    }

    public void setName(StringBuilder name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + ", Nome: ";
    }

}
