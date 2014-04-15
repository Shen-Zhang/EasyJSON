package zhangshe.json;

import java.util.Stack;

public class JSON
{

  static boolean checkFirst = false;
  int index = 0;
  int length = 0;
  String parseStr;
  static boolean getLength = false;

  /**
   * Parse a string. See README.md for more details.
   * 
   * @throws JSONException
   */
  @SuppressWarnings("unchecked")
  public static JSONValue parse(String str)
    throws JSONException
  {
    char ch = str.charAt(0);

    if (!checkFirst && ch != '{' && ch != '[')
      {
        throw new JSONException(
                                "Invalid input: The string should begins with '{' or '[");
      }
    else
      {
        System.out.println("First char: " + ch);
        checkFirst = true;
        switch (ch)
          {
            case '{': // JSONObject
              return parseObj(str);
            case '[': // JSONArray
              return parseArr(str);
            case '"': // JSONString
              return parseStr(str);
            case 'n': // JSONConstant
            case 'f':// JSONConstant
            case 't':
              // JSONReal
            case '-':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
              return parseNum(str);
          }
        System.out.println("finished");
      }
    return null;
  }// parse(String str)

  public static JSONObject parseObj(String str)
    throws JSONException
  {
    boolean checkQuote = false;
    if (str.charAt(0) != '{')
      throw new JSONException("Invalid input: JSONObject should begin with '{'");
    else if (str.charAt(1) != '"')
      throw new JSONException(
                              "Invalid input: JSONObject should begin with a string");
    else
      {
        JSONObject obj = new JSONObject();
        for (int i = 1; i < str.length(); i++)
          {
            JSONValue key = parse(str.substring(i));
            i += key.size();
            if (str.charAt(i) != ':')
              throw new JSONException("Invalid input: Expected ':', given"
                                      + str.charAt(i));
            else
              {
                JSONValue val = parse(str.substring(i));
                obj.add(key, val);
              } // if
          } // for(i)
        return obj;
      } // if

    // throw new JSONException("Invalid input: ");
  } // parse

  public static JSONString parseStr(String str)
    throws JSONException
  {
    if (str.charAt(0) != '"')
      throw new JSONException(
                              "Invalid input: JSONString should begin with a double quote");
    else
      {
        for (int i = 1; i < str.length(); i++)
          {
            if (str.charAt(i) == '"')
              {
                return new JSONString(str.substring(0, i));
              } // if
          } // for(i)
        throw new JSONException(
                                "Invalid input: JSONString should ends with a double quotation mark");
      } // else
  } // parseStr

  public static JSONArray parseArr(String str)
    throws JSONException
  {
    if (str.charAt(0) != '[')
      throw new JSONException("Invalid input: JSONArray should begin with '['");
    else
      {
        JSONArray arr = new JSONArray();

        for (int i = 1; i < str.length(); i++)
          {
            JSONValue val = parse(str.substring(i));
            arr.add(val);
            i += val.size();
            if (i + 1 < str.length())
              {
                if (str.charAt(i + 1) == ',')
                  parse(str.substring(i + 2));
                if (str.charAt(i + 1) == ']')
                  {
                  System.out.println(']');
                  System.out.println(arr.length);
                    return arr;
                  }
                else
                  throw new JSONException("Invalid input:");
              } // if i+1 < str.length()
          } // for(i)
      } // if
    return null;
  } // parseArr(String)

  public static JSONReal parseNum(String str)
    throws JSONException
  {
    boolean period = false;
    boolean zeroFirst = false;
    boolean negate = false;
    boolean e = false;
    if (!Character.isDigit(str.charAt(0)) || str.charAt(0) != '-')
      {
        throw new JSONException(
                                "Invalid input: JSONReal should begin with a digit");
      } // if
    else
      {
        String temp = "";
        int i = 0;
        while (i < str.length())
          {
            char num = str.charAt(i);
            switch (num)
              {
                case ',':
                case '}':
                case ']':
                  return new JSONReal(temp);

                case '-':
                  {
                    if (negate)
                      throw new JSONException("Invalid input: negative sign");
                    else
                      {
                        temp.concat("-");
                        negate = true;
                        i++;
                      }
                  } // case -
                case 'e':
                  {
                    if (e)
                      throw new JSONException("Invalid input: e");
                    else
                      {
                        temp.concat("e");
                        e = true;
                        i++;
                      }
                  } // case e
                case '.':
                  {
                    if (period)
                      throw new JSONException("Invalid input: period");
                    else
                      {
                        temp.concat(".");
                        period = true;
                        i++;
                      }
                  } // case .
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                  {
                    i++;
                    temp.concat(Character.toString(num));
                  }
                default:
                  throw new JSONException("Invalid input: not a valid number");

              } // switch
          } // for
      } // else
    return null;
  } // parseNum(Str)

  public JSONReal parseConstant(String str)
    throws JSONException
  {
    char ch = str.charAt(0);
    if (ch != 'n' || ch != 'f' || ch != 't')
      throw new JSONException(
                              "Invalid input: JSONConstant expects null, true, or false.");
    else
      {
        switch (ch)
          {
            case 'n':
              if (str.substring(0, 3).compareTo("null") != 0)
                throw new JSONException(
                                        "Invalid input: JSONConstant expects null, true, or false.");
              else
                return new JSONReal("null");
            case 'f':
              if (str.substring(0, 3).compareTo("false") != 0)
                throw new JSONException(
                                        "Invalid input: JSONConstant expects null, true, or false.");
              else
                return new JSONReal("false");
            case 't':
              if (str.substring(0, 3).compareTo("true") != 0)
                throw new JSONException(
                                        "Invalid input: JSONConstant expects null, true, or false.");
              else
                return new JSONReal("true");
            default:
              throw new JSONException(
                                      "Invalid input: JSONConstant expects null, true, or false.");
          } // switch
      } // if
  } // parseConstant(String)

} // class JSON
