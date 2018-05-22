package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;

public class XMLDeserialization implements SerStrategyI {
	public FileProcessor fi;
	String line;
	Class<?> cls;
	
	public  XMLDeserialization()
	{}
	public XMLDeserialization (FileProcessor File)
	{
		this.fi = File;
	}
	@Override
	public void processInput(SerializableObject sObject) {
		// TODO Auto-generated method stub
		
	}
	/**
     * Deserialize the Object using Reflection
     */
	@Override
	public SerializableObject DeSerialize() {
		// TODO Auto-generated method stub
		Object NewObject = null;
		while ((line = fi.FileReader())!= null)
		{
			if (line.contains("</DPSerialization>"))
			{
				return (SerializableObject)NewObject;
			}
			else if (line.contains("<DPSerialization>"))
			{
				
			}
			else if (line.contains("<complexType"))
			{
				String Ignore = "[\"]";
				String[] tokens = line.split(Ignore);
				try {
					cls = Class.forName(tokens[1]);
				} catch (ClassNotFoundException e) {
					System.err.println(e);
					System.exit(1);
				}
                try {
                	NewObject = (SerializableObject)cls.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					System.err.println(e);
					System.exit(1);
				}
				
			}
			else if (line.contains("</complexType>"))
			{
				
			}
			else
			{
				String type = line.substring(line.indexOf("<") + 1, line.indexOf("xsi") - 1);
				Field[] fld = cls.getDeclaredFields();
				for(Field f : fld){
					if (f.getName().equals(type))
					{
						String value = line.substring(line.indexOf("\">") + 2, line.indexOf("</"));
						String methodName = "set" + f.getName();
					        Method getterMethod;
							try {
								getterMethod = cls.getDeclaredMethod(methodName,f.getType());
								try {
									if (f.getType() == int.class)
									{
										getterMethod.invoke(NewObject,Integer.parseInt(value.trim()) );
									}
									else if (f.getType() == long.class)
									{
										getterMethod.invoke(NewObject,Long.parseLong(value.trim()) );
									}
									else if (f.getType() == String.class)
									{
										getterMethod.invoke(NewObject,value.trim() );
									}
									else if (f.getType() == boolean.class)
									{
										getterMethod.invoke(NewObject,Boolean.parseBoolean(value.trim()) );
									}
									else if (f.getType() == short.class)
									{
										getterMethod.invoke(NewObject,Short.parseShort(value.trim()) );
									}
									else if (f.getType() == double.class)
									{
										getterMethod.invoke(NewObject,Double.parseDouble(value.trim()) );
									}
									else if (f.getType() == float.class)
									{
										getterMethod.invoke(NewObject,Float.parseFloat(value.trim()) );
									}
									else if (f.getType() == char.class)
									{
										getterMethod.invoke(NewObject,value.trim().charAt(0) );
									}
									
								} catch (IllegalAccessException
										| IllegalArgumentException
										| InvocationTargetException e) {
									System.err.println(e);
									System.exit(1);
								}
							} catch (NoSuchMethodException | SecurityException e) {
								System.err.println(e);
								System.exit(1);
							}
						
						
					}
				}
				
			}
		
		}
		return null;
	}
}
