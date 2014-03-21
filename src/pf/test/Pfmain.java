package pf.test;

import java.awt.EventQueue;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.json.JSONException;
//import org.java.embedjetty.RunHelloServlet.HelloServlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @param args
 */
public class Pfmain {
	
public static void main(String[] args) 
{  
		// set up property for configuration file
		Properties prop = new Properties();
		    // load property		
		    try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader(); 
			InputStream input = loader.getResourceAsStream("config.properties");
			//InputStream input = new FileInputStream("/config.properties");
			// load a properties file
			prop.load(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
		    
			// get the port property value
			String portNo = prop.getProperty("port");
			// convert string to int
	        int portNum = Integer.parseInt(portNo);
	        
	    // Create context handler collection
		ContextHandlerCollection contexts =
				new ContextHandlerCollection();
		
		 // set servlet context
        ServletContextHandler context 
        = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        
        // add servlet context to handlers 
        //  contexts.addContext(context, portNo);
        
        // add servlets    
        context.addServlet(new ServletHolder(new HelloServlet()),"/*");
        
		// build app context, pass servlet context and set handlers 
		contexts.setHandlers(new Handler[] 
			{new AppContextBuilder().buildWebAppContext(), context});
		
	    // construct the server with a port number parameter
		final JettyServer jettyServer = new JettyServer(portNum);		
		
        // set contexts for handler
        jettyServer.setHandler(contexts);
        

		// construct the service for file transformations
		final TransformService transformService = new TransformService();
			
		Runnable runner = new Runnable() {
			@Override
			public void run() {
				new ServerRunner(jettyServer,transformService);
			}
		};
		
		// Batch up and run queued events
		EventQueue.invokeLater(runner);
		
		//TODO: Load Web service 
		//TODO  end HL7 to web service
		//TODO parse response for JSON for Patient Name
		
		// construct the service for file transformations
		//TransformService transformService = new TransformService();
		
		 // HttpResponse response; // some response object
		 //	BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
		 //	String json = reader.readLine();
		 //	JSONTokener tokener = new JSONTokener(json);
		 //	JSONArray finalResult = new JSONArray(tokener);
		 
		//TODO open H2 connection
		//TODO Create tables if not done
		//TODO save Patient Name to H2 database
		
	}
	//inner class for servlet
	static public class HelloServlet extends HttpServlet
	{
	    private String greeting="PFDemo Transform HL7";
		private String hl7toxmlReturn;
		private String jsonStringReturn;
		private String jsonParseReturn;
		
	    public HelloServlet(){}
	    
	    public HelloServlet(String greeting){
	    	this.greeting = greeting;
	    }   
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
     {
     //write to servlet
     response.setContentType("text/html");
     response.setStatus(HttpServletResponse.SC_OK);
     response.getWriter().println("<h1>**Servlet for PFDemo**</h1>");
     response.getWriter().println("session ID =" + request.getSession(true).getId());
     // add exchange methods (hl7 and json)
    
     // HL7 transformation
     String hl7msg1 = "MSH|^~\\&|foo|foo||foo|200108151718||ACK^A01^ACK|1|D|2.4|\rMSA|ArcherHA\r";
	 System.out.println("doGet exchange HL7 message--------------------------");
	 System.out.println(hl7msg1);
	 //HL7 to XML
			String hl7msg = "MSH|^~\\&|foo|foo||foo|200108151718||ACK^A01^ACK|1|D|2.4|\rMSA|HArcher\r";
			//hl7toxmlReturn = passHL7msg("MSH|^~\\&|foo|foo||foo|200108151718||ACK^A01^ACK|1|D|2.4|\rMSA|AA\r");
			System.out.println("HL7 message---from servlet-----------------------");
			System.out.println(hl7msg);
			
			//transform HL7message
			hl7toxmlReturn = passHL7msg(hl7msg1);
			System.out.println("--------------------------");
			// show in console
			System.out.println("returned XMLfromHL7--from servlet after passHL7 method------------------------");
			System.out.println(hl7toxmlReturn);
			System.out.println("--------------------------");
			
			//XML to JSON
			XMLtoJSONws xtoJ = new XMLtoJSONws();
			
			jsonStringReturn = xtoJ.xmlToJSON(hl7toxmlReturn);
			// show in console
			System.out.println("JSON--from servlet------------------------");
			System.out.println(jsonStringReturn);	
			System.out.println("--------------------------");
			
			//parse JSON for patient name
		    JSONParser jparse = new JSONParser();
			String rtnjparse;
				try {
					rtnjparse = jparse.parseJSON(jsonStringReturn);
					jsonParseReturn = rtnjparse;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
 			// show in console
				System.out.println("JSON--parse return from servlet------------------------");
				System.out.println(jsonParseReturn);	
				System.out.println("--------------------------");
			
			
			// add to db
			System.out.println("RunH2 from Servlet--------------------------");	
			RunH2.runH2(jsonParseReturn);
			//RunH2.runH2(patientName);
	}         

		//public void loadServices(){
		//}
		
		public String passHL7msg(String hl7msg){
			//		
			//HL7toXMLws
			String jsonString = hl7msg;
			String rtnjsonString;
			
			HL7toXMLws hl7toXML = new HL7toXMLws();	
			//XMLtoJSONws
			rtnjsonString = hl7toXML.HL7toXML(jsonString);
			//rtnjsonString = hl7toXML.HL7toXML("MSH|^~\\&|foo|foo||foo|200108151718||ACK^A01^ACK|1|D|2.4|\rMSA|Archer\r");
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
		
		
	}
     

   }

