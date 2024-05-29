package entities.teacher;

import entities.subject.Subject;
import utils.objects.Person;

public class Teacher extends Person {

    private Integer id;
    private String telephone;
    private Subject subject;

    public Teacher() {

    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
