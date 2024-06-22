package justification;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import classroom.ClassroomEntity;
import classroom.ClassroomPersistenceEntity;
import classroomStudent.ClassroomStudentPersistenceEntity;
import clearBuffer.ClearBuffer;
import courseSubject.CourseSubjectEntity;
import courseSubject.CourseSubjectPersistenceEntity;
import fault.FaultPersistenceEntity;
import genericEntity.GenericEntity;
import genericEntity.GenericPersistenceEntity;
import isptec.listas.Listas;
import isptec.utils.DataUtils;
import isptec.utils.Utils;
import student.StudentEntity;
import student.StudentPersistenceEntity;
import utils.*;
import utils.enums.TestTypeEnum;

public class JustificationUI {

    public static final String dateRegex = "^([0-2][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";

    public JustificationUI() {

    }

    public static void menu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Justificação de Falta****************\n");

            int opcao = Listas.enviarLerOpcaoEscolhida(Defs.CRUD_LINKS);

            switch (opcao) {
                case 1:
                    create();
                    break;

                case 2:
                    // showlistData();
                    break;

                case 3:
                    // edit();
                    break;

                case 4:
                    // search();
                    break;

                case 5:
                    // drop();
                    break;

                case 6:
                    MainMenu.coordenationMenu();
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

        int testType = 3;

        int dispatchType = 2;
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

        System.out.println("\n*****************Adicionando Justificativo****************\n");

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
                .enviarLerOpcaoEscolhida(GenericPersistenceEntity.findAllNames(Defs.MOTIVO_FALTA_FILE));

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

        System.out.println(justificationEntity.toString());
        MainMenu.pauseToSee();

        JustificationPersistenceEntity.create(justificationEntity);

    }

}
