package genericCheckpointing.xmlStoreRestore;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import genericCheckpointing.util.FileDisplayInterface;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.Results;
import genericCheckpointing.util.SerializableObject;

public class XMLSerialization implements SerStrategyI {
	FileDisplayInterface Result ;
	public FileProcessor fi;
	
	public  XMLSerialization(FileProcessor File)
	{
		this.fi = File;
		Result = new Results(File);
	}
	/**
     * Deserialize the Object using Reflection
     */
	@Override
	public void processInput(SerializableObject sObject) {
		// TODO Auto-generated method stub
		fi.XMLFile.add("<DPSerialization>");
		Class<?> cls = sObject.getClass();
		fi.XMLFile.add( " <complexType xsi:type=\""+cls.getName()+"\">");
		Field[] fld = cls.getDeclaredFields();
		for(Field f : fld){
	    try {
		String methodName = "get" + f.getName();
        Method getterMethod;
		   
			getterMethod = cls.getMethod(methodName);
			Object invokeRet = getterMethod.invoke(sObject);
			String data = invokeRet.toString();
			if(f.getType() == String.class)
			fi.XMLFile.add("  <"+f.getName()+" xsi:type=\"xsd:"+"string"+"\">"+data+"</"+f.getName()+">");
			else if(f.getType() == int.class)
			{
			if(Integer.parseInt(data) > 10)	
			{
			fi.XMLFile.add("  <"+f.getName()+" xsi:type=\"xsd:"+f.getType()+"\">"+Integer.parseInt(data)+"</"+f.getName()+">");
			}
			}
			else if(f.getType() == double.class)
			{
			if(Double.parseDouble(data) > 10)	
			{
			fi.XMLFile.add("  <"+f.getName()+" xsi:type=\"xsd:"+f.getType()+"\">"+Double.parseDouble(data)+"</"+f.getName()+">");
			}
			}
			else if(f.getType() == long.class)
			{
			if(Long.parseLong(data) > 10)	
			{
			fi.XMLFile.add("  <"+f.getName()+" xsi:type=\"xsd:"+f.getType()+"\">"+Long.parseLong(data) +"</"+f.getName()+">");
			}
			}
			else  if(f.getType() == float.class)
			fi.XMLFile.add("  <"+f.getName()+" xsi:type=\"xsd:"+f.getType()+"\">"+Float.parseFloat(data) +"</"+f.getName()+">");
			else  if(f.getType() == short.class)
				fi.XMLFile.add("  <"+f.getName()+" xsi:type=\"xsd:"+f.getType()+"\">"+Short.parseShort(data)+"</"+f.getName()+">");	
			else  if(f.getType() == boolean.class)
				fi.XMLFile.add("  <"+f.getName()+" xsi:type=\"xsd:"+f.getType()+"\">"+Boolean.parseBoolean(data)+"</"+f.getName()+">");
			else  if(f.getType() == char.class)
				fi.XMLFile.add("  <"+f.getName()+" xsi:type=\"xsd:"+f.getType()+"\">"+data.charAt(0)+"</"+f.getName()+">");
		} catch (NoSuchMethodException e) {
			System.err.println(e);
			System.exit(1);
		} catch (SecurityException e) {
			System.err.println(e);
			System.exit(1);
		}
       
		 catch (IllegalAccessException e) {
			 System.err.println(e);
				System.exit(1);
		} catch (IllegalArgumentException e) {
			System.err.println(e);
			System.exit(1);
		} catch (InvocationTargetException e) {
			System.err.println(e);
			System.exit(1);
		}
		}
		fi.XMLFile.add(" </complexType>");
		fi.XMLFile.add("</DPSerialization>");
		try {
			Result.FileWriter(fi.FileName);
		} catch (IOException e) {
			
		}
	}
	@Override
	public SerializableObject DeSerialize() {
		return null;
	}
	

}
