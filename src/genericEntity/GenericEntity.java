package genericEntity;

import utils.Defs;

public class GenericEntity {

    private long id;
    private StringBuilder name = new StringBuilder(Defs.NAME_SIZE);

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
        return name.toString();
    }

    public void setName(String name) {
        this.name = new StringBuilder(name);
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + ", Nome: " + this.getName();
    }

}
