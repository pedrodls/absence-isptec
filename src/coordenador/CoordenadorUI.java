package coordenador;

import java.util.List;
import java.util.Scanner;

import ano_letivo.AnoLetivo;
import ano_letivo.AnoLetivoUI;
import clearBuffer.ClearBuffer;
import curso.Curso;
import curso.CursoUI;
import isptec.listas.Listas;
import isptec.utils.Utils;
import professor.Professor;
import professor.ProfessorUI;
import utils.*;

public class CoordenadorUI {

    public CoordenadorUI() {

    }

    public static void menu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Coordenador*****************\n");

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

        Curso curso = null;

        AnoLetivo anoLetivo = null;
        Professor professor = null;

        System.out.println("\n*****************Criando Coordenador*****************\n");

        do {
            System.out.print("Regra_validação: Insira ano letivo existente ");
            anoLetivo = AnoLetivoUI.searchToEdit();

        } while (anoLetivo == null);

        do {
            System.out.print("Regra_validação: Insira o professor existente ");
            professor = ProfessorUI.searchToEdit();

        } while (professor == null);

        do {
            System.out.print("Regra_validação: Insira curso existente ");
            curso = CursoUI.searchToEdit();

        } while (curso == null);

        Coordenador newCoordenador = new Coordenador(-1, professor.getId(), curso.getId(), anoLetivo.getId());

        CoordenadorPersistente.create(newCoordenador);

        MainMenu.pauseToSee();

    }

    public static void edit() {

        Curso curso = null;

        AnoLetivo anoLetivo = null;
        Professor professor = null;

        Coordenador old = searchToEdit();

        if (old == null) {

            System.out.println("\nCoordenador não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        System.out.println("\n*****************Editando Coordenador*****************\n");

        if (Utils.editarCampo("ID Ano Letivo", old.getIdProfessor() + "")) {

            do {
                System.out.print("Regra_validação: Insira ano letivo existente ");
                anoLetivo = AnoLetivoUI.searchToEdit();

            } while (anoLetivo == null);

        }

        if (Utils.editarCampo("ID Professor", old.getIdProfessor() + "")) {

            do {
                System.out.print("Regra_validação: Insira o professor existente ");
                professor = ProfessorUI.searchToEdit();

            } while (professor == null);

        }

        if (Utils.editarCampo("ID Curso", old.getIdProfessor() + "")) {

            do {
                System.out.print("Regra_validação: Insira curso existente ");
                curso = CursoUI.searchToEdit();

            } while (curso == null);

        }

        old.setIdAnoLetivo(anoLetivo.getId());
        old.setIdProfessor(professor.getId());
        old.setIdCurso(curso.getId());

        CoordenadorPersistente.update(old);

        System.out.println("\nEdição finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void drop() {

        System.out.println("\n*****************Eliminando Coordenador*****************\n");

        Coordenador old = searchToEdit();

        if (old == null) {

            System.out.println("\nCoordenador não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        CoordenadorPersistente.dropOne(old);

        System.out.println("\nEliminação finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void search() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n*****************Procurando Coordenador*****************\n");

        System.out.print("ID: ");
        long id = sc.nextLong();

        CoordenadorPersistente.read(id);

        MainMenu.pauseToSee();

    }

    public static Coordenador searchToEdit() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        long id = sc.nextLong();

        return CoordenadorPersistente.findOne(id);

    }

    public static void list() {

        List<Coordenador> Coordenadors = CoordenadorPersistente.findAll();

        System.out.println("\n*****************Todos Coordenadores*****************\n");

        for (Coordenador pr : Coordenadors)
            System.out.println("\n" + pr.toString() + "\n");

        MainMenu.pauseToSee();

    }

}
