package entities.justification;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

import entities.lostedTest.LostedTest;
import entities.student.Student;
import lib.isptec.listas.Listas;
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

        this.student = null;
        this.createdAt = null;
        this.absenceEnd = null;
        this.description = "";
        this.lostedTests = null;
        this.absenceStart = null;
        this.lostedTestType = null;
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

    public JustificationUI(Student st, LocalDate createdAt, LocalDate absenceStart, LocalDate absenceEnd,
            String description, List<LostedTest> lostedTests, LostedTypeEnum lostedTypeEnum) {

        this.student = st;
        this.createdAt = createdAt;
        this.absenceEnd = absenceEnd;
        this.description = description;
        this.lostedTests = lostedTests;
        this.absenceStart = absenceStart;
        this.lostedTestType = lostedTypeEnum;
    }

    @Override
    public String toString() {

        String justification = "Dados de Ausência\n{\n\tPeriodo: " + this.getAbsenceStart() + " à "
                + this.getAbsenceEnd()
                + ",\n\tTotal de dias: " + DataUtils.getDifferenceDays(this.absenceStart, this.absenceEnd)
                + ",\n\tMotivo: " + this.description + "\n}\n?";

        String student = this.getStudent().toString();

        String lostedTestType = "\nTipo de Prova Perdida\n";
        String lostedTest = "\nDescrição das Provas Perdidas\n";

        if (this.getLostedTestType() != null)
            lostedTestType += this.getLostedTestType().toString() + "\n";

        if (this.getLostedTests().size() > 0) {

            Integer index = 0;

            for (LostedTest lt : this.getLostedTests()) {
                lostedTest += "\nId:" + index + " -> " + lt.toString() + "\n";
                lostedTest += "\n- - -- - - - - - - - - - - - -\n";
                lostedTest += "\n?\n";
            }
        }

        return justification + student + "\n?" + lostedTestType + "\n?\n" + lostedTest;
    }

    public static JustificationUI fromString(String jtString) {

        JustificationUI justificationUI = new JustificationUI();
        Student st = new Student();
        LostedTest lostedTest = new LostedTest();
        List<LostedTest> lostedTests = new ArrayList<LostedTest>();

        String studentStr = jtString.split(";")[0];

        st = new Student(Integer.parseInt(studentStr.split(",")[0]), studentStr.split(",")[1],
                studentStr.split(",")[2], studentStr.split(",")[3], studentStr.split(",")[4]);

        if ((jtString.split(";")).length == 4) {
            String lostedTestsStr = jtString.split(";")[3];

            String[] lostedTestArray = lostedTestsStr.split("_");

            for (Integer i = 0; i < lostedTestArray.length; i++) {

                System.out.println(lostedTestArray[i]);

                String getStr = (lostedTestArray[i]).replace('_', ' ').trim();

                lostedTest = new LostedTest(DataUtils.stringToDate(getStr.split(",")[0]),
                        getStr.split(",")[1],
                        getStr.split(",")[2]);


                lostedTests.add(lostedTest);

            }
        }

        String jt = jtString.split(";")[1];

        String designationEnumStr = "";

        if (jtString.split(";").length > 2) {
            designationEnumStr = jtString.split(";")[2];
        }

        justificationUI = new JustificationUI(st, DataUtils.stringToDate(jt.split(",")[0]),
                DataUtils.stringToDate(jt.split(",")[1]), DataUtils.stringToDate(jt.split(",")[2]), jt.split(",")[3],
                lostedTests, LostedTypeEnum.fromDesignacao(designationEnumStr));

        return justificationUI;
    }

    public static JustificationUI create() {

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

        do {
            if (counterValidationDate > 0) {
                System.out.println("\nData inválida!\n");
            }

            System.out.println("Data de inicio da(s) falta(s) (dd-mm-aaaa): ");
            auxString = sc.nextLine();
            jt.setAbsenceStart(DataUtils.stringToDate(auxString));

            counterValidationDate++;

        } while (jt.getAbsenceStart() == null);

        counterValidationDate = 0;

        do {
            if (counterValidationDate > 0) {
                System.out.println("\nData inválida!\n");
            }

            System.out.println("Data de fim da(s) falta(s) (dd-mm-aaaa): ");
            auxString = sc.nextLine();
            jt.setAbsenceEnd(DataUtils.stringToDate(auxString));

            counterValidationDate++;

        } while (jt.getAbsenceEnd() == null || jt.getAbsenceEnd().compareTo(jt.getAbsenceStart()) < 0);

        do {
            System.out.println("Motivo das faltas: ");
            auxString = sc.nextLine();
            jt.setDescription(auxString);
        } while (!(jt.getDescription().length() > 0));

        System.out.println("\n**********Dados da(s) Prova(s) Perdida(s)**********\n");

        do {

            try {

                System.out.println("Adicionar Prova Perdida [1 - Sim | 2 - Nao]: ");
                String opcaoEscolhida = sc.next();

                switch (opcaoEscolhida) {
                    case "1":

                        while (init) {

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

                        }

                        lostedTest = LostedTest.create(jt.getAbsenceStart(), jt.getAbsenceEnd());

                        lostedTests.add(lostedTest);

                        break;

                    case "2":

                        jt.setLostedTests(lostedTests);

                        getOutAddingLostTest = true;

                        break;
                    default:
                        System.out.println("opcao invalida.");
                }
            } catch (Exception ex) {
                System.out.println("opcao invalida.");
            }

        } while (!getOutAddingLostTest);

        return jt;

    }

    public static JustificationUI edit(JustificationUI jt) {

        Boolean init = true;
        String auxString;
        Boolean getOutAddingLostTest = false;
        Boolean getOutAddingEditing = false;
        Integer counterValidationDate = 0;

        LostedTest lostedTest;
        List<LostedTest> lostedTests = jt.getLostedTests();

        Scanner sc = new Scanner(System.in);

        do {

            System.out.println("\nEscolha a secção a editar\n");

            String opcoes[] = {
                    "Justificativo", "Estudante", "Provas Perdidas", "Sair"
            };

            int opcao = Listas.enviarLerOpcaoEscolhida(opcoes);

            switch (opcao) {

                case 1:
                    System.out.println("\n*****************Dados da(s) Falta*****************\n");

                    do {
                        if (counterValidationDate > 0) {
                            System.out.println("\nData inválida!\n");
                        }

                        System.out.println("Antiga Data de inicio da(s) falta(s): " + jt.getAbsenceStart());

                        System.out.print("Nova Data de inicio da(s) falta(s) (dd-mm-aaaa): ");

                        auxString = sc.nextLine();
                        jt.setAbsenceStart(DataUtils.stringToDate(auxString));

                        counterValidationDate++;

                    } while (jt.getAbsenceStart() == null);

                    counterValidationDate = 0;

                    do {
                        if (counterValidationDate > 0) {
                            System.out.println("\nData inválida!\n");
                        }

                        System.out.println("Antiga Data de fim da(s) falta(s): " + jt.getAbsenceEnd());

                        System.out.print("Data de fim da(s) falta(s) (dd-mm-aaaa): ");
                        auxString = sc.nextLine();
                        jt.setAbsenceEnd(DataUtils.stringToDate(auxString));

                        counterValidationDate++;

                    } while (jt.getAbsenceEnd() == null || jt.getAbsenceEnd().compareTo(jt.getAbsenceStart()) < 0);

                    do {
                        System.out.println("Antigo Motivo das faltas: " + jt.getDescription());
                        System.out.print("Novo Motivo das faltas: ");
                        auxString = sc.nextLine();
                        jt.setDescription(auxString);
                    } while (!(jt.getDescription().length() > 0));

                    getOutAddingEditing = true;

                    System.out.println("Secção actualizada com sucesso!\n");

                    break;

                case 2:
                    System.out.println("\n*****************Dados do estudante*****************\n");

                    Student st = Student.edit(jt.getStudent());

                    jt.setStudent(st);

                    System.out.println("Secção atualizada com sucesso!\n");

                    getOutAddingEditing = true;

                    break;
                case 3:
                    System.out.println("\n**********Dados da(s) Prova(s) Perdida(s)**********\n");

                    while (init) {

                        if (jt.getLostedTestType() != null)
                            System.out.println("Antigo Tipo de Prova: "
                                    + jt.getLostedTestType().toString());

                        System.out.println("Novo Tipo de Prova [1 - Exame | 2 - PP_1 | 3 - PP_2 | 4 - Nenhuma]: ");
                        Integer op = sc.nextInt();

                        switch (op) {
                            case 1:
                                jt.setLostedTestType(LostedTypeEnum.EXAME);
                                init = false;
                                System.out.println("Secção atualizada com sucesso!\n");

                                break;

                            case 2:
                                jt.setLostedTestType(LostedTypeEnum.PP_1);
                                init = false;
                                System.out.println("Secção atualizada com sucesso!\n");

                                break;

                            case 3:
                                jt.setLostedTestType(LostedTypeEnum.PP_2);
                                init = false;
                                System.out.println("Secção atualizada com sucesso!\n");

                                break;
                            case 4:
                                init = false;
                                getOutAddingEditing = true;
                                break;
                            default:
                                System.out.println("opção inválida!");

                        }

                    }

                    if (getOutAddingEditing) {
                        break;
                    }

                    do {

                        System.out.println("Adicionar Prova Perdida [1] :");
                        System.out.println("Editar    Prova Perdida [2] :");
                        System.out.println("Eliminar  Prova Perdida [3] :");
                        System.out.println("Terminar  Edição        [4] :");

                        String opcaoEscolhida = sc.next();

                        switch (opcaoEscolhida) {
                            case "1":

                                lostedTest = LostedTest.create(jt.getAbsenceStart(), jt.getAbsenceEnd());

                                lostedTests.add(lostedTest);

                                System.out.println("Prova perdida adicionada com sucesso!\n");

                                break;

                            case "2":

                                if (jt.getLostedTests().size() > 0) {

                                    Integer index = 0;

                                    System.out.println("\nProvas perdidas\n");

                                    for (LostedTest lt : jt.getLostedTests()) {
                                        System.out.println("\nId:" + index + " -> " + lt.toString() + "\n");
                                        System.out.println("\n- - -- - - - - - - - - - - - -\n");
                                        index++;
                                    }

                                    System.out.print("\nIndica o id da prova a editar: ");
                                    Integer id = sc.nextInt();

                                    if (id >= 0 && id < lostedTests.size()) {

                                        lostedTest = LostedTest.edit(jt.getAbsenceStart(), jt.getAbsenceEnd(),
                                                lostedTests.get(id));

                                        lostedTests.set(id, lostedTest);

                                        System.out.println("Prova perdida editada com sucesso!\n");

                                    } else {
                                        System.out.println("\nId errado");
                                    }
                                } else
                                    System.out.println("Sem Prova(s) perdida(s) para editar!\n");

                                break;

                            case "3":

                                if (jt.getLostedTests().size() > 0) {

                                    Integer index = 0;

                                    System.out.println("\nProvas perdidas\n");

                                    for (LostedTest lt : jt.getLostedTests()) {
                                        System.out.println("\nId:" + index + " -> " + lt.toString() + "\n");
                                        System.out.println("\n- - -- - - - - - - - - - - - -\n");
                                        index++;
                                    }

                                    System.out.print("\nIndica o Id da prova a eliminar: ");
                                    Integer id = sc.nextInt();

                                    if (id >= 0 && id < lostedTests.size()) {

                                        lostedTests.remove(id);

                                        System.out.println("Prova perdida eliminda com sucesso!\n");

                                    } else {
                                        System.out.println("\nId errado");
                                    }
                                } else
                                    System.out.println("Sem Prova perdida para eliminar!\n");

                                break;

                            case "4":

                                jt.setLostedTests(lostedTests);

                                getOutAddingLostTest = true;

                                break;
                            default:
                                System.out.println("opcao invalida.");
                        }

                    } while (!getOutAddingLostTest);

                    getOutAddingEditing = true;

                    break;

                case 4:
                    getOutAddingEditing = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente!");
            }

        } while (!getOutAddingEditing);

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
