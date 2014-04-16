package zhangshe.json;

/**
 * A class to parse a String into JSONValue
 * 
 * @author Shen Zhang
 * 
 */
public class JSON
{
  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * A boolean flag to check if the first character in the given string begins
   * with '{' or '['
   */
  static boolean checkFirst = false;

  /**
   * Parse a string. See README.md for more details.
   * 
   * @throws JSONException
   */
  public static JSONValue parse(String str)
    throws JSONException
  {
    char ch = str.charAt(0);

    // check if the given string starts with '{' or '['
    if (!checkFirst && ch != '{' && ch != '[')
      {
        throw new JSONException(
                                "Invalid input: The string should begins with '{' or '[, given "
                                    + ch);
      }
    else
      {
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
            default:
              throw new JSONException("Invalid input");
          } // switch(ch)
      } // if
  }// parse(String str)

  /**
   * Parse a string to a JSONObject
   * 
   * @param str
   *          A valid string
   * @return JSONObject
   * @throws JSONException
   */
  public static JSONObject parseObj(String str)
    throws JSONException
  {
    if (str.charAt(0) != '{')
      throw new JSONException(
                              "Invalid input: JSONObject should begin with '{', given"
                                  + str.charAt(0));
    else if (str.charAt(1) != '"')
      throw new JSONException(
                              "Invalid input: The first item in JSONObject should be a string, given "
                                  + str.charAt(1));
    else
      {
        JSONObject obj = new JSONObject(); // create a new JSONObject
        int i = 1;
        while (i < str.length())
          {
            if (str.charAt(i) == '}') // hit the end of an JSONObject
              {
                obj.length = i;
                i++;
                return obj;
              } // if
            JSONValue key = parse(str.substring(i)); // get a JSONString as a
                                                     // key
            i += key.size();
            if (str.charAt(i) != ':')
              throw new JSONException("Invalid input: Expect ':', given"
                                      + str.charAt(i));
            else
              {
                i++; // ':'
                JSONValue val = parse(str.substring(i));
                // get a JSONValue as a val
                obj.add(key, val); // add a pair
                i += val.size(); // increment i by the size of val
                if (str.charAt(i) == ',') // JSONObject has more pairs
                  i++;
                else if (str.charAt(i) == '}') // hit the end of the JSONObject
                  {
                    obj.length = i + 1;
                    i++;
                    return obj;
                  }
                else
                  throw new JSONException(
                                          "Invalid input: Expect ',' or '}', but given "
                                              + str.charAt(i));
              } // if
          } // while
        throw new JSONException("Invalid input");
        // return obj;
      } // if
  } // parse

  /**
   * Parse a string to a JSONString
   * 
   * @param str
   *          A valid string
   * @return JSONString
   * @throws JSONException
   */
  public static JSONString parseStr(String str)
    throws JSONException
  {
    if (str.charAt(0) != '"')
      throw new JSONException(
                              "Invalid input: JSONString should begin with a double quote, given "
                                  + str.charAt(0));
    else
      {
        for (int i = 1; i < str.length(); i++)
          {
            if (str.charAt(i) == '"')
              {
                if (str.charAt(i + 1) != ',' && str.charAt(i + 1) != '}'
                    && str.charAt(i + 1) != ']' && str.charAt(i + 1) != ':')
                  throw new JSONException("Invalid input");
                return new JSONString(str.substring(0, i + 1));
              } // if
          } // for(i)
        throw new JSONException(
                                "Invalid input: JSONString should end with a double quotation mark");
      } // else
  } // parseStr

  /**
   * Parse a string to a JSONArray
   * 
   * @param str
   *          A valid string
   * @return JSONArray
   * @throws JSONException
   */

  public static JSONArray parseArr(String str)
    throws JSONException
  {
    if (str.charAt(0) != '[')
      throw new JSONException("Invalid input: JSONArray should begin with '['");
    else
      {
        JSONArray arr = new JSONArray(); // create a JSONAray

        for (int i = 1; i < str.length(); i++)
          {
            if (str.charAt(i) == ']')
              {
                arr.length = i + 1;
                i++;
                return arr;
              } // hit the end of the array
            JSONValue val = parse(str.substring(i));

            arr.add(val); // add value to the array
            i = i + val.size() - 1; // increment i
            while (i + 1 < str.length())
              {
                if (str.charAt(i + 1) == ',')
                  {
                    i++;
                    JSONValue temp = parse(str.substring(i + 1));
                    arr.add(temp);
                    i += temp.size();
                  } // if str.charAt(i+1) is ','
                if (str.charAt(i + 1) == ']')
                  {
                    arr.length = i + 2;
                    return arr;
                  } // if str.charAt(i+1) is ']'
                // else
              } // if i+1 < str.length()
          } // for(i)
      } // if
    throw new JSONException("Invalid input");
  } // parseArr(String)

  /**
   * Parse a string into JSONReal
   * 
   * @param str
   *          A valid string
   * @return JSONReal
   * @throws JSONException
   */
  public static JSONReal parseNum(String str)
    throws JSONException
  {
    /**
     * A boolean flag to check if the period exists in the string
     */
    boolean period = false;
    /**
     * A boolean flag to check if the negative sign exists in the string
     */
    boolean negate = false;
    /**
     * A boolean flag to check if e exists in the string
     */
    boolean e = false;

    if (!Character.isDigit(str.charAt(0)) && str.charAt(0) != '-')
      {
        throw new JSONException(
                                "Invalid input: JSONReal should begin with a digit");
      } // if
    else
      {
        String temp = ""; // create a string
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
                      throw new JSONException(
                                              "Invalid input: expect a number, given"
                                                  + num);
                    else
                      {
                        temp += "-";
                        negate = true;
                        i++;
                      } // if
                    break;
                  } // case -
                case 'e':
                  {
                    if (e)
                      throw new JSONException(
                                              "Invalid input: expect a number, given "
                                                  + num);
                    else
                      {
                        temp += "e";
                        e = true;
                        i++;
                      } // if
                    break;
                  } // case e
                case '.':
                  {
                    if (period)
                      throw new JSONException(
                                              "Invalid input: expect a number, given "
                                                  + num);
                    else
                      {
                        temp += ".";
                        period = true;
                        i++;
                      } // if
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
                  } // case 0 1 2 3 4 5 6 7 8 9
                default:
                  throw new JSONException("Invalid input: not a valid number");
              } // switch
          } // for
      } // else
    throw new JSONException("Invalid input");
  } // parseNum(Str)

  /**
   * parse a string into JSONConstant
   * 
   * @param str
   *          A valid String
   * @return JSONConstant
   * @throws JSONException
   */
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
