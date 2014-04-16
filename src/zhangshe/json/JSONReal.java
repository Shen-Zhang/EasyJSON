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
    System.out.println("JSONReal:" + this.real);
    this.length = str.length();
    System.out.println("JSONReal: length is " + this.length);
  } // JSONReal (String)

  // Methods
  @Override
  public String toJSONString()
  {
    return this.real.toString();
  } // toJSONString()

  @Override
  public int size()
  {
    return length;
  }

} // class JSONReal
