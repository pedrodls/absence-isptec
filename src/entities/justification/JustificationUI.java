package entities.justification;


import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.lostedTest.LostedTest;
import entities.student.Student;
import lib.isptec.utils.DataUtils;
import utils.enums.LostedTypeEnum;

public class JustificationUI {

    private LocalDate createdAt;
    private LocalDate absenceEnd;
    private LocalDate absenceStart;

    private Student student;
    private String description;
    private List<LostedTest> lostedTests;
    private LostedTypeEnum lostedTestType;

    public JustificationUI() {

    }

    public JustificationUI(JustificationUI jt) {

        this.student = jt.getStudent();
        this.createdAt = jt.getCreatedAt();
        this.absenceEnd = jt.getAbsenceEnd();
        this.description = jt.getDescription();
        this.lostedTests = jt.getLostedTests();
        this.absenceStart = jt.getAbsenceStart();
        this.lostedTestType = jt.getLostedTestType();
    }

    @Override
    public String toString(){

        return "Ausencia: " + " { \n\t\t\tPeriodo: de " + this.getAbsenceStart() + " à " + this.getAbsenceEnd() + "\n\t\t\tTotal de dias: " + DataUtils.getDifferenceDays(this.absenceStart, this.absenceEnd) + "\n\t\t\tMotivo: " + this.description + "\n}";
    }

    public static JustificationUI create(){

        
        Boolean init = true;
        String auxString;
        Boolean getOutAddingLostTest = false;
        Integer counterValidationDate = 0;

        LostedTest lostedTest;
        List<LostedTest> lostedTests = new ArrayList<LostedTest>();

        Scanner sc = new Scanner(System.in);

        JustificationUI jt = new JustificationUI();

        System.out.println("\n*****************Dados do estudante*****************\n");
        
        Student st = Student.create();

        jt.setStudent(st);

        System.out.println("\n*****************Dados da(s) Falta*****************\n");

        do{
            if (counterValidationDate > 0) {
                System.out.println("\nData inválida!\n");
            }

            System.out.println("Data de inicio da(s) falta(s) (dd-mm-aaaa): ");
            auxString = sc.nextLine();
            jt.setAbsenceStart(DataUtils.stringToDate(auxString));
    
            counterValidationDate++;

        }while(jt.getAbsenceStart() == null);
        
        counterValidationDate = 0;

        do{
            if (counterValidationDate > 0) {
                System.out.println("\nData inválida!\n");
            }

            System.out.println("Data de fim da(s) falta(s) (dd-mm-aaaa): ");
            auxString = sc.nextLine();
            jt.setAbsenceEnd(DataUtils.stringToDate(auxString));

            counterValidationDate++;
            
        }while(jt.getAbsenceEnd() == null || jt.getAbsenceEnd().compareTo(jt.getAbsenceStart()) < 0);
        

       do {
            System.out.println("Motivo das faltas: ");
            auxString = sc.nextLine();
            jt.setDescription(auxString);
       } while (!(jt.getDescription().length() > 0));

        System.out.println("\n**********Dados da(s) Prova(s) Perdida(s)**********\n");
       

        do
        {
            
            try
            {

                System.out.println("Adicionar Prova Perdida [1 - Sim | 2 - Nao]: ");
                Integer opcaoEscolhida = sc.nextInt();

                switch (opcaoEscolhida) {
                    case 1:
                        
                        while (init) {

                            try {

                                System.out.println("Tipo de Prova [1 - Exame | 2 - 1PP | 3 - 2PP]: ");
                                Integer op = sc.nextInt();

                                switch (op) {
                                    case 1:
                                        jt.setLostedTestType(LostedTypeEnum.EXAME);
                                        init = false;

                                        break;

                                    case 2:
                                        jt.setLostedTestType(LostedTypeEnum.PP_1);
                                        init = false;
                                        
                                        break;
                                    
                                    case 3:
                                        jt.setLostedTestType(LostedTypeEnum.PP_2);
                                        init = false;
                                        
                                        break;
                                    default: 
                                    System.out.println("opcao invalida.");

                                }
                            } catch (Exception e) {
                                System.out.println("opcao invalida.");
                            }
                                
                        }

                        lostedTest = LostedTest.create(jt.getAbsenceStart(), jt.getAbsenceEnd());
                        
                        lostedTests.add(lostedTest);

                        break;

                    case 2: 

                        jt.setLostedTests(lostedTests);

                        getOutAddingLostTest = true;

                    break;
                    default:
                    System.out.println("opcao invalida.");
                }
            }
            catch (Exception ex)
            {
                System.out.println("opcao invalida.");
            }


        }while(!getOutAddingLostTest);

        return jt;

    } 


    public LostedTypeEnum getLostedTestType() {
        return lostedTestType;
    }

    public void setLostedTestType(LostedTypeEnum lostedTestType) {
        this.lostedTestType = lostedTestType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getAbsenceEnd() {
        return absenceEnd;
    }

    public void setAbsenceEnd(LocalDate absenceEnd) {
        this.absenceEnd = absenceEnd;
    }

    public LocalDate getAbsenceStart() {
        return absenceStart;
    }

    public void setAbsenceStart(LocalDate absenceStart) {
        this.absenceStart = absenceStart;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<LostedTest> getLostedTests() {
        return lostedTests;
    }

    public void setLostedTests(List<LostedTest> lostedTests) {
        this.lostedTests = lostedTests;
    }
}
