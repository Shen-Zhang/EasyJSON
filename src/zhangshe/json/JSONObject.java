package zhangshe.json;

import java.util.Hashtable;

public class JSONObject
    implements
      JSONValue
{
  // Fields
  Hashtable<String, JSONValue> hash;

  // Constructor
  public JSONObject()
  {
    this.hash = new Hashtable<String, JSONValue>();
  } // JSONObject()

  // Methods
  public void add(String key, JSONValue value)
  {
    this.hash.put(key, value);
  } // add(String, JSONValue)

  @Override
  public String toJSONString()
  {
    // TODO Auto-generated method stub
    return null;
  } // toJSONString()

} // class JSONObject
