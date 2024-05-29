package entities.teacher;


import java.util.List;

import entities.coordination.Coordination;
import utils.objects.Person;

public class Teacher extends Person {

    private Integer id;
    private String telephone;
    private List<Coordination> coordinations;

    public Teacher() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Coordination> getCoordinations() {
        return coordinations;
    }

    public void setCoordinations(List<Coordination> coordinations) {
        this.coordinations = coordinations;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
