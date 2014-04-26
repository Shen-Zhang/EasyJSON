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
   * Parse a string into JSON
   * 
   * @throws JSONException
   * @throws IOException 
   * @pre str is a valid string
   * @post A JSONValue is given as the content of str, or a JSONException is thrown
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
                  throw new JSONException(
                                          str,
                                          "Number, Object, Array, String, or Constant",
                                          0);
              } // switch(ch)
          } // if ch is not a digit or negative sign
      } // else
  }// parse(String str)

  /**
   * Parse a string to a JSONObject
   * 
   * @param str
   *          A valid string
   * @return JSONObject
   * @throws JSONException
   * @throws IOException 
   * 
   * @pre str is a valid string
   * @post A JSONObject is given as the content of str, or a JSONException is thrown
   */
  public static JSONObject parseObj(String str)
    throws JSONException
  {

    if (str.charAt(0) != '{')
      throw new JSONException();
    // Assume str.charAt(0) is '{', if str.charAt(1) is '}'
    // special case: return an empty JSONObject
    else if (str.charAt(1) == '}')
      {
        JSONObject empty = new JSONObject();
        return empty;
      } // if str.charAt(1) = }

    // Or if str.charAt(1) is not a '"', throw an exception
    else if (str.charAt(1) != '"')
      throw new JSONException(str, "\"", 1);

    else
      {
        // create a new JSONObject
        JSONObject obj = new JSONObject();
        int i = 1;

        while (i < str.length())
          {
            // hit the end of an JSONObject
            if (str.charAt(i) == '}')
              return obj;

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
                  {
                    // skip over the comma
                    i++;
                    obj.length++; // add ','
                  } // if str.charAt(i) == ','
                // if we hit the end of the JSONObject
                else if (str.charAt(i) == '}')
                  return obj;
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
   * @throws IOException 
   * 
   * @pre str is a valid string
   * @post A JSONString is given as the content of str, or a JSONException is thrown
   */
  public static JSONString parseStr(String str)
    throws JSONException
  {
    // check if given string begins with '"'
    if (str.charAt(0) != '"')
      throw new JSONException();
    else
      {
        int i = 1;
        char ch = 0;
        int count = 0;
        StringBuilder builder = new StringBuilder();
        while (i < str.length() && (ch = str.charAt(i)) != '"')
          {
            if (ch != '\\')
              {
                builder.append(ch);
                i++;
              } // if ch is not backslash
            else
              {
                // ch is a backslash
                ch = str.charAt(++i);
                switch (ch)
                  {

                  // special characters cases:
                    case '"':
                      i++;
                      builder.append('\"');
                      count++;
                      break;
                    case '\\':
                    case '/':
                      i++;
                      builder.append(ch);
                      count++;
                      break;
                    case 'b':
                      i++;
                      builder.append('\b');
                      count++;
                      break;
                    case 'f':
                      i++;
                      builder.append('\f');
                      count++;
                      break;
                    case 'n':
                      i++;
                      builder.append('\n');
                      count++;
                      break;
                    case 'r':
                      i++;
                      builder.append('\r');
                      count++;
                      break;
                    case 't':
                      i++;
                      builder.append('\t');
                      count++;
                      break;
                    // unicode case:
                    case 'u':
                      int begin = i;
                      for (int n = 0; n < 4; n++)
                        {
                          if (i + 1 < str.length())
                            {
                              ch = str.charAt(i + 1);
                              System.out.println(ch);
                              if (Character.isUnicodeIdentifierPart(ch))
                                {
                                  i++;
                                  count++;
                                } // if (Character.isUnicodeIdentifierPart(ch)) 
                              else
                                throw new JSONException(str, "a valid Unicode",
                                                        i);
                            } // if i+1 < str.length()
                          else
                            throw new JSONException();
                        } // for (n)
                      builder.append((char) Integer.parseInt(str.substring(begin + 1,
                                                                           i + 1),
                                                             16));
                      break;
                    default:
                      throw new JSONException();
                  } // switch (ch)
              } // else
          } // while

        // return if we encounter '"'
        if (str.charAt(i) == '"')
          {
            JSONString s = new JSONString('"' + builder.toString() + '"');
            s.length += count;

            return s;
          }
      } //else
    throw new JSONException();

  }// parseStr(String)

  /**
   * Parse a string to a JSONArray
   * 
   * @param str
   *          A valid string
   * @return JSONArray
   * @throws JSONException
   * @throws IOException 
   * 
   * @pre str is a valid string
   * @post A JSONArray is given, or a JSONException is thrown
   */

  public static JSONArray parseArr(String str)
    throws JSONException
  {
    if (str.charAt(0) != '[')
      throw new JSONException();
    else
      {
        // create a JSONAray 
        JSONArray arr = new JSONArray();

        for (int i = 1; i < str.length(); i++)
          {
            // hit the end of the array
            if (str.charAt(i) == ']')
              return arr;

            // parse the next JSONValue and add it to the array
            JSONValue val = parse(str.substring(i));
            arr.add(val);

            // skip over val
            i += val.size();

            while (i < str.length())
              {
                if (str.charAt(i) == ',')
                  {
                    JSONValue temp = parse(str.substring(++i));
                    arr.add(temp);
                    arr.length++; // add ','
                    i += temp.size();

                  } // if str.charAt(i+1) is ','
                else if (str.charAt(i) == ']')
                  {
                    return arr;
                  } // if str.charAt(i+1) is ']'
                else
                  throw new JSONException(str, "',' or ']'", i);
                // else
              } // if i+1 < str.length()
          } // for(i)
      } // else
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
   * @post A JSONReal is given as content of str, or a JSONException is thrown
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
   * @post A JSONConstant is given as content of str, or a JSONException is thrown
   */
  public static JSONConstant parseConstant(String str)
    throws JSONException
  {
    char ch = str.charAt(0);
    switch (ch)
      {
        case 'n':
          return constantHelper(str.substring(0, 4), JSONConstant.NULL);
        case 'f':
          return constantHelper(str.substring(0, 5), JSONConstant.FALSE);
        case 't':
          return constantHelper(str.substring(0, 4), JSONConstant.TRUE);
        default:
          throw new JSONException(
                                  "Invalid input: JSONConstant expects null, true, or false.");
      } // switch (ch)
  } // parseConstant(String)

  // +--------+----------------------------------------------------------
  // | Helper |
  // +--------+

  /**
   * A helper to help building JSONConstant or throwing exception
   * @param str
   *    A valid String
   * @param constant
   *    A JSONConstant
   * @return
   *    A JSONConstant if the str is null/false/true
   * @throws JSONException
   */
  public static JSONConstant constantHelper(String str, JSONConstant constant)
    throws JSONException
  {
    JSONConstant temp = new JSONConstant(str);
    if (temp.isEqual(constant))
      return temp;
    else
      throw new JSONException(
                              "Invalid input: JSONConstant expects null, true, or false.");
  } // constantHelper(String)

} // class JSON
