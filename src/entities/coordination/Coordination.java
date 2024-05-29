package entities.coordination;

import java.util.List;

import entities.classroom.Classroom;
import entities.course.Course;
import entities.student.Student;
import entities.subject.Subject;
import entities.teacher.Teacher;

public class Coordination {

    private Teacher coordinator;
    private List<Course> courses;
    private List<Classroom> classrooms;

    public Coordination() {
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourse(List<Course> courses) {
        this.courses = courses;
    }

    public Teacher getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Teacher coordinator) {
        this.coordinator = coordinator;
    }

}
