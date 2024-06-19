package utils;

import java.util.Scanner;

import clearBuffer.ClearBuffer;
import course.CourseUI;
import faultDescription.FaultDescriptionUI;
import isptec.listas.Listas;
import subject.SubjectUI;
import teacher.TeacherUI;

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
                    coordenacaoMenu();
                    break;

                case 3:
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
                    CourseUI.menu();
                    break;

                case 2:
                    SubjectUI.menu();
                    break;
                case 3:
                    TeacherUI.menu();
                    break;
                case 4:
                    // CoordenatorUI
                    break;
                case 5:
                    // StudentUI
                    break;
                case 6:
                    FaultDescriptionUI.menu();
                    break;

                case 10:
                    System.out.println("Programa terminado");
                    System.exit(0);
                    break;
            }
        }
    }

    public static void coordenacaoMenu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Coordenação*****************\n");

            int opcao = Listas.enviarLerOpcaoEscolhida(Defs.COORDENATION_LINKS);

            switch (opcao) {

                case 1:
                    // TurmaUI.menu();
                    break;

                case 6:
                    adminMenu();
                    break;
                case 10:
                    System.out.println("Programa terminado");
                    System.exit(0);
                    break;
            }
        }
    }

}
