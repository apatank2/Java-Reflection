package genericCheckpointing.driver;
import java.io.FileNotFoundException;

import genericCheckpointing.util.Results;
import genericCheckpointing.util.StdoutDisplayInterface;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.xmlStoreRestore.SerDeser;
import genericCheckpointing.xmlStoreRestore.SerMode;
/**
* The genericCheckpointing program create a generic library to serialize and deserialize objects
* @author  Abhishek Patankar
* @version 1.0
* @since   2018-06-05
*/
public class Driver {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		try {
		CommandLineValidation(args);
		int NUM_OF_OBJECTS = Integer.parseInt(args[1]);
		String Mode = args[0];
		String InputFileName = args[2];
		FileProcessor File = null;
		if (Mode.equals(SerMode.deser.toString()))
		File = new FileProcessor(InputFileName);
		else
		File = new FileProcessor();	
		StdoutDisplayInterface Result = new Results(File);
		SerDeser SD = new SerDeser(NUM_OF_OBJECTS,Mode,Result,File,InputFileName);
		SD.Reflection();
        } catch (FileNotFoundException e) {
                       System.out.println("Exception occured: "+e.getMessage());
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
	
	public static void CommandLineValidation (String[] args)
	{
		if (args.length != 3 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")) {

	    System.err.println("Error: Incorrect number of arguments. Program accepts 3 arguments.");
	    System.exit(0);
        }
	}
}
