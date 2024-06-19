package schoolYear;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import clearBuffer.ClearBuffer;
import genericEntity.GenericEntity;
import genericEntity.GenericPersistenceEntity;
import isptec.listas.Listas;
import isptec.utils.Utils;
import utils.*;

public class SchoolYearUI {

    public SchoolYearUI() {

    }

    public static void menu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Ano Letivo****************\n");

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
        String nome;
        String regex = "^\\d{4}/\\d{4}$";

        System.out.println("\n*****************Criando Ano Lectivo****************\n");

        do {

            System.out.print("Designção(xxxx/yyyy): ");
            nome = sc.nextLine();

        } while (!(Pattern.compile(regex).matcher(nome).matches() && Utils.validarAnoLetivo(nome)));

        GenericEntity newAcademicYear = new GenericEntity(-1, nome);

        GenericPersistenceEntity.create(newAcademicYear, Defs.ANO_LETIVO_FILE);

        MainMenu.pauseToSee();

    }

    public static void edit() {

        Scanner sc = new Scanner(System.in);

        GenericEntity old = searchToEdit();

        if (old == null) {

            System.out.println("\nAno Letivo não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        System.out.println("\n*****************Editando Ano Lectivo****************\n");

        if (Utils.editarCampo("Nome", old.getName())) {

            String name;

            do {

                System.out.print("\nRegra_validação: no mínimo 3 caracters! ");
                MainMenu.pauseToSee();

                System.out.print("\nNome: ");
                name = sc.nextLine();

            } while (name.length() < 3);

            old.setName(name);

        }

        GenericPersistenceEntity.update(old, Defs.ANO_LETIVO_FILE);

        System.out.println("\nEdição finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void drop() {

        System.out.println("\n*****************Eliminando Ano Letivo****************\n");

        GenericEntity old = searchToEdit();

        if (old == null) {

            System.out.println("\nAno Letivo não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        /* if (GenericPersistenceEntity.findAll(old.getId()).size() > 0
        ) {

            System.out.println("\nAno académico não pode ser apagado pois existem dados ligados ao mesmo!\n");

            MainMenu.pauseToSee();

            return;
        } */

        GenericPersistenceEntity.dropOne(old.getId(), Defs.ANO_LETIVO_FILE);

        System.out.println("\nEliminação finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void search() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n*****************Procurando Ano Letivo****************\n");

        System.out.print("ID: ");
        long id = sc.nextLong();

        GenericPersistenceEntity.read(id, Defs.ANO_LETIVO_FILE);

        MainMenu.pauseToSee();

    }

    public static GenericEntity searchToEdit() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        long id = sc.nextLong();

        return GenericPersistenceEntity.findOne(id, Defs.ANO_LETIVO_FILE);

    }

    public static void list() {

        List<GenericEntity> data = GenericPersistenceEntity.findAll(Defs.ANO_LETIVO_FILE);

        System.out.println("\n*****************Todos anos academicos*****************\n");

        for (GenericEntity datum : data)
            System.out.println("\n" + datum + "\n");

        MainMenu.pauseToSee();

    }

}
