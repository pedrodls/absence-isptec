package motivo_falta;

import java.util.List;
import java.util.Scanner;

import clearBuffer.ClearBuffer;
import isptec.listas.Listas;
import isptec.utils.Utils;
import utils.*;

public class MotivoFaltaUI {

    public MotivoFaltaUI() {

    }

    public static void menu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Motivo de Falta*****************\n");

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

        System.out.println("\n*****************Criando Motivo de falta****************\n");

        do {

            System.out.print("Regra_validação: no mínimo 3 caracters!");
            MainMenu.pauseToSee();

            System.out.print("Nome: ");
            nome = sc.nextLine();

        } while (nome.length() < 3);

        MotivoFalta newMotivoFalta = new MotivoFalta(-1, nome);

        MotivoFaltaPersistente.create(newMotivoFalta);

        MainMenu.pauseToSee();

    }

    public static void edit() {

        Scanner sc = new Scanner(System.in);

        MotivoFalta old = searchToEdit();

        if (old == null) {

            System.out.println("\nMotivoFalta não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        String nome = old.getNome();

        System.out.println("\n*****************Editando Motivo de falta****************\n");

        if (Utils.editarCampo("Nome", old.getNome())) {

            do {

                System.out.print("\nRegra_validação: no mínimo 3 caracters! ");
                MainMenu.pauseToSee();

                System.out.print("\nNome: ");
                nome = sc.nextLine();

            } while (nome.length() < 3);

            old.setNome(nome);

        }

        MotivoFaltaPersistente.update(old);

        System.out.println("\nEdição finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void drop() {

        System.out.println("\n*****************Eliminando Motivo de falta****************\n");

        MotivoFalta old = searchToEdit();

        if (old == null) {

            System.out.println("\nMotivoFalta não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        MotivoFaltaPersistente.dropOne(old);

        System.out.println("\nEliminação finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void search() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n*****************Procurando Motivo de falta****************\n");

        System.out.print("ID: ");
        long id = sc.nextLong();

        MotivoFaltaPersistente.read(id);

        MainMenu.pauseToSee();

    }

    public static MotivoFalta searchToEdit() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        long id = sc.nextLong();

        return MotivoFaltaPersistente.findOne(id);

    }

    public static void list() {

        List<MotivoFalta> MotivoFaltas = MotivoFaltaPersistente.findAll();

        System.out.println("\n*****************Todas MotivoFaltas*****************\n");

        for (MotivoFalta pr : MotivoFaltas)
            System.out.println("\n" + pr.toString() + "\n");

        MainMenu.pauseToSee();

    }

}
