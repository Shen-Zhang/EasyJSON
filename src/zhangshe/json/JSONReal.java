package zhangshe.json;

import java.io.PrintWriter;
import java.math.BigDecimal;

/**
 * A JSON representation of real numbers
 * 
 * @author Shen Zhang
 * 
 */
public class JSONReal
    implements JSONValue
{
  /**
   * A PrintWriter for print()
   */
  static PrintWriter pen = new PrintWriter(System.out, true);
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
  public String toString()
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

  /**
   * print the content in the JSONReal
   */
  @Override
  public void print()
  {
    this.print(1);
  } // print()

  /**
   * print the content in the JSONReal, format the result with given number of spaces
   */
  @Override
  public void print(int format)
  {
    pen.format("%" + (format + this.size()) + "s", this.real);
    pen.flush();
  } // print(String)

  /**
   * To get the type of this JSONValue
   */
  @Override
  public String type()
  {
    return "JSONReal";
  } // type()
} // class JSONReal
