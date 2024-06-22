package justification;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

import isptec.utils.DataUtils;
import isptec.utils.FileUtils;
import utils.Defs;

public class JustificationPersistenceEntity {

    private static HashMap<Integer, JustificationEntity> hashData = new HashMap<>();

    private static HashMap<Integer, Long> hashPositions = new HashMap<>();

    public JustificationPersistenceEntity() {

    }

    public static void fillHashTable() {

        List<JustificationEntity> data = findAll();

        if (data == null) {
            return;
        }

        hashData.clear();

        for (JustificationEntity datum : data)
            hashData.put(datum.getId(), datum);

    }

    public static void create(JustificationEntity entity) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.JUSTIFICATIVO_FILE, "rw");

            fillHashTable();

            long position = file.length();

            file.seek(position);

            int newId = hashPositions.size() + 1;

            if (findOne(newId) != null)
                newId++;

            file.writeInt(newId);

            file.writeInt(entity.getTestType());
            file.writeInt(entity.getDispatchType());

            FileUtils.writeString(file, entity.getTopic(), Defs.NAME_SIZE);

            file.writeInt(entity.getCourseId());
            file.writeInt(entity.getStudentId());
            file.writeInt(entity.getEmployeeId());
            file.writeInt(entity.getClassroomId());
            file.writeInt(entity.getFaultDescriptionId());

            file.writeLong(entity.getEndAt());
            file.writeLong(DataUtils.dateNow());
            file.writeLong(entity.getStartedAt());
            file.writeLong(entity.getDispatchDate());

            file.close();

            System.out.println("\nCriado com sucesso!\n");

        } catch (Exception e) {
            System.out.println("\nErro ao criar!\n");
        }
    }

    public static void update(JustificationEntity entity) {
        try {

            RandomAccessFile file = new RandomAccessFile(Defs.JUSTIFICATIVO_FILE, "rw");

            fillHashTable();

            long position = hashPositions.get(entity.getId());

            file.seek(position);

            file.writeInt(entity.getId());

            file.writeInt(entity.getTestType());
            file.writeInt(entity.getDispatchType());

            FileUtils.writeString(file, entity.getTopic(), Defs.NAME_SIZE);

            file.writeInt(entity.getCourseId());
            file.writeInt(entity.getClassroomId());
            file.writeInt(entity.getStudentId());
            file.writeInt(entity.getEmployeeId());

            file.writeInt(entity.getFaultDescriptionId());

            file.writeLong(entity.getEndAt());
            file.writeLong(entity.getCreatedAt());
            file.writeLong(entity.getStartedAt());
            file.writeLong(entity.getDispatchDate());

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

            RandomAccessFile file = new RandomAccessFile(Defs.JUSTIFICATIVO_FILE, "rw");

            fillHashTable();

            Long position = hashPositions.get(id);

            file.seek(position);

            file.writeLong(-1); // ID -1 indica registro deletado

            file.close();

        } catch (Exception ex) {
            System.out.println("Erro ao eliminar dado");
        }
    }

    public static JustificationEntity findOne(int id) {

        fillHashTable();

        return hashData.get(id);
    }

    public static List<JustificationEntity> findAll() {

        List<JustificationEntity> data = new ArrayList<JustificationEntity>();

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.JUSTIFICATIVO_FILE, "rw");

            if (file != null) {

                hashPositions.clear();

                file.seek(0);

                long position = file.getFilePointer();

                while (position < file.length()) {

                    int id = file.readInt();
                    int testType = file.readInt();
                    int dispatchType = file.readInt();

                    String topic = FileUtils.readString(file, Defs.NAME_SIZE);

                    int courseId = file.readInt();
                    int studentId = file.readInt();
                    int employeeId = file.readInt();
                    int classroomId = file.readInt();
                    int faultDescriptionId = file.readInt();

                    long endAt = file.readLong();
                    long createdAt = file.readLong();
                    long startedAt = file.readLong();
                    long dispatchDate = file.readLong();

                    hashPositions.put(id, position);

                    if (id > 0)
                        data.add(new JustificationEntity(
                                id,
                                testType,
                                dispatchType,
                                topic,
                                courseId,
                                classroomId,
                                studentId,
                                employeeId,
                                faultDescriptionId,
                                endAt,
                                createdAt,
                                startedAt,
                                dispatchDate));

                    position = file.getFilePointer();

                }

                file.close();

            }

        } catch (Exception ex) {

            data = null;
            System.out.println("\nÉ ncessário criar dados!" + ex);

        }

        return data;

    }

}