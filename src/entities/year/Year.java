package entities.year;

import java.util.List;

import entities.classroom.Classroom;
import entities.subject.Subject;
import utils.objects.TeacherSubject;

public class Year {

    private Integer id;
    private List<Subject> subjects;
    private List<Classroom> classrooms;
    private List<TeacherSubject> teachersSubjects;

    public Year() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public List<TeacherSubject> getTeachersSubjects() {
        return teachersSubjects;
    }

    public void setTeachersSubjects(List<TeacherSubject> teachersSubjects) {
        this.teachersSubjects = teachersSubjects;
    }

}
