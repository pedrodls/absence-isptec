package entities.justification;

import java.util.Date;
import java.util.List;

import entities.student.Student;
import utils.enums.Dispatch;
import utils.objects.LostedTest;

public class Justification {

    private Integer id;
    private Date createdAt;
    private Date verifiedAt;
    private Date absenceEnd;
    private Date absenceStart;
    private Student student;
    private Dispatch dispatch;
    private List<LostedTest> lostedTests;

    public Justification() {

    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getVerifiedAt() {
        return verifiedAt;
    }

    public void setVerifiedAt(Date verifiedAt) {
        this.verifiedAt = verifiedAt;
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

    public Dispatch getDispatch() {
        return dispatch;
    }

    public void setDispatch(Dispatch dispatch) {
        this.dispatch = dispatch;
    }

    public List<LostedTest> getLostedTests() {
        return lostedTests;
    }

    public void setLostedTests(List<LostedTest> lostedTests) {
        this.lostedTests = lostedTests;
    }

  
}
