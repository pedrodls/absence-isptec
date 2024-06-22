package classroomStudent;

import java.util.List;
import java.util.Scanner;

import classroom.ClassroomEntity;
import classroom.ClassroomPersistenceEntity;
import clearBuffer.ClearBuffer;
import genericEntity.GenericEntity;
import isptec.listas.Listas;
import isptec.utils.Utils;
import student.StudentEntity;
import student.StudentPersistenceEntity;
import utils.*;

public class ClassroomStudentUI {

    public ClassroomStudentUI() {

    }

    public static void menu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Turma <-> Estudante****************\n");

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
                    MainMenu.coordenationMenu();
                    break;
            }
        }
    }

    public static boolean validate() {
        if (!(ClassroomPersistenceEntity.findAll().size() > 0
                && StudentPersistenceEntity.findAll().size() > 0)) {
            System.out.print(
                    "Não existem dados suficientes[Turma, Estudante]!\n\n");

            MainMenu.pauseToSee();
            return false;
        }

        return true;
    }

    public static void create() {

        ClassroomEntity classroom = null;
        StudentEntity student = null;

        System.out.println("\n*****************Criando Turma <-> Estudante****************\n");

        if (!validate())
            return;

        do {

            System.out.print("Regra_validação: Turma existente! ");
            classroom = ClassroomPersistenceEntity.searchToEdit();

        } while (classroom == null);

        do {

            System.out.print("Regra_validação: Estudante existente! ");
            student = StudentPersistenceEntity.searchToEdit();

        } while (student == null);

        ClassroomStudentEntity entity = new ClassroomStudentEntity(-1, classroom.getId(), student.getId());

        ClassroomStudentPersistenceEntity.create(entity);

        MainMenu.pauseToSee();

    }

    public static void edit() {

        ClassroomStudentEntity old = searchToEdit();

        Boolean edited = false;

        if (old == null) {

            System.out.println("\nNão encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        System.out.println("\n*****************Editando Turma <-> Estudante****************\n");

        if (Utils.editarCampo("Turma", ClassroomPersistenceEntity.findOne(old.getClassroomId()).getName())) {

            ClassroomEntity classroom = null;

            do {

                System.out.print("Regra_validação: Turma existente! ");
                classroom = ClassroomPersistenceEntity.searchToEdit();

            } while (classroom == null);

            old.setClassroomId(classroom.getId());

            edited = true;
        }

        if (Utils.editarCampo("Estudante",
                StudentPersistenceEntity.findOne(old.getStudentId()).getName())) {

            StudentEntity student = null;

            do {

                System.out.print("Regra_validação: Estudante existente! ");
                student = StudentPersistenceEntity.searchToEdit();

            } while (student == null);

            old.setStudentId(student.getId());

            edited = true;
        }

        if (edited)
            ClassroomStudentPersistenceEntity.update(old);

        System.out.println("\nEdição finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void drop() {

        System.out.println("\n*****************Eliminando Turma <-> Estudante****************\n");

        ClassroomStudentEntity entity = searchToEdit();

        if (entity == null) {

            System.out.println("\nNão encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        ClassroomStudentPersistenceEntity.dropOne(entity.getId());

        System.out.println("\nEliminação finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void search() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n*****************Procurando Turma <-> Estudante****************\n");

        System.out.print("ID: ");
        int id = sc.nextInt();

        ClassroomStudentPersistenceEntity.read(id);

        MainMenu.pauseToSee();

    }

    public static ClassroomStudentEntity searchToEdit() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        int id = sc.nextInt();

        return ClassroomStudentPersistenceEntity.findOne(id);

    }

    public static void showlistData() {

        List<ClassroomStudentEntity> data = ClassroomStudentPersistenceEntity.findAll();

        if (data == null) {
            MainMenu.pauseToSee();
            return;
        }
        
        System.out.println("\n*****************Todos Dados*****************\n");

        for (ClassroomStudentEntity datum : data) {
            System.out.println("\n" + datum + "\n");
            System.out.println("---------------------------------------------");
        }

        MainMenu.pauseToSee();

    }

    public static void showlistDataFromStudentId() {

        List<ClassroomStudentEntity> data = ClassroomStudentPersistenceEntity.findAll();

        if (data == null) {
            MainMenu.pauseToSee();
            return;
        }
        
        System.out.println("\n*****************Suas Turmas****************\n");

        System.out.println("\nInsira o seu ID para continuar!\n");

        StudentEntity studentEntity = StudentPersistenceEntity.searchToEdit();

        if (studentEntity == null) {
            System.out.println("Estudante não encontrado!");
            MainMenu.pauseToSee();
            return;
        }

        for (ClassroomStudentEntity datum : data) {
            if (datum.getStudentId() == studentEntity.getId()) {
                System.out.println("\n" + datum + "\n");
                System.out.println("---------------------------------------------");
            }
        }

        MainMenu.pauseToSee();

    }

}
