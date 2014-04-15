package zhangshe.json;

import java.math.BigDecimal;

public class JSONReal
    implements
      JSONValue
{
  // Fields
  BigDecimal real;
  int length;

  // Constructor
  public JSONReal(String str)
  {

    this.real = new BigDecimal(str);
    this.length = str.length();
  } // JSONReal (String)

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

} // class JSONReal
