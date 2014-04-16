package zhangshe.json;

/**
 * A JSON representation of null, true and false
 * 
 * @author Shen Zhang
 * 
 */
public class JSONConstant
    implements
      JSONValue
{

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

} // class JSONConstant
