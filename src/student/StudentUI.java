package student;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import clearBuffer.ClearBuffer;
import genericEntity.GenericEntity;
import genericEntity.GenericPersistenceEntity;
import isptec.listas.Listas;
import isptec.utils.Utils;
import utils.*;

public class StudentUI {

    public static String telephoneRegex = "^9\\d{8}$";

    public StudentUI() {

    }

    public static void menu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Estudante****************\n");

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
                && GenericPersistenceEntity.findAll(Defs.ANO_ACADEMICO_FILE).size() > 0)) {
            System.out.print("Não existem dados suficientes[Curso, Ano Académico] para criar um Estudante\n\n");

            MainMenu.pauseToSee();
            return false;
        }

        return true;
    }

    public static void create() {

        Scanner sc = new Scanner(System.in);

        GenericEntity academicYear = null;
        GenericEntity course = null;
        String name, email = Defs.EMAIL_ADDRESS, telephone;

        System.out.println("\n*****************Criando Estudante****************\n");

        if (!validate())
            return;

        do {

            System.out.print("Regra_validação: Ano Academico de ingresso existente! ");
            academicYear = GenericPersistenceEntity.searchToEdit(Defs.ANO_ACADEMICO_FILE);

        } while (academicYear == null);

        do {

            System.out.print("\nRegra_validação: Curso existente! ");
            course = GenericPersistenceEntity.searchToEdit(Defs.CURSO_FILE);

        } while (course == null);

        do {

            System.out.println("\nRegra_validação: no mínimo 3 caracters! ");
            System.out.print("Nome: ");
            name = sc.nextLine();

        } while (name.length() < 3);

        do {

            System.out.print("\nTelefone(9________): ");
            telephone = sc.nextLine();

        } while (!Pattern.matches(telephoneRegex, telephone));

        StudentEntity entity = new StudentEntity(-1, name, email, telephone, course.getId(), academicYear.getId());

        StudentPersistenceEntity.create(entity);

        MainMenu.pauseToSee();

    }

    public static void edit() {

        Scanner sc = new Scanner(System.in);

        StudentEntity old = searchToEdit();

        Boolean edited = false;

        if (old == null) {

            System.out.println("\nNão encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        System.out.println("\n*****************Editando Estudante****************\n");

        if (Utils.editarCampo("ID Curso",
                GenericPersistenceEntity.findOne(old.getCourseId(), Defs.CURSO_FILE).getName())) {

            GenericEntity course = null;

            do {

                System.out.print("\nRegra_validação: Curso existente! ");
                course = GenericPersistenceEntity.searchToEdit(Defs.CURSO_FILE);

            } while (course == null);

            old.setCourseId(course.getId());

            edited = true;
        }

        if (Utils.editarCampo("ID Ano Académico",
                GenericPersistenceEntity.findOne(old.getAccessedYearId(), Defs.ANO_ACADEMICO_FILE).getName())) {

            GenericEntity academicYear = null;

            do {

                System.out.print("\nRegra_validação: Ano academico existente! ");
                academicYear = GenericPersistenceEntity.searchToEdit(Defs.ANO_ACADEMICO_FILE);

            } while (academicYear == null);

            old.setAccessedYearId(academicYear.getId());

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

        if (Utils.editarCampo("Telefone", old.getTelephone())) {

            String telephone;

            do {

                System.out.print("\nTelefone(9________): ");
                telephone = sc.nextLine();

            } while (!Pattern.matches(telephoneRegex, telephone));

            old.setTelephone(telephone);

            edited = true;
        }

        if (edited)
            StudentPersistenceEntity.update(old);

        System.out.println("\nEdição finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void drop() {

        System.out.println("\n*****************Eliminando Estudante****************\n");

        StudentEntity entity = searchToEdit();

        if (entity == null) {

            System.out.println("\nNão encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        StudentPersistenceEntity.dropOne(entity.getId());

        System.out.println("\nEliminação finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void search() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n*****************Procurando Estudante****************\n");

        System.out.print("ID: ");
        int id = sc.nextInt();

        StudentPersistenceEntity.read(id);

        MainMenu.pauseToSee();

    }

    public static void myData() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n*****************Seus Dados****************\n");

        System.out.println("Insira o seu ID para continuar!");

        System.out.print("ID: ");
        int id = sc.nextInt();

        StudentPersistenceEntity.read(id);

        MainMenu.pauseToSee();

    }

    public static StudentEntity searchToEdit() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        int id = sc.nextInt();

        return StudentPersistenceEntity.findOne(id);

    }

    public static void showlistData() {

        List<StudentEntity> data = StudentPersistenceEntity.findAll();

        if (data == null) {
            MainMenu.pauseToSee();
            return;
        }
      
        System.out.println("\n*****************Todos Estudantes*****************\n");

        for (StudentEntity datum : data)
            System.out.println("\n" + datum + "\n");

        MainMenu.pauseToSee();

    }

}
