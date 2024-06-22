package fault;

import classroom.ClassroomPersistenceEntity;
import classroomStudent.ClassroomStudentPersistenceEntity;
import courseSubject.CourseSubjectPersistenceEntity;
import genericEntity.GenericPersistenceEntity;
import isptec.utils.DataUtils;
import student.StudentPersistenceEntity;
import teacherSubject.TeacherSubjectPersistenceEntity;
import utils.Defs;

public class FaultEntity {

    private int id;
    private int teacherSubjectId;
    private int classroomStudentId;
    private long createdAt;

    public FaultEntity(int id, int teacherSubjectId, int classroomStudentId, long createdAt) {
        this.id = id;
        this.teacherSubjectId = teacherSubjectId;
        this.classroomStudentId = classroomStudentId;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacherSubjectId() {
        return teacherSubjectId;
    }

    public void setTeacherSubjectId(int teacherSubjectId) {
        this.teacherSubjectId = teacherSubjectId;
    }

    public int getClassroomStudentId() {
        return classroomStudentId;
    }

    public void setClassroomStudentId(int classroomStudentId) {
        this.classroomStudentId = classroomStudentId;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId()
                + ", Professor: "
                + GenericPersistenceEntity.findOne(TeacherSubjectPersistenceEntity.findOne(teacherSubjectId).getTeacherId(), Defs.PROFESSOR_FILE).getName()
                + ", Turma: "
                + ClassroomPersistenceEntity.findOne(TeacherSubjectPersistenceEntity.findOne(teacherSubjectId).getClassroomId()).getName()
                + ", Disciplina: "
                + GenericPersistenceEntity.findOne(CourseSubjectPersistenceEntity.findOne(TeacherSubjectPersistenceEntity.findOne(getTeacherSubjectId()).getCourseSubjectId()).getSubjectId(), Defs.DISCIPLINA_FILE).getName()
                + ", ID Estudante: "
                + StudentPersistenceEntity.findOne(ClassroomStudentPersistenceEntity.findOne(classroomStudentId).getStudentId()).getId()
                + ", Nome Estudante: "
                + StudentPersistenceEntity.findOne(ClassroomStudentPersistenceEntity.findOne(classroomStudentId).getStudentId()).getName()
                + ", Data de Criação: "
                + DataUtils.fromLongToDate(createdAt);
    }

}
