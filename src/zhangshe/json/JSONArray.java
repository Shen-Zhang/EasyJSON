package zhangshe.json;

import java.util.ArrayList;

public class JSONArray
    implements
      JSONValue
{
  // Fields
  ArrayList<JSONValue> array;

  // Constructor
  public JSONArray()
  {
    this.array = new ArrayList<JSONValue>();
  }

  // Methods
  void add(JSONValue val)
  {
    this.array.add(val);
  } // add(JSONValue)

  @Override
  public String toJSONString()
  {
    // TODO Auto-generated method stub
    return null;
  } // toJSONString()

} // class JSONParser
