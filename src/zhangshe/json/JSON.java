package zhangshe.json;

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
        System.out.println("JSON.parse(): First char: " + ch);
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
              return parseConstant(str);
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
          } // switch(ch)
      }
    return null;
  }// parse(String str)

  public static JSONObject parseObj(String str)
    throws JSONException
  {
    if (str.charAt(0) != '{')
      throw new JSONException("Invalid input: JSONObject should begin with '{'");
    else if (str.charAt(1) != '"')
      throw new JSONException(
                              "Invalid input: JSONObject should begin with a string");
    else
      {
        JSONObject obj = new JSONObject();
        int i = 1;
        while (i < str.length())
          {
            if (str.charAt(i) == '}')
              {
                obj.length = i;
                i++;
                return obj;
              } // if
            JSONValue key = parse(str.substring(i));
            i += key.size();
            if (str.charAt(i) != ':')
              throw new JSONException("Invalid input: Expected ':', given"
                                      + str.charAt(i));
            else
              {
                i++; // ':'
                JSONValue val = parse(str.substring(i));
                obj.add(key, val);
                i += val.size();
                if (str.charAt(i) == ',')
                  i++;
                else if (str.charAt(i) == '}')
                  {
                    obj.length = i+1;
                    i++;
                    return obj;
                  }
                else
                  throw new JSONException();
              } // if
          } // while
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
                System.out.println("parseStr: " + str.substring(0, i + 1));
                if (str.charAt(i + 1) != ',' && str.charAt(i + 1) != '}'
                    && str.charAt(i + 1) != ']' && str.charAt(i + 1) != ':')
                  throw new JSONException();
                return new JSONString(str.substring(0, i + 1));
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
        if (str.charAt(1) == ']')
          return arr;

        for (int i = 1; i < str.length(); i++)
          {
            System.out.println("parseArr: before parse: " + str.substring(i));
            JSONValue val = parse(str.substring(i));

            arr.add(val);
            i = i + val.size() - 1;
            System.out.println("parseArr: i is " + i);
            while (i + 1 < str.length())
              {
                if (str.charAt(i + 1) == ',')
                  {
                    i++;
                    JSONValue temp = parse(str.substring(i + 1));
                    arr.add(temp);
                    i += temp.size();
                    System.out.println("parseArr: i is (in side ,) " + i);
                  }
                if (str.charAt(i + 1) == ']')
                  {
                    arr.length = i + 1;
                    System.out.println("parseArr: arrLength " + arr.length);
                    System.out.println("finished");
                    return arr;
                  }
                // else
                // throw new JSONException("Invalid input:");
              } // if i+1 < str.length()
            System.out.println("parseArr: for loop 1 time");
          } // for(i)
      } // if
    return null;
  } // parseArr(String)

  public static JSONReal parseNum(String str)
    throws JSONException
  {
    boolean period = false;
    boolean negate = false;
    boolean e = false;
    if (!Character.isDigit(str.charAt(0)) && str.charAt(0) != '-')
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
                  {
                    System.out.println("parseNum: " + num);
                    return new JSONReal(temp);
                  }
                case '-':
                  {
                    if (negate)
                      throw new JSONException("Invalid input: negative sign");
                    else
                      {
                        temp += "-";
                        negate = true;
                        i++;
                      }
                    break;
                  } // case -
                case 'e':
                  {
                    if (e)
                      throw new JSONException("Invalid input: e");
                    else
                      {
                        temp += "e";
                        e = true;
                        i++;
                      }
                    break;
                  } // case e
                case '.':
                  {
                    if (period)
                      throw new JSONException("Invalid input: period");
                    else
                      {
                        temp += ".";
                        period = true;
                        i++;
                      }
                    break;
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
                    temp += Character.toString(num);

                    break;
                  }
                default:
                  throw new JSONException("Invalid input: not a valid number");

              } // switch
          } // for
      } // else
    return null;
  } // parseNum(Str)

  public static JSONConstant parseConstant(String str)
    throws JSONException
  {
    char ch = str.charAt(0);
    if (ch != 'n' && ch != 'f' && ch != 't')
      throw new JSONException(
                              "Invalid input: JSONConstant expects null, true, or false.");
    else
      {
        switch (ch)
          {
            case 'n':
              if (str.substring(0, 4).compareTo("null") != 0)
                throw new JSONException(
                                        "Invalid input: JSONConstant expects null, true, or false.");
              else
                return new JSONConstant("null");
            case 'f':
              if (str.substring(0, 5).compareTo("false") != 0)
                throw new JSONException(
                                        "Invalid input: JSONConstant expects null, true, or false.");
              else
                return new JSONConstant("false");
            case 't':
              if (str.substring(0, 4).compareTo("true") != 0)
                throw new JSONException(
                                        "Invalid input: JSONConstant expects null, true, or false.");
              else
                return new JSONConstant("true");
            default:
              throw new JSONException(
                                      "Invalid input: JSONConstant expects null, true, or false.");
          } // switch
      } // if
  } // parseConstant(String)

} // class JSON
