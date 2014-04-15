package zhangshe.json;

public interface JSONValue
{
  /**
   * Convert back to a JSON string.
   */
  public String toJSONString();

  /**
   * Get the size of the JSONValue
   * 
   * @return
   */
  public int size();
} // interface JSonObject
