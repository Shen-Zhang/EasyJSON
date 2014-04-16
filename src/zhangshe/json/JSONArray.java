package zhangshe.json;

import java.util.ArrayList;

public class JSONArray
    implements
      JSONValue
{

  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+
  ArrayList<JSONValue> array;
  int length = 0;

  // +-------------+----------------------------------------------------------
  // | Constructor |
  // +-------------+
  public JSONArray()
  {
    this.array = new ArrayList<JSONValue>();
    length++; // '['
  }

  // +---------+----------------------------------------------------------
  // | Methods |
  // +---------+
  void add(JSONValue val)
  {
    this.array.add(val);
    length += val.size();
  } // add(JSONValue)

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

  @Override
  public int size()
  {
    return length;
  }

} // class JSONParser
