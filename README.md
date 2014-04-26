EasyJSON
=========
Program Version 1.0 04/25/2014
Author: Shen Zhang


What is it?
-----------
EasyJSON can parse Java String to JSON(JavaScript Object Notation), which is a lightweight, data interchange format, and it can covert a JSON back to String. It also provides print method, which prints a JSON in a nice and easy-to-read format. For users who are not familier with JSON, they can use the user interface in JSONUI.java, which provides instructions to build a JSON.

Here is some basic information of JSON:	
JSON is built on two structures: array or objects. Array begins with '[' (left bracket) and ends with ']' (right bracket). It's an ordered collection of values, and each value is separated by ',' (comma). Object begins with '{' (left brace) and ends '}' (right brace). It stores pairs of key and values. Each key is followed by ':', and each pair is separated by ',' (comma). 

A value can be a String in double quotation, an array, an object, a number, true, false or null.

To get more information, please read: http://www.json.org/

File List
---------
JSON.java   -- Provides the parse method which converts a String to json	
JSONValue.java -- An interface of JSON 	
JSONArray.java -- The class of JSONArray	 
JSONObject.java -- The class of JSONObject 	
JSONConstant.java -- The class of JSONConstant	 
JSONReal.java -- The class of JSONString 	
JSONString.java -- The class of JSONString	 
JSONException.java -- Throws exception and provides error message		
JSONTest.java -- Tests the parse in JSON.java and toString() method in JSONValue	
JSONUI.java -- An user interface which allows users to create and print a JSONValue		
Citation -- A file containing all references
JSONIntro -- An introduction of JSON


How to use it?
--------------
To convert a String into JSON, please call parse method in JSON.java. Here is an example:		
			String sample = "["Hello","World"]";	
			JSONValue val = JSON.parse(sample);	
To convert this val back to String, please do:	
			String convert = val.toString();	
To print this val, please do:	
			val.print();	
Or users can go to JSONUI.java and create and print JSONValue by given instructions.	

Licensing
---------
Please see the file called LICENSE


