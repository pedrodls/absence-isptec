package teacherSubject;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Scanner;

import classroomStudent.ClassroomStudentEntity;
import courseSubject.CourseSubjectPersistenceEntity;
import utils.Defs;

public class TeacherSubjectPersistenceEntity {

    private static HashMap<Integer, TeacherSubjectEntity> hashData = new HashMap<>();

    private static HashMap<Integer, Long> hashPositions = new HashMap<>();

    public TeacherSubjectPersistenceEntity() {

    }

    public static void fillHashTable() {

        List<TeacherSubjectEntity> data = findAll();

        if (data == null) {
            return;
        }

        hashData.clear();

        for (TeacherSubjectEntity datum : data)
            hashData.put(datum.getId(), datum);

    }

    public static void create(TeacherSubjectEntity entity) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.PROFESSOR_DISCIPLINA_FILE, "rw");

            fillHashTable();

            long position = file.length();

            file.seek(position);

            int newId = hashPositions.size() + 1;

            if (findOne(newId) != null)
                newId++;

            file.writeInt(newId);
            file.writeInt(entity.getTeacherId());
            file.writeInt(entity.getClassroomId());
            file.writeInt(entity.getCourseSubjectId());

            file.close();

            System.out.println("\nCriado com sucesso!\n");

        } catch (Exception e) {
            System.out.println("\nErro ao criar!\n");
        }
    }

    public static void update(TeacherSubjectEntity entity) {
        try {

            RandomAccessFile file = new RandomAccessFile(Defs.PROFESSOR_DISCIPLINA_FILE, "rw");

            fillHashTable();

            long position = hashPositions.get(entity.getId());

            file.seek(position);

            file.writeInt(entity.getId());
            file.writeInt(entity.getTeacherId());
            file.writeInt(entity.getClassroomId());
            file.writeInt(entity.getCourseSubjectId());


            file.close();

            System.out.println("\nEditado com sucesso!\n");

        } catch (Exception ex) {
            System.out.println("Erro ao atualizar  dados");
        }
    }

    public static void read(int id) {
        try {

            fillHashTable();

            if (hashData.get(id) != null) {
                System.out.println("Encontrado -> " + hashData.get(id));
            } else {
                System.out.println("Não encontrado!");
            }

        } catch (Exception ex) {
            System.out.println("Erro!");
        }
    }

    public static void dropOne(int id) {
        try {

            RandomAccessFile file = new RandomAccessFile(Defs.PROFESSOR_DISCIPLINA_FILE, "rw");

            fillHashTable();

            Long position = hashPositions.get(id);

            file.seek(position);

            file.writeLong(-1); // ID -1 indica registro deletado

            file.close();

        } catch (Exception ex) {
            System.out.println("Erro ao eliminar dado");
        }
    }

    public static TeacherSubjectEntity findOne(int id) {

        fillHashTable();

        return hashData.get(id);
    }

    public static List<TeacherSubjectEntity> findAll() {

        List<TeacherSubjectEntity> data = new ArrayList<TeacherSubjectEntity>();

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.PROFESSOR_DISCIPLINA_FILE, "r");

            if (file != null) {

                hashPositions.clear();

                file.seek(0);

                long position = file.getFilePointer();

                while (position < file.length()) {

                    Integer id = file.readInt();

                    Integer teacherId = file.readInt();

                    Integer classroomId = file.readInt();

                    Integer courseSubjectId = file.readInt();

                    hashPositions.put(id, position);

                    if (id > 0)
                        data.add(new TeacherSubjectEntity(id, teacherId, classroomId, courseSubjectId));

                    position = file.getFilePointer();

                }

                file.close();

            }

        } catch (Exception ex) {
            data = null;

            System.out.println("\nÉ ncessário criar dados!");
        }

        return data;

    }

    public static List<TeacherSubjectEntity> findAllByTeacherId(int id) {

        List<TeacherSubjectEntity> data = new ArrayList<TeacherSubjectEntity>();

        fillHashTable();

        hashData.forEach((k, c) -> {
            if (c.getTeacherId() == id) {
                data.add(c);
            }
        });

        return data;

    }

    public static List<TeacherSubjectEntity> printAllByTeacherId(int id) {

        List<TeacherSubjectEntity> data = new ArrayList<TeacherSubjectEntity>();

        fillHashTable();

        hashData.forEach((k, c) -> {
            if (c.getTeacherId() == id) {
                System.out.println(c.toString());
                System.out.println("-----------------------------------------------------------");
            }
        });

        return data;

    }

    public static List<TeacherSubjectEntity> findAllByClassroomId(int id) {

        List<TeacherSubjectEntity> data = new ArrayList<TeacherSubjectEntity>();

        fillHashTable();

        hashData.forEach((k, c) -> {
            if (c.getClassroomId() == id) {
                data.add(c);
            }
        });

        return data;
    }


    public static TeacherSubjectEntity searchToEdit() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        int id = sc.nextInt();

        return TeacherSubjectPersistenceEntity.findOne(id);

    }

    
}