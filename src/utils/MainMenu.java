package utils;

import java.util.Scanner;

import academicYear.AcademicYearUI;
import classroom.ClassroomUI;
import classroomStudent.ClassroomStudentUI;
import clearBuffer.ClearBuffer;
import coordinator.CoordinatorUI;
import course.CourseUI;
import courseSubject.CourseSubjectUI;
import faultDescription.FaultDescriptionUI;
import isptec.listas.Listas;
import student.StudentUI;
import subject.SubjectUI;
import teacher.TeacherUI;
import teacherSubject.TeacherSubjectUI;

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
                    coordenationMenu();
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
                    try {
                        AcademicYearUI.menu();
                    } catch (Exception e) {
                        System.out.println("Ocorreu um erro inesperado, verifique bem as validações de campos");
                        MainMenu.pauseToSee();
                    }
                    break;

                case 2:
                    try {
                        CourseUI.menu();
                    } catch (Exception e) {
                        System.out.println("Ocorreu um erro inesperado, verifique bem as validações de campos");
                        MainMenu.pauseToSee();
                    }
                    break;

                case 3:
                    try {
                        SubjectUI.menu();
                    } catch (Exception e) {
                        System.out.println("Ocorreu um erro inesperado, verifique bem as validações de campos");
                        MainMenu.pauseToSee();
                    }
                    break;
                case 4:
                    try {
                        TeacherUI.menu();
                    } catch (Exception e) {
                        System.out.println("Ocorreu um erro inesperado, verifique bem as validações de campos");
                        MainMenu.pauseToSee();
                    }
                    break;
                case 5:
                    try {
                        CoordinatorUI.menu();
                    } catch (Exception e) {
                        System.out.println("Ocorreu um erro inesperado, verifique bem as validações de campos");
                        MainMenu.pauseToSee();
                    }
                    break;
                case 6:
                    try {
                        StudentUI.menu();
                    } catch (Exception e) {
                        System.out.println("Ocorreu um erro inesperado, verifique bem as validações de campos");
                        MainMenu.pauseToSee();
                    }
                    break;
                case 7:
                    try {
                        FaultDescriptionUI.menu();
                    } catch (Exception e) {
                        System.out.println("Ocorreu um erro inesperado, verifique bem as validações de campos");
                        MainMenu.pauseToSee();
                    }
                    break;

                case 8:
                    mainMenu();
                    break;
            }
        }
    }

    public static void coordenationMenu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Coordenação*****************\n");

            int opcao = Listas.enviarLerOpcaoEscolhida(Defs.COORDENATION_LINKS);

            switch (opcao) {

                case 1:
                    try {
                        ClassroomUI.menu();
                    } catch (Exception e) {
                        System.out.println("Ocorreu um erro inesperado, verifique bem as validações de campos");
                        MainMenu.pauseToSee();
                    }
                    break;

                case 2:
                    try {
                        ClassroomStudentUI.menu();
                    } catch (Exception e) {
                        System.out.println("Ocorreu um erro inesperado, verifique bem as validações de campos");
                        MainMenu.pauseToSee();
                    }
                    break;

                case 3:
                    try {
                        CourseSubjectUI.menu();
                    } catch (Exception e) {
                        System.out.println("Ocorreu um erro inesperado, verifique bem as validações de campos");
                        MainMenu.pauseToSee();
                    }
                    break;
                case 4:
                    try {
                        TeacherSubjectUI.menu();
                    } catch (Exception e) {
                        System.out.println("Ocorreu um erro inesperado, verifique bem as validações de campos");
                        MainMenu.pauseToSee();
                    }
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
