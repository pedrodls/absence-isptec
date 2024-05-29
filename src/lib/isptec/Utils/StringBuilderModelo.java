package lib.isptec.utils;



import java.io.*;
import javax.swing.*;


public class StringBuilderModelo //extends ObjectSerializableGeneric
{
	private StringBuilder buffer;

	public StringBuilderModelo(String name, int size)
	{
		buffer = null;
		
		int nl = name != null ? name.length() : 0;
		if ( name != null )
         	buffer = new StringBuilder( name );
		else
         	buffer = new StringBuilder( size );

      	buffer.setLength( size );

		for (int i = nl; i < size; i++ )
			buffer.setCharAt(i, ' ');
	}
	
	public StringBuilder getBuffer()
	{
		return buffer;
	}
	
	public StringBuilderModelo(int size)
	{
		buffer = new StringBuilder( size );
		buffer.setLength( size );
		
		for (int i = 0; i < size; i++ )
			buffer.setCharAt(i, ' ');
	}
	
    public int compareToIgnoreCase(StringBuilderModelo st)
    {
        return buffer.toString().compareToIgnoreCase(st.toString());
    }
    
    public int compareTo(StringBuilderModelo st)
    {
        return buffer.toString().compareTo(st.toString());
    }
    
	public long sizeof()
	{
		return 2 * buffer.length();
	}
	
    @Override
	public String toString()
	{
		return buffer.toString(); 
	}
	
	public String toStringEliminatingSpaces()
	{
		StringBuilder newBuffer = new StringBuilder(new String(buffer));
		int nl = newBuffer.length();
		
		char ch = 0;
		boolean change = true;
		for (int i = nl - 1; change && i >= 0; i--)
		{
			ch = newBuffer.charAt(i);
			if (Character.isWhitespace(ch) == true)
				newBuffer.setCharAt(i, '\0');
			else
				change = false;
		}
		return new String (newBuffer).trim();
	}
	
	public void write(RandomAccessFile stream)
	{
		try
		{
			stream.writeChars( buffer.toString() );
		}
		catch (IOException ex)
		{
			String msg = "falha na escrita de uma frase no ficheiro " ;
			JOptionPane.showMessageDialog(null, msg);
			System.exit(1);
		}
	}
	
	public void read(RandomAccessFile stream)
	{
		try
		{
			char name[] = new char[ buffer.length() ], temp;

			for ( int i = 0; i < name.length; i++ )
			{
				 temp = stream.readChar();
				 name[ i ] = temp;
			}
			buffer = new StringBuilder(new String( name ).replace( '\0', ' ' ));
			buffer.setLength(name.length);
		}
		catch (IOException ex)
		{
			String msg = "falha na leitura de uma frase no ficheiro " ;
			JOptionPane.showMessageDialog(null, msg);
			System.exit(1);
		}
	}
	
	public static String readString(RandomAccessFile stream, int tamanho)
	{
		StringBuilderModelo buf = new StringBuilderModelo(tamanho);
		buf.read ( stream );
		return buf.toString();
	}	
}
