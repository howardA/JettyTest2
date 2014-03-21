package pf.test;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import ca.uhn.hl7v2.parser.*;

import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v24.message.ACK;

@WebService(
        portName = "HL7toXML",
        serviceName = "HL7toXMLServiceWs",
        targetNamespace = "http://pfish.demo.org/wsdl",
        endpointInterface = "org.demo.pfish.ws.HL7toXMLWs")

public class HL7toXMLws {
    private String ackMessageString;
    private String returnAckMessageInXML;
    
    public String HL7toXML(String stringHL7) {
       // get passed string    
       ackMessageString = stringHL7;
    	    	    	
       //for demo purposes, we just declare a literal message string 
       //ackMessageString = "MSH|^~\\&|foo|foo||foo|200108151718||ACK^A01^ACK|1|D|2.4|\rMSA|AA\r";
    	
    	// original Patient info string
    	//MSH|^~\&|EPIC|EPICADT|SMS|SMSADT|199912271408|CHARRIS|ADT^A04|1817457|D|2.5|
    	//PID||0493575^^^2^ID 1|454721||DOE^JOHN^^^^|DOE^JOHN^^^^|19480203|M||B|254 MYSTREET AVE^^MYTOWN^OH^44123^USA||(216)123-4567|||M|NON|400003403~1129086|
    	//NK1||ROE^MARIE^^^^|SPO||(216)123-4567||EC|||||||||||||||||||||||||||
    	//PV1||O|168 ~219~C~PMA^^^^^^^^^||||277^ALLEN MYLASTNAME^BONNIE^^^^|||||||||| ||2688684|||||||||||||||||||||||||199912271408||||||002376853
        //ackMessageString 
        //= "MSH|^~\\&|EPIC|EPICADT|SMS|SMSADT|199912271408|CHARRIS|ADT^A04|1817457|D|2.5|" +
        //  "PID||0493575^^^2^ID 1|454721||DOE^JOHN^^^^|DOE^JOHN^^^^|19480203|M||B|254 MYSTREET AVE^^MYTOWN^OH^44123^USA||(216)123-4567|||M|NON|400003403~1129086| "+
    	//  "NK1||ROE^MARIE^^^^|SPO||(216)123-4567||EC|||||||||||||||||||||||||||"+
    	//  "PV1||O|168 ~219~C~PMA^^^^^^^^^||||277^ALLEN MYLASTNAME^BONNIE^^^^|||||||||| ||2688684|||||||||||||||||||||||||199912271408||||||002376853" ;
        
        //print XML-encoded message to standard out
        System.out.println("HL7 to XML passed string--------------------------");
        System.out.println(stringHL7);
        System.out.println("--------------------------");
        //print XML-encoded message to standard out
        System.out.println("HL7 to XML in ackMessageString internal demo string--------------------------");
        System.out.println(ackMessageString);
        System.out.println("--------------------------");
        
        //instantiate a PipeParser, which handles the "traditional encoding" 
        PipeParser pipeParser = new PipeParser();
        
        try {
            //parse the message string into a Message object 
            Message message = pipeParser.parse(ackMessageString);
            
            //if it is an ACK message (as we know it is),  cast it to an 
            // ACK object so that it is easier to work with, and change a value            
            if (message instanceof ACK) {
                ACK ack = (ACK) message;
                ack.getMSH().getProcessingID().getProcessingMode().setValue("P");
            }
            
            //instantiate an XML parser 
            XMLParser xmlParser = new DefaultXMLParser();
            
            //encode message in XML 
            String ackMessageInXML = xmlParser.encode(message);
            returnAckMessageInXML = ackMessageInXML;
            
            //print XML-encoded message to standard out
            System.out.println("HL7 to XML in method--------------------------");
            System.out.println(ackMessageInXML);
            System.out.println("--------------------------");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
      //publish web service
       // Endpoint.publish("http://localhost:8586/HL7toXMLWs", 
       // 		new XMLtoJSONws()); ;
	
		return returnAckMessageInXML;       
    }
}