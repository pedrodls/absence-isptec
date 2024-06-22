package courseSubject;

import genericEntity.GenericPersistenceEntity;
import utils.Defs;

public class CourseSubjectEntity {

    private int id;
    private int level;
    

    private int courseId;
    private int subjectId;

    public CourseSubjectEntity() {

    }

    public CourseSubjectEntity(int id, int courseId, int level, int subjectId) {
        setId(id);
        setCourseId(courseId);
        setLevel(level);
        setSubjectId(subjectId);
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

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + ", Curso: "
                + GenericPersistenceEntity.findOne(getCourseId(), Defs.CURSO_FILE).getName()
                + ", Nível Académico: "
                + getLevel() + "º ano"
                + ", Disciplina: "
                + GenericPersistenceEntity.findOne(getSubjectId(), Defs.DISCIPLINA_FILE).getName();
    }

}
