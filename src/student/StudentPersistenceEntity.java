package student;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Scanner;

import genericEntity.GenericEntity;
import genericEntity.GenericPersistenceEntity;
import isptec.utils.FileUtils;
import utils.Defs;

public class StudentPersistenceEntity {

    private static HashMap<Integer, StudentEntity> hashData = new HashMap<>();

    private static HashMap<Integer, Long> hashPositions = new HashMap<>();

    public StudentPersistenceEntity() {

    }

    public static void fillHashTable() {

        List<StudentEntity> data = findAll();

        if (data == null) {
            return;
        }

        hashData.clear();

        for (StudentEntity datum : data)
            hashData.put(datum.getId(), datum);

    }

    public static void create(StudentEntity entity) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.ESTUDANTE_FILE, "rw");

            fillHashTable();

            long position = file.length();

            file.seek(position);

            int newId = hashPositions.size() + 1;

            if (findOne(newId) != null)
                newId++;

            String email = GenericPersistenceEntity.findOne(entity.getAccessedYearId(), Defs.ANO_ACADEMICO_FILE)
                    .getName()
                    .replace("/", "-") + newId + entity.getEmail();

            file.writeInt(newId);
            file.writeInt(entity.getCourseId());
            file.writeInt(entity.getAccessedYearId());

            FileUtils.writeString(file, entity.getName(), Defs.NAME_SIZE);
            FileUtils.writeString(file, email, Defs.EMAIL_SIZE);
            FileUtils.writeString(file, entity.getTelephone(), Defs.TELEPHONE_SIZE);

            file.close();

            System.out.println("\nCriado com sucesso!\n");

        } catch (Exception e) {
            System.out.println("\nErro ao criar!\n");
        }
    }

    public static void update(StudentEntity entity) {
        try {

            RandomAccessFile file = new RandomAccessFile(Defs.ESTUDANTE_FILE, "rw");

            fillHashTable();

            long position = hashPositions.get(entity.getId());

            file.seek(position);

            file.writeInt(entity.getId());
            file.writeInt(entity.getCourseId());
            file.writeInt(entity.getAccessedYearId());

            FileUtils.writeString(file, entity.getName(), Defs.NAME_SIZE);
            FileUtils.writeString(file, entity.getEmail(), Defs.EMAIL_SIZE);
            FileUtils.writeString(file, entity.getTelephone(), Defs.TELEPHONE_SIZE);

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
                System.out.println("\nEncontrado -> " + hashData.get(id)+"\n");
            } else {
                System.out.println("Não encontrado!");
            }

        } catch (Exception ex) {
            System.out.println("Erro!");
        }
    }

    public static void dropOne(int id) {
        try {

            RandomAccessFile file = new RandomAccessFile(Defs.ESTUDANTE_FILE, "rw");

            fillHashTable();

            Long position = hashPositions.get(id);

            file.seek(position);

            file.writeLong(-1); // ID -1 indica registro deletado

            file.close();

        } catch (Exception ex) {
            System.out.println("Erro ao eliminar dado");
        }
    }

    public static StudentEntity findOne(int id) {

        fillHashTable();

        return hashData.get(id);
    }

    public static List<StudentEntity> findAll() {

        List<StudentEntity> data = new ArrayList<StudentEntity>();

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.ESTUDANTE_FILE, "r");

            if (file != null) {

                hashPositions.clear();

                file.seek(0);

                long position = file.getFilePointer();

                while (position < file.length()) {

                    Integer id = file.readInt();

                    Integer courseId = file.readInt();

                    Integer accessedYearId = file.readInt();

                    String name = FileUtils.readString(file, Defs.NAME_SIZE);

                    String email = FileUtils.readString(file, Defs.EMAIL_SIZE);

                    String telephone = FileUtils.readString(file, Defs.TELEPHONE_SIZE);

                    hashPositions.put(id, position);

                    if (id > 0)
                        data.add(new StudentEntity(id, name, email, telephone, courseId, accessedYearId));

                    position = file.getFilePointer();

                }

                file.close();

            }

        } catch (Exception ex) {
            System.out.println("\nÉ ncessário criar dados!");
        }

        return data;

    }

    public static List<StudentEntity> findAllByAccessedYearId(int id) {

        List<StudentEntity> data = new ArrayList<StudentEntity>();

        fillHashTable();

        hashData.forEach((k, c) -> {
            if (c.getAccessedYearId() == id) {
                data.add(c);
            }
        });

        return data;

    }

    public static StudentEntity searchToEdit() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        int id = sc.nextInt();

        return StudentPersistenceEntity.findOne(id);

    }

    public static List<StudentEntity> findAllByCourseId(int id) {

        List<StudentEntity> data = new ArrayList<StudentEntity>();

        fillHashTable();

        hashData.forEach((k, c) -> {
            if (c.getCourseId() == id) {
                data.add(c);
            }
        });

        return data;

    }

}
