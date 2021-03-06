package zhangshe.json;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 *  A JSON representation of array
 * @author Shen Zhang
 *
 */
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
   * Store the number of characters in the JSONObject, including the open and closed brackets.
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
    // create a hash table to store keys and values
    this.hash = new Hashtable<JSONValue, JSONValue>();
    // set length to 2, which stands for the '{' and '}'
    this.length = 2;
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
    // add a pair of key and value into the hash table
    this.hash.put(key, value);

    // increment length by the size of key, the size of value
    // and the size of colon, which is one
    length += (key.size() + value.size() + 1);
  } // add(String, JSONValue)

  /**
   * Convert a JSONObject into a string
   * 
   * @pre none
   * @post a String is given
   */
  @Override
  public String toString()
  {
    // initialize the string to "{"
    String str = "{";

    Enumeration<JSONValue> keys = hash.keys(); // get all keys in the HashTable

    while (keys.hasMoreElements())
      {
        JSONValue temp = keys.nextElement();
        str += temp.toString() + ":" + hash.get(temp).toString();
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

  /**
   * Get the value in the Object by key
   * 
   * @param key
   * @return 
   * The value paired with the given key
   */
  public JSONValue get(JSONValue key)
  {
    return this.hash.get(key);
  } // get(JSONValue)

  /**
   * print the content in the JSONObject
   */
  @Override
  public void print()
  {
    // print the object with format = 1
    this.print(1);
  } // print()

  /**
   * print the content in the JSONObject, format the result with given number of spaces
   */
  @Override
  public void print(int format)
  {
    // format and print the open bracket
    pen.println();
    pen.format("%" + format + "s", "{");
    //pen.print("{");

    // get all keys in the HashTable
    Enumeration<JSONValue> keys = hash.keys();
    // if the Object is empty, print the closed bracket and return
    if (!keys.hasMoreElements())
      {
        pen.print("}");
        pen.flush();
      } // if keys have no more elements
    else
      {
        // otherwise, print all keys and values in the JSONObject
        while (keys.hasMoreElements())
          {
            pen.println();
            JSONValue temp = keys.nextElement();
            temp.print(format + 1);
            pen.print(":");
            pen.flush();
            JSONValue next = hash.get(temp);
            if (next.type().compareTo("JSONArray") == 0
                || next.type().compareTo("JSONObject") == 0)
              next.print(format + temp.size());
            else
              next.print(format);
            if (keys.hasMoreElements())
              pen.print(",");
          } // for(i)

        // start a new line and put the closed bracket
        pen.println();
        pen.format("%" + format + "s", "}");
        //pen.print("}");
        pen.flush();
      } // if keys.hasMoreElments()
  } // print(String)

  /**
   * To get the type of this JSONValue
   */
  @Override
  public String type()
  {
    return "JSONObject";
  } // type()
} // class JSONObject
