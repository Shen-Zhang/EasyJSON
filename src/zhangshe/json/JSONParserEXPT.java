package zhangshe.json;

import java.io.PrintWriter;

public class JSONParserEXPT
{

  public static void main(String[] args)
    throws JSONException
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    String a = "[\"Hello\",\"hello\",\"abc\",\"231232\"]";
    String b = "[3,\"Hello\",null]";
    String c = "{\"abc\":null,\"dsa\":3,\"ds\":[\"Hello\",\"hello\",\"abc\",\"231232\"]}";
    String d = "{\"uid\":\"rebelsky\",\"id\":32154}";
    String e = "{\"name\":\"sam\",\"title\":\"prof\",\"alive\":true}";
    String f = "{\"name\":\"sam\",\"job\":{\"employer\":\"Grinnell\",\"title\":\"Professor\"},\"alive\":true}";
    JSONValue val = JSON.parse(f);
  } // main(String[] args)
} // class JSONParserEXPT
