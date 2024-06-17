package ano_academico;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import isptec.utils.FileUtils;
import utils.Defs;

public class AnoAcademicoPersistente {

    private static HashMap<Long, AnoAcademico> myList = new HashMap<>();

    public static long numberOfRecords(RandomAccessFile file) {
        try {
            return Math.round((file.length() / 2) / Defs.RECORD_SIZE);
        } catch (IOException e) {
            
            return 0;
        }
    }

    public AnoAcademicoPersistente() {

    }

    public static void fillMyList() {
        List<AnoAcademico> AnoAcademicos = findAll();

        for (AnoAcademico pr : AnoAcademicos)
            myList.put(pr.getId(), pr);

    }

    public static void create(AnoAcademico AnoAcademico) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.ANO_ACADEMICO_FILE, "rw");

            long newId = numberOfRecords(file);

            file.seek(file.length());

            file.writeLong(newId);

            writeString(file, AnoAcademico.getNome(), Defs.NAME_SIZE);

            file.close();

            System.out.println("\nCriado com sucesso!\n");

        } catch (Exception e) {
            System.out.println("\nErro ao criar AnoAcademico!\n");
        }
    }

    public static void realocate(AnoAcademico AnoAcademico) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.ANO_ACADEMICO_FILE, "rw");

            long id = numberOfRecords(file);

            file.seek(file.length());

            file.writeLong(id);

            writeString(file, AnoAcademico.getNome(), Defs.NAME_SIZE);

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

    public static void update(AnoAcademico AnoAcademico) {
        try {

            fillMyList();

            myList.replace(AnoAcademico.getId(), AnoAcademico);

            FileUtils.delete(Defs.ANO_ACADEMICO_FILE);

            myList.forEach((t, u) -> {
                realocate(u);
            });

        } catch (Exception ex) {
            System.out.println("Erro ao atualizar  dados");
        }
    }

    public static void dropOne(AnoAcademico AnoAcademico) {
        try {

            fillMyList();

            myList.remove(AnoAcademico.getId());

            FileUtils.delete(Defs.ANO_ACADEMICO_FILE);

            myList.forEach((t, u) -> {
                realocate(u);
            });

        } catch (Exception ex) {
            System.out.println("Erro ao eliminar dado");
        }
    }

    public static AnoAcademico findOne(long id) {

        fillMyList();

        return myList.get(id);
    }

    public static List<AnoAcademico> findAll() {

        List<AnoAcademico> AnoAcademicos = new ArrayList<AnoAcademico>();

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.ANO_ACADEMICO_FILE, "r");

            for (long id = 0; id <= numberOfRecords(file); id++) {

                long position = id * Defs.RECORD_SIZE;

                file.seek(position);

                long recordId = file.readLong();

                String nome = readString(file, Defs.NAME_SIZE);

                AnoAcademicos.add(new AnoAcademico(id, nome));

            }

            file.close();
        } catch (Exception ex) {

        }

        return AnoAcademicos;

    }

}
