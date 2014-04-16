package zhangshe.json;

import java.util.ArrayList;

/**
 * A JSON representation of array
 * 
 * @author Shen Zhang
 * 
 */
public class JSONArray
    implements
      JSONValue
{

  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+
  /**
   * Store the values
   */
  ArrayList<JSONValue> array;

  /**
   * store the size of this JSONArray
   */
  int length = 0;

  // +-------------+----------------------------------------------------------
  // | Constructor |
  // +-------------+
  /**
   * Construct a JSONArray
   */
  public JSONArray()
  {
    this.array = new ArrayList<JSONValue>();
    length++; // '['
  } // JSONArray()

  // +---------+----------------------------------------------------------
  // | Methods |
  // +---------+
  /**
   * Add a value into JSONArray
   * 
   * @param val
   *          A JSONValue
   * 
   * @pre val should be a valid JSONValue
   * @post JSONArray contains one more element, and the size of JSONArray
   *       increments by 1
   */
  void add(JSONValue val)
  {
    this.array.add(val);
    length += val.size();
  } // add(JSONValue)

  /**
   * Convert a JSONArray back into a string
   * 
   * @post A string is given
   */
  @Override
  public String toJSONString()
  {
    String str = "[";
    int i = 0;
    if (array.size() == 0)
      return str + "]";
    for (i = 0; i < array.size() - 1; i++)
      {
        str += array.get(i).toJSONString() + ",";
      } // for(i)
    str += array.get(i).toJSONString() + "]";
    return str;
  } // toJSONString()

  /**
   * Get the size of this JSONArray
   * 
   * @post An integer is given
   */
  @Override
  public int size()
  {
    return length;
  } // size()

} // class JSONParser
