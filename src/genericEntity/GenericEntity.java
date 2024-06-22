package genericEntity;

public class GenericEntity {

    private Integer id;
    private String name;

    public GenericEntity() {

    }

    public GenericEntity(Integer id, String nome) {
        setId(id);
        setName(nome);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
