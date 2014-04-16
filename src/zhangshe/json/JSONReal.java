package zhangshe.json;

import java.math.BigDecimal;

/**
 * A JSON representation of real numbers
 * 
 * @author Shen Zhang
 * 
 */
public class JSONReal
    implements
      JSONValue
{
  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+
  /**
   * Store the value of the number
   */
  BigDecimal real;

  /**
   * Store the length of the string of a number
   */
  int length = 0;

  // +-------------+----------------------------------------------------------
  // | Constructor |
  // +-------------+
  /**
   * Construct a JSONReal
   * 
   * @param str
   *          a valid string
   */
  public JSONReal(String str)
  {
    this.real = new BigDecimal(str);
    this.length = str.length();
  } // JSONReal (String)

  // +---------+----------------------------------------------------------
  // | Methods |
  // +---------+
  /**
   * Convert the JSONReal back to string
   * @post A string is given
   */
  @Override
  public String toJSONString()
  {
    return this.real.toString();
  } // toJSONString()

  /**
   * Get the size of the JSONReal
   * @post An integer is given
   */
  @Override
  public int size()
  {
    return length;
  } // size()

} // class JSONReal
