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

            String opcoes[] = {
                    "Motivos de Falta", "Sair"
            };

            int opcao = Listas.enviarLerOpcaoEscolhida(opcoes);

            switch (opcao) {
                case 1:

                    System.out.println("Começamos");

                    break;

                case 2:
                    System.out.println("Programa terminado");
                    System.exit(0);
                    break;
            }
        }
    }

}
