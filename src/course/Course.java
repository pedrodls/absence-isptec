package course;

import utils.Defs;

public class Course {

    private long id;
    private StringBuilder name = new StringBuilder(Defs.NAME_SIZE);

    public Course() {

    }

    public Course(long id, String name) {
        setId(id);
        setName(name);
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
