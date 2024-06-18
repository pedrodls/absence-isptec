package estudante;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import ano_letivo.AnoLetivo;
import ano_letivo.AnoLetivoPersistente;
import ano_letivo.AnoLetivoUI;
import clearBuffer.ClearBuffer;
import curso.Curso;
import curso.CursoUI;
import isptec.listas.Listas;
import isptec.utils.Utils;
import utils.Defs;
import utils.MainMenu;


public class EstudanteUI {

    public static String telefoneRegex = "^9\\d{8}$";

    public EstudanteUI() {

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
        String email = Defs.EMAIL_ADDRESS;
        String telefone;
        AnoLetivo anoIngresso = null;
        Curso curso = null;

        System.out.println("\n*****************Criando Estudante*****************\n");

        do {
            System.out.print("Regra_validação: Insira ano letivo existente ");
            anoIngresso = AnoLetivoUI.searchToEdit();

        } while (anoIngresso == null);

        do {
            System.out.print("Regra_validação: Insira curso existente ");
            curso = CursoUI.searchToEdit();

        } while (anoIngresso == null);

        do {

            System.out.println("Regra_validação: no mínimo 3 caracters! ");

            System.out.print("Nome: ");
            nome = sc.nextLine();

        } while (nome.length() < 3);

        do {

            System.out.print("Telefone(9________): ");
            telefone = sc.nextLine();

        } while (!Pattern.matches(telefoneRegex, telefone));

        Estudante newEstudante = new Estudante(-1, nome, email, telefone, anoIngresso.getId(), curso.getId());

        EstudantePersistente.create(newEstudante);

        MainMenu.pauseToSee();

    }

    public static void edit() {

        Scanner sc = new Scanner(System.in);

        Estudante old = searchToEdit();

        if (old == null) {

            System.out.println("\nEstudante não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        String nome = old.getNome();
        String telefone = old.getTelefone();

        System.out.println("\n*****************Editando Estudante****************\n");

        if (Utils.editarCampo("Nome", nome)) {

            do {

                System.out.println("\nRegra_validação: no mínimo 3 caracters! ");

                System.out.print("\nNome: ");
                nome = sc.nextLine();

            } while (nome.length() < 3);

            old.setNome(nome);

        }

        if (Utils.editarCampo("Telefone", telefone)) {

            do {

                System.out.print("Telefone(9________): ");
                telefone = sc.nextLine();
    
            } while (!Pattern.matches(telefoneRegex, telefone));

            old.setTelefone(telefone);

        }

        EstudantePersistente.update(old);

        System.out.println("\nEdição finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void drop() {

        System.out.println("\n*****************Eliminando Estudante****************\n");

        Estudante old = searchToEdit();

        if (old == null) {

            System.out.println("\nEstudante não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        EstudantePersistente.dropOne(old);

        System.out.println("\nEliminação finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void search() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n*****************Procurando Estudante****************\n");

        System.out.print("ID: ");
        long id = sc.nextLong();

        EstudantePersistente.read(id);

        MainMenu.pauseToSee();

    }

    public static Estudante searchToEdit() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        long id = sc.nextLong();

        return EstudantePersistente.findOne(id);

    }

    public static void list() {

        List<Estudante> Estudantes = EstudantePersistente.findAll();

        System.out.println("\n*****************Todos Estudantes*****************\n");

        for (Estudante es : Estudantes)
            System.out.println("\n" + es.toString() + "\n");

        MainMenu.pauseToSee();

    }

}
