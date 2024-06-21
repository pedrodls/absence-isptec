package classroom;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import clearBuffer.ClearBuffer;
import genericEntity.GenericEntity;
import genericEntity.GenericPersistenceEntity;
import isptec.listas.Listas;
import isptec.utils.Utils;
import utils.*;

public class ClassroomUI {

    public ClassroomUI() {

    }

    public static void menu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Turma****************\n");

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
        if (!(GenericPersistenceEntity.findAll(Defs.CURSO_FILE).size() > 0)) {

            System.out.print("Não existem dados suficientes[Curso] para criar um Estudante\n\n");

            MainMenu.pauseToSee();
            return false;
        }

        return true;
    }

    public static void create() {

        Scanner sc = new Scanner(System.in);

        String name;
        int level;
        GenericEntity course = null;

        System.out.println("\n*****************Criando Turma****************\n");

        if (!validate())
            return;

        do {
            System.out.print("\nInsira um nível superior académico válido[1 - 5]: ");
            level = sc.nextInt();
        } while (!(level >= 1 && level <= 5));

        do {

            System.out.print("\nRegra_validação: Curso existente! ");
            course = GenericPersistenceEntity.searchToEdit(Defs.CURSO_FILE);

        } while (course == null);

        do {

            System.out.println("Regra_validação: no mínimo 3 caracters! ");
            System.out.print("Nome: ");
            name = sc.nextLine();

        } while (name.length() < 3);

        ClassroomEntity entity = new ClassroomEntity(-1, name, course.getId(), level);

        ClassroomPersistenceEntity.create(entity);

        MainMenu.pauseToSee();

    }

    public static void edit() {

        Scanner sc = new Scanner(System.in);

        ClassroomEntity old = searchToEdit();

        Boolean edited = false;

        if (old == null) {

            System.out.println("\nNão encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        System.out.println("\n*****************Editando Turma****************\n");

        if (Utils.editarCampo("Curso",
                GenericPersistenceEntity.findOne(old.getCourseId(), Defs.CURSO_FILE).getName())) {

            GenericEntity course = null;

            do {

                System.out.print("\nRegra_validação: Curso existente! ");
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

        if (Utils.editarCampo("Nome", old.getName())) {

            String name;

            do {

                System.out.print("\nRegra_validação: no mínimo 3 caracters! ");
                System.out.print("Nome: ");
                name = sc.nextLine();

            } while (name.length() < 3);

            old.setName(name);

            edited = true;
        }

        if (edited)
            ClassroomPersistenceEntity.update(old);

        System.out.println("\nEdição finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void drop() {

        System.out.println("\n*****************Eliminando Turma****************\n");

        ClassroomEntity entity = searchToEdit();

        if (entity == null) {

            System.out.println("\nNão encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        ClassroomPersistenceEntity.dropOne(entity.getId());

        System.out.println("\nEliminação finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void search() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n*****************Procurando Turma****************\n");

        System.out.print("ID: ");
        int id = sc.nextInt();

        ClassroomPersistenceEntity.read(id);

        MainMenu.pauseToSee();

    }

    public static ClassroomEntity searchToEdit() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        int id = sc.nextInt();

        return ClassroomPersistenceEntity.findOne(id);

    }

    public static void showlistData() {

        List<ClassroomEntity> data = ClassroomPersistenceEntity.findAll();

        System.out.println("\n*****************Todas Turmas*****************\n");

        for (ClassroomEntity datum : data)
            System.out.println("\n" + datum + "\n");

        MainMenu.pauseToSee();

    }

}
