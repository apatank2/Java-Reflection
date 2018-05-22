package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.SerializableObject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import genericCheckpointing.util.FileProcessor;

public class StoreRestoreHandler implements InvocationHandler{
	public FileProcessor fi;
	public StoreRestoreHandler()
	{
		
	}
	public StoreRestoreHandler(FileProcessor f)
	{
		this.fi = f;
	}
	/**
	 * Strategy Pattern to Invoke Serialize and Deserialize methods
	 */
	public Object invoke(Object proxy, Method m, Object[] args)
			throws Throwable {
		if (m.getName().equals("writeObj"))
		{
		SerStrategyI Strategy = new XMLSerialization(fi);
		serializeData((SerializableObject)args[0],Strategy);
		}
		else if (m.getName().equals("readObj"))
		{
			Object obj = null;
			obj = (SerializableObject) DeserializeData();
			return obj;
		}
		return null;
	}
	 public void serializeData(SerializableObject sObject, SerStrategyI sStrategy) {
         sStrategy.processInput(sObject);
     }
	 public Object DeserializeData()
	 {
		    SerStrategyI Strategy = new XMLDeserialization(fi);
			SerializableObject obj = Strategy.DeSerialize();
			return obj;
	 }
}
