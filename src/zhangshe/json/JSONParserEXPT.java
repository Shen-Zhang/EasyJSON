package zhangshe.json;

import java.io.PrintWriter;

/**
 * A simple experiment of the JSONParser and toJSONString method
 * 
 * @author Shen Zhang
 * 
 */
public class JSONParserEXPT
{

  public static void main(String[] args)
    throws JSONException
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    String i = "[{}]";
    String a = "[\"Hello\",\"hello\",\"abc\",\"2312e2\"]";
    String b = "[3,\"Hello\",null]";
    String g = "[3,\"Hello\",null,[]]";
    String c =
        "{\"abc\":null,\"dsa\":3,\"ds\":[\"Hello\",\"hello\",\"abc\",\"231232\"]}";
    String d = "{\"a\":\"rebelsky\",\"b\":354,\"c\":3214,\"d\":32154}";

    JSONValue val = JSON.parse(i);

    pen.println(val.toJSONString());

  } // main(String[] args)
} // class JSONParserEXPT
