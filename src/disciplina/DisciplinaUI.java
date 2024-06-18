package disciplina;

import java.util.List;
import java.util.Scanner;

import clearBuffer.ClearBuffer;
import isptec.listas.Listas;
import isptec.utils.Utils;
import utils.*;

public class DisciplinaUI {

    public DisciplinaUI() {

    }

    public static void menu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Disciplina*****************\n");

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

        System.out.println("\n*****************Criando Disciplina*****************\n");

        do {

            System.out.print("Regra_validação: no mínimo 3 caracters!");
            MainMenu.pauseToSee();

            System.out.print("Nome: ");
            nome = sc.nextLine();

        } while (nome.length() < 3);

        Disciplina newDisciplina = new Disciplina(-1, nome);

        DisciplinaPersistente.create(newDisciplina);

        MainMenu.pauseToSee();

    }

    public static void edit() {

        Scanner sc = new Scanner(System.in);

        Disciplina old = searchToEdit();

        if (old == null) {

            System.out.println("\nDisciplina não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        String nome = old.getNome();

        System.out.println("\n*****************Editando Disciplina*****************\n");

        if (Utils.editarCampo("Nome", old.getNome())) {

            do {

                System.out.print("\nRegra_validação: no mínimo 3 caracters! ");
                MainMenu.pauseToSee();

                System.out.print("\nNome: ");
                nome = sc.nextLine();

            } while (nome.length() < 3);

            old.setNome(nome);

        }

        DisciplinaPersistente.update(old);

        System.out.println("\nEdição finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void drop() {

        System.out.println("\n*****************Eliminando Disciplina*****************\n");

        Disciplina old = searchToEdit();

        if (old == null) {

            System.out.println("\nDisciplina não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        DisciplinaPersistente.dropOne(old);

        System.out.println("\nEliminação finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void search() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n*****************Procurando Disciplina*****************\n");

        System.out.print("ID: ");
        long id = sc.nextLong();

        DisciplinaPersistente.read(id);

        MainMenu.pauseToSee();

    }

    public static Disciplina searchToEdit() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        long id = sc.nextLong();

        return DisciplinaPersistente.findOne(id);

    }

    public static void list() {

        List<Disciplina> Disciplinas = DisciplinaPersistente.findAll();

        System.out.println("\n*****************Todas Disciplinas*****************\n");

        for (Disciplina pr : Disciplinas)
            System.out.println("\n" + pr.toString() + "\n");

        MainMenu.pauseToSee();

    }

}
