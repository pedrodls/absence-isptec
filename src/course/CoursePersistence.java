import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import utils.Defs;

public class CoursePersistence {

    private static RandomAccessFile file;
    private static Hashtable<Integer, Long> indexTable = new Hashtable<>();

    static {
        try {
            file = new RandomAccessFile(Defs.CURSO_FILE, "rw");
            loadIndexTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadIndexTable() {
        try {

            long pointer = 0;

            file.seek(0);

            while (pointer < file.length()) {

                int id = file.readInt();

                long position = pointer;

                indexTable.put(id, position);

                int nameLength = file.readInt();

                file.seek(file.getFilePointer() + nameLength);

                pointer = file.getFilePointer();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void create(Course course) {
        try {

            long position = file.length();
            
            file.seek(position);

            file.writeInt(course.getId());
            file.writeInt(course.getName().length());
            file.writeBytes(course.getName());

            indexTable.put(course.getId(), position);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Course read(int id) {
        try {

            Long position = indexTable.get(id);
            
            if (position == null) {
                return null;
            }

            file.seek(position);

            int courseId = file.readInt();

            int nameLength = file.readInt();
            
            byte[] nameBytes = new byte[nameLength];
            
            file.readFully(nameBytes);
            
            String name = new String(nameBytes);

            return new Course(courseId, name);
            
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void update(Course course) {
        try {

            Long position = indexTable.get(course.getId());
            
            if (position == null) {
                System.out.println("Dado não encontrado!");
                return;
            }

            file.seek(position);
            file.writeInt(course.getId());
            file.writeInt(course.getName().length());
            file.writeBytes(course.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int id) {
        try {
            
            Long position = indexTable.get(id);

            if (position == null) {
                System.out.println("Dado não encontrado!");
                return;
            }

            indexTable.remove(id);

            // Marcar o Course como deletado no arquivo
            file.seek(position);

            file.writeInt(-1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Fechar o arquivo
    public static void close() {
        try {
            if (file != null) {
                file.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Course> findAll() {

        List<Course> Courses = new ArrayList<>();
        
        try {

            file.seek(0);

            while (file.getFilePointer() < file.length()) {

                int id = file.readInt();

                if (id != -1) { 

                    int nameLength = file.readInt();

                    byte[] nameBytes = new byte[nameLength];

                    file.readFully(nameBytes);

                    String name = new String(nameBytes);

                    Courses.add(new Course(id, name));

                } else {

                    int nameLength = file.readInt();

                    file.seek(file.getFilePointer() + nameLength);
                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Courses;
    }

}
