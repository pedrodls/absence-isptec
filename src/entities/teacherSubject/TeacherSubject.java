package entities.teacherSubject;

import java.util.List;

import entities.student.Student;
import entities.subject.Subject;
import entities.teacher.Teacher;

public class TeacherSubject {

    private Teacher teacher;

    private Subject subject;

    private List<Student> students;

    public TeacherSubject() {
    }

    public TeacherSubject(Teacher teacher, Subject subject) {
        this.teacher = teacher;
        this.subject = subject;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
