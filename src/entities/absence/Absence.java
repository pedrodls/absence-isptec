package src.entities.absence;

import java.util.Date;

import src.entities.teacherSubject.TeacherSubject;
import src.utils.Enums.Presence;

public class Absence {

    private Integer id;
    private Presence presence;
    private TeacherSubject teacherSubject;
    private Date date;

    public Absence() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Presence getPresence() {
        return presence;
    }

    public void setPresence(Presence presence) {
        this.presence = presence;
    }

    public TeacherSubject getTeacherSubject() {
        return teacherSubject;
    }

    public void setTeacherSubject(TeacherSubject teacherSubject) {
        this.teacherSubject = teacherSubject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
