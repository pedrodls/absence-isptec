package turma;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import isptec.utils.FileUtils;
import utils.Defs;

public class TurmaPersistente {

    private static HashMap<Long, Turma> myList = new HashMap<>();

    public static long numberOfRecords(RandomAccessFile file) {
        try {
            return Math.round((file.length() / 2) / TurmaDefs.RECORD_SIZE);
        } catch (IOException e) {
            return 0;
        }
    }

    public TurmaPersistente() {

    }

    public static void fillMyList() {
        List<Turma> turmas = findAll();

        for (Turma tr : turmas)
            myList.put(tr.getId(), tr);

    }

    public static void create(Turma turma) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.TURMA_FILE, "rw");

            fillMyList();

            Iterator<Turma> iterator = myList.values().iterator();

            while (iterator.hasNext()) {

                if (iterator.next().getNome().toLowerCase().equals(turma.getNome().toLowerCase())) {
                    file.close();
                    System.out.println("\nEste dado já existe!\n");
                    return;
                }
            }

            long newId = numberOfRecords(file);

            file.seek(file.length());

            file.writeLong(newId);

            file.writeLong(turma.getCursoId());

            file.writeLong(turma.getAnoAcademicoId());

            writeString(file, turma.getNome(), TurmaDefs.NAME_SIZE);

            file.close();

            System.out.println("\nCriado com sucesso!\n");

        } catch (Exception e) {
            System.out.println("\nErro ao criar Turma!\n");
        }
    }

    public static void realocate(Turma turma) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.TURMA_FILE, "rw");

            file.seek(file.length());

            file.writeLong(turma.getId());

            file.writeLong(turma.getCursoId());

            file.writeLong(turma.getAnoAcademicoId());

            writeString(file, turma.getNome(), TurmaDefs.NAME_SIZE);

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

    public static void update(Turma turma) {
        try {

            fillMyList();

            myList.replace(turma.getId(), turma);

            FileUtils.delete(Defs.TURMA_FILE);

            myList.forEach((t, u) -> {
                realocate(u);
            });

        } catch (Exception ex) {
            System.out.println("Erro ao atualizar  dados");
        }
    }

    public static void dropOne(Turma turma) {
        try {

            fillMyList();

            myList.remove(turma.getId());

            FileUtils.delete(Defs.TURMA_FILE);

            myList.forEach((t, u) -> {
                realocate(u);
            });

        } catch (Exception ex) {
            System.out.println("Erro ao eliminar dado");
        }
    }

    public static Turma findOne(long id) {

        fillMyList();

        return myList.get(id);
    }

    public static List<Turma> findAllByCursoId(long cursoId) {

        fillMyList();

        List<Turma> turmas = new ArrayList<Turma>();

        myList.forEach((k, c) -> {
            if (c.getCursoId() == cursoId) {
                turmas.add(c);
            }
        });

        return turmas;
    }

    public static List<Turma> findAllByAnoAcademico(long anoAcademicoId) {

        List<Turma> turmas = new ArrayList<Turma>();

        fillMyList();

        myList.forEach((k, u) -> {
            if (u.getAnoAcademicoId() == anoAcademicoId) {
                turmas.add(u);
            }
        });

        return turmas;

    }

    public static List<Turma> findAll() {

        List<Turma> turmas = new ArrayList<Turma>();

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.TURMA_FILE, "r");

            for (long id = 0; id <= numberOfRecords(file); id++) {

                long position = id * TurmaDefs.RECORD_SIZE;

                file.seek(position);

                long recordId = file.readLong();

                turmas.add(new Turma(id, file.readLong(), file.readLong(), readString(file, TurmaDefs.NAME_SIZE)));

            }

            file.close();
        } catch (Exception ex) {

        }

        return turmas;

    }

    

    

}
