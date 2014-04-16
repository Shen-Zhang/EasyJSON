package zhangshe.json;

public class JSONConstant
    implements
      JSONValue
{

  // Fields
  String constant;

  int length;

  // Constructor
  public JSONConstant(String constant)
  {
    this.constant = constant;
    this.length = constant.length();
  } // JSONConstant

  // Methods
  @Override
  public String toJSONString()
  {
    return this.constant;
  } // toJSONString()

  @Override
  public int size()
  {
    return length;
  }

} // class JSONConstant
