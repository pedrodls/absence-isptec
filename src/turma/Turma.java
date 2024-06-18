package turma;

import ano_academico.AnoAcademicoPersistente;
import curso.CursoPersistente;

public class Turma {

    private long id;
    private long cursoId;
    private long anoAcademicoId;
    private StringBuilder nome;
    
    public Turma() {

    }

    public Turma(long id, long cursoId, long anoAcademicoId, String nome) {
        setId(id);
        setCursoId(cursoId);
        setAnoAcademicoId(anoAcademicoId);
        setNome(nome);
    }

    public long getAnoAcademicoId() {
        return anoAcademicoId;
    }

    public void setAnoAcademicoId(long anoAcademicoId) {
        this.anoAcademicoId = anoAcademicoId;
    }

    public String getNome() {
        return nome.toString();
    }

    public void setNome(String nome) {
        this.nome = new StringBuilder(nome);
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
        return "ID: " + this.getId() + ", Nome: " + this.getNome() + ", Curso: " + CursoPersistente.findOne(this.getCursoId()).getNome() + ", Ano académico: " + AnoAcademicoPersistente.findOne(this.getAnoAcademicoId()).getNome();
    }

}
