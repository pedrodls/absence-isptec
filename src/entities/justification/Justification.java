package entities.justification;

import java.util.Date;
import java.util.List;

import entities.lostedTest.LostedTest;
import entities.student.Student;
import utils.enums.LostedTypeEnum;

public class Justification {

    private Date createdAt;
    private Date absenceEnd;
    private Date absenceStart;
    private Student student;
    private String description;
    private List<LostedTest> lostedTests;
    private LostedTypeEnum lostedTestType;

    public Justification() {

    }

    public LostedTypeEnum getLostedTestType() {
        return lostedTestType;
    }

    public void setLostedTestType(LostedTypeEnum lostedTestType) {
        this.lostedTestType = lostedTestType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getAbsenceEnd() {
        return absenceEnd;
    }

    public void setAbsenceEnd(Date absenceEnd) {
        this.absenceEnd = absenceEnd;
    }

    public Date getAbsenceStart() {
        return absenceStart;
    }

    public void setAbsenceStart(Date absenceStart) {
        this.absenceStart = absenceStart;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<LostedTest> getLostedTests() {
        return lostedTests;
    }

    public void setLostedTests(List<LostedTest> lostedTests) {
        this.lostedTests = lostedTests;
    }
}
