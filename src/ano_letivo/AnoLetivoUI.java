package ano_letivo;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import clearBuffer.ClearBuffer;
import coordenacao.CoordenacaoPersistente;
import coordenador.CoordenadorPersistente;
import estudante.EstudantePersistente;
import isptec.listas.Listas;
import isptec.utils.Utils;
import utils.*;

public class AnoLetivoUI {

    public AnoLetivoUI() {

    }

    public static void menu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Ano Lectivo****************\n");

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

        AnoLetivo newAnoLetivo = new AnoLetivo(-1, nome);

        AnoLetivoPersistente.create(newAnoLetivo);

        MainMenu.pauseToSee();

    }

    public static void edit() {

        Scanner sc = new Scanner(System.in);

        AnoLetivo old = searchToEdit();

        if (old == null) {

            System.out.println("\nAnoLetivo não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        String nome = old.getNome();

        System.out.println("\n*****************Editando Ano Lectivo****************\n");

        if (Utils.editarCampo("Nome", old.getNome())) {

            do {

                System.out.print("\nRegra_validação: no mínimo 3 caracters! ");
                MainMenu.pauseToSee();

                System.out.print("\nNome: ");
                nome = sc.nextLine();

            } while (nome.length() < 3);

            old.setNome(nome);

        }

        AnoLetivoPersistente.update(old);

        System.out.println("\nEdição finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void drop() {

        System.out.println("\n*****************Eliminando Ano Lectivo****************\n");

        
        AnoLetivo old = searchToEdit();

        if (old == null) {

            System.out.println("\nAnoLetivo não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        if (EstudantePersistente.findAllByAnoIngressoId(old.getId()).size() > 0) {

            System.out.println("\nAnoLetivo não pode ser apagado pois existem dados ligados ao mesmo!\n");

            MainMenu.pauseToSee();

            return;
        }

        if (CoordenadorPersistente.findAllByAnoLetivoId(old.getId()).size() > 0) {

            System.out.println("\nAnoLetivo não pode ser apagado pois existem dados ligados ao mesmo!\n");

            MainMenu.pauseToSee();

            return;
        }

        AnoLetivoPersistente.dropOne(old);

        System.out.println("\nEliminação finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void search() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n*****************Procurando Ano Lectivo****************\n");

        System.out.print("ID: ");
        long id = sc.nextLong();

        AnoLetivoPersistente.read(id);

        MainMenu.pauseToSee();

    }

    public static AnoLetivo searchToEdit() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        long id = sc.nextLong();

        return AnoLetivoPersistente.findOne(id);

    }

    public static void list() {

        List<AnoLetivo> AnoLetivos = AnoLetivoPersistente.findAll();

        System.out.println("\n*****************Todas AnoLetivos*****************\n");

        for (AnoLetivo pr : AnoLetivos)
            System.out.println("\n" + pr.toString() + "\n");

        MainMenu.pauseToSee();

    }

}
