package zhangshe.json;

public class JSONString
    implements
      JSONValue
{

  // Fields
  String str;
  int length;

  // Constructor
  public JSONString(String str)
  {
    this.str = str;
    this.length = str.length();
  } // JSONString(String)

  // Methods

  @Override
  public String toJSONString()
  {
    // TODO Auto-generated method stub
    return null;
  } // toJSONString()

  @Override
  public int size()
  {
    return length;
  }

} // class JSONString
