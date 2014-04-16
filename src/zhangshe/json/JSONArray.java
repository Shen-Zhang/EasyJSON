package zhangshe.json;

import java.util.ArrayList;

public class JSONArray
    implements
      JSONValue
{
  // Fields
  ArrayList<JSONValue> array;
  int length = 0;

  // Constructor
  public JSONArray()
  {
    this.array = new ArrayList<JSONValue>();
    length++; // '['
  }

  // Methods
  void add(JSONValue val)
  {
    this.array.add(val);
    System.out.println("JSONArray: add 1");
    length += val.size();
  } // add(JSONValue)

  @Override
  public String toJSONString()
  {
    // TODO Auto-generated method stub
    return null;
  } // toJSONString()

  @Override
  public int size()
  {
    return length;
  }

} // class JSONParser
