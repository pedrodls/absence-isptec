package course;

import java.util.List;
import java.util.Scanner;

import clearBuffer.ClearBuffer;
import coordenacao.CoordenacaoPersistente;
import coordenador.CoordenadorPersistente;
import estudante.EstudantePersistente;
import isptec.listas.Listas;
import isptec.utils.Utils;
import turma.TurmaPersistente;
import utils.*;

public class CursoUI {

    public CursoUI() {

    }

    public static void menu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Curso*****************\n");

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

        System.out.println("\n*****************Criando Curso*****************\n");

        do {

            System.out.print("Regra_validação: no mínimo 3 caracters!");
            MainMenu.pauseToSee();

            System.out.print("Nome: ");
            nome = sc.nextLine();

        } while (nome.length() < 3);

        Curso newCurso = new Curso(-1, nome);

        CursoPersistente2.create(newCurso);

        MainMenu.pauseToSee();

    }

    public static void edit() {

        Scanner sc = new Scanner(System.in);

        Curso old = searchToEdit();

        if (old == null) {

            System.out.println("\nCurso não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        String nome = old.getNome();

        System.out.println("\n*****************Editando Curso*****************\n");

        if (Utils.editarCampo("Nome", old.getNome())) {

            do {

                System.out.print("\nRegra_validação: no mínimo 3 caracters! ");
                MainMenu.pauseToSee();

                System.out.print("\nNome: ");
                nome = sc.nextLine();

            } while (nome.length() < 3);

            old.setNome(nome);

        }

        CursoPersistente.update(old);

        System.out.println("\nEdição finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void drop() {

        System.out.println("\n*****************Eliminando Curso*****************\n");

        Curso old = searchToEdit();

        if (old == null) {

            System.out.println("\nCurso não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        if (EstudantePersistente.findAllByCursoId(old.getId()).size() > 0
                || CoordenadorPersistente.findAllByCursoId(old.getId()).size() > 0
                || CoordenacaoPersistente.findAllByCursoId(old.getId()).size() > 0
                || TurmaPersistente.findAllByCursoId(old.getId()).size() > 0) {

            System.out.println("\nCurso não pode ser apagado pois existem dados ligados ao mesmo!\n");

            MainMenu.pauseToSee();

            return;
        }

        CursoPersistente.dropOne(old);

        System.out.println("\nEliminação finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void search() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n*****************Procurando Curso*****************\n");

        System.out.print("ID: ");
        long id = sc.nextLong();

        CursoPersistente.read(id);

        MainMenu.pauseToSee();

    }

    public static Curso searchToEdit() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        long id = sc.nextLong();

        return CursoPersistente.findOne(id);

    }

    public static void list() {

        List<Curso> Cursos = CursoPersistente.findAll();

        System.out.println("\n*****************Todas Cursos*****************\n");

        for (Curso pr : Cursos)
            System.out.println("\n" + pr.toString() + "\n");

        MainMenu.pauseToSee();

    }

}
