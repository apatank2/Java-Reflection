package genericCheckpointing.util;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Results implements FileDisplayInterface,StdoutDisplayInterface{
	
/**
 * Constructor
 */
public FileProcessor fi;
public Results(){};
public Results(FileProcessor File){
	this.fi = File;
};
/**
 * Write the XML tags
 * into Output File.
 */
public void FileWriter(String Filename) throws IOException {
	// TODO Auto-generated method stub
	try
	{
	File foutput = new File(Filename);
	FileOutputStream fostream = new FileOutputStream(foutput);
 
	BufferedWriter bwriter = new BufferedWriter(new OutputStreamWriter(fostream));
	for (int i = 0; i < fi.XMLFile.size(); i++) {
		 bwriter.write(fi.XMLFile.get(i));
		 bwriter.newLine();
	}
	bwriter.close();
	}
	catch(FileNotFoundException fnotfound)
    {
	 System.out.println("FileNotFoundException : "+fnotfound.getMessage());
	 System.exit(0);
    }
    catch(Exception e)
	{
		System.out.println("Exception occured: "+e.getMessage());
		System.exit(0);
	}
	finally
	{
	}
	}
/**
 * Print the Results on Console
 */
public void PrintOnConsole()
{
	for (int i = 0; i < fi.Print.size(); i++) {
		 System.out.println(fi.Print.get(i));
	}
}
		
}
