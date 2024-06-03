package utils.functions;

import entities.justification.JustificationUI;
import lib.clearBuffer.ClearBuffer;
import lib.isptec.listas.Listas;

public class JustificatioMenu {
    

    public static void presentMenu() {

        Boolean getOutLoop = false;
        Boolean created = false;

        while(!getOutLoop) {
            
            ClearBuffer.clear();

            System.out.println("\n*****************Menu Justificativo*****************\n");

            if (created) {
                
                System.out.println("Justificativo criado com sucesso!\n");

                created = false;
            }

            String opcoes[] = {
                    "Novo", "Ver", "Editar", "Apagar", "Sair"
            };
            
            int opcao = Listas.enviarLerOpcaoEscolhida(opcoes);

            switch (opcao) {
                case 1:
                    
                    ClearBuffer.clear();

                    JustificationUI justification =  JustificationUI.create();

                    created = true;

                    ClearBuffer.clear();

                    /* System.out.println(justification.toString());

                    System.out.println(justification.getStudent().toString());

                    System.out.println("\nProvas perdidas\n");

                    for(LostedTest lt : justification.getLostedTests()){
                        System.out.println("\n"+lt.toString()+"\n");
                    } */

                    break;

                case 5:
                    MainMenu.mainMenu();
            }
        }
    }
    
}
