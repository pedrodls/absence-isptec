package motivo_falta;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import isptec.utils.FileUtils;
import utils.Defs;

public class MotivoFaltaPersistente {

    private static HashMap<Long, MotivoFalta> myList = new HashMap<>();

    public static long numberOfRecords(RandomAccessFile file) {
        try {
            return Math.round((file.length() / 2) / Defs.RECORD_SIZE);
        } catch (IOException e) {

            return 0;
        }
    }

    public MotivoFaltaPersistente() {

    }

    public static void fillMyList() {
        List<MotivoFalta> MotivoFaltas = findAll();

        for (MotivoFalta pr : MotivoFaltas)
            myList.put(pr.getId(), pr);

    }

    public static void create(MotivoFalta motivoFalta) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.ANO_ACADEMICO_FILE, "rw");

            fillMyList();

            Iterator<MotivoFalta> iterator = myList.values().iterator();

            while (iterator.hasNext()) {

                if (iterator.next().getNome().toLowerCase().equals(motivoFalta.getNome().toLowerCase())) {
                    file.close();
                    System.out.println("\nEste dado já existe!\n");
                    return;
                }
            }

            long newId = numberOfRecords(file);

            file.seek(file.length());

            file.writeLong(newId);

            writeString(file, motivoFalta.getNome(), Defs.NAME_SIZE);

            file.close();

            System.out.println("\nCriado com sucesso!\n");

        } catch (Exception e) {
            System.out.println("\nErro ao criar MotivoFalta!\n");
        }
    }

    public static void realocate(MotivoFalta MotivoFalta) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.ANO_ACADEMICO_FILE, "rw");

            long id = numberOfRecords(file);

            file.seek(file.length());

            file.writeLong(id);

            writeString(file, MotivoFalta.getNome(), Defs.NAME_SIZE);

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

    public static void update(MotivoFalta MotivoFalta) {
        try {

            fillMyList();

            myList.replace(MotivoFalta.getId(), MotivoFalta);

            FileUtils.delete(Defs.ANO_ACADEMICO_FILE);

            myList.forEach((t, u) -> {
                realocate(u);
            });

        } catch (Exception ex) {
            System.out.println("Erro ao atualizar  dados");
        }
    }

    public static void dropOne(MotivoFalta MotivoFalta) {
        try {

            fillMyList();

            myList.remove(MotivoFalta.getId());

            FileUtils.delete(Defs.ANO_ACADEMICO_FILE);

            myList.forEach((t, u) -> {
                realocate(u);
            });

        } catch (Exception ex) {
            System.out.println("Erro ao eliminar dado");
        }
    }

    public static MotivoFalta findOne(long id) {

        fillMyList();

        return myList.get(id);
    }

    public static List<MotivoFalta> findAll() {

        List<MotivoFalta> MotivoFaltas = new ArrayList<MotivoFalta>();

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.ANO_ACADEMICO_FILE, "r");

            for (long id = 0; id <= numberOfRecords(file); id++) {

                long position = id * Defs.RECORD_SIZE;

                file.seek(position);

                long recordId = file.readLong();

                String nome = readString(file, Defs.NAME_SIZE);

                MotivoFaltas.add(new MotivoFalta(id, nome));

            }

            file.close();
        } catch (Exception ex) {

        }

        return MotivoFaltas;

    }

}
