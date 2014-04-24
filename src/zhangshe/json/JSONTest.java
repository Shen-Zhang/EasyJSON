package zhangshe.json;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class JSONTest
{

  @Test
  public void testEmpty()
    throws JSONException,
      IOException
  {
    assertEquals("[]", JSON.parse("[]").toString());
    assertEquals("{}", JSON.parse("{}").toString());
    assertEquals("[{}]", JSON.parse("[{}]").toString());
  } // testEmpty

  @Test
  public void testString()
    throws JSONException,
      IOException
  {
    assertEquals("[\"abc\"]", JSON.parse("[\"abc\"]").toString());
    assertEquals("[\"abc\",\"dcecdcdcdcdcd\"]",
                 JSON.parse("[\"abc\",\"dcecdcdcdcdcd\"]").toString());
    assertEquals("{\"abc\":\"ABC\"}",
                 JSON.parse("{\"abc\":\"ABC\"}").toString());
  } // testString()

  @Test
  public void testNum()
    throws JSONException,
      IOException
  {
    assertEquals("[3]", JSON.parse("[3]").toString());
    assertEquals("[3.5]", JSON.parse("[3.5]").toString());
    assertEquals("[-3.5]", JSON.parse("[-3.5]").toString());
    assertEquals("[1234567890]", JSON.parse("[1234567890]").toString());

    // negative sign cannot appear twice
    try
      {
        assertEquals("[--3]", JSON.parse("[--3]").toString());
      }
    catch (JSONException e)
      {
        // success
      } // try catch

    // e cannot be the first digit
    try
      {
        assertEquals("[e3]", JSON.parse("[e3]").toString());
      }
    catch (JSONException e)
      {
        // success
      } // try catch

    // decimal mark cannot appear twice
    try
      {
        assertEquals("[3..3]", JSON.parse("[3..3]").toString());
      }
    catch (JSONException e)
      {
        // success
      } // try catch

  } // testNum()

  @Test
  public void testConstant()
    throws JSONException,
      IOException
  {
    assertEquals("[null]", JSON.parse("[null]").toString());
    assertEquals("[false]", JSON.parse("[false]").toString());
    assertEquals("[true]", JSON.parse("[true]").toString());
    assertEquals("[true,false,null,false,true]",
                 JSON.parse("[true,false,null,false,true]").toString());
  } // testConstant()

  @Test
  public void testObject()
    throws JSONException,
      IOException
  {
    assertEquals("{\"Shen\":\"Zhang\"}",
                 JSON.parse("{\"Shen\":\"Zhang\"}").toString());
  } // testObject()

  @Test
  public void testArr()
    throws JSONException,
      IOException
  {

    assertEquals("[[[[[]]]]]", JSON.parse("[[[[[]]]]]").toString());
    assertEquals("[123,null,\"csc\",{\"Shen\":\"Zhang\"},[1]]",
                 JSON.parse("[123,null,\"csc\",{\"Shen\":\"Zhang\"},[1]]")
                     .toString());
  } // testArr()

} // JSONTest
