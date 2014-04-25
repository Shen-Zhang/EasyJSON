package zhangshe.json;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * A JSON representation of array
 * 
 * @author Shen Zhang
 * 
 */
public class JSONArray
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
    // create an arraylist to store values
    this.array = new ArrayList<JSONValue>();
    // set length to 2, which stands for '[' and ']'
    length = 2;
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
  public String toString()
  {
    String str = "[";
    int i = 0;
    if (array.size() == 0)
      return str + "]";
    for (i = 0; i < array.size() - 1; i++)
      {
        str += array.get(i).toString() + ",";
      } // for(i)
    str += array.get(i).toString() + "]";
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

  /**
   * print the content in the JSONArray
   */
  @Override
  public void print()
  {
    // print the array with format = 1
    this.print(1);
  } // print()

  /**
   * print the content in the JSONArray, format the result with given number of spaces
   */
  @Override
  public void print(int format)
  {
    // format and print the open brackets
    pen.format("%" + -format + "s", "[");
    //pen.print("[");
    // if the array is empty, then print the closed brackets and return
    if (array.size() == 0)
      pen.print("]");
    // if the array is not empty, start a new line, print all values stored in the array
    else
      {
        pen.println();
        int i = 0;
        for (i = 0; i < array.size() - 1; i++)
          {
            // in order to have a better indentation, values are printed with 3 more spaces than the brackets
            JSONValue next = array.get(i);
            if (next.type().compareTo("JSONArray") == 0
                || next.type().compareTo("JSONObject") == 0)
              next.print(format + format);
            else
              next.print(format);
            pen.println(",");
          } // for (i)
        // print the last element in the array
        JSONValue next = array.get(i);
        if (next.type().compareTo("JSONArray") == 0
            || next.type().compareTo("JSONObject") == 0)
          next.print(format + format);
        else
          next.print(format);
        pen.println();
        // format and print the closed brackets
        pen.format("%" + format + "s", "]");
        //pen.print("]");
        pen.flush();
      } // if array.size() != 0
  } // print(int)

  /**
   * Get the value from this JSONArray
   * @param index, an integer within the length of this.array
   * @return
   * A JSONValue which is stored in the given index of this array 
   */
  public JSONValue get(int index)
  {
    return this.get(index);
  } // get(int)

  /**
   * Set a given position with given value
   * @param index, an integer within the length of this.array
   * @param val, a valid JSONValue
   * @post JSONArray is updated with given val added
   */
  public void set(int index, JSONValue val)
  {
    this.array.set(index, val);
  } // set(int, val)

  /**
   * To get the type of this JSONValue
   */
  @Override
  public String type()
  {
    return "JSONArray";
  } // type()
} // class JSONArray
