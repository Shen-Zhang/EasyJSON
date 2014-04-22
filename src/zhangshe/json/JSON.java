package zhangshe.json;

/**
 * A class to parse a String into JSONValue
 * 
 * @author Shen Zhang
 */

public class JSON
{
  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * A boolean flag to check if the first character in the given string begins
   * with '{' or '['. 
   */
  static boolean checkFirst = false;

  /**
   * Parse a string. See README.md for more details.
   * 
   * @throws JSONException
   * @pre str is a valid string
   * @post A JSONValue is given, or a JSONException is thrown
   */
  public static JSONValue parse(String str)
    throws JSONException
  {
    if (str.length() == 0)
      throw new JSONException("Invalid Input: Expecting Object or Array");

    char ch = str.charAt(0);

    // check if the given string starts with '{' or '['
    if (!checkFirst && ch != '{' && ch != '[')
      {
        throw new JSONException();
      }
    else
      {
        // The first character is '[' or '{', which means the sting begins with an object or array.
        // Set checkFirst to true, and we don't need to use this boolean anymore.
        checkFirst = true;

        // pass str to parseNum if ch is a digit, or negative sign      
        if (Character.isDigit(ch) || ch == '-')
          return parseNum(str);
        else
          {
            switch (ch)
              {
              // JSONObject
                case '{':
                  return parseObj(str);
                  // JSONArray
                case '[':
                  return parseArr(str);
                  // JSONString
                case '"':
                  return parseStr(str);
                  // JSONConstant
                case 'n':
                case 'f':
                case 't':
                  return parseConstant(str);

                  // Any other characters are invalid input
                default:
                  throw new JSONException(str,
                                          "Number, Object, Array, or Constnat",
                                          0);
              } // switch(ch)
          } // if ch is not a digit or negative sign
      } // if
  }// parse(String str)

  /**
   * Parse a string to a JSONObject
   * 
   * @param str
   *          A valid string
   * @return JSONObject
   * @throws JSONException
   * 
   * @pre str is a valid string
   * @post A JSONObject is given, or a JSONException is thrown
   */
  public static JSONObject parseObj(String str)
    throws JSONException
  {

    // Assume str.charAt(0) is '{', if str.charAt(1) is '}'
    // return an empty JSONObject
    if (str.charAt(1) == '}')
      {
        JSONObject empty = new JSONObject();
        empty.length++;
        return empty;
      } // if str.charAt(1) = }

    // Or if str.charAt(1) is not a '"', throw an exception
    else if (str.charAt(1) != '"')
      throw new JSONException(str, "\"", 1);

    else
      {
        // create a new JSONObject
        JSONObject obj = new JSONObject();
        // Assume index 0 is '{'. Iteration starts from i = 1
        int i = 1;

        while (i < str.length())
          {
            // hit the end of an JSONObject
            if (str.charAt(i) == '}')
              {
                obj.length = i;
                i++;
                return obj;
              } // if str.charAt(i) = }

            // Get a JSONString as the key
            JSONString key = parseStr(str.substring(i));
            // skup over key
            i += key.size();

            if (str.charAt(i) != ':')
              throw new JSONException(str, ":", i);
            else
              {
                i++; // skip ':'
                // get a JSONValue as a val
                JSONValue val = parse(str.substring(i));
                // add a pair to the object
                obj.add(key, val);

                // skip over val
                i += val.size();
                // if JSONObject has more pairs
                if (str.charAt(i) == ',')
                  // skip over the comma
                  i++;
                // if we hit the end of the JSONObject
                else if (str.charAt(i) == '}')
                  {
                    obj.length = i + 1;
                    i++;
                    return obj;
                  } // if str.charAt(i) = '}'
                else
                  throw new JSONException(str, "',' or '}'", i);
              } // if str.charAt(i) != }
          } // while i < str.length()
        throw new JSONException("Invalid input");
      } // else 
  } // parseObj(String)

  /**
   * Parse a string to a JSONString
   * 
   * @param str
   *          A valid string
   * @return JSONString
   * @throws JSONException
   * 
   * @pre str is a valid string
   * @post A JSONString is given, or a JSONException is thrown
   */
  public static JSONString parseStr(String str)
    throws JSONException
  {
    for (int i = 1; i < str.length(); i++)
      {
        if (str.charAt(i) == '"')
          {
            if (str.charAt(i + 1) != ',' && str.charAt(i + 1) != '}'
                && str.charAt(i + 1) != ']' && str.charAt(i + 1) != ':')
              throw new JSONException(str, "Invalid Input", i);
            return new JSONString(str.substring(0, i + 1));
          } // if
      } // for(i)
    throw new JSONException();
  } // parseStr(String)

  /**
   * Parse a string to a JSONArray
   * 
   * @param str
   *          A valid string
   * @return JSONArray
   * @throws JSONException
   * 
   * @pre str is a valid string
   * @post A JSONArray is given, or a JSONException is thrown
   */

  public static JSONArray parseArr(String str)
    throws JSONException
  {
    // create a JSONAray 
    JSONArray arr = new JSONArray();

    for (int i = 1; i < str.length(); i++)
      {
        // hit the end of the array
        if (str.charAt(i) == ']')
          {
            arr.length = i + 1;
            i++;
            return arr;
          } // if str.charAt(i) = ']'

        // parse the next JSONValue and add it to the array
        JSONValue val = parse(str.substring(i));
        arr.add(val);
        // skip over val
        i = i + val.size() - 1;
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
    throw new JSONException("Invalid input");
  }// parseArr(String)

  /**
   * Parse a string into JSONReal
   * 
   * @param str
   *          A valid string
   * @return JSONReal
   * @throws JSONException
   * 
   * @pre str is a valid string
   * @post A JSONReal is given, or a JSONException is thrown
   */
  public static JSONReal parseNum(String str)
    throws JSONException
  {

    boolean period = false; // Have we encountered a period?
    boolean negate = false; // Did we began with a negative sign?
    boolean e = false; //  Did we see the exponent character ('e')?

    if (!Character.isDigit(str.charAt(0)) && str.charAt(0) != '-')
      {
        throw new JSONException(
                                "Invalid Input: Expecting a number or negative sign");
      } // if
    else
      {
        int i = 0;
        while (i < str.length())
          {
            char num = str.charAt(i);
            if (Character.isDigit(num))
              i++; // skip the number
            else
              {
                switch (num)
                  {
                  // hit the end of a number
                    case ',':
                    case '}':
                    case ']':
                      // the last character should be a digit
                      if (!Character.isDigit(str.charAt(i - 1)))
                        throw new JSONException(str, "a number", i - 1);
                      return new JSONReal(str.substring(0, i));
                    case '-':
                      if (negate)
                        throw new JSONException(
                                                "Invalid Input: Negative sign cannot appear twice!"
                                                    + num);
                      else
                        {
                          negate = true;
                          i++; // skip '-'
                        } // if negate is true
                      break;
                    case 'E':
                    case 'e':
                      if (e)
                        throw new JSONException(
                                                "Invalid Input: 'e' cannot appear twice "
                                                    + num);
                      else
                        {
                          e = true;
                          // Allow positive or negative sign immediately after 'e'/'E'
                          if (i + 1 < str.length()
                              && (str.charAt(i + 1) == '+' || str.charAt(i + 1) == '-'))
                            i++; // skip '+'/'-'
                          i++; // skip 'e'/'E'
                        } // if e is true
                      break;
                    case '.':
                      if (period)
                        throw new JSONException(
                                                "Invalid Input: decimal point cannot appear twice"
                                                    + num);
                      else
                        {
                          period = true;
                          i++; // skip '.'
                        } // if period is true
                      break;

                    default:
                      throw new JSONException(
                                              "Invalid input: not a valid number");

                  } // switch
              } // else
          } // while
        throw new JSONException("Invalid input");
      } // if str.charAt(0) is a digit or '-'
  } // parseNum(String)

  /**
   * parse a string into JSONConstant
   * 
   * @param str
   *          A valid String
   * @return JSONConstant
   * @throws JSONException
   * 
   * @pre str is a valid string
   * @post A JSONConstant is given, or a JSONException is thrown
   */
  public static JSONConstant parseConstant(String str)
    throws JSONException
  {
    char ch = str.charAt(0);
    switch (ch)
      {
        case 'n':
          constantHelper(str.substring(0, 4), JSONConstant.NULL);
        case 'f':
          constantHelper(str.substring(0, 5), JSONConstant.FALSE);
        case 't':
          constantHelper(str.substring(0, 4), JSONConstant.TRUE);
        default:
          throw new JSONException(
                                  "Invalid input: JSONConstant expects null, true, or false.");
      } // switch (ch)
  } // parseConstant(String)

  /**
   * A helper to help building JSONConstant or throwing exception
   * @param str
   * A valid String
   * @param constant
   * A JSONConstant
   * @return
   * A JSONConstant if the str is null/false/true
   * @throws JSONException
   */
  public static JSONConstant constantHelper(String str, JSONConstant constant)
    throws JSONException
  {
    JSONConstant temp = new JSONConstant(str);
    if (temp == JSONConstant.NULL)
      return temp;
    else
      throw new JSONException(
                              "Invalid input: JSONConstant expects null, true, or false.");
  } // constantHelper(String)

} // class JSON
