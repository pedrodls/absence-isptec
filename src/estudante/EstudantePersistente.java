package estudante;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ano_letivo.AnoLetivoPersistente;
import isptec.utils.FileUtils;
import utils.Defs;

public class EstudantePersistente {

    private static HashMap<Long, Estudante> myList = new HashMap<>();

    public static long numberOfRecords(RandomAccessFile file) {
        try {
            return Math.round((file.length() / 2) / EstudanteDefs.RECORD_SIZE);
        } catch (IOException e) {

            return 0;
        }
    }

    public EstudantePersistente() {

    }

    public static void fillMyList() {
        List<Estudante> Estudantes = findAll();

        for (Estudante pr : Estudantes)
            myList.put(pr.getId(), pr);

    }

    public static void create(Estudante estudante) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.ESTUDANTE_FILE, "rw");

            long newId = numberOfRecords(file);

            file.seek(file.length());

            file.writeLong(newId);

            file.writeLong(estudante.getIdAnoIngresso());

            writeString(file, estudante.getNome(), EstudanteDefs.NAME_SIZE);

            writeString(file, AnoLetivoPersistente.findOne(estudante.getIdAnoIngresso()).getNome().replace("/", "-") + newId
                    + estudante.getEmail(), EstudanteDefs.EMAIL_SIZE);

            writeString(file, estudante.getTelefone(), EstudanteDefs.TELEFONE_SIZE);

            file.close();

            System.out.println("\nCriado com sucesso!\n");

        } catch (Exception e) {
            System.out.println("\nErro ao criar Estudante!\n");
        }
    }

    public static void realocate(Estudante estudante) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.ESTUDANTE_FILE, "rw");

            long id = numberOfRecords(file);

            file.seek(file.length());

            file.writeLong(id);

            file.writeLong(estudante.getIdAnoIngresso());

            writeString(file, estudante.getNome(), EstudanteDefs.NAME_SIZE);

            writeString(file, AnoLetivoPersistente.findOne(estudante.getIdAnoIngresso()).getNome().split("/")[0] + id
                    + estudante.getEmail(), EstudanteDefs.EMAIL_SIZE);

            writeString(file, estudante.getTelefone(), EstudanteDefs.TELEFONE_SIZE);


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

    public static void update(Estudante estudante) {
        try {

            fillMyList();

            myList.replace(estudante.getId(), estudante);

            FileUtils.delete(Defs.ESTUDANTE_FILE);

            myList.forEach((t, u) -> {
                realocate(u);
            });

        } catch (Exception ex) {
            System.out.println("Erro ao atualizar  dados");
        }
    }

    public static void dropOne(Estudante Estudante) {
        try {

            fillMyList();

            myList.remove(Estudante.getId());

            FileUtils.delete(Defs.ESTUDANTE_FILE);

            myList.forEach((t, u) -> {
                realocate(u);
            });

        } catch (Exception ex) {
            System.out.println("Erro ao eliminar dado");
        }
    }

    public static Estudante findOne(long id) {

        fillMyList();

        return myList.get(id);
    }

    public static List<Estudante> findAll() {

        List<Estudante> Estudantes = new ArrayList<Estudante>();

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.ESTUDANTE_FILE, "r");

            for (long id = 0; id <= numberOfRecords(file); id++) {

                long position = id * EstudanteDefs.RECORD_SIZE;

                file.seek(position);

                long recordId = file.readLong();
                long anoIngressoId = file.readLong();

                String nome = readString(file, EstudanteDefs.NAME_SIZE);
                String email = readString(file, EstudanteDefs.EMAIL_SIZE);
                String telefone = readString(file, EstudanteDefs.TELEFONE_SIZE);

                Estudantes.add(new Estudante(id, nome, email, telefone, anoIngressoId));

            }

            file.close();
        } catch (Exception ex) {

        }

        return Estudantes;

    }

    public static List<Estudante> findAllByAnoIngressoId(long idAnoIngresso) {

        List<Estudante> Estudantes = new ArrayList<Estudante>();

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.ESTUDANTE_FILE, "r");

            for (long id = 0; id <= numberOfRecords(file); id++) {

                long position = id * EstudanteDefs.RECORD_SIZE;

                file.seek(position);

                long recordId = file.readLong();

                long anoIngressoId = file.readLong();

                if(idAnoIngresso == anoIngressoId){

                    String nome = readString(file, EstudanteDefs.NAME_SIZE);
                    String email = readString(file, EstudanteDefs.EMAIL_SIZE);
                    String telefone = readString(file, EstudanteDefs.TELEFONE_SIZE);
    
                    Estudantes.add(new Estudante(id, nome, email, telefone, anoIngressoId));
    
                }
                
            }

            file.close();
        } catch (Exception ex) {

        }

        return Estudantes;

    }

}
