package utils.functions;

import lib.clearBuffer.ClearBuffer;
import lib.isptec.listas.Listas;

/**
 *
 * @author Pedro João
 */

public class MainMenu {

    public static void mainMenu() {
        for (;;) {
            
            ClearBuffer.clear();

            System.out.println("\n*****************Menu Principal*****************\n");

            String opcoes[] = {
                    "Justificativo", "Sair"
            };
            
            int opcao = Listas.enviarLerOpcaoEscolhida(opcoes);

            switch (opcao) {
                case 1:
                
                    JustificatioMenu.presentMenu();

                    break;

                case 2:
                    System.out.println("Programa terminado");
                    System.exit(0);
                    break;
            }
        }
    }

}
