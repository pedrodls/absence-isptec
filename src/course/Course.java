package course;

public class Course {

    private int id;
    private String name;

    public Course() {

    }

    public Course(int id, String name) {
        setId(id);
        setName(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
