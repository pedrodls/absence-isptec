package data.courses;

import java.util.List;

import entities.course.Course;

public class CourseData {
    private List<Course> courses;

    public CourseData() {

    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

}
