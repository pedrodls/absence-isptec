package coordenacao;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import isptec.utils.FileUtils;
import utils.Defs;

public class CoordenacaoPersistente {

    private static HashMap<Long, Coordenacao> myList = new HashMap<>();

    public static long numberOfRecords(RandomAccessFile file) {
        try {
            return Math.round((file.length() / 2) / CoordenacaoDefs.RECORD_SIZE);
        } catch (IOException e) {

            return 0;
        }
    }

    public CoordenacaoPersistente() {

    }

    public static void fillMyList() {
        List<Coordenacao> coordenacoes = findAll();

        for (Coordenacao pr : coordenacoes)
            myList.put(pr.getId(), pr);

    }

    public static void create(Coordenacao coordenacao) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.COORDENACAO_FILE, "rw");

            fillMyList();

            Iterator<Coordenacao> iterator = myList.values().iterator();

            while (iterator.hasNext()) {

                if (iterator.next().getCursoId() == coordenacao.getCursoId()) {
                    file.close();
                    System.out.println("\nEste dado já existe!\n");
                    return;
                }
            }

            long newId = numberOfRecords(file);

            file.seek(file.length());

            file.writeLong(newId);

            file.writeLong(coordenacao.getCursoId());

            file.close();

            System.out.println("\nCriado com sucesso!\n");

        } catch (Exception e) {
            System.out.println("\nErro ao criar Coordenacao!\n");
        }
    }

    public static void realocate(Coordenacao coordenacao) {

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.COORDENACAO_FILE, "rw");

            file.seek(file.length());

            file.writeLong(coordenacao.getId());

            file.writeLong(coordenacao.getCursoId());

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

    public static void update(Coordenacao Coordenacao) {
        try {

            fillMyList();

            myList.replace(Coordenacao.getId(), Coordenacao);

            FileUtils.delete(Defs.COORDENACAO_FILE);

            myList.forEach((t, u) -> {
                realocate(u);
            });

        } catch (Exception ex) {
            System.out.println("Erro ao atualizar  dados");
        }
    }

    public static void dropOne(Coordenacao Coordenacao) {
        try {

            fillMyList();

            myList.remove(Coordenacao.getId());

            FileUtils.delete(Defs.COORDENACAO_FILE);

            myList.forEach((t, u) -> {
                realocate(u);
            });

        } catch (Exception ex) {
            System.out.println("Erro ao eliminar dado");
        }
    }

    public static Coordenacao findOne(long id) {

        fillMyList();

        return myList.get(id);
    }

    public static List<Coordenacao> findAllByCursoId(long cursoId) {
    
        fillMyList();

        List<Coordenacao> coordenacoes = new ArrayList<Coordenacao>();

        myList.forEach((k, c) -> {
            if (c.getCursoId() == cursoId) {
                coordenacoes.add(c);
            }
        });
        
        return coordenacoes;
    }
    
    public static List<Coordenacao> findAll() {

        List<Coordenacao> coordenacoes = new ArrayList<Coordenacao>();

        try {

            RandomAccessFile file = new RandomAccessFile(Defs.COORDENACAO_FILE, "r");

            for (long id = 0; id <= numberOfRecords(file); id++) {

                long position = id * CoordenacaoDefs.RECORD_SIZE;

                file.seek(position);

                long recordId = file.readLong();

                long cursoId = file.readLong();

                coordenacoes.add(new Coordenacao(id, cursoId));

            }

            file.close();
        } catch (Exception ex) {

        }

        return coordenacoes;

    }

}
