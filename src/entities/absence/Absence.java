package entities.absence;

import java.util.Date;

import utils.enums.Presence;

public class Absence {

    private Integer id;
    private Presence presence;
    private Date date;

    public Absence() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Presence getPresence() {
        return presence;
    }

    public void setPresence(Presence presence) {
        this.presence = presence;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
