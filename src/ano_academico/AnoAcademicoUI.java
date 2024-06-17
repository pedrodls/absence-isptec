package ano_academico;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import clearBuffer.ClearBuffer;
import isptec.listas.Listas;
import isptec.utils.Utils;
import utils.*;

public class AnoAcademicoUI {

    public AnoAcademicoUI() {

    }

    public static void menu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Ano Academico****************\n");

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
        String regex = "^\\d+-ANO$";

        System.out.println("\n*****************Criando Ano Academico****************\n");

        do {

            System.out.print("Regra_validação: no mínimo 4 caracter! ");
            MainMenu.pauseToSee();

            System.out.print("Designação(x-Ano): ");
            nome = sc.nextLine();

        } while (!Pattern.compile(regex).matcher(nome).matches());

        AnoAcademico newAnoAcademico = new AnoAcademico(-1, nome);

        AnoAcademicoPersistente.create(newAnoAcademico);

        MainMenu.pauseToSee();

    }

    public static void edit() {

        Scanner sc = new Scanner(System.in);

        AnoAcademico old = searchToEdit();

        if (old == null) {

            System.out.println("\nAnoAcademico não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        String nome = old.getNome();

        System.out.println("\n*****************Editando Ano Academico****************\n");

        if (Utils.editarCampo("Nome", old.getNome())) {

            do {

                System.out.print("\nRegra_validação: no mínimo 3 caracters! ");
                MainMenu.pauseToSee();

                System.out.print("\nNome: ");
                nome = sc.nextLine();

            } while (nome.length() < 3);

            old.setNome(nome);

        }

        AnoAcademicoPersistente.update(old);

        System.out.println("\nEdição finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void drop() {

        System.out.println("\n*****************Eliminando Ano Academico****************\n");

        AnoAcademico old = searchToEdit();

        if (old == null) {

            System.out.println("\nAnoAcademico não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        AnoAcademicoPersistente.dropOne(old);

        System.out.println("\nEliminação finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void search() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n*****************Procurando Ano Academico****************\n");

        System.out.print("ID: ");
        long id = sc.nextLong();

        AnoAcademicoPersistente.read(id);

        MainMenu.pauseToSee();

    }

    public static AnoAcademico searchToEdit() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        long id = sc.nextLong();

        return AnoAcademicoPersistente.findOne(id);

    }

    public static void list() {

        List<AnoAcademico> AnoAcademicos = AnoAcademicoPersistente.findAll();

        System.out.println("\n*****************Todas AnoAcademicos*****************\n");

        for (AnoAcademico pr : AnoAcademicos)
            System.out.println("\n" + pr.toString() + "\n");

        MainMenu.pauseToSee();

    }

}
