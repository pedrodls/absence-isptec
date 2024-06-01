package entities.classroom;

import java.util.List;

import entities.student.Student;
import utils.objects.Common;

public class Classroom extends Common {

    private List<Student> students;

    public Classroom(){

    }
    
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

   
}
