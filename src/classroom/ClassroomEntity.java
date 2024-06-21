package classroom;

import genericEntity.GenericPersistenceEntity;
import utils.Defs;

public class ClassroomEntity {

    private int id;
    private int courseId;
    private int level;

    private String name;

    public ClassroomEntity() {

    }

    public ClassroomEntity(int id, String name, int courseId, int level) {
        setId(id);
        setCourseId(courseId);
        setLevel(level);
        setName(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + ", Nome: "
                + getName().toUpperCase()
                + ", Curso: " + GenericPersistenceEntity.findOne(getCourseId(), Defs.CURSO_FILE).getName()
                + ", Nivel académico: " + getLevel() + "º ano";
    }

}
