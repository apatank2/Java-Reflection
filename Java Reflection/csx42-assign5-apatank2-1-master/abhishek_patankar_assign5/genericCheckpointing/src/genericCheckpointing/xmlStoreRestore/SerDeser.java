package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.StdoutDisplayInterface;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class SerDeser {
	private int NUM_OF_OBJECTS = 0;
	private String Mode = null;
	public String FileName = null;
	StdoutDisplayInterface Result = null;
	FileProcessor File = null;
	StoreRestoreI cpointRef = null;
	MyAllTypesFirst myFirst = null;
	MyAllTypesSecond  mySecond = null;
	Vector<SerializableObject> DeSerializableVector = new Vector<SerializableObject>();
	Vector<SerializableObject> SerializableVector = new Vector<SerializableObject>();
	public SerDeser (){
	}
	/**
	 * Constructor to Set the mode, No. of Object enter on command line argument
	 * @param ObjectCnt
	 * @param mode
	 * @param s
	 * @param F
	 */
	public SerDeser(int ObjectCnt,String mode,StdoutDisplayInterface s,FileProcessor F,String FileNameIn)
	{
		NUM_OF_OBJECTS = ObjectCnt;
		Mode = mode;
		Result = s;
		File = F;
		FileName = FileNameIn;
	}
	/**
	 * Call the appropriate method based on mode selected
	 */
	public void Reflection ()
	{
	ProxyCreator pc = new ProxyCreator();
	StoreRestoreHandler StoreRestHandler = new StoreRestoreHandler(File);
	cpointRef = (StoreRestoreI) pc.createProxy(
								 new Class[] {
								     StoreI.class, RestoreI.class
								 }, StoreRestHandler
								 );
		
	
    if(Mode.equals(SerMode.serdeser.toString()))
    {
    	Ser();
	}
    else if (Mode.equals(SerMode.deser.toString()))
    {
    	Deser();
    }
	}
	/**
	 * Serialize the object and then deserialize it, and then compare the serialized and deserialized vectors and report how many instances match
	 */
	public void Ser() {
		File.FileName = this.FileName;
		for (int i=0; i< NUM_OF_OBJECTS; i++) {
	        Random random = new Random();
			myFirst = new MyAllTypesFirst(random.nextInt(100),ThreadLocalRandom.current().nextLong(1000),"Checkpointing Objects",true,random.nextInt(100));
		    SerializableVector.add(myFirst);
		    short value = (short) random.nextInt();
		    mySecond = new MyAllTypesSecond(value,'Y',(random.nextDouble()+random.nextInt(100)),random.nextFloat(),(random.nextDouble()+random.nextInt(100)));
		    SerializableVector.add(mySecond);
	        ((StoreI) cpointRef).writeObj(myFirst, 0 ,"XML");
		    ((StoreI) cpointRef).writeObj(mySecond, 0 ,"XML");

		}
		try {
			File.SetFileDetails();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
		}
		SerializableObject myRecordRet;
		
		for (int j=0; j< 2 * NUM_OF_OBJECTS; j++) {

		    myRecordRet = ((RestoreI) cpointRef).readObj("XML");
		    DeSerializableVector.add(myRecordRet);
		}
		
		int ObjMatchCnt=0;
		int MisObjMatchCnt=0;
		for(int i=0; i<2*NUM_OF_OBJECTS; i++) {
	       if(SerializableVector.get(i).equals(DeSerializableVector.get(i))) 
	        ObjMatchCnt++;
	       else
	    	MisObjMatchCnt++;
		}
		if (ObjMatchCnt > 0 && MisObjMatchCnt == 0)
			File.Print.add(0 + " mismatched objects");
		else
			File.Print.add(MisObjMatchCnt + " mismatched objects");
		File.Print.add("");
		File.Print.add("DeSerialize Objects : ");
		File.Print.add("");
		DeserDisplay();
	}
	/**
	 * Only Deserialize the object
	 */
	public void Deser()
	{
		SerializableObject myRecordRet;
		try {
			while(File.bufferreader.readLine() != null)
			{
				myRecordRet = ((RestoreI) cpointRef).readObj("XML");
			    DeSerializableVector.add(myRecordRet);
			}
		} catch (IOException e) {
			System.out.println("Exception occured: "+e.getMessage());
			System.exit(0);
		}
		DeserDisplay();
	}
	/**
	 * Print the Deserialize Object
	 * @param DeSerializableVector
	 */
	public void DeserDisplay()
	{
     for(SerializableObject D: DeSerializableVector) {
			
            File.Print.add("Class Name : "+ D.getClass().getSimpleName());
            File.Print.add("Values : ");
            Class<?> cls =  D.getClass();
            for (Field f : cls.getDeclaredFields()) {
            	    
					try {
						f.setAccessible(true);
    			        String methodName = "get" + f.getName();
    			        Method getterMethod;
						getterMethod = cls.getMethod(methodName);
						Object invokeRet;
						
							invokeRet = getterMethod.invoke(D);
							String data = invokeRet.toString();
							File.Print.add(f.getName() + "=" + data);
					}
						 catch (IllegalAccessException e) {
							
						} catch (IllegalArgumentException e) {
							
						} catch (InvocationTargetException e) {
							
						}
    				
					catch (NoSuchMethodException | SecurityException e) {
						
					}
					
				}
            File.Print.add("");
			}
	Result.PrintOnConsole();
	}
}
