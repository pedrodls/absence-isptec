package utils.functions;

import lib.isptec.listas.Listas;



/**
 *
 * @author Pedro João
 */

public class MainMenu {

    public static void mainMenu() {
        for (;;) {
            String opcoes[] = {
                    "Clientes", "Consultas", "Sair"
            };
            int opcao = Listas.enviarLerOpcaoEscolhida(opcoes);
            switch (opcao) {
                case 1:
                    System.out.println("Em desenvolvimento 1");

                    break;
                case 2:
                    System.out.println("Em desenvolvimento 2");

                    break;
                case 3:
                    System.out.println("Programa terminado");
                    System.exit(0);
                    break;
            }
        }
    }

}
