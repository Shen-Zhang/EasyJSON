package zhangshe.json;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;

public class JSONObject
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
   * A Hashtable to store objects
   */
  Hashtable<JSONValue, JSONValue> hash;

  /**
   * The size of this JSONObject
   */
  int length = 0;

  // +-------------+----------------------------------------------------------
  // | Constructor |
  // +-------------+
  /**
   * Construct a JSONObject
   */
  public JSONObject()
  {
    this.hash = new Hashtable<JSONValue, JSONValue>();
    length++; // '{';
  } // JSONObject()

  // +---------+----------------------------------------------------------
  // | Methods |
  // +---------+
  /**
   * Add paris into JSONObject
   * 
   * @param key
   *          A JSONValue
   * @param value
   *          A JSONValue
   * @pre Both key and value should be valid JSONValue
   * @post this JSONObject contains one more pair, and the size is incremented
   *       by 1
   */
  public void add(JSONValue key, JSONValue value)
  {
    this.hash.put(key, value);
    length += (key.size() + value.size() + 1); // ':'
  } // add(String, JSONValue)

  /**
   * Convert a JSONObject into a string
   * 
   * @pre none
   * @post a String is given
   */
  @Override
  public String toJSONString()
  {

    String str = "{"; // initialize the string
    Enumeration<JSONValue> keys = hash.keys(); // get all keys in the HashTable

    while (keys.hasMoreElements())
      {
        JSONValue temp = keys.nextElement();
        str += temp.toJSONString() + ":" + hash.get(temp).toJSONString();
        if (keys.hasMoreElements())
          str += ",";
      } // for(i)
    str += "}";
    return str;
  } // toJSONString()

  /**
   * Get the size of the JSONObject
   * 
   * @post An Integer is given
   */
  @Override
  public int size()
  {
    return length;
  } // size()

  @Override
  public void print()
  {
    this.print(1,0);
  } // print()

  @Override
  public void print(int begin, int format)
  {
    pen.format("%" + begin + "s", "{");

    Enumeration<JSONValue> keys = hash.keys(); // get all keys in the HashTable
    if (!keys.hasMoreElements())
      {
        pen.print("}");
        pen.flush();
        return;
      } // if keys have no more elements
    while (keys.hasMoreElements())
      {
        pen.println();
        JSONValue temp = keys.nextElement();
        temp.print(begin+2, format);
        pen.print(":");
        pen.flush();
        hash.get(temp).print(format,0);
        if (keys.hasMoreElements())
          pen.print(",");
      } // for(i)
    pen.println();
    pen.format("%" + (format + 1) + "s", "}");
    pen.flush();
  } // print(String)
} // class JSONObject
