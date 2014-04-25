package zhangshe.json;

import java.io.IOException;
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
    throws JSONException, IOException
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    String i = "[[[[[[]]]]]]";
    String a = "[\"\\\\dsads333\",\"hello\",\"abc\",\"2312e2\"]";
    String b = "[3,\"Hello\",null]";
    String g = "[3,\"Hello\",null,[]]";
    String c =
        "{\"ab\\\\c\":null,\"dsa\":3,\"ds\":[\"Hello\",\"hello\",\"abc\",\"231232\"]}";
    String d = "{\"a\":\"rebelsky\",\"b\":354,\"c\":3214,\"d\":32154}";

    String f = "[{\"a\":[1,23],\"b\":true},{\"c\":{\"C\":0},\"d\":10},null]";
    JSONValue val = JSON.parse(a);
    val.print();
    //pen.println(val.toString());

  } // main(String[] args)
} // class JSONParserEXPT
