package ano_letivo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import course.Course;
import isptec.utils.FileUtils;
import utils.Defs;

public class AnoLetivoPersistente {

    private static HashMap<Long, AnoLetivo> myList = new HashMap<>();

    public static long numberOfRecords(RandomAccessFile file) {
        try {
            return Math.round((file.length() / 2) / Defs.RECORD_SIZE);
        } catch (IOException e) {

            return 0;
        }
    }

    public AnoLetivoPersistente() {

    }

    public static void fillMyList() {
        List<AnoLetivo> AnoLetivos = findAll();

        for (AnoLetivo pr : AnoLetivos)
            myList.put(pr.getId(), pr);

    }

    public static void create(AnoLetivo anoLetivo) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.ANO_LETIVO_FILE, "rw");

            long position = file.length();

            file.seek(position);

            long newId = numberOfRecords(file);

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

            if (id >= 0 && id < myList.size()) {
                System.out.println("Encontrado -> " + myList.get(id).toString());
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

    public static void dropOne(AnoLetivo AnoLetivo) {
        try {

            

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

            RandomAccessFile file = new RandomAccessFile(Defs.ANO_LETIVO_FILE, "r");

            file.seek(0);

            while (file.getFilePointer() < file.length()) {

                int id = file.readInt();

                AnoLetivos.add(new AnoLetivo(id, FileUtils.readString(file, Defs.NAME_SIZE)));
                
            }

            file.close();
        } catch (Exception ex) {

        }

        return AnoLetivos;

    }

}
