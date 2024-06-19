package genericEntity;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

import isptec.utils.FileUtils;
import utils.Defs;

public class GenericPersistenceEntity {

    private static HashMap<Long, GenericEntity> myList = new HashMap<>();

    private static HashMap<Long, Long> myListPosition = new HashMap<>();

    public GenericPersistenceEntity() {

    }

    public static void fillMyList(String fileName) {
        List<GenericEntity> data = findAll(fileName);

        myList.clear();

        for (GenericEntity datum : data)
            myList.put(datum.getId(), datum);

    }

    public static void create(GenericEntity entity, String fileName) {

        fillMyList(fileName);

        try {

            RandomAccessFile file = new RandomAccessFile(fileName, "rw");

            long position = file.length();

            file.seek(position);

            long newId = myListPosition.size() + 1;

            if (findOne(newId, fileName) != null) {
                newId++;
            }

            file.writeLong(newId);

            FileUtils.writeString(file, entity.getName(), Defs.NAME_SIZE);

            file.close();

            System.out.println("\nCriado com sucesso!\n");

        } catch (Exception e) {
            System.out.println("\nErro ao criar!\n");
        }
    }

    public static void read(long id, String fileName) {
        try {

            fillMyList(fileName);

            if (myList.get(id) != null) {
                System.out.println("Encontrado -> " + myList.get(id));
            } else {
                System.out.println("Não encontrado!");
            }

        } catch (Exception ex) {
            System.out.println("Erro!");
        }
    }

    public static void update(GenericEntity GenericEntity, String fileName) {
        try {

        } catch (Exception ex) {
            System.out.println("Erro ao atualizar  dados");
        }
    }

    public static void dropOne(long id, String fileName) {
        try {

            RandomAccessFile file = new RandomAccessFile(fileName, "rw");

            fillMyList(fileName);

            Long position = myListPosition.get(id);

            file.seek(position);

            file.writeLong(-1); // ID -1 indica registro deletado

            file.close();

        } catch (Exception ex) {
            System.out.println("Erro ao eliminar dado");
        }
    }

    public static GenericEntity findOne(long id, String fileName) {

        fillMyList(fileName);

        return myList.get(id);
    }

    public static List<GenericEntity> findAll(String fileName) {

        List<GenericEntity> data = new ArrayList<GenericEntity>();

        try {

            RandomAccessFile file = new RandomAccessFile(fileName, "rw");

            myListPosition.clear();

            file.seek(0);

            long position = file.getFilePointer();

            while (position < file.length()) {

                long id = file.readLong();

                String name = FileUtils.readString(file, Defs.NAME_SIZE);

                myListPosition.put(id, position);

                if (id > 0)
                    data.add(new GenericEntity(id, name));

                position = file.getFilePointer();

            }

            file.close();

        } catch (Exception ex) {
            System.out.println("Erro ao buscar dados!");
        }

        return data;

    }

}
