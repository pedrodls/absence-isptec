package coordenador;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import isptec.utils.FileUtils;
import utils.Defs;

public class CoordenadorPersistente {

    private static HashMap<Long, Coordenador> myList = new HashMap<>();

    public static long numberOfRecords(RandomAccessFile file) {
        try {
            return Math.round((file.length() / 2) / Defs.RECORD_SIZE);
        } catch (IOException e) {

            return 0;
        }
    }

    public CoordenadorPersistente() {

    }

    public static void fillMyList() {
        List<Coordenador> Coordenadors = findAll();

        for (Coordenador pr : Coordenadors)
            myList.put(pr.getId(), pr);

    }

    public static void create(Coordenador coordenador) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.COORDENADOR_FILE, "rw");

            fillMyList();

            Iterator<Coordenador> iterator = myList.values().iterator();

            while (iterator.hasNext()) {

                if (iterator.next().getIdProfessor() == coordenador.getIdProfessor()
                        && iterator.next().getIdAnoLetivo() == coordenador.getIdAnoLetivo()) {
                    file.close();
                    System.out.println("\nEste dado já existe!\n");
                    return;
                }
            }

            long newId = numberOfRecords(file);

            file.seek(file.length());

            file.writeLong(newId);

            file.writeLong(coordenador.getIdProfessor());
            file.writeLong(coordenador.getIdCurso());
            file.writeLong(coordenador.getIdAnoLetivo());

            file.close();

            System.out.println("\nCriado com sucesso!\n");

        } catch (Exception e) {
            System.out.println("\nErro ao criar Coordenador!\n");
        }
    }

    public static void realocate(Coordenador coordenador) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.COORDENADOR_FILE, "rw");

            long id = numberOfRecords(file);

            file.seek(file.length());

            file.writeLong(id);

            file.writeLong(coordenador.getIdProfessor());
            file.writeLong(coordenador.getIdCurso());
            file.writeLong(coordenador.getIdAnoLetivo());

            file.close();

        } catch (Exception e) {

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

    public static void update(Coordenador coordenador) {
        try {

            fillMyList();

            myList.replace(coordenador.getId(), coordenador);

            FileUtils.delete(Defs.COORDENADOR_FILE);

            myList.forEach((t, u) -> {
                realocate(u);
            });

        } catch (Exception ex) {
            System.out.println("Erro ao atualizar  dados");
        }
    }

    public static void dropOne(Coordenador Coordenador) {
        try {

            fillMyList();

            myList.remove(Coordenador.getId());

            FileUtils.delete(Defs.COORDENADOR_FILE);

            myList.forEach((t, u) -> {
                realocate(u);
            });

        } catch (Exception ex) {
            System.out.println("Erro ao eliminar dado");
        }
    }

    public static Coordenador findOne(long id) {

        fillMyList();

        return myList.get(id);
    }

    public static List<Coordenador> findAllByCursoId(long idCurso) {

        fillMyList();

        List<Coordenador> coordenadores = new ArrayList<Coordenador>();

        myList.forEach((k, c) -> {
            if (c.getIdCurso() == idCurso) {
                coordenadores.add(c);
            }
        });
        
        return coordenadores;
    }

    public static List<Coordenador> findAllByAnoLetivoId(long idAnoLetivo) {

        fillMyList();

        List<Coordenador> coordenadores = new ArrayList<Coordenador>();

        myList.forEach((k, c) -> {
            if (c.getIdAnoLetivo() == idAnoLetivo) {
                coordenadores.add(c);
            }
        });
        
        return coordenadores;

    }

    public static List<Coordenador> findAll() {

        List<Coordenador> Coordenadores = new ArrayList<Coordenador>();

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.COORDENADOR_FILE, "r");

            for (long id = 0; id <= numberOfRecords(file); id++) {

                long position = id * Defs.RECORD_SIZE;

                file.seek(position);

                file.readLong();

                Coordenadores.add(new Coordenador(id, file.readLong(), file.readLong(), file.readLong()));

            }

            file.close();
        } catch (Exception ex) {

        }

        return Coordenadores;

    }

}
