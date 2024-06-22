package genericEntity;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Scanner;

import isptec.utils.FileUtils;
import utils.Defs;

public class GenericPersistenceEntity {

    private static HashMap<Integer, GenericEntity> hashData = new HashMap<>();

    private static HashMap<Integer, Long> hashPositions = new HashMap<>();

    private static int cont = 0;

    public GenericPersistenceEntity() {

    }

    public static void fillHashTable(String fileName) {

        List<GenericEntity> data = findAll(fileName);

        if (data == null) {
            return;
        }

        hashData.clear();

        for (GenericEntity datum : data)
            hashData.put(datum.getId(), datum);

    }

    public static void create(GenericEntity entity, String fileName) {

        try {

            RandomAccessFile file = new RandomAccessFile(fileName, "rw");

            fillHashTable(fileName);

            long position = file.length();

            file.seek(position);

            int newId = hashPositions.size() + 1;

            if (findOne(newId, fileName) != null)
                newId++;

            file.writeInt(newId);

            FileUtils.writeString(file, entity.getName(), Defs.NAME_SIZE);

            file.close();

            System.out.println("\nCriado com sucesso!\n");

        } catch (Exception e) {
            System.out.println("\nErro ao criar!\n");
        }
    }

    public static void update(GenericEntity entity, String fileName) {
        try {

            RandomAccessFile file = new RandomAccessFile(fileName, "rw");

            fillHashTable(fileName);

            long position = hashPositions.get(entity.getId());

            file.seek(position);

            file.writeInt(entity.getId());

            FileUtils.writeString(file, entity.getName(), Defs.NAME_SIZE);

            file.close();

            System.out.println("\nEditado com sucesso!\n");

        } catch (Exception ex) {
            System.out.println("Erro ao atualizar  dados");
        }
    }

    public static void read(int id, String fileName) {
        try {

            fillHashTable(fileName);

            if (hashData.get(id) != null) {
                System.out.println("Encontrado -> " + hashData.get(id));
            } else {
                System.out.println("Não encontrado!");
            }

        } catch (Exception ex) {
            System.out.println("Erro!");
        }
    }

    public static void dropOne(int id, String fileName) {
        try {

            RandomAccessFile file = new RandomAccessFile(fileName, "rw");

            fillHashTable(fileName);

            Long position = hashPositions.get(id);

            file.seek(position);

            file.writeInt(-1); // ID -1 indica registro deletado

            file.close();

        } catch (Exception ex) {
            System.out.println("Erro ao eliminar dado");
        }
    }

    public static GenericEntity findOne(int id, String fileName) {

        fillHashTable(fileName);

        return hashData.get(id);
    }

    public static String[] findAllNames(String fileName) {

        fillHashTable(fileName);

        String[] names = new String[hashData.size()];

        cont = 0;
        
        hashData.forEach((k, d) -> {
            names[cont] = d.getName();
            cont++;
        });

        return names;
    }

    public static List<GenericEntity> findAll(String fileName) {

        List<GenericEntity> data = new ArrayList<GenericEntity>();

        try {

            RandomAccessFile file = new RandomAccessFile(fileName, "r");

            if (file != null) {

                hashPositions.clear();

                file.seek(0);

                long position = file.getFilePointer();

                while (position < file.length()) {

                    Integer id = file.readInt();

                    String name = FileUtils.readString(file, Defs.NAME_SIZE);

                    hashPositions.put(id, position);

                    if (id > 0)
                        data.add(new GenericEntity(id, name));

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

    public static GenericEntity searchToEdit(String fileName) {

        Scanner sc = new Scanner(System.in);

        System.out.print("ID: ");
        int id = sc.nextInt();

        return GenericPersistenceEntity.findOne(id, fileName);

    }

}
