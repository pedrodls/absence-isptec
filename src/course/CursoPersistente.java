package course;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import isptec.utils.FileUtils;
import utils.Defs;

public class CursoPersistente {

    private static HashMap<Long, Curso> myList = new HashMap<>();

    public static long numberOfRecords(RandomAccessFile file) {
        try {
            return Math.round((file.length()) / Defs.RECORD_SIZE);
        } catch (IOException e) {

            return 0;
        }
    }

    public CursoPersistente() {

    }

    public static void fillMyList() {
        List<Curso> Cursos = findAll();

        for (Curso pr : Cursos)
            myList.put(pr.getId(), pr);

    }

    public static void closeFile(RandomAccessFile file) {
        try {
            file.close();
        } catch (Exception e) {

        }

    }

    public static void create(Curso curso) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.CURSO_FILE, "rw");

            fillMyList();

            myList.forEach((k, c) -> {

                if (c.getNome().toLowerCase().equals(curso.getNome().toLowerCase())) {
                    closeFile(file);
                    System.out.println("\nEste dado já existe!\n");
                    return;
                }
            });

            // long newId = numberOfRecords(file);

            curso.setId(numberOfRecords(file) + 1);

            realocate(curso);

            /*
             * file.seek(file.length());
             * 
             * file.writeLong(newId);
             * 
             * writeString(file, curso.getNome(), Defs.NAME_SIZE);
             * 
             * file.close();
             */

            System.out.println("\nCriado com sucesso!\n");

        } catch (Exception e) {
            System.out.println("\nErro ao criar Curso!\n");
        }
    }

    public static void realocate(Curso curso) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.CURSO_FILE, "rw");

            file.seek(file.length());

            writeString(file, curso.getId() + "", Defs.ID_SIZE);

            writeString(file, curso.getNome(), Defs.NAME_SIZE);

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

            if (id > 0 && id <= myList.size()) {
                System.out.println("Encontrado -> " + myList.get(id).toString());
            } else {
                System.out.println("Não encontrado!");
            }

        } catch (Exception ex) {
            System.out.println("Erro!");
        }
    }

    public static void update(Curso Curso) {
        try {

            fillMyList();

            myList.replace(Curso.getId(), Curso);

            FileUtils.delete(Defs.CURSO_FILE);

            myList.forEach((t, u) -> {
                realocate(u);
            });

        } catch (Exception ex) {
            System.out.println("Erro ao atualizar  dados");
        }
    }

    public static void dropOne(Curso Curso) {
        try {

            fillMyList();

            myList.remove(Curso.getId());

            FileUtils.delete(Defs.CURSO_FILE);

            myList.forEach((t, u) -> {
                realocate(u);
            });

        } catch (Exception ex) {
            System.out.println("Erro ao eliminar dado");
        }
    }

    public static Curso findOne(long id) {

        fillMyList();

        return myList.get(id);
    }

    public static List<Curso> findAll() {

        List<Curso> Cursos = new ArrayList<Curso>();

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.CURSO_FILE, "r");

            for (long id = 0; id < numberOfRecords(file); id++) {

                long position = id * Defs.RECORD_SIZE;

                file.seek(position);
                
                System.out.println(position);

                Long recordId = Long.parseLong(readString(file, Defs.ID_SIZE));

                String nome = readString(file, Defs.NAME_SIZE);

                Cursos.add(new Curso(recordId, nome));

            }

            file.close();

        } catch (Exception ex) {

        }

        return Cursos;

    }

}
