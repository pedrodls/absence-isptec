package ano_letivo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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

            fillMyList();

            Iterator<AnoLetivo> iterator = myList.values().iterator();

            while (iterator.hasNext()) {

                if (iterator.next().getNome().toLowerCase().equals(anoLetivo.getNome().toLowerCase())) {
                    file.close();
                    System.out.println("\nEste dado já existe!\n");
                    return;
                }
            }

            long newId = numberOfRecords(file);

            file.seek(file.length());

            file.writeLong(newId);

            writeString(file, anoLetivo.getNome(), Defs.NAME_SIZE);

            file.close();

            System.out.println("\nCriado com sucesso!\n");

        } catch (Exception e) {
            System.out.println("\nErro ao criar AnoLetivo!\n");
        }
    }

    public static void realocate(AnoLetivo AnoLetivo) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.ANO_LETIVO_FILE, "rw");

            long id = numberOfRecords(file);

            file.seek(file.length());

            file.writeLong(id);

            writeString(file, AnoLetivo.getNome(), Defs.NAME_SIZE);

            file.close();

        } catch (Exception e) {

        }
    }

    private static void writeString(RandomAccessFile file, String str, int length) {

        try {

            StringBuilder sb = new StringBuilder(str);

            while (sb.length() < length)
                sb.append(' ');

            file.writeChars(sb.toString());

        } catch (Exception e) {

        }

    }

    private static String readString(RandomAccessFile file, int length) {
        try {

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < length; i++)
                sb.append(file.readChar());

            return sb.toString().trim();

        } catch (Exception e) {
            return "";
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

            fillMyList();

            myList.replace(AnoLetivo.getId(), AnoLetivo);

            FileUtils.delete(Defs.ANO_LETIVO_FILE);

            myList.forEach((t, u) -> {
                realocate(u);
            });

        } catch (Exception ex) {
            System.out.println("Erro ao atualizar  dados");
        }
    }

    public static void dropOne(AnoLetivo AnoLetivo) {
        try {

            fillMyList();

            myList.remove(AnoLetivo.getId());

            FileUtils.delete(Defs.ANO_LETIVO_FILE);

            myList.forEach((t, u) -> {
                realocate(u);
            });

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

            for (long id = 0; id <= numberOfRecords(file); id++) {

                long position = id * Defs.RECORD_SIZE;

                file.seek(position);

                long recordId = file.readLong();

                String nome = readString(file, Defs.NAME_SIZE);

                AnoLetivos.add(new AnoLetivo(id, nome));

            }

            file.close();
        } catch (Exception ex) {

        }

        return AnoLetivos;

    }

}
