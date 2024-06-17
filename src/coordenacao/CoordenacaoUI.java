package coordenacao;

import java.util.List;
import java.util.Scanner;

import clearBuffer.ClearBuffer;
import isptec.listas.Listas;
import isptec.utils.Utils;
import utils.*;

public class CoordenacaoUI {

    public CoordenacaoUI() {

    }

    public static void menu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Coordenacao*****************\n");

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

        System.out.println("\n*****************Criando Coordenacao*****************\n");

        do {

            System.out.print("Regra_validação: no mínimo 3 caracters!");
            MainMenu.pauseToSee();

            System.out.print("Nome: ");
            nome = sc.nextLine();

        } while (nome.length() < 3);

        Coordenacao newCoordenacao = new Coordenacao(-1, nome);

        CoordenacaoPersistente.create(newCoordenacao);

        MainMenu.pauseToSee();

    }

    public static void edit() {

        Scanner sc = new Scanner(System.in);

        Coordenacao old = searchToEdit();

        if (old == null) {

            System.out.println("\nCoordenacao não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        String nome = old.getNome();

        System.out.println("\n*****************Editando Coordenacao*****************\n");

        if (Utils.editarCampo("Nome", old.getNome())) {

            do {

                System.out.print("\nRegra_validação: no mínimo 3 caracters! ");
                MainMenu.pauseToSee();

                System.out.print("\nNome: ");
                nome = sc.nextLine();

            } while (nome.length() < 3);

            old.setNome(nome);

        }

        CoordenacaoPersistente.update(old);

        System.out.println("\nEdição finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void drop() {

        System.out.println("\n*****************Eliminando Coordenacao*****************\n");

        Coordenacao old = searchToEdit();

        if (old == null) {

            System.out.println("\nCoordenacao não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        CoordenacaoPersistente.dropOne(old);

        System.out.println("\nEliminação finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void search() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n*****************Procurando Coordenacao*****************\n");

        System.out.print("ID: ");
        long id = sc.nextLong();

        CoordenacaoPersistente.read(id);

        MainMenu.pauseToSee();

    }

    public static Coordenacao searchToEdit() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        long id = sc.nextLong();

        return CoordenacaoPersistente.findOne(id);

    }

    public static void list() {

        List<Coordenacao> Coordenacaos = CoordenacaoPersistente.findAll();

        System.out.println("\n*****************Todas Coordenacaos*****************\n");

        for (Coordenacao pr : Coordenacaos)
            System.out.println("\n" + pr.toString() + "\n");

        MainMenu.pauseToSee();

    }

}
