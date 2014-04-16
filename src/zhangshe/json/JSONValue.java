package zhangshe.json;

/**
 * An interface of each JSONValue
 * 
 * @author Shen Zhang
 * 
 */
public interface JSONValue
{
  /**
   * Convert back to a JSON string.
   */
  public String toJSONString();

  /**
   * Get the size of the JSONValue
   * 
   * @return An integer, the size of the JSONValue
   */
  public int size();
} // interface JSonObject
