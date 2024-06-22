package classroom;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Scanner;

import isptec.utils.FileUtils;
import student.StudentEntity;
import utils.Defs;

public class ClassroomPersistenceEntity {

    private static HashMap<Integer, ClassroomEntity> hashData = new HashMap<>();

    private static HashMap<Integer, Long> hashPositions = new HashMap<>();

    public ClassroomPersistenceEntity() {

    }

    public static void fillHashTable() {

        List<ClassroomEntity> data = findAll();

        if (data == null) {
            return;
        }

        hashData.clear();

        for (ClassroomEntity datum : data)
            hashData.put(datum.getId(), datum);

    }

    public static void create(ClassroomEntity entity) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.TURMA_FILE, "rw");

            fillHashTable();

            long position = file.length();

            file.seek(position);

            int newId = hashPositions.size() + 1;

            if (findOne(newId) != null)
                newId++;

            file.writeInt(newId);
            file.writeInt(entity.getCourseId());
            file.writeInt(entity.getLevel());
            file.writeInt(entity.getAcademicYearId());

            FileUtils.writeString(file, entity.getName(), Defs.NAME_SIZE);

            file.close();

            System.out.println("\nCriado com sucesso!\n");

        } catch (Exception e) {
            System.out.println("\nErro ao criar!\n");
        }
    }

    public static void update(ClassroomEntity entity) {
        try {

            RandomAccessFile file = new RandomAccessFile(Defs.TURMA_FILE, "rw");

            fillHashTable();

            long position = hashPositions.get(entity.getId());

            file.seek(position);

            file.writeInt(entity.getId());
            file.writeInt(entity.getCourseId());
            file.writeInt(entity.getLevel());
            file.writeInt(entity.getAcademicYearId());

            FileUtils.writeString(file, entity.getName(), Defs.NAME_SIZE);

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

            RandomAccessFile file = new RandomAccessFile(Defs.TURMA_FILE, "rw");

            fillHashTable();

            Long position = hashPositions.get(id);

            file.seek(position);

            file.writeLong(-1); // ID -1 indica registro deletado

            file.close();

        } catch (Exception ex) {
            System.out.println("Erro ao eliminar dado");
        }
    }

    public static ClassroomEntity findOne(int id) {

        fillHashTable();

        return hashData.get(id);
    }

    public static List<ClassroomEntity> findAll() {

        List<ClassroomEntity> data = new ArrayList<ClassroomEntity>();

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.TURMA_FILE, "r");

            if (file != null) {

                hashPositions.clear();

                file.seek(0);

                long position = file.getFilePointer();

                while (position < file.length()) {

                    Integer id = file.readInt();

                    Integer courseId = file.readInt();

                    Integer level = file.readInt();

                    Integer academicYearId = file.readInt();

                    String name = FileUtils.readString(file, Defs.NAME_SIZE);

                    hashPositions.put(id, position);

                    if (id > 0)
                        data.add(new ClassroomEntity(id, name, courseId, level,academicYearId));

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

    public static List<ClassroomEntity> findAllByCourseId(int id) {

        List<ClassroomEntity> data = new ArrayList<ClassroomEntity>();

        fillHashTable();

        hashData.forEach((k, c) -> {
            if (c.getCourseId() == id) {
                data.add(c);
            }
        });

        return data;

    }

    public static ClassroomEntity searchToEdit() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        int id = sc.nextInt();

        return ClassroomPersistenceEntity.findOne(id);

    }

}
