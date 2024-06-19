package faultDescription;

import java.util.List;
import java.util.Scanner;

import clearBuffer.ClearBuffer;
import genericEntity.GenericEntity;
import genericEntity.GenericPersistenceEntity;
import isptec.listas.Listas;
import isptec.utils.Utils;
import utils.*;

public class FaultDescriptionUI {

    public FaultDescriptionUI() {

    }

    public static void menu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Motivo de Falta****************\n");

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

        System.out.println("\n*****************Criando Motivo de Falta****************\n");

        do {

            System.out.print("Regra_validação: no mínimo 3 caracters!");
            MainMenu.pauseToSee();

            System.out.print("Nome: ");
            name = sc.nextLine();

        } while (name.length() < 3);

        GenericEntity entity = new GenericEntity(-1, name);

        GenericPersistenceEntity.create(entity, Defs.MOTIVO_FALTA_FILE);

        MainMenu.pauseToSee();

    }

    public static void edit() {

        Scanner sc = new Scanner(System.in);

        GenericEntity entity = searchToEdit();

        Boolean edited = false;

        if (entity == null) {

            System.out.println("\nMotivo de falta não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        System.out.println("\n*****************Editando Motivo de Falta****************\n");

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
            GenericPersistenceEntity.update(entity, Defs.MOTIVO_FALTA_FILE);

        System.out.println("\nEdição finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void drop() {

        System.out.println("\n*****************Eliminando Motivo de Falta****************\n");

        GenericEntity entity = searchToEdit();

        if (entity == null) {

            System.out.println("\nMotivo de falta não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        GenericPersistenceEntity.dropOne(entity.getId(), Defs.MOTIVO_FALTA_FILE);

        System.out.println("\nEliminação finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void search() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n*****************Procurando Motivo de Falta****************\n");

        System.out.print("ID: ");
        int id = sc.nextInt();

        GenericPersistenceEntity.read(id, Defs.MOTIVO_FALTA_FILE);

        MainMenu.pauseToSee();

    }

    public static GenericEntity searchToEdit() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        int id = sc.nextInt();

        return GenericPersistenceEntity.findOne(id, Defs.MOTIVO_FALTA_FILE);

    }

    public static void showlistData() {

        List<GenericEntity> data = GenericPersistenceEntity.findAll(Defs.MOTIVO_FALTA_FILE);

        System.out.println("\n*****************Todos Motivos de faltas*****************\n");

        for (GenericEntity datum : data)
            System.out.println("\n" + datum + "\n");

        MainMenu.pauseToSee();

    }

}
