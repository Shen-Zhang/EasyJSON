package zhangshe.json;

import java.io.PrintWriter;

/**
 * A JSON representation of strings
 * 
 * @author Shen Zhang
 * 
 */
public class JSONString
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
   * Store the content of the string
   */
  String str;
  /**
   * Store the length of the string, including the quotation marks
   */
  int length = 0;

  // +-------------+----------------------------------------------------------
  // | Constructor |
  // +-------------+
  /**
   * Construct a JSONString by given string
   * 
   * @param str
   *          A valid string
   */
  public JSONString(String str)
  {
    this.str = str;
    // set this.length to the length of str. 
    this.length = str.length();
  } // JSONString(String)

  // +---------+----------------------------------------------------------
  // | Methods |
  // +---------+
  /**
   * Convert a JSONString to String
   * 
   * @post A string is given
   */
  @Override
  public String toString()
  {
    return this.str;
  } // toJSONString()

  /**
   * Get the size of JSONString
   * 
   * @post An integer is given
   */
  @Override
  public int size()
  {
    return this.length;
  } // size()

  /**
   * print the content in the JSONString
   */
  @Override
  public void print()
  {
    // print the array with format = 1
    this.print(1);
  } // print()

  /**
   * print the content in the JSONString, format the result with given number of spaces
   */
  @Override
  public void print(int format)
  {
    // format and print the string
    pen.format("%" + (format + this.length) + "s", this.str);
    pen.flush();
  } // print(String)

  /**
   * To get the type of this JSONValue
   */
  @Override
  public String type()
  {
    return "JSONString";
  } // type()
} // class JSONString
