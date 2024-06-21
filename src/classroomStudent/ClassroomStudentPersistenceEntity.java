package classroomStudent;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

import isptec.utils.FileUtils;
import utils.Defs;

public class ClassroomStudentPersistenceEntity {

    private static HashMap<Integer, ClassroomStudentEntity> hashData = new HashMap<>();

    private static HashMap<Integer, Long> hashPositions = new HashMap<>();

    public ClassroomStudentPersistenceEntity() {

    }

    public static void fillHashTable() {

        List<ClassroomStudentEntity> data = findAll();

        if (data == null) {
            return;
        }

        hashData.clear();

        for (ClassroomStudentEntity datum : data)
            hashData.put(datum.getId(), datum);

    }

    public static void create(ClassroomStudentEntity entity) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.TURMA_ESTUDANTE_FILE, "rw");

            fillHashTable();

            long position = file.length();

            file.seek(position);

            int newId = hashPositions.size() + 1;

            if (findOne(newId) != null)
                newId++;

            file.writeInt(newId);
            file.writeInt(entity.getClassroomId());
            file.writeInt(entity.getStudentId());

            file.close();

            System.out.println("\nCriado com sucesso!\n");

        } catch (Exception e) {
            System.out.println("\nErro ao criar!\n");
        }
    }

    public static void update(ClassroomStudentEntity entity) {
        try {

            RandomAccessFile file = new RandomAccessFile(Defs.TURMA_ESTUDANTE_FILE, "rw");

            fillHashTable();

            long position = hashPositions.get(entity.getId());

            file.seek(position);

            file.writeInt(entity.getId());
            file.writeInt(entity.getClassroomId());
            file.writeInt(entity.getStudentId());

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

            RandomAccessFile file = new RandomAccessFile(Defs.TURMA_ESTUDANTE_FILE, "rw");

            fillHashTable();

            Long position = hashPositions.get(id);

            file.seek(position);

            file.writeLong(-1); // ID -1 indica registro deletado

            file.close();

        } catch (Exception ex) {
            System.out.println("Erro ao eliminar dado");
        }
    }

    public static ClassroomStudentEntity findOne(int id) {

        fillHashTable();

        return hashData.get(id);
    }

    public static List<ClassroomStudentEntity> findAll() {

        List<ClassroomStudentEntity> data = new ArrayList<ClassroomStudentEntity>();

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.TURMA_ESTUDANTE_FILE, "r");

            if (file != null) {

                hashPositions.clear();

                file.seek(0);

                long position = file.getFilePointer();

                while (position < file.length()) {

                    Integer id = file.readInt();

                    Integer classroomId = file.readInt();

                    Integer studentId = file.readInt();

                    hashPositions.put(id, position);

                    if (id > 0)
                        data.add(new ClassroomStudentEntity(id, classroomId, studentId));

                    position = file.getFilePointer();

                }

                file.close();

            }

        } catch (Exception ex) {
            System.out.println("\nÉ ncessário criar dados!");
        }

        return data;

    }

    public static List<ClassroomStudentEntity> findAllByClassroomId(int id) {

        List<ClassroomStudentEntity> data = new ArrayList<ClassroomStudentEntity>();

        fillHashTable();

        hashData.forEach((k, c) -> {
            if (c.getClassroomId() == id) {
                data.add(c);
            }
        });

        return data;

    }

}
