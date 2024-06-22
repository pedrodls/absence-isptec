package classroomStudent;

import classroom.ClassroomPersistenceEntity;
import genericEntity.GenericEntity;
import genericEntity.GenericPersistenceEntity;
import student.StudentPersistenceEntity;
import utils.Defs;


public class ClassroomStudentEntity {

    private int id;
    private int classroomId;
    private int studentId;

    public ClassroomStudentEntity() {

    }

    public ClassroomStudentEntity(int id, int classroomId, int studentId) {
        setId(id);
        setClassroomId(classroomId);
        setStudentId(studentId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + ", Turma: "
                + ClassroomPersistenceEntity.findOne(getClassroomId()).getName().toUpperCase()
                + ", Curso: "
                + GenericPersistenceEntity.findOne(ClassroomPersistenceEntity.findOne(getClassroomId()).getCourseId(), Defs.CURSO_FILE).getName()
                + ", Ano letivo: "
                + GenericPersistenceEntity.findOne(ClassroomPersistenceEntity.findOne(getClassroomId()).getAcademicYearId(), Defs.ANO_ACADEMICO_FILE).getName()
                + ", Ano: "
                + ClassroomPersistenceEntity.findOne(getClassroomId()).getLevel() + "º"
                + ", Estudante -> "
                + " ID: " + StudentPersistenceEntity.findOne(getStudentId()).getId()
                + ", Nome: "
                + StudentPersistenceEntity.findOne(getStudentId()).getName();
    }

}
