
import lib.clearBuffer.ClearBuffer;
import lib.isptec.utils.Utils;
import utils.functions.MainMenu;

public class App {

    public static void main(String[] args) {
        enviarFormularioApresentacao();
    }

    private static void enviarFormularioApresentacao() {

        ClearBuffer.clear();

        System.out.println("\t\t\t\tSistema de Gestao de Justificação de Falta");
        System.out.println("\t\t\tDesenvolvido pelo Estudante de Nº 20220519, de nome Pedro João");
        System.out.println("\tNo Âmbito da cadeira de Programação II, Sob Orientação do Professor: Doutor Aires Veloso.");

        System.out.println("\t\t\tEste sistema é da titularidade do ISPTEC, deseja continuar?\n\n");

        if (Utils.concorda()) {

            MainMenu.mainMenu();

        } else {
            Utils.exit("Programa Terminado!");
        }
    }

}
