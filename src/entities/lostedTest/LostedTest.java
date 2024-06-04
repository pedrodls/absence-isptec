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

    public LostedTest(LocalDate date, String teacher, String subject) {
        this.date = date;
        this.teacher = teacher;
        this.subject = subject;
    }

    public LostedTest(LostedTest lt) {

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
        auxString = sc.nextLine();
        lt.setSubject(auxString);

        do {

            if (counterValidationDate > 0) {
                System.out.println("\nData inválida ou fora do intervalo!\n");

            }

            System.out.println("Data da falta (dd-mm-aaaa): ");
            auxString = sc.nextLine();
            lt.setDate(DataUtils.stringToDate(auxString));

            counterValidationDate++;

        } while (lt.getDate() == null
                || !(lt.getDate().compareTo(starDate) >= 0 && lt.getDate().compareTo(enDate) <= 0));

        return lt;
    }

    public static LostedTest edit(LocalDate starDate, LocalDate enDate, LostedTest oldTest) {

        Scanner sc = new Scanner(System.in);

        String auxString;

        Integer counterValidationDate = 0;

        System.out.println("Antigo Nome do Professor: "+oldTest.getTeacher());
        System.out.print("Novo Nome do Professor: ");
        auxString = sc.nextLine();
        oldTest.setTeacher(auxString);

        System.out.println("Antigo Nome da disciplina: "+oldTest.getSubject());
        System.out.print("Novo Nome da disciplina: ");
        auxString = sc.nextLine();
        oldTest.setSubject(auxString);

        do {

            if (counterValidationDate > 0) {
                System.out.println("\nData inválida ou fora do intervalo!\n");

            }

            System.out.println("Antiga Data da falta (dd-mm-aaaa): "+oldTest.getDate());
            System.out.println("Nova Data da falta (dd-mm-aaaa): ");
            auxString = sc.nextLine();
            oldTest.setDate(DataUtils.stringToDate(auxString));

            counterValidationDate++;

        } while (
            oldTest.getDate() == null
            || !(oldTest.getDate().compareTo(starDate) >= 0 
            && oldTest.getDate().compareTo(enDate) <= 0)
        );

        return oldTest;
    }

    @Override
    public String toString() {

        return  "\n{ \n\tDisciplina: " + this.getSubject() + ",\n\tData: " + this.getDate()
                + ",\n\tProfessor: " + this.getTeacher()
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
