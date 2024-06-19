package estudante;

import Year.AnoLetivoPersistente;
import curso.CursoPersistente;

public class Estudante {

    private long id;
    private long idAnoIngresso;
    private long idCurso;

    

    private StringBuilder nome;
    private StringBuilder email;
    private StringBuilder telefone;

    public Estudante() {

    }

    public Estudante(long id, String nome, String email, String telefone, long idAnoIngresso, long idCurso) {
        setId(id);
        setEmail(email);
        setNome(nome);
        setTelefone(telefone);
        setIdCurso(idCurso);
        setIdAnoIngresso(idAnoIngresso);
    }

    public long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(long idCurso) {
        this.idCurso = idCurso;
    }

    public long getIdAnoIngresso() {
        return idAnoIngresso;
    }

    public void setIdAnoIngresso(long idAnoIngresso) {
        this.idAnoIngresso = idAnoIngresso;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome.toString();
    }

    public void setNome(String nome) {
        this.nome = new StringBuilder(nome);
    }

    public String getEmail() {
        return email.toString();
    }

    public void setEmail(String email) {
        this.email = new StringBuilder(email);
    }

    public String getTelefone() {
        return telefone.toString();
    }

    public void setTelefone(String telefone) {
        this.telefone = new StringBuilder(telefone);
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + ", Nome: " + this.getNome() + ", Email: " + this.getEmail() + ", Telefone: "
                + this.getTelefone() + ", Ano de Ingresso: "
                + AnoLetivoPersistente.findOne(this.getIdAnoIngresso()).getNome()
                + ", Curso: " + CursoPersistente.findOne(this.getIdCurso()).getNome();
    }

}
