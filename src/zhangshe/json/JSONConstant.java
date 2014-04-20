package zhangshe.json;

import java.io.PrintWriter;

/**
 * A JSON representation of null, true and false
 * 
 * @author Shen Zhang
 * 
 */
public class JSONConstant
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
   * Store the value of constant
   */
  String constant;
  /**
   * Store the length of the JSONConstant
   */
  int length;

  // +-------------+----------------------------------------------------------
  // | Constructor |
  // +-------------+
  /**
   * Construct a JSONConstant
   * 
   * @param constant
   *          true, false or null
   */
  public JSONConstant(String constant)
  {
    this.constant = constant;
    this.length = constant.length();
  } // JSONConstant

  // +---------+----------------------------------------------------------
  // | Methods |
  // +---------+
  /**
   * Convert a JSONConstant to a string
   * @post A string is given
   */
  @Override
  public String toJSONString()
  {
    return this.constant;
  } // toJSONString()

  /**
   * Get the size of a JSONConstant
   * @post An integer is given
   */
  @Override
  public int size()
  {
    return length;
  } // size()

  @Override 
  public void print()
  {
    this.print(1);
  } // print(int)
  
  @Override
  public void print(int format)
  {
    pen.format("%" + format + "s", "");
    pen.print(this.constant);
    
    pen.flush();
  } // print(String)
} // class JSONConstant
