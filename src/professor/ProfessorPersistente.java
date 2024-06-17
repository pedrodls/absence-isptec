package professor;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import isptec.utils.FileUtils;
import utils.Defs;

public class ProfessorPersistente {

    private static HashMap<Long, Professor> myList = new HashMap<>();

    public static long numberOfRecords(RandomAccessFile file) {
        try {
            return Math.round((file.length() / 2) / ProfessorConstants.RECORD_SIZE);
        } catch (IOException e) {
            
            return 0;
        }
    }

    public ProfessorPersistente() {

    }

    public static void fillMyList() {
        List<Professor> professors = findAll();

        for (Professor pr : professors)
            myList.put(pr.getId(), pr);

    }

    public static void create(Professor professor) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.PROFESSOR_FILE, "rw");

            long newId = numberOfRecords(file);

            file.seek(file.length());

            file.writeLong(newId);

            writeString(file, professor.getNome(), ProfessorConstants.NAME_SIZE);

            file.close();

            System.out.println("\nProfessor criado com sucesso!\n");

        } catch (Exception e) {
            System.out.println("\nErro ao criar Professor!\n");
        }
    }

    public static void realocate(Professor professor) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.PROFESSOR_FILE, "rw");

            long id = numberOfRecords(file);

            file.seek(file.length());

            file.writeLong(id);

            writeString(file, professor.getNome(), ProfessorConstants.NAME_SIZE);

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

    public static void update(Professor professor) {
        try {

            fillMyList();

            myList.replace(professor.getId(), professor);

            FileUtils.delete(Defs.PROFESSOR_FILE);

            myList.forEach((t, u) -> {
                realocate(u);
            });

        } catch (Exception ex) {
            System.out.println("Erro ao atualizar  dados");
        }
    }

    public static void dropOne(Professor professor) {
        try {

            fillMyList();

            myList.remove(professor.getId());

            FileUtils.delete(Defs.PROFESSOR_FILE);

            myList.forEach((t, u) -> {
                realocate(u);
            });

        } catch (Exception ex) {
            System.out.println("Erro ao eliminar dado");
        }
    }

    public static Professor findOne(long id) {

        fillMyList();

        return myList.get(id);
    }

    public static List<Professor> findAll() {

        List<Professor> professors = new ArrayList<Professor>();

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.PROFESSOR_FILE, "r");

            for (long id = 0; id <= numberOfRecords(file); id++) {

                long position = id * ProfessorConstants.RECORD_SIZE;

                file.seek(position);

                long recordId = file.readLong();

                String nome = readString(file, ProfessorConstants.NAME_SIZE);

                professors.add(new Professor(id, nome));

            }

            file.close();
        } catch (Exception ex) {

        }

        return professors;

    }

}
