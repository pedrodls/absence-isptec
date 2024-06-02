package utils.objects;

import utils.enums.GenderTypeEnum;

public class Person {

    private String BI;
    private String name;
    private GenderTypeEnum gender;

    public Person(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBI() {
        return BI;
    }

    public void setBI(String bI) {
        BI = bI;
    }   
    
    public GenderTypeEnum getGender() {
        return gender;
    }

    public void setGender(GenderTypeEnum gender) {
        this.gender = gender;
    }
}