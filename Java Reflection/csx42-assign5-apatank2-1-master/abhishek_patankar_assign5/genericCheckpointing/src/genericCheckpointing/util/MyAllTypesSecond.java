package genericCheckpointing.util;

public class MyAllTypesSecond extends SerializableObject{
private short myShortT;
private char myCharT;
private double myDoubleT;
private float myFloatT;
private double myOtherDoubleT;
public MyAllTypesSecond ()
{
	this.myShortT = 0;
	this.myCharT = 'N';
	this.myDoubleT = 0;
	this.myFloatT = 0;
	this.myOtherDoubleT = 0;
}
public MyAllTypesSecond (short myShortTIn,char myCharTIn,double myDoubleTIn,float myFloatTIn,double myOtherDoubleTIn)
{
	this.myShortT = myShortTIn;
	this.myCharT = myCharTIn;
	this.myDoubleT = myDoubleTIn;
	this.myFloatT = myFloatTIn;
	this.myOtherDoubleT = myOtherDoubleTIn;
}

@Override
public boolean equals(Object o)
{
	if (o instanceof MyAllTypesSecond)
	{
		MyAllTypesSecond F = (MyAllTypesSecond)o;
		if (this.getmyCharT() == F.getmyCharT() && this.getmyDoubleT() == F.getmyDoubleT() &&
				this.getmyFloatT() == F.getmyFloatT() && this.getmyShortT() == F.getmyShortT() &&
				this.getmyOtherDoubleT() == F.getmyOtherDoubleT() && this.hashCode() == F.hashCode())
		{
			return true;
		}
	}
	return false;
}
@Override
public int hashCode()
{
	int PrimeNumber = 97;
	int hashcode = (int) (getmyDoubleT() + getmyFloatT() + getmyShortT() + getmyOtherDoubleT() + PrimeNumber);
	return hashcode;
	
}
public void setmyShortT (short myShortTIn)
{
	this.myShortT = myShortTIn;
}

public short getmyShortT()
{
	return this.myShortT;
}

public void setmyCharT (char myCharTIn)
{
	this.myCharT = myCharTIn;
}

public char getmyCharT()
{
	return this.myCharT;
}

public void setmyDoubleT (double myDoubleTIn)
{
	this.myDoubleT = myDoubleTIn;
}

public double getmyDoubleT()
{
	return this.myDoubleT;
}

public void setmyFloatT (float myFloatTIn)
{
	this.myFloatT = myFloatTIn;
}

public float getmyFloatT()
{
	return this.myFloatT;
}

public void setmyOtherDoubleT (double myOtherDoubleTIn)
{
	this.myOtherDoubleT = myOtherDoubleTIn;
}

public double getmyOtherDoubleT()
{
	return this.myOtherDoubleT;
}
}
