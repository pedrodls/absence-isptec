package coordenador;

import ano_letivo.AnoLetivoPersistente;
import curso.CursoPersistente;
import professor.ProfessorPersistente;

public class Coordenador {

    private long id;
    private long idProfessor;
    private long idAnoLetivo;
    private long idCurso;

    public Coordenador() {

    }

    public Coordenador(long id, long idProfessor, long idCurso, long idAnoLetivo) {
        setId(id);
        setIdCurso(idCurso);
        setIdProfessor(idProfessor);
        setIdAnoLetivo(idAnoLetivo);
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

    public long getIdAnoLetivo() {
        return idAnoLetivo;
    }

    public void setIdAnoLetivo(long idAnoLetivo) {
        this.idAnoLetivo = idAnoLetivo;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + ", Nome: " + ProfessorPersistente.findOne(this.getIdProfessor()).getNome() + ", Curso: " + CursoPersistente.findOne(this.getIdCurso()).getNome() + ", Ano letivo: " + AnoLetivoPersistente.findOne(this.getIdAnoLetivo()).getNome();
    }

}
