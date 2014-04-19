package zhangshe.json;

/**
 * A JSON representation of strings
 * 
 * @author Shen Zhang
 * 
 */
public class JSONString
    implements JSONValue
{

  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+
  /**
   * Store the content of the string
   */
  String str;
  /**
   * Store the lenght of the string
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
  public String toJSONString()
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

} // class JSONString
