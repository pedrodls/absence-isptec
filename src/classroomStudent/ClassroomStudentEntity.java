package classroomStudent;

import classroom.ClassroomPersistenceEntity;
import student.StudentPersistenceEntity;


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
                + ", Estudante -> Nome: "
                + StudentPersistenceEntity.findOne(getStudentId()).getName()
                + ", ID: " + StudentPersistenceEntity.findOne(getStudentId()).getId();
    }

}
