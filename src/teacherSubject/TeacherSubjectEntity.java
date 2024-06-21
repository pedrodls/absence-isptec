package teacherSubject;

import classroom.ClassroomPersistenceEntity;
import courseSubject.CourseSubjectPersistenceEntity;
import genericEntity.GenericPersistenceEntity;
import utils.Defs;

public class TeacherSubjectEntity {

    private int id;
    private int teacherId;
    private int classroomId;
    private int courseSubjectId;

    public TeacherSubjectEntity(int id, int teacherId, int classroomId, int courseSubjectId) {
        this.id = id;
        this.teacherId = teacherId;
        this.classroomId = classroomId;
        this.courseSubjectId = courseSubjectId;
    }

    public TeacherSubjectEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getCourseSubjectId() {
        return courseSubjectId;
    }

    public void setCourseSubjectId(int courseSubjectId) {
        this.courseSubjectId = courseSubjectId;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() 
                + ", Professor: "
                + GenericPersistenceEntity.findOne(getTeacherId(), Defs.PROFESSOR_FILE).getName()
                + ", Curso: "
                + GenericPersistenceEntity.findOne(CourseSubjectPersistenceEntity.findOne(getCourseSubjectId()).getCourseId(), Defs.CURSO_FILE).getName()
                + ", Turma: "
                + ClassroomPersistenceEntity.findOne(getClassroomId()).getName()
                + ", Ano: "
                + ClassroomPersistenceEntity.findOne(getClassroomId()).getLevel() + "º"
                + ", Disciplina: "
                + GenericPersistenceEntity.findOne(CourseSubjectPersistenceEntity.findOne(getCourseSubjectId()).getSubjectId(), Defs.DISCIPLINA_FILE).getName()
                ;
    }

}
