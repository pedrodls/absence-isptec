package professor;

public class Professor {

    private Integer id;
    private String nome;

    public Professor() {

    }

    public Professor(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void save() {
        System.out.println("\nProfessor criado com sucesso!\n");
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + ", Nome: " + this.getNome();
    }

}
