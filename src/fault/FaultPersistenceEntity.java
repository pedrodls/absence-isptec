package fault;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

import classroomStudent.ClassroomStudentEntity;
import classroomStudent.ClassroomStudentPersistenceEntity;
import utils.Defs;
import utils.MainMenu;

public class FaultPersistenceEntity {

    private static HashMap<Integer, FaultEntity> hashData = new HashMap<>();

    private static HashMap<Integer, Long> hashPositions = new HashMap<>();

    private static boolean verify = false;

    public FaultPersistenceEntity() {

    }

    public static void fillHashTable() {

        List<FaultEntity> data = findAll();

        if (data == null) {
            return;
        }

        hashData.clear();

        for (FaultEntity datum : data)
            hashData.put(datum.getId(), datum);

    }

    public static void create(FaultEntity entity) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.FALTA_FILE, "rw");

            fillHashTable();

            long position = file.length();

            file.seek(position);

            int newId = hashPositions.size() + 1;

            if (findOne(newId) != null)
                newId++;

            file.writeInt(newId);
            file.writeInt(entity.getTeacherSubjectId());
            file.writeInt(entity.getClassroomStudentId());
            file.writeLong(entity.getCreatedAt());

            file.close();

            System.out.println("\nCriado com sucesso!\n");

        } catch (Exception e) {
            System.out.println("\nErro ao criar!\n");
        }
    }

    public static void update(FaultEntity entity) {
        try {

            RandomAccessFile file = new RandomAccessFile(Defs.FALTA_FILE, "rw");

            fillHashTable();

            long position = hashPositions.get(entity.getId());

            file.seek(position);

            file.writeInt(entity.getId());
            file.writeInt(entity.getTeacherSubjectId());
            file.writeInt(entity.getClassroomStudentId());
            file.writeLong(entity.getCreatedAt());

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

            RandomAccessFile file = new RandomAccessFile(Defs.FALTA_FILE, "rw");

            fillHashTable();

            Long position = hashPositions.get(id);

            file.seek(position);

            file.writeLong(-1); // ID -1 indica registro deletado

            file.close();

        } catch (Exception ex) {
            System.out.println("Erro ao eliminar dado");
        }
    }

    public static FaultEntity findOne(int id) {

        fillHashTable();

        return hashData.get(id);
    }

    public static List<FaultEntity> findAll() {

        List<FaultEntity> data = new ArrayList<FaultEntity>();

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.FALTA_FILE, "r");

            if (file != null) {

                hashPositions.clear();

                file.seek(0);

                long position = file.getFilePointer();

                while (position < file.length()) {

                    Integer id = file.readInt();

                    Integer teacherSubjectId = file.readInt();

                    Integer classroomStudentId = file.readInt();

                    long createdAt = file.readLong();

                    hashPositions.put(id, position);

                    if (id > 0)
                        data.add(new FaultEntity(id, teacherSubjectId, classroomStudentId, createdAt));

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

    public static boolean verifyFault(long startedDate, long endDate, int studentId) {

        fillHashTable();

        hashData.forEach((k, c) -> {
            if (c.getCreatedAt() >= startedDate && c.getCreatedAt() <= endDate
                    && ClassroomStudentPersistenceEntity.findOne(c.getClassroomStudentId()).getStudentId() == studentId)
                verify = true;
            return;
        });

        return verify;

    }

    public static void getFaultsFromStudent(int studentId) {

        fillHashTable();

        hashData.forEach((k, c) -> {
            ClassroomStudentEntity entity = ClassroomStudentPersistenceEntity.findOne(c.getClassroomStudentId());

            if (entity != null) {
                if (entity.getStudentId() == studentId)
                    System.out.println(c.toString());
                System.out.println("\n------------------------------------------------------\n");
            }
        });


    }
}