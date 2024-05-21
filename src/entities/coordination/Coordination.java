package src.entities.coordination;

import java.util.List;

import src.entities.course.Course;


public class Coordination {

    private Integer id;

    private List<Course> courses;

    public Coordination() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourse(List<Course> courses) {
        this.courses = courses;
    }

}
