package professor;

import java.util.Scanner;

import clearBuffer.ClearBuffer;
import isptec.listas.Listas;
import utils.*;

public class ProfessorUI {

    public ProfessorUI() {

    }

    public static void menu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Professor*****************\n");

            int opcao = Listas.enviarLerOpcaoEscolhida(Defs.CRUD_LINKS);

            switch (opcao) {
                case 1:
                        create();
                    break;

                case 2:

                    break;
                case 4:
                    MainMenu.adminMenu();
                    break;
            }
        }
    }

    public static void create() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n*****************Criando Professor*****************\n");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        //Buscar a quantidade de dados existentes e retorna id;

        Professor newProfessor = new Professor(0, nome);

        newProfessor.save();

        MainMenu.pauseToSee();

    }

    



}
