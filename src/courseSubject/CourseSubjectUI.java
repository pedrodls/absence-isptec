package courseSubject;

import java.util.List;
import java.util.Scanner;

import clearBuffer.ClearBuffer;
import genericEntity.GenericEntity;
import genericEntity.GenericPersistenceEntity;
import isptec.listas.Listas;
import isptec.utils.Utils;
import utils.*;

public class CourseSubjectUI {

    public CourseSubjectUI() {

    }

    public static void menu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Curso <-> Disciplina****************\n");

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
                    MainMenu.adminMenu();
                    break;
            }
        }
    }

    public static boolean validate() {
        if (!(GenericPersistenceEntity.findAll(Defs.CURSO_FILE).size() > 0
                && GenericPersistenceEntity.findAll(Defs.DISCIPLINA_FILE).size() > 0)) {
            System.out.print("Não existem dados suficientes[Curso, Disciplina] para criar um Coordenador\n\n");

            MainMenu.pauseToSee();
            return false;
        }

        return true;
    }

    public static void create() {

        Scanner sc = new Scanner(System.in);

        GenericEntity course = null;
        GenericEntity subject = null;
        int level;

        System.out.println("\n*****************Criando Curso <-> Disciplina****************\n");

        if (!validate()) 
            return;

        do {

            System.out.print("Regra_validação: Curso existente! ");
            course = GenericPersistenceEntity.searchToEdit(Defs.CURSO_FILE);

        } while (course == null);

        do {

            System.out.print("Regra_validação: Disciplina existente! ");
            subject = GenericPersistenceEntity.searchToEdit(Defs.DISCIPLINA_FILE);

        } while (subject == null);

        do {
            System.out.print("\nInsira um nível superior académico válido[1 - 5]: ");
            level = sc.nextInt();
        } while (!(level >= 1 && level <= 5));

        CourseSubjectEntity entity = new CourseSubjectEntity(-1, course.getId(), level, subject.getId());

        CourseSubjectPersistenceEntity.create(entity);

        MainMenu.pauseToSee();

    }

    public static void edit() {

        Scanner sc = new Scanner(System.in);

        CourseSubjectEntity old = searchToEdit();

        Boolean edited = false;

        if (old == null) {

            System.out.println("\nNão encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        System.out.println("\n*****************Editando Curso <-> Disciplina****************\n");

        if (Utils.editarCampo("Curso", GenericPersistenceEntity.findOne(old.getCourseId(), Defs.CURSO_FILE).getName())) {

            GenericEntity course = null;

            do {

                System.out.print("Regra_validação: Curso existente! ");
                course = GenericPersistenceEntity.searchToEdit(Defs.CURSO_FILE);

            } while (course == null);

            old.setCourseId(course.getId());

            edited = true;
        }

        if (Utils.editarCampo("Disciplina", GenericPersistenceEntity.findOne(old.getSubjectId(), Defs.DISCIPLINA_FILE).getName())) {

            GenericEntity course = null;

            do {

                System.out.print("Regra_validação: Disciplina existente! ");
                course = GenericPersistenceEntity.searchToEdit(Defs.CURSO_FILE);

            } while (course == null);

            old.setCourseId(course.getId());

            edited = true;
        }

        if (Utils.editarCampo("Nível Académico", old.getLevel() + "º ano")) {

            int level;

            do {
                System.out.print("\nInsira um nível superior académico válido[1 - 5]: ");
                level = sc.nextInt();
            } while (!(level >= 1 && level <= 5));

            old.setLevel(level);

            edited = true;
        }

        if (edited)
            CourseSubjectPersistenceEntity.update(old);

        System.out.println("\nEdição finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void drop() {

        System.out.println("\n*****************Eliminando Curso <-> Disciplina****************\n");

        CourseSubjectEntity entity = searchToEdit();

        if (entity == null) {

            System.out.println("\nNão encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        CourseSubjectPersistenceEntity.dropOne(entity.getId());

        System.out.println("\nEliminação finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void search() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n*****************Procurando Curso <-> Disciplina****************\n");

        System.out.print("ID: ");
        int id = sc.nextInt();

        CourseSubjectPersistenceEntity.read(id);

        MainMenu.pauseToSee();

    }

    public static CourseSubjectEntity searchToEdit() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        int id = sc.nextInt();

        return CourseSubjectPersistenceEntity.findOne(id);

    }

    public static void showlistData() {

        List<CourseSubjectEntity> data = CourseSubjectPersistenceEntity.findAll();

        System.out.println("\n*****************Todos Curso <-> Disciplina*****************\n");

        for (CourseSubjectEntity datum : data)
            System.out.println("\n" + datum + "\n");

        MainMenu.pauseToSee();

    }

}
