package utils.functions;

import java.text.SimpleDateFormat;
import java.util.Date;

import entities.justification.JustificationUI;
import entities.lostedTest.LostedTest;
import lib.clearBuffer.ClearBuffer;
import lib.isptec.listas.Listas;
import lib.isptec.utils.DataUtils;

public class JustificatioMenu {

    public static void presentMenu() {

        Boolean getOutLoop = false;
        Boolean created = false;
        Boolean droped = false;
        Boolean edited = false;
        JustificationUI justification = new JustificationUI();

        ClearBuffer.clear();

        while (!getOutLoop) {

            System.out.println("\n*****************Menu Justificativo*****************\n");

            if (created) {

                System.out.println("Justificativo criado com sucesso!\n");

                created = false;
            }

            if (edited) {

                System.out.println("Justificativo atualizado com sucesso!\n");

                edited = false;
            }

            if (droped) {

                System.out.println("Justificativo deletado com sucesso!\n");

                droped = false;
            }

            String opcoes[] = {
                    "Novo", "Ver", "Editar", "Eliminar", "Sair"
            };

            int opcao = Listas.enviarLerOpcaoEscolhida(opcoes);

            switch (opcao) {
                case 1:

                    ClearBuffer.clear();

                    justification = JustificationUI.create();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                    // Get current date
                    Date currentDate = new Date();

                    // Format the date
                    String formattedDate = dateFormat.format(currentDate);

                    justification.setCreatedAt(DataUtils.stringToDate(formattedDate));

                    created = true;

                    ClearBuffer.clear();

                    break;

                case 2:

                    ClearBuffer.clear();

                    if (justification.getCreatedAt() != null) {

                       System.out.println(justification.toString());

                    }else
                        System.out.println("\nNão existe formulário disponível!\n");

                    break;

                case 3:

                    if (justification.getCreatedAt() != null) {

                        ClearBuffer.clear();

                        justification = JustificationUI.edit(justification);

                        edited = true;

                        ClearBuffer.clear();

                    } else
                        System.out.println("\nNão existe Justificativo disponível!\n");

                    break;

                case 4:

                    if (justification.getCreatedAt() != null) {

                        justification = new JustificationUI();

                        System.out.println("\nJustificado deletado com sucesso!\n");

                        droped = true;

                        ClearBuffer.clear();

                    } else
                        System.out.println("\nNão existe Justificativo disponível!\n");

                    break;
                case 5:
                    MainMenu.mainMenu();
            }
        }
    }

}
