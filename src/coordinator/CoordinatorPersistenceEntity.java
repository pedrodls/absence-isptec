package coordinator;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

import isptec.utils.FileUtils;
import utils.Defs;

public class CoordinatorPersistenceEntity {

    private static HashMap<Integer, CoordinatorEntity> hashData = new HashMap<>();

    private static HashMap<Integer, Long> hashPositions = new HashMap<>();

    public CoordinatorPersistenceEntity() {

    }

    public static void fillHashTable() {

        List<CoordinatorEntity> data = findAll();

        if (data == null) {
            return;
        }

        hashData.clear();

        for (CoordinatorEntity datum : data)
            hashData.put(datum.getId(), datum);

    }

    public static void create(CoordinatorEntity entity) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.COORDENADOR_FILE, "rw");

            fillHashTable();

            long position = file.length();

            file.seek(position);

            int newId = hashPositions.size() + 1;

            if (findOne(newId) != null)
                newId++;

            file.writeInt(newId);
            file.writeInt(entity.getCourseId());
            file.writeInt(entity.getTeacherId());
            file.writeInt(entity.getAcademicYearId());


            file.close();

            System.out.println("\nCriado com sucesso!\n");

        } catch (Exception e) {
            System.out.println("\nErro ao criar!\n");
        }
    }

    public static void update(CoordinatorEntity entity ) {
        try {

            RandomAccessFile file = new RandomAccessFile(Defs.COORDENADOR_FILE, "rw");

            fillHashTable();

            long position = hashPositions.get(entity.getId());

            file.seek(position);

            file.writeInt(entity.getId());
            file.writeInt(entity.getCourseId());
            file.writeInt(entity.getTeacherId());
            file.writeInt(entity.getAcademicYearId());

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

            RandomAccessFile file = new RandomAccessFile(Defs.COORDENADOR_FILE, "rw");

            fillHashTable();

            Long position = hashPositions.get(id);

            file.seek(position);

            file.writeLong(-1); // ID -1 indica registro deletado

            file.close();

        } catch (Exception ex) {
            System.out.println("Erro ao eliminar dado");
        }
    }

    public static CoordinatorEntity findOne(int id) {

        fillHashTable();

        return hashData.get(id);
    }

    public static List<CoordinatorEntity> findAll() {

        List<CoordinatorEntity> data = new ArrayList<CoordinatorEntity>();

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.COORDENADOR_FILE, "r");

            if (file != null) {

                hashPositions.clear();

                file.seek(0);

                long position = file.getFilePointer();

                while (position < file.length()) {

                    Integer id = file.readInt();

                    Integer courseId = file.readInt();

                    Integer teacherId = file.readInt();

                    Integer academicYearId = file.readInt();

                    hashPositions.put(id, position);

                    if (id > 0)
                        data.add(new CoordinatorEntity(id, courseId, teacherId, academicYearId));

                    position = file.getFilePointer();

                }

                file.close();

            }

        } catch (Exception ex) {
            System.out.println("\nÉ ncessário criar dados!");
        }

        return data;

    }

}
