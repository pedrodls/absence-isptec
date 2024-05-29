
import lib.clearBuffer.ClearBuffer;
import lib.isptec.Utils.Utils;
import utils.functions.MainMenu;

public class App {

    public static void main(String[] args) {
        enviarFormularioApresentacao();
    }

    private static void enviarFormularioApresentacao() {

        ClearBuffer.clear();

        System.out.println("\tSistema de Gestao de Justificação de Falta");
        System.out.println("\t\tDesenvolvido pelo ISPTEC");

        if (Utils.concorda()) {

            MainMenu.mainMenu();

        } else {
            Utils.exit("Programa Terminado!");
        }
    }

}
