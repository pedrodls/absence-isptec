package utils;
import ano_academico.AnoAcademicoUI;
import clearBuffer.ClearBuffer;
import isptec.listas.Listas;

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

    public static void adminMenu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Admin*****************\n");

            int opcao = Listas.enviarLerOpcaoEscolhida(Defs.ADMIN_LINKS);

            switch (opcao) {
                case 1:
                    AnoAcademicoUI.menu();

                    break;

                case 2:
                    System.out.println("Programa terminado");
                    System.exit(0);
                    break;
            }
        }
    }

}
