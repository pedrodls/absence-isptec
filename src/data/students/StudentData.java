package data.students;

import java.util.List;

import entities.student.Student;

public class StudentData {

    private List<Student> students;

    public StudentData() {
        
        Student student = new Student();


    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
