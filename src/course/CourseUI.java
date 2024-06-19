package course;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import clearBuffer.ClearBuffer;
import coordenacao.CoordenacaoPersistente;
import coordenador.CoordenadorPersistente;
import estudante.EstudantePersistente;
import isptec.listas.Listas;
import isptec.utils.Utils;
import turma.TurmaPersistente;
import utils.*;

public class CourseUI {

    public CourseUI() {

    }

    public static void menu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Curso****************\n");

            int opcao = Listas.enviarLerOpcaoEscolhida(Defs.CRUD_LINKS);

            switch (opcao) {
                case 1:
                    create();
                    break;

                case 2:
                    list();
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

    public static void create() {

        Scanner sc = new Scanner(System.in);
        String nome;

        System.out.println("\n*****************Criando Curso****************\n");

        do {

            System.out.print("Regra_validação: no mínimo 3 caracters!");
            MainMenu.pauseToSee();

            System.out.print("Nome: ");
            nome = sc.nextLine();

        } while (nome.length() < 3);

        
        try {
            
            CoursePersistence entity = new CoursePersistence();
            
            Course newCurso = new Course(entity.getNextId(), nome);

            entity.create(newCurso);

            System.out.println("\nCriado com sucesso!\n");

            MainMenu.pauseToSee();

        } catch (Exception e) {
            System.out.println("\nErro ao criar\n");
        }

    }

    public static void edit() {

        Scanner sc = new Scanner(System.in);

        Course old = searchToEdit();

        if (old == null) {

            System.out.println("\nCurso não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        String nome = old.getName();

        System.out.println("\n*****************Editando Curso****************\n");

        if (Utils.editarCampo("Nome", old.getName())) {

            do {

                System.out.print("\nRegra_validação: no mínimo 3 caracters! ");
                MainMenu.pauseToSee();

                System.out.print("\nNome: ");
                nome = sc.nextLine();

            } while (nome.length() < 3);

            old.setName(nome);

        }

        try {

            CoursePersistence entity = new CoursePersistence();

            entity.update(old);

        } catch (Exception e) {
            System.out.println("\nErro ao atualizar\n");
        }

        System.out.println("\nEdição finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void drop() {

        System.out.println("\n*****************Eliminando Curso****************\n");

        Course old = searchToEdit();

        if (old == null) {

            System.out.println("\nCurso não encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        if (EstudantePersistente.findAllByCursoId(old.getId()).size() > 0
                || CoordenadorPersistente.findAllByCursoId(old.getId()).size() > 0
                || CoordenacaoPersistente.findAllByCursoId(old.getId()).size() > 0
                || TurmaPersistente.findAllByCursoId(old.getId()).size() > 0) {

            System.out.println("\nCurso não pode ser apagado pois existem dados ligados ao mesmo!\n");

            MainMenu.pauseToSee();

            return;
        }

        // CursoPersistente.dropOne(old);

        System.out.println("\nEliminação finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void search() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n*****************Procurando Curso****************\n");

        System.out.print("ID: ");
        long id = sc.nextLong();

        try {
            Course found = new CoursePersistence().read(id);

            if (found == null) {
                System.out.println("Não encontrado");
                return;
            }

            System.out.println(found.toString());

        } catch (Exception e) {
            System.out.println("Não encontrado");
            ;
        }

        MainMenu.pauseToSee();

    }

    public static Course searchToEdit() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        long id = sc.nextLong();

        try {
            return new CoursePersistence().read(id);
        } catch (Exception e) {
            return null;
        }

    }

    public static void list() {

        System.out.println("\n*****************Todos Cursos*****************\n");

        try {

            CoursePersistence entity = new CoursePersistence();

            if (entity.getCoursesIndexes().size() == 0) 
                return;

            for (Course pr : entity.getCoursesIndexes().values())
                System.out.println("\n" + pr.toString() + "\n");

        } catch (Exception e) {
            System.out.println("Erro ao carregar dados!" + e);
        }

        MainMenu.pauseToSee();

    }

}
