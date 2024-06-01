package utils.objects;

import entities.subject.Subject;
import entities.teacher.Teacher;

public class TeacherSubject {

    private Teacher teacher;
    private Subject subject;
    
    public TeacherSubject() {

    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    
}
