package utils.objects;

import java.util.Date;

import utils.enums.Gender;

public class Person {

    private String BI;
    private String name;
    private Gender gender;
    private Date birthDate;

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