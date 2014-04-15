package zhangshe.json;

import java.util.Hashtable;

public class JSONObject
    implements
      JSONValue
{
  // Fields
  Hashtable<JSONValue, JSONValue> hash;
  int length = 0;

  // Constructor
  public JSONObject()
  {
    this.hash = new Hashtable<JSONValue, JSONValue>();
    length++; //'{';
  } // JSONObject()

  // Methods
  public void add(JSONValue key, JSONValue value)
  {
    this.hash.put(key, value);
    length += (key.size() + value.size() + 1); // ':'
  } // add(String, JSONValue)

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

} // class JSONObject
