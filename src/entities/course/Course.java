package src.entities.course;

import java.util.List;

import src.entities.student.Student;
import src.entities.subject.Subject;
import src.entities.teacher.Teacher;
import src.entities.teacherSubject.TeacherSubject;
import src.utils.objects.Common;

public class Course extends Common{
    
    private Teacher coordinator;
    private List<Subject> subjects;
    private List<Student> students;
    private List<TeacherSubject> teacherSubjects;
    
    public Course(){

    }
    
    public Teacher getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Teacher coordinator) {
        this.coordinator = coordinator;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<TeacherSubject> getTeacherSubjects() {
        return teacherSubjects;
    }

    public void setTeacherSubjects(List<TeacherSubject> teacherSubjects) {
        this.teacherSubjects = teacherSubjects;
    }

   

}
