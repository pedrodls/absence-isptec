package student;

import genericEntity.GenericPersistenceEntity;
import utils.Defs;

public class StudentEntity {

    private int id;
    private int courseId;
    private int accessedYearId;

    private String name;
    private String telephone;
    private String email;

    public StudentEntity() {

    }

    public StudentEntity(int id, String name, String email, String telephone, int courseId, int accessedYearId) {
        setId(id);
        setCourseId(courseId);
        setAccessedYearId(accessedYearId);
        setName(name);
        setTelephone(telephone);
        setEmail(email);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccessedYearId() {
        return accessedYearId;
    }

    public void setAccessedYearId(int accessedYearId) {
        this.accessedYearId = accessedYearId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                + getName()
                + ", Email: " + getEmail()
                + ", Telefone: " + getTelephone()
                + ", Curso: " + GenericPersistenceEntity.findOne(getCourseId(), Defs.CURSO_FILE).getName()
                + ", Ano de Ingresso: "
                + GenericPersistenceEntity.findOne(getAccessedYearId(), Defs.ANO_ACADEMICO_FILE).getName();
    }

}
