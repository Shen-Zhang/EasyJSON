package zhangshe.json;

import java.util.Collection;
import java.util.Enumeration;
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
    length++; // '{';
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

    String str = "{";
    Enumeration<JSONValue> keys = hash.keys();
    //Enumeration<JSONValue> values = hash.elements();

    while (keys.hasMoreElements())
      {
        JSONValue temp = keys.nextElement();
        str +=
            temp.toJSONString() + ":"
                + hash.get(temp).toJSONString() + ",";
      } // for(i)
    str += "}";
    return str;
  } // toJSONString()

  @Override
  public int size()
  {
    return length;
  }

} // class JSONObject
