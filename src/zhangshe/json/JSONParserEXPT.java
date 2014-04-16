package zhangshe.json;

import java.io.PrintWriter;

public class JSONParserEXPT
{

  public static void main(String[] args)
    throws JSONException
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    String i = "[15]";
    String a = "[\"Hello\",\"hello\",\"abc\",\"2312e2\"]";
    String b = "[3,\"Hello\",null]";
    String g = "[3,\"Hello\",null,[3]]";
    String c =
        "{\"abc\":null,\"dsa\":3,\"ds\":[\"Hello\",\"hello\",\"abc\",\"231232\"]}";
    String d = "{\"a\":\"rebelsky\",\"b\":354,\"c\":3214,\"d\":32154}";
    String e = "{\"name\":\"sam\",\"title\":\"prof\",\"alive\":true}";
    String f =
        "{\"name\":\"sam\",\"job\":{\"employer\":\"Grinnell\",\"title\":\"Professor\"},\"alive\":true}";
    String h =
        "{\"name\":\"sam\",\"grades\":{\"exam1\":80,\"exam2\":[85],\"final\":{\"A\":100}},\"letter\":\"B\"}";
    JSONValue val = JSON.parse(h);
    pen.println(val.toJSONString());
    /*
     * JSONString s = new JSONString("a"); JSONString z = new JSONString("b");
     * JSONConstant n = new JSONConstant("null"); JSONConstant l = new
     * JSONConstant("false"); JSONObject obj = new JSONObject(); obj.add(s, l);
     * obj.add(z, n); //pen.println(obj.hash;
     */
  } // main(String[] args)
} // class JSONParserEXPT
