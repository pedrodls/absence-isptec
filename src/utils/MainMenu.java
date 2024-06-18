package utils;

import java.util.Scanner;

import ano_academico.AnoAcademicoUI;
import ano_letivo.AnoLetivoUI;
import clearBuffer.ClearBuffer;
import coordenacao.CoordenacaoUI;
import curso.CursoUI;
import disciplina.DisciplinaUI;
import estudante.EstudanteUI;
import isptec.listas.Listas;
import motivo_falta.MotivoFaltaUI;
import professor.ProfessorUI;

/**
 *
 * @author Pedro João
 */

public class MainMenu {

    public static void mainMenu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Principal*****************\n");

            int opcao = Listas.enviarLerOpcaoEscolhida(Defs.MAIN_MENU_LINKS);

            switch (opcao) {
                case 1:

                    adminMenu();

                    break;

                case 2:
                    System.out.println("Programa terminado");
                    System.exit(0);
                    break;
            }
        }
    }

    public static void pauseToSee() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Prima Enter para continuar...");
        String op = sc.nextLine();

    }

    public static void adminMenu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Admin*****************\n");

            int opcao = Listas.enviarLerOpcaoEscolhida(Defs.ADMIN_LINKS);

            switch (opcao) {

                case 1:
                    AnoLetivoUI.menu();
                    break;

                case 2:
                    AnoAcademicoUI.menu();
                    break;

                case 3:
                    CursoUI.menu();
                    break;

                case 4:
                    CoordenacaoUI.menu();
                    break;

                case 5:
                    // Coordenador
                    break;

                case 6:
                    ProfessorUI.menu();
                    break;

                case 7:
                    EstudanteUI.menu();
                    break;

                case 8:
                    DisciplinaUI.menu();
                    break;

                case 9:
                    MotivoFaltaUI.menu();
                    break;

                case 10:
                    System.out.println("Programa terminado");
                    System.exit(0);
                    break;
            }
        }
    }

}
