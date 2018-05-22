package genericCheckpointing.xmlStoreRestore;


import genericCheckpointing.util.SerializableObject;

public interface SerStrategyI {
	void processInput(SerializableObject sObject);
	public SerializableObject DeSerialize();
}
