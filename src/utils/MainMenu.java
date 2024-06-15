package utils;

import java.util.Scanner;

import clearBuffer.ClearBuffer;
import isptec.listas.Listas;
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
                    ProfessorUI.menu();

                    break;

                case 2:
                    System.out.println("Programa terminado");
                    System.exit(0);
                    break;
            }
        }
    }

}
