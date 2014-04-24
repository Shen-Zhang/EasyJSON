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
  static PrintWriter pen = new PrintWriter(System.out, true);
  static InputStreamReader is = new InputStreamReader(System.in);
  static BufferedReader eyes = new BufferedReader(is);

  public static void main(String[] args)
    throws IOException
  {
    pen.println("Welcome to the JSONtools!");
    pen.println("Menu:");
    pen.println("n - Create a new JSONValue");
    pen.println("p - Print the current JSONValue");
    pen.println("s - Convert the current JSONValue to a String");
    pen.println("c - Clear the current JSONValue");
    pen.println("q - quit the JSONtools");

    String command = eyes.readLine();

    while (command != "q")
      {
        if (command == "n")
          {
            pen.println("Option:");
            pen.println("o - Create a JSONObject");
            pen.println("a - Create a JSONArray");
            pen.println("n - Create a JSONNumber ");
            pen.println("c - Create a JSONConstant");
            pen.println("i - Information about different JSONValues");
            pen.println("b - Go back to the main menu");
            String option = eyes.readLine();
            if (option == "o")
              {
                // stub
              } // create a JSONObject
            if (option == "a")
              {
                // stub
              } // create a JSONObject
            if (option == "n")
              {
                // stub
              } // create a JSONObject
            if (option == "c")
              {
                // stub
              } // create a JSONObject
            if (option == "i")
              {
                // stub
              } // create a JSONObject
            if (option == "b")
              {
                // stub
              } // create a JSONObject
          } // create a new JSONValue
        if (command == "p")
          {
            //stub
          } // print the current JSONValue
        if (command == "c")
          {
            // stub
          } // clear the current JSONValue
        if (command == "s")
          {
            // stub
          } // Convert the current JSONValue to a String

      } // while command != "q"

  } // main(String[])

} // class JSONUI
