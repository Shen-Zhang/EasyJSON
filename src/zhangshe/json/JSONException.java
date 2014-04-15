package zhangshe.json;

/* Citation: http://stackoverflow.com/questions/1754315/how-to-create-our-own-exceptions-in-java */
public class JSONException
    extends
      Exception
{
  // Fields
  String msg = null;

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
