package zhangshe.json;

/**
 * An interface of each JSONValue
 * 
 * @author Shen Zhang
 * 
 */
public interface JSONValue
{
  /**
   * Convert a JSONValue back to a string.
   */
  public String toString();

  /**
   * Get the length of a JSONValue, including the quotation marks and
   * open/closed brackets if the JSONValue is an Object, Array or String
   * 
   * @return An integer which stores the value of length of the JSONValue.
   */
  public int size();

  /**
   *  Print a JSONValue
   */
  public void print();

  /**
   * Print a JSONValue. The result is formatted by the given number of spaces
   */
  public void print(int format);

  /**
   * To get the type of the JSONValue, like JSONObject, JSONArray, JSONReal, JSONConstant, and JSONString
   * @return A string, which specifies the type of this JSONValue
   */
  public String type();
} // interface JSonObject
