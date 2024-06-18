/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isptec.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import utils.MainMenu;

/**
 *
 * @author Aires Veloso
 */
public class FileUtils
{

    public static void delete(String filename)
    {
        File f = new File(filename);

        if (f.exists())
            f.delete();

    }

    public static long length(String filename)
    {
        File f = new File(filename);

        if (!f.exists())
            return 0;
            

        return f.length();
        
    }

    public static void rename(String filename, String newFilename)
    {
        File f = new File(filename);

        f.renameTo(new File(newFilename));
    }

    public static RandomAccessFile openRandomAccessFile(String filename,
        String mode)
    {
        RandomAccessFile stream = null;
        try
        {
            stream = new RandomAccessFile(filename, mode);
        }
        catch (FileNotFoundException ex)
        {
            Utils.exit("Falhou a abertura do ficheiro \""
                + filename + "\"", 1);
            return null;
        }
        return stream;
    }
    

    public static void close(RandomAccessFile stream, String filename)
    {
        try
        {
            stream.close();
            stream = null;
        }
        catch (IOException ex)
        {
            Utils.exit("Falhou o encerramento do ficheiro \""
                + filename + "\"", 1);
        }
    }
}
