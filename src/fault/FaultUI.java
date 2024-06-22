package fault;

import java.util.List;
import java.util.Scanner;

import classroom.ClassroomEntity;
import classroom.ClassroomPersistenceEntity;
import classroomStudent.ClassroomStudentEntity;
import classroomStudent.ClassroomStudentPersistenceEntity;
import clearBuffer.ClearBuffer;
import courseSubject.CourseSubjectEntity;
import courseSubject.CourseSubjectPersistenceEntity;
import genericEntity.GenericEntity;
import genericEntity.GenericPersistenceEntity;
import isptec.listas.Listas;
import isptec.utils.DataUtils;
import isptec.utils.Utils;
import student.StudentEntity;
import student.StudentPersistenceEntity;
import teacherSubject.TeacherSubjectEntity;
import teacherSubject.TeacherSubjectPersistenceEntity;
import utils.*;

public class FaultUI {

    public FaultUI() {

    }

    public static void menu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Falta****************\n");

            int opcao = Listas.enviarLerOpcaoEscolhida(Defs.CRUD_LINKS);

            switch (opcao) {
                case 1:
                    create();
                    break;

                case 2:
                    showlistData();
                    break;

                case 3:
                    edit();
                    break;

                case 4:
                    search();
                    break;

                case 5:
                    drop();
                    break;

                case 6:
                    MainMenu.teacherMenu();
                    break;
            }
        }
    }

    public static boolean validate() {
        if (!(TeacherSubjectPersistenceEntity.findAll().size() > 0
                && ClassroomStudentPersistenceEntity.findAll().size() > 0)) {
            System.out.print("Não existem dados suficientes[Professor, Disciplina, Turma]\n\n");

            MainMenu.pauseToSee();
            return false;
        }

        return true;
    }

    public static void create() {

        boolean inValidate = false;

        TeacherSubjectEntity teacherSubjectEntity = null;
        ClassroomStudentEntity classroomStudentEntity = null;
        long createdAt;

        System.out.println("\n*****************Adicionando Falta****************\n");

        if (!validate())
            return;

        do {

            System.out.print("Regra_validação: Professor <-> Disciplina existente! ");
            teacherSubjectEntity = TeacherSubjectPersistenceEntity.searchToEdit();

        } while (teacherSubjectEntity == null);

        do {
            
            System.out.print("Regra_validação: Turma existente! ");
            ClassroomEntity classroomEntity = ClassroomPersistenceEntity.searchToEdit();

            System.out.print("Regra_validação: Estudante existente! ");
            StudentEntity studentEntity = StudentPersistenceEntity.searchToEdit();

            classroomStudentEntity = ClassroomStudentPersistenceEntity.findOneByClassroomIdStudentId(classroomEntity.getId(), studentEntity.getId());

            if (classroomStudentEntity != null) {
                if (classroomStudentEntity.getClassroomId() != teacherSubjectEntity.getClassroomId()) {

                    System.out.println("\nEsta turma não pertence à este Professor, deseja continuar?\n");

                    if (!Utils.concorda()) {
                        inValidate = true;
                        break;
                    }

                    classroomStudentEntity = null;
                }
            }
        } while (classroomStudentEntity == null);

        if (inValidate)
            return;

        createdAt = DataUtils.dateNow();

        FaultEntity entity = new FaultEntity(-1, teacherSubjectEntity.getId(), classroomStudentEntity.getId(),
                createdAt);

        FaultPersistenceEntity.create(entity);

        MainMenu.pauseToSee();

    }

    public static void edit() {

        FaultEntity old = searchToEdit();

        if (old == null) {

            System.out.println("\nNão encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        TeacherSubjectEntity teacherSubjectEntity = TeacherSubjectPersistenceEntity.findOne(old.getTeacherSubjectId());

        Boolean edited = false;
        boolean inValidate = false;

        System.out.println("\n*****************Editando Falta****************\n");

        if (Utils.editarCampo("ID Professor <-> Disciplina", teacherSubjectEntity.getId() + "")) {

            teacherSubjectEntity = null;

            do {

                System.out.print("Regra_validação: Professor <-> Disciplina existente! ");
                teacherSubjectEntity = TeacherSubjectPersistenceEntity.searchToEdit();

            } while (teacherSubjectEntity == null);

            old.setTeacherSubjectId(teacherSubjectEntity.getId());

            edited = true;
        }

        if (Utils.editarCampo("ID Turma <-> Estudante", old.getClassroomStudentId() + "")) {

            ClassroomStudentEntity classroomStudentEntity = null;

            do {

                System.out.print("Regra_validação: Turma <-> Estudante existente! ");
                classroomStudentEntity = ClassroomStudentPersistenceEntity.searchToEdit();

                if (classroomStudentEntity != null) {
                    if (classroomStudentEntity.getClassroomId() != teacherSubjectEntity.getClassroomId()) {

                        System.out.println("\nEsta turma não pertence à este Professor, deseja continuar?\n");

                        if (!Utils.concorda()) {
                            inValidate = true;
                            break;
                        }

                        classroomStudentEntity = null;
                    }
                }
            } while (classroomStudentEntity == null);

            if (inValidate) {
                return;
            }

            old.setClassroomStudentId(classroomStudentEntity.getId());

            edited = true;
        }

        if (edited)
            FaultPersistenceEntity.update(old);

        System.out.println("\nEdição finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void drop() {

        System.out.println("\n*****************Eliminando Falta****************\n");

        FaultEntity entity = searchToEdit();

        if (entity == null) {

            System.out.println("\nNão encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        FaultPersistenceEntity.dropOne(entity.getId());

        System.out.println("\nEliminação finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void search() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n*****************Procurando Falta****************\n");

        System.out.print("ID: ");
        int id = sc.nextInt();

        FaultPersistenceEntity.read(id);

        MainMenu.pauseToSee();

    }

    public static void searchStudentFaults() {

        System.out.println("\n*****************Minhas Faltas****************\n");

        System.out.println("Introduza o seu ID de Estudante!");
        StudentEntity studentEntity = StudentPersistenceEntity.searchToEdit();

        if (studentEntity == null) {
            System.out.println("Estudante não encontrado!");
            MainMenu.pauseToSee();
            return;
        }

        FaultPersistenceEntity.getFaultsFromStudent(studentEntity.getId());
        MainMenu.pauseToSee();

    }

    public static FaultEntity searchToEdit() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        int id = sc.nextInt();

        return FaultPersistenceEntity.findOne(id);

    }

    public static void showlistData() {

        List<FaultEntity> data = FaultPersistenceEntity.findAll();

        
        System.out.println("\n*****************Todas Falta*****************\n");

        if (data == null) {
        
            MainMenu.pauseToSee();

            return;
        }
        
        for (FaultEntity datum : data)
            System.out.println("\n" + datum + "\n");

        MainMenu.pauseToSee();

    }

}
