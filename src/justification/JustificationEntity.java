package justification;

import classroom.ClassroomPersistenceEntity;
import genericEntity.GenericPersistenceEntity;
import isptec.utils.DataUtils;
import student.StudentPersistenceEntity;
import utils.Defs;
import utils.enums.DispatchTypeEnum;
import utils.enums.TestTypeEnum;

public class JustificationEntity {

    private int id;
    private int testType;
    private int dispatchType;

    private String topic;

    private int courseId;
    private int studentId;
    private int employeeId;
    private int classroomId;

    private int faultDescriptionId;

    private long endAt;
    private long createdAt;
    private long startedAt;
    private long dispatchDate;

    public JustificationEntity(
            int id,
            int testType,
            int dispatchType,
            String topic,
            int courseId,
            int classroomId,
            int studentId,
            int employeeId,
            int faultDescriptionId,
            long endAt,
            long createdAt,
            long startedAt,
            long dispatchDate) {
        this.id = id;
        this.testType = testType;
        this.dispatchType = dispatchType;
        this.topic = topic;
        this.courseId = courseId;
        this.classroomId = classroomId;
        this.studentId = studentId;
        this.employeeId = employeeId;
        this.faultDescriptionId = faultDescriptionId;
        this.endAt = endAt;
        this.createdAt = createdAt;
        this.startedAt = startedAt;
        this.dispatchDate = dispatchDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTestType() {
        return testType;
    }

    public void setTestType(int testType) {
        this.testType = testType;
    }

    public int getDispatchType() {
        return dispatchType;
    }

    public void setDispatchType(int dispatchType) {
        this.dispatchType = dispatchType;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getFaultDescriptionId() {
        return faultDescriptionId;
    }

    public void setFaultDescriptionId(int faultDescriptionId) {
        this.faultDescriptionId = faultDescriptionId;
    }

    public long getEndAt() {
        return endAt;
    }

    public void setEndAt(long endAt) {
        this.endAt = endAt;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(long startedAt) {
        this.startedAt = startedAt;
    }

    public long getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(long dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    @Override
    public String toString() {

        return "ID: " +
                getId() +
                "Teste perdido: " +
                TestTypeEnum.fromInteger(getTestType())
                +
                "Estado do Dispacho: " +
                DispatchTypeEnum.fromInteger(getDispatchType()) +
                "Assunto: " +
                getTopic() +
                "Curso: " +
                GenericPersistenceEntity.findOne(getCourseId(), Defs.CURSO_FILE).getName() +
                "Turma: " +
                ClassroomPersistenceEntity.findOne(classroomId).getName() +
                "Estudante -> " +
                StudentPersistenceEntity.findOne(getStudentId())
                +
                "Funcionário: " +
                GenericPersistenceEntity.findOne(getCourseId(), Defs.EMPLOYEE_FILE).getName() +
                "Motivo da falta: " +
                GenericPersistenceEntity.findOne(getFaultDescriptionId(), Defs.MOTIVO_FALTA_FILE).getName() +
                "Periodo Inicial: " +
                DataUtils.fromLongToDate(getStartedAt()) +
                "Periodo Final: " +
                DataUtils.fromLongToDate(getEndAt()) +
                "Data de criação: " +
                DataUtils.fromLongToDate(getCreatedAt()) +
                "Estado: " +
                DispatchTypeEnum.fromInteger(getDispatchType());

    }
}
