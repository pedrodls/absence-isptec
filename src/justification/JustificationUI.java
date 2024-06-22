package justification;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import classroom.ClassroomEntity;
import classroom.ClassroomPersistenceEntity;
import classroomStudent.ClassroomStudentPersistenceEntity;
import clearBuffer.ClearBuffer;
import fault.FaultPersistenceEntity;
import genericEntity.GenericEntity;
import genericEntity.GenericPersistenceEntity;
import isptec.listas.Listas;
import isptec.utils.DataUtils;
import isptec.utils.Utils;
import student.StudentEntity;
import student.StudentPersistenceEntity;
import utils.*;
import utils.enums.DispatchTypeEnum;
import utils.enums.TestTypeEnum;

public class JustificationUI {

    public static final String dateRegex = "^([0-2][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";

    public JustificationUI() {

    }

    public static void menuStudent() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Justificação de Falta do Estudante****************\n");

            int opcao = Listas.enviarLerOpcaoEscolhida(Defs.JUSTIFICATION_LINKS);

            switch (opcao) {
                case 1:
                    forJustifyMenu(false);
                    break;

                case 2:
                    // showlistData();
                    break;

                case 3:
                    // edit();
                    break;

                case 5:
                    // drop();
                    break;

                case 4:
                    MainMenu.studentMenu();
                    break;
            }
        }
    }

    public static void menuCoordination() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Justificação de Falta da Coordenação****************\n");

            int opcao = Listas.enviarLerOpcaoEscolhida(Defs.JUSTIFICATION_LINKS);

            switch (opcao) {
                case 1:
                    forJustifyMenu(true);
                    break;

                case 2:

                    break;

                case 3:
                    // edit();
                    break;

                case 4:
                    MainMenu.coordenationMenu();
                    break;
            }
        }
    }

    public static void forJustifyMenu(boolean coordination) {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu de Pedidos por Justificar ****************\n");

            int opcao = coordination ? Listas.enviarLerOpcaoEscolhida(Defs.COORDINATION_JUSTIFICATION_LINKS)
                    : Listas.enviarLerOpcaoEscolhida(Defs.STUDENT_JUSTIFICATION_LINKS);

            switch (opcao) {
                case 1:
                    if (!coordination) {
                        create();
                    } else {
                        showlistDataForJustify();
                    }
                    break;

                case 2:
                    if (!coordination) {
                        showlistDataForJustifyStudent();
                    } else {
                        search();
                    }
                    break;

                case 3:
                    if (!coordination) {
                        editJusticationStudent();
                    } else {
                        // Justificar
                    }
                    break;

                case 4:
                    if (!coordination) {
                        search();
                    } else {// Não justificar
                    }
                    break;

                case 5:
                    if (!coordination) {
                        drop();
                    } else {
                        menuCoordination();
                    }
                    break;

                case 6:
                    if (!coordination)
                        menuCoordination();

                    break;
            }
        }
    }

    public static void forJustifiedMenu(boolean coordination) {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu de Pedidos Justificados ****************\n");

            int opcao = coordination ? Listas.enviarLerOpcaoEscolhida(Defs.COORDINATION_JUSTIFICATION_LINKS)
                    : Listas.enviarLerOpcaoEscolhida(Defs.STUDENT_JUSTIFICATION_LINKS);

            switch (opcao) {
                case 1:
                    if (!coordination) {
                        create();
                    } else {
                        // listar
                    }
                    break;

                case 2:
                    // showlistData();
                    break;

                case 3:
                    // edit();
                    break;

                case 4:
                    if (coordination)
                        menuCoordination();
                    else
                        menuStudent();
                    break;
            }
        }
    }

    public static void forNotJustifiedMenu(boolean coordination) {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu de Pedidos Não Justificados ****************\n");

            int opcao = coordination ? Listas.enviarLerOpcaoEscolhida(Defs.COORDINATION_JUSTIFICATION_LINKS)
                    : Listas.enviarLerOpcaoEscolhida(Defs.STUDENT_JUSTIFICATION_LINKS);

            switch (opcao) {
                case 1:
                    // create();
                    break;

                case 2:
                    // showlistData();
                    break;

                case 3:
                    // edit();
                    break;

                case 4:
                    if (coordination)
                        menuCoordination();
                    else
                        menuStudent();
                    break;
            }
        }
    }

    public static boolean validate() {
        if (!(GenericPersistenceEntity.findAll(Defs.CURSO_FILE).size() > 0
                && StudentPersistenceEntity.findAll().size() > 0
                && GenericPersistenceEntity.findAll(Defs.EMPLOYEE_FILE).size() > 0
                && FaultPersistenceEntity.findAll().size() > 0
                && GenericPersistenceEntity.findAll(Defs.MOTIVO_FALTA_FILE).size() > 0)) {
            System.out.print(
                    "Não existem dados suficientes[Curso, Estudante, Funcionário, Motivo de Falta, Falta]\n\n");

            MainMenu.pauseToSee();
            return false;
        }

        return true;
    }

    public static void create() {

        Scanner sc = new Scanner(System.in);
        String stringDate;
        boolean inValidate = false;

        int testType = 4;

        int dispatchType = 3;
        String topic = Defs.ASSUNTO;

        ClassroomEntity classroomEntity;
        GenericEntity courseEntity;
        StudentEntity studentEntity;
        GenericEntity employeeEntity;

        int faultDescriptionId;

        long createdAt = -1;

        long endAt;
        long startedAt;

        long dispatchDate = -1;

        System.out.println("\n*****************Solicitando Justificação de Falta****************\n");

        if (!validate())
            return;

        do {

            System.out.print("Regra_validação: Estudante existente! ");
            studentEntity = StudentPersistenceEntity.searchToEdit();

        } while (studentEntity == null);

        do {

            System.out.print("Regra_validação: Curso existente! ");
            courseEntity = GenericPersistenceEntity.searchToEdit(Defs.CURSO_FILE);

        } while (courseEntity == null);

        do {

            System.out.print("Regra_validação: Turma existente! ");
            classroomEntity = ClassroomPersistenceEntity.searchToEdit();

            if (classroomEntity != null) {

                if (classroomEntity.getCourseId() != courseEntity.getId()) {

                    System.out.println("\nEsta turma não pertence à este curso, deseja continuar?\n");

                    if (!Utils.concorda()) {
                        inValidate = true;
                        break;
                    }

                    classroomEntity = null;
                } else {
                    if (!(ClassroomStudentPersistenceEntity.verifyClassroomIdStudentId(studentEntity.getId(),
                            classroomEntity.getId()))) {

                        System.out.println("\nEsta turma não pertence à este estudante, deseja continuar?\n");

                        if (!Utils.concorda()) {
                            inValidate = true;
                            break;
                        }

                        classroomEntity = null;
                    }
                }

            }

        } while (classroomEntity == null);

        if (inValidate)
            return;

        do {

            System.out.print("Periodo inicial (DD/MM/AAAA): ");
            stringDate = sc.next();

        } while (!(Pattern.compile(dateRegex).matcher(stringDate).matches()));

        startedAt = DataUtils.converterStringToDate(stringDate).getTime();
        stringDate = "";

        do {

            System.out.print("Periodo final (DD/MM/AAAA)! ");
            stringDate = sc.next();

        } while (!(Pattern.compile(dateRegex).matcher(stringDate).matches()
                && DataUtils.converterStringToDate(stringDate).getTime() >= startedAt));

        endAt = DataUtils.converterStringToDate(stringDate).getTime();

        if (FaultPersistenceEntity.verifyFault(startedAt, endAt, studentEntity.getId())) {
            System.out.println("Este estudante não possui faltas neste periodo!");
            MainMenu.pauseToSee();
            return;
        }

        System.out.println("Motivo da falta: ");

        faultDescriptionId = Listas
                .enviarLerOpcaoEscolhida(GenericPersistenceEntity.findAllNames(Defs.MOTIVO_FALTA_FILE)) - 1;

        if (Utils.continua("Perdeu prova?")) {

            testType = Listas.enviarLerOpcaoEscolhida(TestTypeEnum.getDesignacoes());
            // Criar provas perdidas
        }

        do {

            System.out.print("Funcionário existente! ");
            employeeEntity = GenericPersistenceEntity.searchToEdit(Defs.EMPLOYEE_FILE);

        } while (employeeEntity == null);

        JustificationEntity justificationEntity = new JustificationEntity(
                -1,
                testType,
                dispatchType,
                topic,
                courseEntity.getId(),
                classroomEntity.getId(),
                studentEntity.getId(),
                employeeEntity.getId(),
                faultDescriptionId,
                endAt,
                createdAt,
                startedAt,
                dispatchDate);

        JustificationPersistenceEntity.create(justificationEntity);

        MainMenu.pauseToSee();
    }

    public static void editJusticationStudent() {

        Scanner sc = new Scanner(System.in);
        String stringDate;
        boolean inValidate = false;

        int testType = 4;

        int dispatchType = 3;
        String topic = Defs.ASSUNTO;

        ClassroomEntity classroomEntity;
        GenericEntity courseEntity;
        StudentEntity studentEntity;
        GenericEntity employeeEntity;

        int faultDescriptionId;

        long createdAt = -1;

        long endAt;
        long startedAt;

        long dispatchDate = -1;

        System.out.println("\n*****************Solicitando Justificação de Falta****************\n");

        if (!validate())
            return;

        do {

            System.out.print("Regra_validação: Estudante existente! ");
            studentEntity = StudentPersistenceEntity.searchToEdit();

        } while (studentEntity == null);

        do {

            System.out.print("Regra_validação: Curso existente! ");
            courseEntity = GenericPersistenceEntity.searchToEdit(Defs.CURSO_FILE);

        } while (courseEntity == null);

        do {

            System.out.print("Regra_validação: Turma existente! ");
            classroomEntity = ClassroomPersistenceEntity.searchToEdit();

            if (classroomEntity != null) {

                if (classroomEntity.getCourseId() != courseEntity.getId()) {

                    System.out.println("\nEsta turma não pertence à este curso, deseja continuar?\n");

                    if (!Utils.concorda()) {
                        inValidate = true;
                        break;
                    }

                    classroomEntity = null;
                } else {
                    if (!(ClassroomStudentPersistenceEntity.verifyClassroomIdStudentId(studentEntity.getId(),
                            classroomEntity.getId()))) {

                        System.out.println("\nEsta turma não pertence à este estudante, deseja continuar?\n");

                        if (!Utils.concorda()) {
                            inValidate = true;
                            break;
                        }

                        classroomEntity = null;
                    }
                }

            }

        } while (classroomEntity == null);

        if (inValidate)
            return;

        do {

            System.out.print("Periodo inicial (DD/MM/AAAA): ");
            stringDate = sc.next();

        } while (!(Pattern.compile(dateRegex).matcher(stringDate).matches()));

        startedAt = DataUtils.converterStringToDate(stringDate).getTime();
        stringDate = "";

        do {

            System.out.print("Periodo final (DD/MM/AAAA)! ");
            stringDate = sc.next();

        } while (!(Pattern.compile(dateRegex).matcher(stringDate).matches()
                && DataUtils.converterStringToDate(stringDate).getTime() >= startedAt));

        endAt = DataUtils.converterStringToDate(stringDate).getTime();

        if (FaultPersistenceEntity.verifyFault(startedAt, endAt, studentEntity.getId())) {
            System.out.println("Este estudante não possui faltas neste periodo!");
            MainMenu.pauseToSee();
            return;
        }

        System.out.println("Motivo da falta: ");

        faultDescriptionId = Listas
                .enviarLerOpcaoEscolhida(GenericPersistenceEntity.findAllNames(Defs.MOTIVO_FALTA_FILE)) - 1;

        if (Utils.continua("Perdeu prova?")) {

            testType = Listas.enviarLerOpcaoEscolhida(TestTypeEnum.getDesignacoes());
            // Criar provas perdidas
        }

        do {

            System.out.print("Funcionário existente! ");
            employeeEntity = GenericPersistenceEntity.searchToEdit(Defs.EMPLOYEE_FILE);

        } while (employeeEntity == null);

        JustificationEntity justificationEntity = new JustificationEntity(
                -1,
                testType,
                dispatchType,
                topic,
                courseEntity.getId(),
                classroomEntity.getId(),
                studentEntity.getId(),
                employeeEntity.getId(),
                faultDescriptionId,
                endAt,
                createdAt,
                startedAt,
                dispatchDate);

        JustificationPersistenceEntity.create(justificationEntity);

        MainMenu.pauseToSee();
    }

    public static void search() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n*****************Procurando Pedidos Solicitados****************\n");

        System.out.print("ID: ");
        int id = sc.nextInt();

        JustificationPersistenceEntity.read(id);

        MainMenu.pauseToSee();

    }

    public static JustificationEntity searchToEdit() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        int id = sc.nextInt();

        return JustificationPersistenceEntity.findOne(id);

    }

    public static void drop() {

        System.out.println("\n*****************Eliminando Solicitação de Justificação de Falta****************\n");

        JustificationEntity entity = searchToEdit();

        if (entity == null) {

            System.out.println("\nNão encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        JustificationPersistenceEntity.dropOne(entity.getId());

        System.out.println("\nEliminação finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void showlistDataForJustify() {

        List<JustificationEntity> data = JustificationPersistenceEntity.findAll();

        System.out.println("\n*****************Todos Pedidos Por Justificar*****************\n");

        for (JustificationEntity datum : data) {
            System.out.println("\n" + datum + "\n");

            if (datum.getDispatchType() == DispatchTypeEnum.AGUARDANDO.toInteger()) {
                System.out.println("\n" + datum + "\n");
            }

        }

        MainMenu.pauseToSee();

    }

    public static void showlistDataForJustifyStudent() {

        List<JustificationEntity> data = JustificationPersistenceEntity.findAll();

        if (data == null) {

            MainMenu.pauseToSee();

            return;
        }

        System.out.println("\n*****************Todos Pedidos Por Justificar*****************\n");

        System.out.println("Introduza o seu ID de Estudante!");
        StudentEntity studentEntity = StudentPersistenceEntity.searchToEdit();

        if (studentEntity == null) {
            System.out.println("Estudante não encontrado!");
            MainMenu.pauseToSee();
            return;
        }

        try {
            for (JustificationEntity datum : data) {

                if (datum.getDispatchType() == DispatchTypeEnum.AGUARDANDO.toInteger()
                        && datum.getStudentId() == studentEntity.getId()) {
                    System.out.println("\n" + datum + "\n");
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        MainMenu.pauseToSee();

    }
}
