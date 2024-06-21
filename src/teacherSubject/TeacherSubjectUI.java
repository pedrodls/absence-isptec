package teacherSubject;

import java.util.List;
import java.util.Scanner;

import classroom.ClassroomEntity;
import classroom.ClassroomPersistenceEntity;
import clearBuffer.ClearBuffer;
import courseSubject.CourseSubjectEntity;
import courseSubject.CourseSubjectPersistenceEntity;
import genericEntity.GenericEntity;
import genericEntity.GenericPersistenceEntity;
import isptec.listas.Listas;
import isptec.utils.Utils;
import utils.*;

public class TeacherSubjectUI {

    public TeacherSubjectUI() {

    }

    public static void menu() {
        for (;;) {

            ClearBuffer.clear();

            System.out.println("\n*****************Menu Professor -> Disciplina****************\n");

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
                    MainMenu.coordenationMenu();
                    break;
            }
        }
    }

    public static boolean validate() {
        if (!(GenericPersistenceEntity.findAll(Defs.PROFESSOR_FILE).size() > 0
                && CourseSubjectPersistenceEntity.findAll().size() > 0
                && ClassroomPersistenceEntity.findAll().size() > 0)) {
            System.out.print("Não existem dados suficientes[Professor, Disciplina, Turma]\n\n");

            MainMenu.pauseToSee();
            return false;
        }

        return true;
    }

    public static void create() {

        Scanner sc = new Scanner(System.in);

        boolean inValidate = false;

        GenericEntity teacher = null;
        CourseSubjectEntity courseSubject = null;
        ClassroomEntity classroom = null;

        System.out.println("\n*****************Adicionando Professor -> Disciplina ****************\n");

        if (!validate())
            return;

        do {

            System.out.print("Regra_validação: Professor existente! ");
            teacher = GenericPersistenceEntity.searchToEdit(Defs.PROFESSOR_FILE);

        } while (teacher == null);

        do {

            System.out.print("Regra_validação: Curso <-> Disciplina existente! ");
            courseSubject = CourseSubjectPersistenceEntity.searchToEdit();

        } while (courseSubject == null);

        do {

            System.out.print("Regra_validação: Turma existente! ");
            classroom = ClassroomPersistenceEntity.searchToEdit();

            if (classroom != null) {
                if (classroom.getCourseId() != courseSubject.getCourseId()
                        || classroom.getLevel() != courseSubject.getLevel()) {

                    System.out.println("\nTurma -> "+ classroom);
                    System.out.println("Dados de entrada -> " + courseSubject );

                    System.out.println("\nEsta turma não pertence à este curso nem à este nivel, deseja continuar?\n");

                    if (!Utils.concorda()) {
                        inValidate = true;
                        break;
                    }

                    classroom = null;
                }
            }

        } while (classroom == null);

        if (inValidate)
            return;

        TeacherSubjectEntity entity = new TeacherSubjectEntity(-1, teacher.getId(), classroom.getId(),
                courseSubject.getId());

        TeacherSubjectPersistenceEntity.create(entity);

        MainMenu.pauseToSee();

    }

    public static void edit() {

        TeacherSubjectEntity old = searchToEdit();

        if (old == null) {

            System.out.println("\nNão encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        CourseSubjectEntity courseSubject = CourseSubjectPersistenceEntity.findOne(old.getCourseSubjectId());

        Boolean edited = false;
        boolean inValidate = false;

        System.out.println("\n*****************Editando Professor -> Disciplina****************\n");

        if (Utils.editarCampo("Professor",
                GenericPersistenceEntity.findOne(old.getTeacherId(), Defs.PROFESSOR_FILE).getName())) {

            GenericEntity teacher = null;

            do {

                System.out.print("Regra_validação: Professor existente! ");
                teacher = GenericPersistenceEntity.searchToEdit(Defs.PROFESSOR_FILE);

            } while (teacher == null);

            old.setTeacherId(teacher.getId());

            edited = true;
        }

        if (Utils.editarCampo("Curso <-> Disciplina",
                CourseSubjectPersistenceEntity.findOne(old.getCourseSubjectId()).toString())) {

            do {

                System.out.print("Regra_validação: Curso <-> Disciplina existente! ");
                courseSubject = CourseSubjectPersistenceEntity.searchToEdit();

            } while (courseSubject == null);

            old.setCourseSubjectId(courseSubject.getCourseId());

            edited = true;
        }

        if (Utils.editarCampo("Turma", ClassroomPersistenceEntity.findOne(old.getClassroomId()).toString())) {

            ClassroomEntity classroom = null;

            do {

                System.out.print("Regra_validação: Turma existente! ");
                classroom = ClassroomPersistenceEntity.searchToEdit();
    
                if (classroom != null) {
                    if (classroom.getCourseId() != courseSubject.getCourseId()
                            || classroom.getLevel() != courseSubject.getLevel()) {
    
                        System.out.println("\nTurma -> "+ classroom);
                        System.out.println("Dados de entrada -> " + courseSubject );
    
                        System.out.println("\nEsta turma não pertence à este curso nem à este nivel, deseja continuar?\n");
    
                        if (!Utils.concorda()) {
                            inValidate = true;
                            break;
                        }
    
                        classroom = null;
                    }
                }
    
            } while (classroom == null);

            if(inValidate){
                return;
            }

            old.setClassroomId(classroom.getId());

            edited = true;
        }

        if (edited)
            TeacherSubjectPersistenceEntity.update(old);

        System.out.println("\nEdição finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void drop() {

        System.out.println("\n*****************Eliminando Professor -> Disciplina****************\n");

        TeacherSubjectEntity entity = searchToEdit();

        if (entity == null) {

            System.out.println("\nNão encontrado!\n");

            MainMenu.pauseToSee();

            return;
        }

        TeacherSubjectPersistenceEntity.dropOne(entity.getId());

        System.out.println("\nEliminação finalizada!\n");

        MainMenu.pauseToSee();

    }

    public static void search() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n*****************Procurando Professor -> Disciplina****************\n");

        System.out.print("ID: ");
        int id = sc.nextInt();

        TeacherSubjectPersistenceEntity.read(id);

        MainMenu.pauseToSee();

    }

    public static TeacherSubjectEntity searchToEdit() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        int id = sc.nextInt();

        return TeacherSubjectPersistenceEntity.findOne(id);

    }

    public static void showlistData() {

        List<TeacherSubjectEntity> data = TeacherSubjectPersistenceEntity.findAll();

        System.out.println("\n*****************Todos Professor -> Disciplina*****************\n");

        for (TeacherSubjectEntity datum : data)
            System.out.println("\n" + datum + "\n");

        MainMenu.pauseToSee();

    }

}
