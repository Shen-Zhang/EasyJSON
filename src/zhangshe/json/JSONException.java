package zhangshe.json;

/**
 * Citation: Use the method in the following web site as a reference.
 * http://stackoverflow
 * .com/questions/1754315/how-to-create-our-own-exceptions-in-java
 * 
 * To throw an Exception
 * 
 * @author Shen Zhang
 * 
 */

@SuppressWarnings("serial")
public class JSONException
    extends Exception
{

  // +--------------+----------------------------------------------------------
  // | Constructors |
  // +--------------+
  /**
   * A bunch of JSONExceptions
   */
  public JSONException()
  {
    super();
  } // JSONException()

  public JSONException(String msg)
  {
    super(msg);
  } // JSONException(String)

  public JSONException(String str, String expected, int index)
  {
    System.out.println(str);
    System.err.format("%" + (1 + index) + "s", "^");
    System.err.println("\n" + "Invalid input: Expecting " + expected);
  } // JSONException(String, String, int)

  public JSONException(String msg, Throwable cause)
  {
    super(msg, cause);
  } // JSONException(String, Throwable)

  public JSONException(Throwable cause)
  {
    super(cause);
  } // JSONException(Throwable)

} // class JSONException
