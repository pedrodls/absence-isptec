package courseSubject;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Scanner;

import classroom.ClassroomEntity;
import classroom.ClassroomPersistenceEntity;
import utils.Defs;

public class CourseSubjectPersistenceEntity {

    private static HashMap<Integer, CourseSubjectEntity> hashData = new HashMap<>();

    private static HashMap<Integer, Long> hashPositions = new HashMap<>();

    public CourseSubjectPersistenceEntity() {

    }

    public static void fillHashTable() {

        List<CourseSubjectEntity> data = findAll();

        if (data == null) {
            return;
        }

        hashData.clear();

        for (CourseSubjectEntity datum : data)
            hashData.put(datum.getId(), datum);

    }

    public static void create(CourseSubjectEntity entity) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.CURSO_ANO_DISCIPLINA_FILE, "rw");

            fillHashTable();

            long position = file.length();

            file.seek(position);

            int newId = hashPositions.size() + 1;

            if (findOne(newId) != null)
                newId++;

            file.writeInt(newId);
            file.writeInt(entity.getCourseId());
            file.writeInt(entity.getLevel());
            file.writeInt(entity.getSubjectId());

            file.close();

            System.out.println("\nCriado com sucesso!\n");

        } catch (Exception e) {
            System.out.println("\nErro ao criar!\n");
        }
    }

    public static void update(CourseSubjectEntity entity) {
        try {

            RandomAccessFile file = new RandomAccessFile(Defs.CURSO_ANO_DISCIPLINA_FILE, "rw");

            fillHashTable();

            long position = hashPositions.get(entity.getId());

            file.seek(position);

            file.writeInt(entity.getId());
            file.writeInt(entity.getCourseId());
            file.writeInt(entity.getLevel());
            file.writeInt(entity.getSubjectId());

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

            RandomAccessFile file = new RandomAccessFile(Defs.CURSO_ANO_DISCIPLINA_FILE, "rw");

            fillHashTable();

            Long position = hashPositions.get(id);

            file.seek(position);

            file.writeLong(-1); // ID -1 indica registro deletado

            file.close();

        } catch (Exception ex) {
            System.out.println("Erro ao eliminar dado");
        }
    }

    public static CourseSubjectEntity findOne(int id) {

        fillHashTable();

        return hashData.get(id);
    }

    public static List<CourseSubjectEntity> findAll() {

        List<CourseSubjectEntity> data = new ArrayList<CourseSubjectEntity>();

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.CURSO_ANO_DISCIPLINA_FILE, "r");

            if (file != null) {

                hashPositions.clear();

                file.seek(0);

                long position = file.getFilePointer();

                while (position < file.length()) {

                    Integer id = file.readInt();

                    Integer courseId = file.readInt();

                    Integer teacherId = file.readInt();

                    Integer level = file.readInt();

                    hashPositions.put(id, position);

                    if (id > 0)
                        data.add(new CourseSubjectEntity(id, courseId, teacherId, level));

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

    public static List<CourseSubjectEntity> findAllBylevel(int id) {

        List<CourseSubjectEntity> data = new ArrayList<CourseSubjectEntity>();

        fillHashTable();

        hashData.forEach((k, c) -> {
            if (c.getLevel() == id) {
                data.add(c);
            }
        });

        return data;

    }

    public static List<CourseSubjectEntity> findAllByCourseId(int id) {

        List<CourseSubjectEntity> data = new ArrayList<CourseSubjectEntity>();

        fillHashTable();

        hashData.forEach((k, c) -> {
            if (c.getCourseId() == id) {
                data.add(c);
            }
        });

        return data;

    }

    public static CourseSubjectEntity searchToEdit() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        int id = sc.nextInt();

        return CourseSubjectPersistenceEntity.findOne(id);

    }

}
