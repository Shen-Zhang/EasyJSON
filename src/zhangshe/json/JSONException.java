package zhangshe.json;

/**
 * Citation: Use the method in the following website as a referrence.
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
  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+
  String msg = null;

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
    this.msg = msg;
  } // JSONException(String)

  public JSONException(String msg, Throwable cause)
  {
    super(msg, cause);
  } // JSONException(String, Throwable)

  public JSONException(Throwable cause)
  {
    super(cause);
  } // JSONException(Throwable)

} // class JSONException
