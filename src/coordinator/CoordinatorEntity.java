package coordinator;

import genericEntity.GenericPersistenceEntity;
import utils.Defs;

public class CoordinatorEntity {

    private int id;
    private int courseId;
    private int teacherId;
    private int academicYearId;

    
    public CoordinatorEntity() {

    }

    public CoordinatorEntity(int id, int courseId, int teacherId, int academicYearId) {
        setId(id);
        setCourseId(courseId);
        setTeacherId(teacherId);
        setAcademicYearId(academicYearId);
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

    public int getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(int academicYearId) {
        this.academicYearId = academicYearId;
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
                + GenericPersistenceEntity.findOne(getTeacherId(), Defs.PROFESSOR_FILE).getName()
                + ", Curso: " + GenericPersistenceEntity.findOne(getCourseId(), Defs.CURSO_FILE).getName()
                + ", Ano Acadêmico: "
                + GenericPersistenceEntity.findOne(getAcademicYearId(), Defs.ANO_ACADEMICO_FILE).getName();
    }

}
