package ano_academico;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

public class AnoAcademicoPersistente {
    
    private RandomAccessFile raf;
    private Map<Integer, Long> index;

    public AnoAcademicoPersistente(String filename) throws IOException {
        raf = new RandomAccessFile(filename, "rw");
        index = new HashMap<>();
        loadIndex();
    }

    private void loadIndex() throws IOException {
        raf.seek(0);
        while (raf.getFilePointer() < raf.length()) {
            long position = raf.getFilePointer();

            int id = raf.readInt();
            
            index.put(id, position);
        }
    }

    public void addAnoAcademico(AnoAcademico record) throws IOException {
        long position = raf.length();
        raf.seek(position);
        record.write(raf);
        index.put(record.getId(), position);
    }

    public AnoAcademico getAnoAcademico(int id) throws IOException {
        Long position = index.get(id);
        if (position == null) {
            return null;
        }
        raf.seek(position);
        return AnoAcademico.read(raf);
    }

    public void close() throws IOException {
        raf.close();
    }
}
