package zhangshe.json;

public class JSONString
    implements
      JSONValue
{

  // Fields
  String str;
  int length = 0;

  // Constructor
  public JSONString(String str)
  {
    this.str = str;
    this.length = str.length();
    System.out.println("JSONString: length is " + this.length);
  } // JSONString(String)

  // Methods

  @Override
  public String toJSONString()
  { 
    return this.str;
  } // toJSONString()

  @Override
  public int size()
  {
    
    return this.length;
  }

} // class JSONString
