package justificacao;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

public class JustificacaoPersistente {
    
    private RandomAccessFile raf;
    private Map<Integer, Long> index;

    public JustificacaoPersistente(String filename) throws IOException {
        raf = new RandomAccessFile(filename, "rw");
        index = new HashMap<>();
        loadIndex();
    }

    private void loadIndex() throws IOException {
        raf.seek(0);
        while (raf.getFilePointer() < raf.length()) {
            long position = raf.getFilePointer();

            int id = raf.readInt();
            raf.readUTF(); // Name
            raf.readDouble(); // Balance
            
            index.put(id, position);
        }
    }

    public void addRecord(Justificacao record) throws IOException {
        long position = raf.length();
        raf.seek(position);
        record.write(raf);
        index.put(record.getId(), position);
    }

    public Justificacao getRecord(int id) throws IOException {
        Long position = index.get(id);
        if (position == null) {
            return null;
        }
        raf.seek(position);
        return Justificacao.read(raf);
    }

    public void close() throws IOException {
        raf.close();
    }
}
