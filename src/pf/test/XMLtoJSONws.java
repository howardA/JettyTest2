package pf.test;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

@WebService(
        portName = "XMLtoJSON",
        serviceName = "XMLtoJSONServiceWs",
        targetNamespace = "http://pfish.demo.org/wsdl",
        endpointInterface = "org.demo.pfish.ws.XMLtoJSONWs")
public class XMLtoJSONws {

	/**
	 * @param args
	 */
	public static int PRETTY_PRINT_INDENT_FACTOR = 4;
      //public static String TEST_XML_STRING =
      //   "<?xml version=\"1.0\" ?><test attrib=\"moretest\">Turn this to JSON</test>";

    public String xmlToJSON (String xmlString) {
    	String jsonString = null;
    	
        try {
            @SuppressWarnings("unchecked")
			JSONObject xmlJSONObj = (JSONObject) XML.toJSONObject(xmlString);
            jsonString = xmlJSONObj.toString();
           // System.out.println(jsonPrettyPrintString);
        } catch (JSONException je) {
            System.out.println(je.toString());
        }
        
        //publish web service
       // Endpoint.publish("http://localhost:8586/XMLtoJSONServiceWs", 
       // 		new XMLtoJSONws()); ;
		return jsonString;
    }

}



