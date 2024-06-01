package entities.course;

import java.util.List;

import entities.subject.Subject;
import entities.year.Year;
import utils.objects.Common;

public class Course extends Common {

    private List<Year> years;
    private List<Subject> subjects;

    public Course() {

    }

    public List<Year> getYears() {
        return years;
    }

    public void setYears(List<Year> years) {
        this.years = years;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

}
