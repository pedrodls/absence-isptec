package entities.lostedTest;

import java.time.LocalDate;
import java.util.Scanner;

import lib.isptec.utils.DataUtils;

public class LostedTest {

    private String teacher;
    private String subject;

    private LocalDate date;

    public LostedTest() {
        this.date = null;
        this.teacher = "";
        this.subject = "";
    }

    public LostedTest(LostedTest lt){
        
        this.date = lt.getDate();
        this.teacher = lt.getTeacher();
        this.subject = lt.getSubject();

    }

    public static LostedTest create(LocalDate starDate, LocalDate enDate) {

        LostedTest lt = new LostedTest();

        Scanner sc = new Scanner(System.in);

        String auxString;

        Integer counterValidationDate = 0;

        System.out.print("Nome do Professor: ");
        auxString = sc.nextLine();
        lt.setTeacher(auxString);

        System.out.print("Nome da disciplina: ");
        auxString= sc.nextLine();
        lt.setSubject(auxString);

        do{
            
            if (counterValidationDate > 0) {
                System.out.println("\nData inválida ou fora do intervalo!\n");
               
            }

            System.out.println("Data da falta (dd-mm-aaaa): ");
            auxString = sc.nextLine();
            lt.setDate(DataUtils.stringToDate(auxString));

            counterValidationDate++;         
    
        }while(lt.getDate() == null || !(lt.getDate().compareTo(starDate) >= 0 && lt.getDate().compareTo(enDate) <= 0));

        return lt;
    }

    @Override
    public String toString(){

        return "Prova: " + " { \nDisciplina: " + this.getSubject() + "\nData: " + this.getDate() + "\nProfessor: " + this.getTeacher()
        + "\n}";
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
