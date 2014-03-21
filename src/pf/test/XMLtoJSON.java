package pf.test;

import java.io.IOException;
import java.io.StringReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.json.Json;
import javax.json.stream.JsonParser;

import org.json.*;

/**
 * Servlet implementation class XMLtoJSON
 */
@WebServlet("/XMLtoJSON")
public class XMLtoJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XMLtoJSON() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
		private void handleParsing(String JsonData){
			
			JsonParser jsonParser = Json.createParser(new StringReader(JsonData));
			while (jsonParser.hasNext()){
				switch (jsonParser.next()){
				case START_OBJECT:
					System.out.println("START_OBJECT");
					break;
				case END_OBJECT:
					System.out.println("END_OBJECT");
					break;
				case START_ARRAY:
					System.out.println("START_ARRAY");
					break;
				case END_ARRAY:
					System.out.println("END_ARRAY");
					break;
				case VALUE_STRING:
					System.out.println("VALUE_STRING:"+ jsonParser.getString());
					break;
				case VALUE_NUMBER:
					System.out.println("VALUE_NUMBERL" + jsonParser.getInt());
					break;
				default:
					System.out.println("default");
					break;	
				}
			}
			
			// add value to HttpServletResponse response 
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	
			
	}
}
