package genericEntity;

public class GenericEntity {

    private long id;
    private String name;

    public GenericEntity() {

    }

    public GenericEntity(long id, String nome) {
        setId(id);
        setName(nome);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + ", Nome: " + this.getName();
    }

}
