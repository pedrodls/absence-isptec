package course;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

import isptec.utils.FileUtils;
import utils.Defs;

public class CursoPersistente2 {
    private RandomAccessFile file;
    private long nextId;

    public CursoPersistente2(String filename) throws IOException {
        file = new RandomAccessFile(filename, "rw");
        nextId = getNextId();
    }

    private long getNextId() throws IOException {
        long numRecords = file.length() / Defs.RECORD_SIZE;
        if (numRecords == 0) return 1;
        file.seek((numRecords - 1) * Defs.RECORD_SIZE);
        long lastId = file.readLong();
        return lastId + 1;
    }

    public void create(Curso curso) throws IOException {

        file.seek(file.length());
        file.writeLong(nextId);
        file.write(fixedLengthString(curso.getNome(), Defs.NAME_SIZE).getBytes(StandardCharsets.UTF_8));
        nextId++;
    }

    public Curso read(long id) throws IOException {
        
        file.seek(0);

        while (file.getFilePointer() < file.length()) {
            long currentId = file.readLong();
            String nome = readFixedLengthString(Defs.NAME_SIZE);
            if (currentId == id) {
                return new Curso(currentId, nome);
            }
        }
        return null;
    }

    public void update(long id, String newName) throws IOException {
        
        file.seek(0);

        while (file.getFilePointer() < file.length()) {

            long currentId = file.readLong();

            if (currentId == id) {
                file.write(fixedLengthString(newName, Defs.NAME_SIZE).getBytes(StandardCharsets.UTF_8));
                return;
            }

            file.skipBytes(Defs.NAME_SIZE);
        }
    }

    public void delete(long id) throws IOException {

        RandomAccessFile tempFile = new RandomAccessFile("temp.dat", "rw");

        file.seek(0);

        while (file.getFilePointer() < file.length()) {

            long currentId = file.readLong();

            String nome = readFixedLengthString(Defs.NAME_SIZE);

            if (currentId != id) {
                tempFile.writeLong(currentId);
                tempFile.write(fixedLengthString(nome, Defs.NAME_SIZE).getBytes(StandardCharsets.UTF_8));
            }
        }

        file.close();
        
        tempFile.close();
        
        file = new RandomAccessFile("meuArquivo.dat", "rw");

        tempFile = new RandomAccessFile("temp.dat", "rw");

        file.setLength(0);

        while (tempFile.getFilePointer() < tempFile.length()) {

            long currentId = tempFile.readLong();
            String nome = readFixedLengthString(Defs.NAME_SIZE);

            file.writeLong(currentId);

            file.write(fixedLengthString(nome, Defs.NAME_SIZE).getBytes(StandardCharsets.UTF_8));
        }

        tempFile.close();

        FileUtils.delete("temp.dat");
    }

    private String fixedLengthString(String string, int length) {
        
        StringBuilder sb = new StringBuilder(string);

        while (sb.length() < length) 
            sb.append(' ');
        
        return sb.toString();
    }

    private String readFixedLengthString(int length) throws IOException {
        byte[] bytes = new byte[length];
        file.readFully(bytes);
        return new String(bytes, StandardCharsets.UTF_8).trim();
    }

}
