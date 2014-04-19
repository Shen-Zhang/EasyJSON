package zhangshe.json;

import static org.junit.Assert.*;

import org.junit.Test;

public class JSONTest
{

  @Test
  public void testEmpty()
    throws JSONException
  {
    assertEquals("[]", JSON.parse("[]").toJSONString());
    assertEquals("{}", JSON.parse("{}").toJSONString());
    assertEquals("[{}]", JSON.parse("[{}]").toJSONString());
  } // testEmpty

  @Test
  public void testString()
    throws JSONException
  {
    assertEquals("[\"abc\"]", JSON.parse("[\"abc\"]").toJSONString());
    assertEquals("[\"abc\",\"dcecdcdcdcdcd\"]",
                 JSON.parse("[\"abc\",\"dcecdcdcdcdcd\"]").toJSONString());
    assertEquals("{\"abc\":\"ABC\"}",
                 JSON.parse("{\"abc\":\"ABC\"}").toJSONString());
  } // testString()

  @Test
  public void testNum()
    throws JSONException
  {
    assertEquals("[3]", JSON.parse("[3]").toJSONString());
    assertEquals("[3.5]", JSON.parse("[3.5]").toJSONString());
    assertEquals("[-3.5]", JSON.parse("[-3.5]").toJSONString());
    assertEquals("[1234567890]", JSON.parse("[1234567890]").toJSONString());

    // negative sign cannot appear twice
    try
      {
        assertEquals("[--3]", JSON.parse("[--3]").toJSONString());
      }
    catch (JSONException e)
      {
        // success
      } // try catch

    // e cannot be the first digit
    try
      {
        assertEquals("[e3]", JSON.parse("[e3]").toJSONString());
      }
    catch (JSONException e)
      {
        // success
      } // try catch

    // decimal mark cannot appear twice
    try
      {
        assertEquals("[3..3]", JSON.parse("[3..3]").toJSONString());
      }
    catch (JSONException e)
      {
        // success
      } // try catch

  } // testNum()

  @Test
  public void testConstant()
    throws JSONException
  {
    assertEquals("[null]", JSON.parse("[null]").toJSONString());
    assertEquals("[false]", JSON.parse("[false]").toJSONString());
    assertEquals("[true]", JSON.parse("[true]").toJSONString());
    assertEquals("[true,false,null,false,true]",
                 JSON.parse("[true,false,null,false,true]").toJSONString());
  } // testConstant()

  @Test
  public void testObject()
    throws JSONException
  {
    assertEquals("{\"Shen\":\"Zhang\"}",
                 JSON.parse("{\"Shen\":\"Zhang\"}").toJSONString());
  } // testObject()

  @Test
  public void testArr()
    throws JSONException
  {

    assertEquals("[[[[[]]]]]", JSON.parse("[[[[[]]]]]").toJSONString());
    assertEquals("[123,null,\"csc\",{\"Shen\":\"Zhang\"},[1]]",
                 JSON.parse("[123,null,\"csc\",{\"Shen\":\"Zhang\"},[1]]")
                     .toJSONString());
  } // testArr()

} // JSONTest
