package course;

import java.util.List;
import java.util.Scanner;

import classroom.ClassroomPersistenceEntity;
import clearBuffer.ClearBuffer;
import coordinator.CoordinatorPersistenceEntity;
import genericEntity.GenericEntity;
import genericEntity.GenericPersistenceEntity;
import isptec.listas.Listas;
import isptec.utils.Utils;
import student.StudentPersistenceEntity;
import utils.*;

public class CourseUI {

    public CourseUI() {

    }

    public static void menu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Curso****************\n");

            int opcao = Listas.enviarLerOpcaoEscolhida(Defs.CRUD_LINKS);

            switch (opcao) {
                case 1:
                    create();
                    break;

                case 2:
                    list();
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
                    MainMenu.adminMenu();
                    break;
            }
        }
    }

    public static void create() {

        Scanner sc = new Scanner(System.in);
        String name;

        System.out.println("\n*****************Criando Curso****************\n");

        do {

            System.out.print("Regra_validação: no mínimo 3 caracters!");
            MainMenu.pauseToSee();

            System.out.print("Nome: ");
            name = sc.nextLine();

        } while (name.length() < 3);

        GenericEntity newCourse = new GenericEntity(-1, name);

        GenericPersistenceEntity.create(newCourse, Defs.CURSO_FILE);

        MainMenu.pauseToSee();

    }

    public static void edit() {

        Scanner sc = new Scanner(System.in);

        GenericEntity oldCourse = searchToEdit();

        Boolean edited = false;

        if (oldCourse == null) {

            System.out.println("\nCurso não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        System.out.println("\n*****************Editando Curso****************\n");

        if (Utils.editarCampo("Nome", oldCourse.getName())) {

            String name;

            do {

                System.out.print("\nRegra_validação: no mínimo 3 caracters! ");
                MainMenu.pauseToSee();

                System.out.print("\nNome: ");
                name = sc.nextLine();

            } while (name.length() < 3);

            oldCourse.setName(name);

            edited = true;
        }

        if (edited)
            GenericPersistenceEntity.update(oldCourse, Defs.CURSO_FILE);

        System.out.println("\nEdição finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void drop() {

        System.out.println("\n*****************Eliminando Curso****************\n");

        GenericEntity oldCourse = searchToEdit();

        if (oldCourse == null) {

            System.out.println("\nCurso não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        if (CoordinatorPersistenceEntity.findAllByCourseId(oldCourse.getId()).size() > 0
                || StudentPersistenceEntity.findAllByCourseId(oldCourse.getId()).size() > 0
                ||
                ClassroomPersistenceEntity.findAllByCourseId(oldCourse.getId()).size() > 0) {

            System.out.println("\nImpossível eliminar pois este ID está ligada à outro(s) dado(s)!\n");

            MainMenu.pauseToSee();

            return;
        }

        GenericPersistenceEntity.dropOne(oldCourse.getId(), Defs.CURSO_FILE);

        System.out.println("\nEliminação finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void search() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n*****************Procurando Curso****************\n");

        System.out.print("ID: ");
        int id = sc.nextInt();

        GenericPersistenceEntity.read(id, Defs.CURSO_FILE);

        MainMenu.pauseToSee();

    }

    public static GenericEntity searchToEdit() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        int id = sc.nextInt();

        return GenericPersistenceEntity.findOne(id, Defs.CURSO_FILE);

    }

    public static void list() {

        List<GenericEntity> data = GenericPersistenceEntity.findAll(Defs.CURSO_FILE);

        System.out.println("\n*****************Todos Cursos****************\n");

        for (GenericEntity datum : data)
            System.out.println("\n" + datum + "\n");

        MainMenu.pauseToSee();

    }

}
