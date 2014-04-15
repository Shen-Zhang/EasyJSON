package zhangshe.json;

import java.io.PrintWriter;

public class JSONParserEXPT
{

  public static void main(String[] args)
    throws JSONException
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    String a = "[\"Hello\"]";
    pen.println(a.charAt(0));
    JSONValue val = JSON.parse(a);
  } // main(String[] args)
} // class JSONParserEXPT
