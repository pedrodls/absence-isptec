package professor;

import java.util.List;
import java.util.Scanner;

import clearBuffer.ClearBuffer;
import isptec.listas.Listas;
import isptec.utils.Utils;
import utils.*;

public class ProfessorUI {

    public ProfessorUI() {

    }

    public static void menu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Professor*****************\n");

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

        System.out.println("\n*****************Criando Professor*****************\n");

        do {

            System.out.print("Regra_validação: no mínimo 3 caracters!");
            MainMenu.pauseToSee();

            System.out.print("Nome: ");
            nome = sc.nextLine();

        } while (nome.length() < 3);

        Professor newProfessor = new Professor(-1, nome);

        ProfessorPersistente.create(newProfessor);

        MainMenu.pauseToSee();

    }

    public static void edit() {

        Scanner sc = new Scanner(System.in);

        Professor old = searchToEdit();

        if (old == null) {

            System.out.println("\nProfessor não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        String nome = old.getNome();

        System.out.println("\n*****************Editando Professor*****************\n");

        if (Utils.editarCampo("Nome", old.getNome())) {

            do {

                System.out.print("\nRegra_validação: no mínimo 3 caracters! ");
                MainMenu.pauseToSee();

                System.out.print("\nNome: ");
                nome = sc.nextLine();

            } while (nome.length() < 3);

            old.setNome(nome);

        }

        ProfessorPersistente.update(old);

        System.out.println("\nEdição finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void drop() {


        System.out.println("\n*****************Eliminando Professor*****************\n");
        
        Professor old = searchToEdit();

        if (old == null) {

            System.out.println("\nProfessor não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        ProfessorPersistente.dropOne(old);

        System.out.println("\nEliminação finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void search() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n*****************Procurando Professor*****************\n");

        System.out.print("ID: ");
        long id = sc.nextLong();

        ProfessorPersistente.read(id);

        MainMenu.pauseToSee();

    }

    public static Professor searchToEdit() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        long id = sc.nextLong();

        return ProfessorPersistente.findOne(id);

    }

    public static void list() {

        List<Professor> professors = ProfessorPersistente.findAll();

        System.out.println("\n*****************Todos Professores*****************\n");

        for (Professor pr : professors)
            System.out.println("\n" + pr.toString() + "\n");

        MainMenu.pauseToSee();

    }

}
