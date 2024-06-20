package academicYear;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import clearBuffer.ClearBuffer;
import coordinator.CoordinatorPersistenceEntity;
import genericEntity.GenericEntity;
import genericEntity.GenericPersistenceEntity;
import isptec.listas.Listas;
import isptec.utils.Utils;
import student.StudentPersistenceEntity;
import utils.*;

public class AcademicYearUI {

    public AcademicYearUI() {

    }

    public static void menu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Ano Académico****************\n");

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

    public static void create() {

        Scanner sc = new Scanner(System.in);
        String name;
        String regex = "^\\d{4}/\\d{4}$";

        System.out.println("\n*****************Criando Ano Académico****************\n");

        do {

            System.out.print("Designção(xxxx/yyyy): ");
            name = sc.nextLine();

        } while (!(Pattern.compile(regex).matcher(name).matches() && Utils.validarAnoLetivo(name)));

        GenericEntity entity = new GenericEntity(-1, name);

        GenericPersistenceEntity.create(entity, Defs.ANO_ACADEMICO_FILE);

        MainMenu.pauseToSee();

    }

    public static void edit() {

        Scanner sc = new Scanner(System.in);

        GenericEntity entity = searchToEdit();

        Boolean edited = false;

        if (entity == null) {

            System.out.println("\nNão encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        System.out.println("\n*****************Editando Ano Académico****************\n");

        if (Utils.editarCampo("Nome", entity.getName())) {

            String name;

            do {

                System.out.print("\nRegra_validação: no mínimo 3 caracters! ");
                MainMenu.pauseToSee();

                System.out.print("\nNome: ");
                name = sc.nextLine();

            } while (name.length() < 3);

            entity.setName(name);

            edited = true;
        }

        if (edited)
            GenericPersistenceEntity.update(entity, Defs.ANO_ACADEMICO_FILE);

        System.out.println("\nEdição finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void drop() {

        System.out.println("\n*****************Eliminando Ano Académico****************\n");

        GenericEntity entity = searchToEdit();

        if (entity == null) {

            System.out.println("\nNão encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        if (CoordinatorPersistenceEntity.findAllByAcademicYearId(entity.getId()).size() > 0
                ||
                StudentPersistenceEntity.findAllByAccessedYearId(entity.getId()).size() > 0) {

            System.out.println("\nImpossível eliminar pois este ID está ligada à outro(s) dado(s)!\n");

            MainMenu.pauseToSee();

            return;
        }

        GenericPersistenceEntity.dropOne(entity.getId(), Defs.ANO_ACADEMICO_FILE);

        System.out.println("\nEliminação finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void search() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n*****************Procurando Ano Académico****************\n");

        System.out.print("ID: ");
        int id = sc.nextInt();

        GenericPersistenceEntity.read(id, Defs.ANO_ACADEMICO_FILE);

        MainMenu.pauseToSee();

    }

    public static GenericEntity searchToEdit() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        int id = sc.nextInt();

        return GenericPersistenceEntity.findOne(id, Defs.ANO_ACADEMICO_FILE);

    }

    public static void showlistData() {

        List<GenericEntity> data = GenericPersistenceEntity.findAll(Defs.ANO_ACADEMICO_FILE);

        System.out.println("\n*****************Todos Anos Académicos****************\n");

        for (GenericEntity datum : data)
            System.out.println("\n" + datum + "\n");

        MainMenu.pauseToSee();

    }

}
