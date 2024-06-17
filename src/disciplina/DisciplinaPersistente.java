package disciplina;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import isptec.utils.FileUtils;
import utils.Defs;

public class DisciplinaPersistente {

    private static HashMap<Long, Disciplina> myList = new HashMap<>();

    public static long numberOfRecords(RandomAccessFile file) {
        try {
            return Math.round((file.length() / 2) / Defs.RECORD_SIZE);
        } catch (IOException e) {
            
            return 0;
        }
    }

    public DisciplinaPersistente() {

    }

    public static void fillMyList() {
        List<Disciplina> Disciplinas = findAll();

        for (Disciplina pr : Disciplinas)
            myList.put(pr.getId(), pr);

    }

    public static void create(Disciplina Disciplina) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.DISCIPLINA_FILE, "rw");

            long newId = numberOfRecords(file);

            file.seek(file.length());

            file.writeLong(newId);

            writeString(file, Disciplina.getNome(), Defs.NAME_SIZE);

            file.close();

            System.out.println("\nCriado com sucesso!\n");

        } catch (Exception e) {
            System.out.println("\nErro ao criar Disciplina!\n");
        }
    }

    public static void realocate(Disciplina Disciplina) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.DISCIPLINA_FILE, "rw");

            long id = numberOfRecords(file);

            file.seek(file.length());

            file.writeLong(id);

            writeString(file, Disciplina.getNome(), Defs.NAME_SIZE);

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

    public static void update(Disciplina Disciplina) {
        try {

            fillMyList();

            myList.replace(Disciplina.getId(), Disciplina);

            FileUtils.delete(Defs.DISCIPLINA_FILE);

            myList.forEach((t, u) -> {
                realocate(u);
            });

        } catch (Exception ex) {
            System.out.println("Erro ao atualizar  dados");
        }
    }

    public static void dropOne(Disciplina Disciplina) {
        try {

            fillMyList();

            myList.remove(Disciplina.getId());

            FileUtils.delete(Defs.DISCIPLINA_FILE);

            myList.forEach((t, u) -> {
                realocate(u);
            });

        } catch (Exception ex) {
            System.out.println("Erro ao eliminar dado");
        }
    }

    public static Disciplina findOne(long id) {

        fillMyList();

        return myList.get(id);
    }

    public static List<Disciplina> findAll() {

        List<Disciplina> Disciplinas = new ArrayList<Disciplina>();

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.DISCIPLINA_FILE, "r");

            for (long id = 0; id <= numberOfRecords(file); id++) {

                long position = id * Defs.RECORD_SIZE;

                file.seek(position);

                long recordId = file.readLong();

                String nome = readString(file, Defs.NAME_SIZE);

                Disciplinas.add(new Disciplina(id, nome));

            }

            file.close();
        } catch (Exception ex) {

        }

        return Disciplinas;

    }

}
