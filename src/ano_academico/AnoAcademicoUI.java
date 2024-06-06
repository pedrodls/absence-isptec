package ano_academico;

import java.io.IOException;

import clearBuffer.ClearBuffer;
import isptec.listas.Listas;
import utils.*;

public class AnoAcademicoUI {

    public AnoAcademicoUI() {

    }

    public static void menu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Ano Academico*****************\n");

            int opcao = Listas.enviarLerOpcaoEscolhida(Defs.CRUD_LINKS);

            switch (opcao) {
                case 1:
                    create();
                    break;

                case 2:
                    findOne(2);
                    break;
                case 4:
                    MainMenu.adminMenu();
                    break;
            }
        }
    }

    public static void create() {
        try {

            AnoAcademicoPersistente indexedFile = new AnoAcademicoPersistente(Defs.ANO_LETIVO_FILE);

            // Adicionando registros
            indexedFile.addAnoAcademico(new AnoAcademico(1, "2020/2021", false));
            indexedFile.addAnoAcademico(new AnoAcademico(2, "2021/2022", false));
            indexedFile.addAnoAcademico(new AnoAcademico(3, "2023/2024", false));

            // Buscando registros
            AnoAcademico record = indexedFile.getAnoAcademico(2);

            if (record != null) {
                System.out.println("Registro encontrado: " + record.toString());
            } else {
                System.out.println("Registro não encontrado.");
            }

            indexedFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void findOne(int id) {
        try {

            AnoAcademicoPersistente indexedFile = new AnoAcademicoPersistente(Defs.ANO_LETIVO_FILE);

            AnoAcademico record = indexedFile.getAnoAcademico(id);

            if (record != null) {
                System.out.println("Registro encontrado: " + record.toString());
            } else {
                System.out.println("Registro não encontrado.");
            }

            indexedFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
