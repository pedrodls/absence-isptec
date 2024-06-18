package turma;

import java.util.List;
import java.util.Scanner;

import ano_academico.AnoAcademico;
import ano_academico.AnoAcademicoUI;
import clearBuffer.ClearBuffer;
import curso.Curso;
import curso.CursoPersistente;
import curso.CursoUI;
import isptec.listas.Listas;
import isptec.utils.Utils;
import utils.*;

public class TurmaUI {

    public TurmaUI() {

    }

    public static void menu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Turma****************\n");

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
        Curso curso = null;
        AnoAcademico ano = null;
        String nome;

        System.out.println("\n*****************Criando Turma*****************\n");

        do {

            System.out.print("Regra_validação: no mínimo 3 caracters!");
            MainMenu.pauseToSee();

            System.out.print("Nome: ");
            nome = sc.nextLine();

        } while (nome.length() < 3);

        do {

            System.out.print("Regra_validação: Insira curso existente ");
            curso = CursoUI.searchToEdit();

        } while (curso == null);

        do {

            System.out.print("Regra_validação: Insira ano academico existente ");
            ano = AnoAcademicoUI.searchToEdit();

        } while (ano == null);

        Turma newTurma = new Turma(-1, curso.getId(), ano.getId(), nome.toUpperCase());

        TurmaPersistente.create(newTurma);

        MainMenu.pauseToSee();

    }

    public static void edit() {

        Scanner sc = new Scanner(System.in);

        String nome;

        Turma old = searchToEdit();

        Curso newCurso = null;

        AnoAcademico ano = null;

        if (old == null) {

            System.out.println("\nTurma não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        System.out.println("\n*****************Editando Turma****************\n");

        if (Utils.editarCampo("Nome", old.getNome())) {

            do {

                System.out.print("\nRegra_validação: no mínimo 3 caracters! ");
                MainMenu.pauseToSee();

                System.out.print("\nNome: ");
                nome = sc.nextLine();

            } while (nome.length() < 3);

            old.setNome(nome.toUpperCase());

        }

        if (Utils.editarCampo("ID Curso", old.getCursoId() + "")) {

            do {

                System.out.print("Regra_validação: Insira curso existente ");
                newCurso = CursoUI.searchToEdit();

            } while (newCurso == null);

            old.setCursoId(newCurso.getId());
        }

        if (Utils.editarCampo("ID Ano Academico", old.getAnoAcademicoId() + "")) {

            do {

                System.out.print("Regra_validação: Insira Ano Academico existente ");
                ano = AnoAcademicoUI.searchToEdit();

            } while (ano == null);

            old.setAnoAcademicoId(ano.getId());
        }

        TurmaPersistente.update(old);

        System.out.println("\nEdição finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void drop() {

        System.out.println("\n*****************Eliminando Turma****************\n");

        Turma old = searchToEdit();

        if (old == null) {

            System.out.println("\nTurma não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        TurmaPersistente.dropOne(old);

        System.out.println("\nEliminação finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void search() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n*****************Procurando Turma****************\n");

        System.out.print("ID: ");
        long id = sc.nextLong();

        TurmaPersistente.read(id);

        MainMenu.pauseToSee();

    }

    public static Turma searchToEdit() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        long id = sc.nextLong();

        return TurmaPersistente.findOne(id);

    }

    public static void list() {

        List<Turma> Turmas = TurmaPersistente.findAll();

        System.out.println("\n*****************Todas Turmas*****************\n");

        for (Turma pr : Turmas)
            System.out.println("\n" + pr.toString() + "\n");

        MainMenu.pauseToSee();

    }

}
