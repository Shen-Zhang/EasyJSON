package zhangshe.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * A user interface for JSONParser
 * 
 * @author Shen Zhang
 *
 */
public class JSONUI
{
  /**
   * A PrintWriter to print Menu/Option and messages
   */
  static PrintWriter pen = new PrintWriter(System.out, true);

  /**
   * An InputStreamReader to read user's input
   */
  static InputStreamReader is = new InputStreamReader(System.in);

  /**
   * A BufferedReader to read InputStreamReader
   */
  static BufferedReader eyes = new BufferedReader(is);

  /**
   * To store newly created JSONValue
   */
  static JSONValue val;

  /**
   * To interact with users and create JSONValue
   * @param args
   * @throws IOException
   * @throws JSONException
   */
  public static void main(String[] args)
    throws IOException,
      JSONException
  {
    // Welcome message and options
    pen.println("Welcome to the JSONtools!");
    pen.println("Menu:");
    pen.println("n - Create a new JSONValue");
    pen.println("p - Print the current JSONValue");
    pen.println("c - Clear the current JSONValue");
    pen.println("q - quit the JSONtools");

    // read user' command
    String command = eyes.readLine();
    while (true)
      {
        // create a JSONValue
        if (command.compareTo("n") == 0)
          {
            // two options: 
            pen.println("a - Create a JSONArray");
            pen.println("o - Create a JSONObject");

            command = eyes.readLine();
            if (command.compareTo("o") == 0)
              {
                JSON.checkFirst = true;
                val = object();
              } // command is o
            else if (command.compareTo("a") == 0)
              {
                JSON.checkFirst = true;
                val = array();
              } // command is a

          } //  if command is "n"

        // print the JSONValue
        else if (command.compareTo("p") == 0)
          {
            pen.println();
            pen.println("Current JSONValue: ");
            if (val == null)
              pen.println("Empty!");
            else
              val.print();
          } //  if command is "p"

        // clear the stored JSONValue
        else if (command.compareTo("c") == 0)
          {
            val = null;
          } // if command is "c"

        // quit the program
        else if (command.compareTo("q") == 0)
          {
            // flush reader and writer then close
            pen.println("Bye!");
            pen.flush();
            eyes.close();
            return;
          }//  if command is "q"
        else
          {
            pen.println("Invalid Command!");
          } // else

        // ask for new command
        pen.println();
        pen.println();
        pen.println("Menu:");
        pen.println("n - Create a new JSONValue");
        pen.println("p - Print the current JSONValue");
        pen.println("s - Convert the current JSONValue to a String");
        pen.println("c - Clear the current JSONValue");
        pen.println("q - quit the JSONtools");
        command = eyes.readLine();
      } // while true
  } // main(String[])

  /**
   * Provide the list of options that this program can build
   * pass the command and create the JSONValue
   * @return
   * @throws IOException
   * @throws JSONException
   */
  public static JSONValue option()
    throws IOException,
      JSONException
  {
    // options
    pen.println("Option:");
    pen.println("o - Build a JSONObject");
    pen.println("a - Build a JSONArray");
    pen.println("n - Build a JSONNumber ");
    pen.println("c - Build a JSONConstant");
    pen.println("s - Build a JSONString");
    pen.println("b - Go Back to the previous menu");
    pen.println("i - Information about different JSONValues");
    String option = eyes.readLine();

    char opt = option.charAt(0);
    switch (opt)
      {
        case 'o': // object
          return object();
        case 'a': // array
          return array();
        case 'n': // number
          return number();
        case 'c': // constant
          return constant();
        case 's': // string
          return string();
        case 'i': // information
          //stub
          break;
        case 'b': // go back
          return null;
        default:
          pen.println("Invalid Command!");
          break;
      } // switch
    return null;
  } // option() 

  /**
   * to build a JSONObject
   * @return
   *    JSONObject, with given key(s) and value(s)
   * @throws IOException
   * @throws JSONException
   */
  public static JSONObject object()
    throws IOException,
      JSONException
  {
    JSONObject obj = new JSONObject();
    pen.println("Please enter the key, or enter 'q' to leave:");
    String keyStr = eyes.readLine();
    if (keyStr.compareTo("q") == 0)
      return obj;

    while (true)
      {
        JSONString key = new JSONString("\"" + keyStr + "\"");
        while (keyStr.compareTo("q") != 0)
          {
            pen.println("Please enter the JSONValue paired up with the key: ");
            // Call option() to create a JSONValue
            JSONValue objVal = option();
            if (objVal != null)
              {
                obj.add(key, objVal);
                pen.println(obj.toString() + " added");
              } // val != null
            pen.println("Please enter the new key, or enter 'q' to leave");
            keyStr = eyes.readLine();
          } // while 
        if (keyStr.compareTo("q") == 0)
          return obj;
      } // while keyStr != "q"

  }// object()

  /**
   * To build a JSONArray
   * @return
   * JSONArray with given JSONValues stored inside
   * @throws JSONException
   * @throws IOException
   */
  public static JSONArray array()
    throws JSONException,
      IOException
  {
    JSONArray arr = new JSONArray();
    pen.println("Please enter the type to add in the array, enter 'q' to leave: ");
    while (true)
      {
        JSONValue val = option();
        if (val != null)
          {
            arr.add(val);
            pen.println("added");
            pen.println("Please enter the type to add in the array, enter 'q' to leave: ");
          } // if val is not null
        else
          return arr;
      } // while true
  } // array()

  /**
   * To create a JSONReal
   * @return
   * A JSONReal with given value
   * @throws IOException
   * @throws JSONException
   */
  public static JSONReal number()
    throws IOException,
      JSONException
  {
    pen.println("Please enter the number, enter 'q' to leave: ");
    String str = eyes.readLine();
    if (str.compareTo("q") != 0)
      return (JSONReal) JSON.parseNum(str + ",");
    else
      return null;
  } // number()

  /**
   * To create a JSONString
   * @return 
   * A JSONString with given value
   * @throws JSONException
   * @throws IOException
   */
  public static JSONString string()
    throws JSONException,
      IOException
  {
    pen.println("Please enter the content of String, enter 'q' to leave: ");
    String str = eyes.readLine();
    if (str.compareTo("q") != 0)
      {
        return (JSONString) JSON.parse("\"" + str + "\"");
      } // if str.compareTo("q") != 0
    else
      return null;
  } // string()

  /**
   * To create a JSONConstant
   * @return
   * A JSONConstant with given value
   * @throws JSONException
   * @throws IOException
   */
  public static JSONConstant constant()
    throws JSONException,
      IOException
  {
    pen.println("Please enter the Constant, enter 'q' to leave: ");
    String str = eyes.readLine();
    if (str.compareTo("q") != 0)
      return (JSONConstant) JSON.parse(str);
    else
      return null;
  } // constant()

} // class JSONUI
