package code;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import code.model.Model;

/**
 * File I/O utility for saving and loading boards.
 * @author Blake, Dr. Carl Alphonce
 */
public class FileIO {

	/**
	 * File extension for Master Labyrinth board files.
	 */
	private static final String FILE_EXTENSION = ".mls";
	/**
	 * Default filename for Master Labyrinth game files.
	 */
	public static final String DEFAULT_FILENAME = "CSE116_T151.mls";
	/**
	 * Temp filename for Easter Egg purposes.
	 */
	public static final String TEMP_FILENAME = "CSE116_T151_temp";
	/**
	 * Reads the contents of the given file and returns them as a string.
	 * @param filename the name of the file
	 * @return the contents of the file as a String
	 * @author Blake, Dr. Carl Alphonce
	 */
	public static String load(String filename)
	{
		//Ensures the filename ends with the proper extension
		if (!filename.endsWith(FILE_EXTENSION))
			filename += FILE_EXTENSION;
		Scanner sc = null;
		String content = "";
		try
		{
			sc = new Scanner(new File(filename));
			if (sc.hasNextLine())
				content += sc.nextLine();
			while (sc.hasNextLine()) {
				content += System.lineSeparator() + sc.nextLine();
			}
			sc.close();
		}
		catch (FileNotFoundException e)
		{
			System.err.println(filename+" not found.");
		}
		return content;
	}
	/**
	 * Writes the given String to the given file.
	 * If the file exists, overwrites it;
	 * if the file does not exist, creates it.
	 * @param filename the name of the file to write to
	 * @param content the String to be written to the file
	 * @return whether writing to the file was successful
	 * @author blake, Dr. Carl Alphonce
	 */
	public static boolean save(String filename, String content)
	{
		if (filename.isEmpty() || filename.startsWith(".", 0))
			return false;
		//Ensures the filename ends with the proper extension
		if (!filename.endsWith(FILE_EXTENSION))
			filename += FILE_EXTENSION;
		PrintStream ps = null;
		boolean success = false;
		try
		{
			ps = new PrintStream(filename);
			ps.format("%s%n", content);
			success = true;
		}
		catch (FileNotFoundException e)
		{
			System.err.println(filename + " cannot be found or created.");
		}
		finally
		{
			ps.close();
		}
		return success;
	}
	
	/**
	 * Calls the above save method using the string representation of the passed-in model
	 * @param filename name of the file to be saved into
	 * @param content Model to be saved
	 * @return whether file has been successfully saved
	 */
	public static boolean save(String filename, Model content)
	{
		return save(filename, content.toString());
	}
}
