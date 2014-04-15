package zhangshe.json;

import java.math.BigDecimal;

public class JSONReal
    implements
      JSONValue
{
  // Fields
  BigDecimal real;

  // Constructor
  public JSONReal(String str)
  {
    this.real = new BigDecimal(str);
  } // JSONReal (String)

  // Methods
  @Override
  public String toJSONString()
  {
    // TODO Auto-generated method stub
    return null;
  } // toJSONString()

} // class JSONReal
