package pf.test;

import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.ServletException;
//import javax.servlet.FilterRegistration;

//import javax.servlet.http.*;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
//import org.eclipse.jetty.server.Server;
//import org.eclipse.jetty.server.Request;
//import org.eclipse.jetty.client.api.Response;
//import org.eclipse.jetty.client.api.Request;
//import org.eclipse.jetty.client.Address;

//import org.eclipse.jetty.client.HttpExchange;
//import org.eclipse.jetty.client.HttpRequest;
//import org.eclipse.jetty.client.HttpClient;
//import org.eclipse.jetty.client.api.ContentResponse;
//import org.eclipse.jetty.client.api.Response;

//import org.eclipse.jetty.server.handler.AbstractHandler;
//import org.eclipse.jetty.server.nio.*;
//import org.eclipse.jetty.server.handler.*;
//import org.eclipse.jetty.server.Handler;
//import org.eclipse.jetty.servlet.ServletContextHandler;

//import org.eclipse.jetty.io.Buffer;
//import org.eclipse.jetty.client.*;
//import org.eclipse.jetty.http.*;

//import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.json.JSONException;



//call hl7toXML, then XMLtoJSON web services
//setup to save to DB

public class TransformService {
	
	private TransformService transformService;
	private String hl7toxmlReturn;
	private String jsonStringReturn;
	private String patientName;
	private String exchResult;
	
	//constructor
	public TransformService(){
		
	}

	
 	// run methods
	public void runTransform() throws JSONException{
				
		//request to servlet with HL7 msg, return JSON (or just pt name), parse, display and save to DB.
		//
		 //HttpClient httpClient = new HttpClient();
		 HttpClient client = new HttpClient();
		// client.setConnectorType(HttpClient.CONNECTOR_SELECT_CHANNEL);

		 // start client
		 try {
			client.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// begin Request/Response 
		try {
			ContentResponse resp = client.GET("http://localhost:8586/");
			exchResult = resp.getContentAsString();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TimeoutException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
     }
}
		
/*		
		 //HttpExchange exchange = new HttpExchange();
		// Optionally set the HTTP method
		// exchange.setMethod("POST");
		 //exchange.setMethod("GET");
		  
		 String hl7msg1 = "MSH|^~\\&|foo|foo||foo|200108151718||ACK^A01^ACK|1|D|2.4|\rMSA|AA\r";
		 //exchange.setRequestContentType(hl7msg1);
		 
		 //exchange.setAddress(new Address("localhost", 8586)); // TODO change port to integer portNo
		 
		 
		 System.out.println("exchange HL7 message--------------------------");
		 System.out.println(hl7msg1);
	     
		 // sends exchange		 
		 //ContentResponse response = client..newRequest("localhost", 8586)
	      //          	                .send();
				 
		 //try {
		//	client.send(exchange);
			
			//exchange.waitForStatus(HttpExchange.STATUS_COMPLETED);
			//Buffer bresult=exchange.getRequestContent();
		  //   byte[] buffer = null;
		     //PrintStream out = null;
			 //while (bresult.hasContent())
	          //  {
	          // 	int len=bresult.get(buffer,0,buffer.length);
	          // 	out.write((byte[]) buffer,0,len);  // May block here for a little bit!
			//	}
			 //   exchResult = buffer.toString();
		        //String exchRslt = bresult.toString();
		    
		  
		    
	//	} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
	
		 System.out.println("--------------------------");
		 System.out.println("Exchange sent");
		 System.out.println("exchange--------------------------");
		 System.out.println("Exchange result");
		 System.out.println(exchResult);
		 		 		  
		 
		 
		 //HttpClient.GET("http://localhost:8080/").get().status();
		//ContentResponse response = httpClient.execute("http://domain.com/path?query");
		//ContentResponse response = httpClient.GET("http://domain.com/path?query");
		
		//Request request = httpClient.send("http://domain.com/path?query");
		//request.method(HttpMethod.HEAD);
		//request.agent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:17.0) Gecko/20100101 Firefox/17.0");
		//ContentResponse response = request.send();
		
		// TODO change these method calls to SOAP calls when services loaded
		
		//HL7 to XML
		String hl7msg = "MSH|^~\\&|foo|foo||foo|200108151718||ACK^A01^ACK|1|D|2.4|\rMSA|AA\r";
		//hl7toxmlReturn = passHL7msg("MSH|^~\\&|foo|foo||foo|200108151718||ACK^A01^ACK|1|D|2.4|\rMSA|AA\r");
		System.out.println("HL7 message--------------------------");
		System.out.println(hl7msg);
		
		//transform HL7message
		hl7toxmlReturn = passHL7msg(hl7msg);
		System.out.println("--------------------------");
		// show in console
		System.out.println("XMLfromHL7--------------------------");
		System.out.println(hl7toxmlReturn);
		System.out.println("--------------------------");
		
		//XML to JSON
		XMLtoJSONws xtoJ = new XMLtoJSONws();
		
		jsonStringReturn = xtoJ.xmlToJSON(hl7toxmlReturn);
		// show in console
		System.out.println("JSON--------------------------");
		System.out.println(jsonStringReturn);		System.out.println("--------------------------");
		
		//parse JSON for patient name
		//try{
	    	JSONParser jparse = new JSONParser();
		    String rtnjparse = jparse.parseJSON(jsonStringReturn);
		//} catch (JSONException e) {
		//	e.printStackTrace();
		//}
		
		// add to db
		//RunH2.runH2(rtnjparse); // TODO check if this already setup by servlet
		//RunH2.runH2(patientName);
}         
*/
	//public void loadServices(){
	//}
	/*
	public String passHL7msg(String hl7Msg){
		//		
		//HL7toXMLws
		String jsonString = hl7Msg;
		String rtnjsonString;
		
		HL7toXMLws hl7toXML = new HL7toXMLws();	
		//XMLtoJSONws
		rtnjsonString = hl7toXML.HL7toXML("MSH|^~\\&|foo|foo||foo|200108151718||ACK^A01^ACK|1|D|2.4|\rMSA|AA\r");
		//rtnjsonString = hl7toXML.HL7toXML(jsonString);
	
		return rtnjsonString;
	}
	
	public String passXMLtoJsonmsg(String xmlMsg){
	      String xmlString = xmlMsg;
	      XMLtoJSONws xmltoJsonTrans = new XMLtoJSONws();
	      String jsonFromXML = xmltoJsonTrans.xmlToJSON(xmlString);
	      
	      // show in console
	      System.out.println(jsonFromXML);
	      
	      return jsonFromXML;
	    }
	
	
	public void saveToDB(){
		
	}
}
	*/