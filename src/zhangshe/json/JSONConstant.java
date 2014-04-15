package zhangshe.json;

public class JSONConstant
    implements
      JSONValue
{

  // Fields
  String constant;

  int length;

  // Constrcutor
  public JSONConstant(String constant)
  {
    this.constant = constant;
    this.length = constant.length();
  } // JSONConstant

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

} // class JSONConstant
