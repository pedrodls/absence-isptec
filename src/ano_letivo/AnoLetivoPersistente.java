package ano_letivo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

import isptec.utils.FileUtils;
import utils.Defs;

public class AnoLetivoPersistente {

    private static HashMap<Long, AnoLetivo> myList = new HashMap<>();

    private static HashMap<Long, Long> myListPosition = new HashMap<>();

    public AnoLetivoPersistente() {

    }

    public static void fillMyList() {
        List<AnoLetivo> AnoLetivos = findAll();

        myList.clear();

        for (AnoLetivo pr : AnoLetivos)
            myList.put(pr.getId(), pr);

    }

    public static void create(AnoLetivo anoLetivo) {

        fillMyList();

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.ANO_LETIVO_FILE, "rw");

            long position = file.length();

            file.seek(position);

            long newId = myListPosition.size() + 1;

            if (findOne(newId) != null) {
                newId++;    
            }

            file.writeLong(newId);

            FileUtils.writeString(file, anoLetivo.getNome(), Defs.NAME_SIZE);

            file.close();

            System.out.println("\nCriado com sucesso!\n");

        } catch (Exception e) {
            System.out.println("\nErro ao criar!\n");
        }
    }

    public static void read(long id) {
        try {

            fillMyList();

            if (myList.get(id) != null) {
                System.out.println("Encontrado -> " + myList.get(id));
            } else {
                System.out.println("Não encontrado!");
            }

        } catch (Exception ex) {
            System.out.println("Erro!");
        }
    }

    public static void update(AnoLetivo AnoLetivo) {
        try {

        } catch (Exception ex) {
            System.out.println("Erro ao atualizar  dados");
        }
    }

    public static void dropOne(long id) {
        try {

            RandomAccessFile file = new RandomAccessFile(Defs.ANO_LETIVO_FILE, "rw");

            fillMyList();

            Long position = myListPosition.get(id);

            // Marcar o curso como deletado no arquivo
            file.seek(position);

            file.writeLong(-1); // ID -1 indica registro deletado

            file.close();

        } catch (Exception ex) {
            System.out.println("Erro ao eliminar dado");
        }
    }

    public static AnoLetivo findOne(long id) {

        fillMyList();

        return myList.get(id);
    }

    public static List<AnoLetivo> findAll() {

        List<AnoLetivo> AnoLetivos = new ArrayList<AnoLetivo>();

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.ANO_LETIVO_FILE, "rw");

            myListPosition.clear();

            file.seek(0);

            long position = file.getFilePointer();

            while (position < file.length()) {

                long id = file.readLong();

                String name = FileUtils.readString(file, Defs.NAME_SIZE);

                myListPosition.put(id, position);

                if (id > 0)
                    AnoLetivos.add(new AnoLetivo(id, name));

                position = file.getFilePointer();

            }

            file.close();

        } catch (Exception ex) {
            System.out.println("Erro ao buscar dados!");
        }

        return AnoLetivos;

    }

}
