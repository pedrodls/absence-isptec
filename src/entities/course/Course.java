package entities.course;

import java.util.List;

import entities.subject.Subject;
import utils.objects.Common;

public class Course extends Common {

    private List<Subject> subjects;
    
    public Course(String name, Integer id) {
        super(name, id);
    }
    
    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

   

}
