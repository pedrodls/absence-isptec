package coordinator;

import java.util.List;
import java.util.Scanner;

import clearBuffer.ClearBuffer;
import genericEntity.GenericEntity;
import genericEntity.GenericPersistenceEntity;
import isptec.listas.Listas;
import isptec.utils.Utils;
import utils.*;

public class CoordinatorUI {

    public CoordinatorUI() {

    }

    public static void menu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Coordenador****************\n");

            int opcao = Listas.enviarLerOpcaoEscolhida(Defs.CRUD_LINKS);

            switch (opcao) {
                case 1:
                    create();
                    break;

                case 2:
                    showlistData();
                    break;

                case 3:
                    edit();
                    break;

                case 4:
                    search();
                    break;

                case 5:
                    drop();
                    break;

                case 6:
                    MainMenu.adminMenu();
                    break;
            }
        }
    }

    public static boolean validate() {
        if (!(GenericPersistenceEntity.findAll(Defs.CURSO_FILE).size() > 0
                && GenericPersistenceEntity.findAll(Defs.ANO_ACADEMICO_FILE).size() > 0
                && GenericPersistenceEntity.findAll(Defs.PROFESSOR_FILE).size() > 0)) {
            System.out.print("Não existem dados suficientes[Curso, Ano Académico, Professor] para criar um Coordenador\n\n");

            MainMenu.pauseToSee();
            return false;
        }

        return true;
    }

    public static void create() {

        GenericEntity academicYear = null;
        GenericEntity course = null;
        GenericEntity teacher = null;

        System.out.println("\n*****************Criando Coordenador****************\n");

        if (!validate()) 
            return;
        

        do {

            System.out.print("Regra_validação: Ano academico existente! ");
            academicYear = GenericPersistenceEntity.searchToEdit(Defs.ANO_ACADEMICO_FILE);

        } while (academicYear == null);

        do {

            System.out.print("Regra_validação: Curso existente! ");
            course = GenericPersistenceEntity.searchToEdit(Defs.CURSO_FILE);

        } while (course == null);

        do {

            System.out.print("Regra_validação: Professor existente! ");
            teacher = GenericPersistenceEntity.searchToEdit(Defs.PROFESSOR_FILE);

        } while (teacher == null);

        CoordinatorEntity entity = new CoordinatorEntity(-1, course.getId(), teacher.getId(), academicYear.getId());

        CoordinatorPersistenceEntity.create(entity);

        MainMenu.pauseToSee();

    }

    public static void edit() {

        Scanner sc = new Scanner(System.in);

        CoordinatorEntity old = searchToEdit();

        Boolean edited = false;

        if (old == null) {

            System.out.println("\nNão encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        System.out.println("\n*****************Editando Coordenador****************\n");

        if (Utils.editarCampo("ID Curso", GenericPersistenceEntity.findOne(old.getCourseId(), Defs.CURSO_FILE).getName())) {

            GenericEntity course = null;

            do {

                System.out.print("Regra_validação: Curso existente! ");
                course = GenericPersistenceEntity.searchToEdit(Defs.CURSO_FILE);

            } while (course == null);

            old.setCourseId(course.getId());

            edited = true;
        }

        if (Utils.editarCampo("ID Ano Académico", GenericPersistenceEntity.findOne(old.getAcademicYearId(), Defs.ANO_ACADEMICO_FILE).getName())) {

            GenericEntity academicYear = null;

            do {

                System.out.print("Regra_validação: Ano academico existente! ");
                academicYear = GenericPersistenceEntity.searchToEdit(Defs.ANO_ACADEMICO_FILE);

            } while (academicYear == null);

            old.setAcademicYearId(academicYear.getId());

            edited = true;
        }

        if (edited)
            CoordinatorPersistenceEntity.update(old);

        System.out.println("\nEdição finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void drop() {

        System.out.println("\n*****************Eliminando Coordenador****************\n");

        CoordinatorEntity entity = searchToEdit();

        if (entity == null) {

            System.out.println("\nNão encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        CoordinatorPersistenceEntity.dropOne(entity.getId());

        System.out.println("\nEliminação finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void search() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n*****************Procurando Coordenador****************\n");

        System.out.print("ID: ");
        int id = sc.nextInt();

        CoordinatorPersistenceEntity.read(id);

        MainMenu.pauseToSee();

    }

    public static CoordinatorEntity searchToEdit() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        int id = sc.nextInt();

        return CoordinatorPersistenceEntity.findOne(id);

    }

    public static void showlistData() {

        List<CoordinatorEntity> data = CoordinatorPersistenceEntity.findAll();

        if (data == null) {
            MainMenu.pauseToSee();
            return;
        }
        
        System.out.println("\n*****************Todos Coordenadores*****************\n");

        for (CoordinatorEntity datum : data)
            System.out.println("\n" + datum + "\n");

        MainMenu.pauseToSee();

    }

}
