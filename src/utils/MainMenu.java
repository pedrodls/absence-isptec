package utils;

import java.util.Scanner;

import academicYear.AcademicYearUI;
import clearBuffer.ClearBuffer;
import coordinator.CoordinatorUI;
import course.CourseUI;
import faultDescription.FaultDescriptionUI;
import isptec.listas.Listas;
import student.StudentUI;
import subject.SubjectUI;
import teacher.TeacherUI;

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
                    coordenacaoMenu();
                    break;

                case 5:
                    System.out.println("Programa terminado");
                    System.exit(0);
                    break;
            }
        }
    }

    public static void pauseToSee() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Prima Enter para continuar...");
        String op = sc.nextLine();

    }

    public static void adminMenu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Admin*****************\n");

            int opcao = Listas.enviarLerOpcaoEscolhida(Defs.ADMIN_LINKS);

            switch (opcao) {

                case 1:
                    AcademicYearUI.menu();
                    break;

                case 2:
                    CourseUI.menu();
                    break;

                case 3:
                    SubjectUI.menu();
                    break;
                case 4:
                    TeacherUI.menu();
                    break;
                case 5:
                    CoordinatorUI.menu();
                    break;
                case 6:
                    StudentUI.menu();
                    break;
                case 7:
                    FaultDescriptionUI.menu();
                    break;

                case 8:
                    mainMenu();
                    break;
            }
        }
    }

    public static void coordenacaoMenu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Coordenação*****************\n");

            int opcao = Listas.enviarLerOpcaoEscolhida(Defs.COORDENATION_LINKS);

            switch (opcao) {

                case 1:
                    // TurmaUI.menu();
                    break;

                case 6:
                    adminMenu();
                    break;
                case 10:
                    System.out.println("Programa terminado");
                    System.exit(0);
                    break;
            }
        }
    }

}
