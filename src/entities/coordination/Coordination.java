package entities.coordination;

import entities.course.Course;
import entities.teacher.Teacher;
import utils.objects.Common;


public class Coordination extends Common {

    private Course course;
    private Teacher coordinator;

    public Coordination() {
        
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Teacher coordinator) {
        this.coordinator = coordinator;
    }
    
}
