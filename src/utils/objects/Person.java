package src.utils.objects;
import java.util.Date;

import src.utils.Enums.Gender;

public class Person {

    private String name;
    private Date birthDate;
    private Gender gender;
    private String BI;

    public Person(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    

    public String getBI() {
        return BI;
    }

    public void setBI(String bI) {
        BI = bI;
    }   
    
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}