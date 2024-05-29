package entities.classroom;

import java.util.List;

import entities.absence.Absence;
import entities.course.Course;
import entities.student.Student;
import entities.teacher.Teacher;
import utils.objects.Common;

public class Classroom extends Common {

    private Course course;

    private List<Absence> absences;

    private List<Teacher> teachers;

    private List<Student> students;


    public Classroom(String name, Integer id) {
        super(name, id);
    }

    public List<Absence> getAbsences() {
        return absences;
    }

    public void setAbsences(List<Absence> absences) {
        this.absences = absences;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    

   
}
