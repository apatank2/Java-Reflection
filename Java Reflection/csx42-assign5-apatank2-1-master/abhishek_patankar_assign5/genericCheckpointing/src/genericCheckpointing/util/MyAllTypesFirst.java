package genericCheckpointing.util;

public class MyAllTypesFirst extends SerializableObject{
private int myInt;
private long myLong;
private String myString;
private boolean myBool;
private int myOtherInt;
public MyAllTypesFirst ()
{
	this.myInt = 0;
	this.myLong = 0;
	this.myString = "default";
	this.myBool = false;
	this.myOtherInt = 0;
}
public MyAllTypesFirst (int myIntIn,long myLongIn, String  myStringIn,boolean myBoolIn,int myOtherIntIn)
{
	this.myInt = myIntIn;
	this.myLong = myLongIn;
	this.myString = myStringIn;
	this.myBool = myBoolIn;
	this.myOtherInt = myOtherIntIn;
}
@Override
public boolean equals(Object o)
{
	if (o instanceof MyAllTypesFirst)
	{
		
		MyAllTypesFirst F = (MyAllTypesFirst)o;
		
		if (this.getmyString().equals(F.getmyString()) && this.getmyInt() == F.getmyInt() &&
				this.getmyBool() == F.getmyBool() && this.getmyLong() == F.getmyLong() &&
				this.getmyOtherInt() == F.getmyOtherInt() && this.hashCode() == F.hashCode())
		{
			return true;
		}
		
	}
	return false;
}
@Override
public int hashCode()
{
	int PrimeNumber = 17;
	int hashcode = (int) (getmyInt() + getmyOtherInt() + getmyLong() + PrimeNumber);
	return hashcode;
	
}
public void setmyInt (int myIntIn)
{
	this.myInt = myIntIn;
}

public int getmyInt ()
{
	return this.myInt;
}

public void setmyLong (long myLongIn)
{
	this.myLong = myLongIn;
}

public long getmyLong ()
{
	return this.myLong;
}

public void setmyString (String myStringIn)
{
	this.myString = myStringIn;
}

public String getmyString ()
{
	return this.myString;
}

public void setmyBool (boolean myBoolIn)
{
	this.myBool = myBoolIn;
}

public boolean getmyBool ()
{
	return this.myBool;
}

public void setmyOtherInt (int myOtherIntIn)
{
	this.myOtherInt = myOtherIntIn;
}

public int getmyOtherInt ()
{
	return this.myOtherInt;
}


}
