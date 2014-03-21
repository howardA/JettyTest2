package pf.test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.*;

public class JSONParser{
	
public JSONParser(){
	
}

  private final static String JSON_DATA =
     "{" 
   + "  \"geodata\": [" 
   + "    {" 
   + "      \"id\": \"1\"," 
   + "      \"name\": \"Julie Sherman\","                  
   + "      \"gender\" : \"female\"," 
   + "      \"latitude\" : \"37.33774833333334\"," 
   + "      \"longitude\" : \"-121.88670166666667\""
   + "    }," 
   + "    {" 
   + "      \"id\": \"2\"," 
   + "      \"name\": \"Johnny Depp\","          
   + "      \"gender\" : \"male\"," 
   + "      \"latitude\" : \"37.336453\"," 
   + "      \"longitude\" : \"-121.884985\""
   + "    }" 
   + "  ]" 
   + "}"; 

  // Data3 without concat 
  //  "{\"geodata\": [{\"id\": \"1\",\"name\": \"Julie Sherman\",\"gender\" : \"female\",\"latitude\" : \"37.33774833333334\",\"longitude\" : \"-121.88670166666667\"},"{\"id\": \"2\",\"name\": \"Johnny Depp\",\"gender\" : \"male\",\"latitude\" : \"37.336453\",\"longitude\" : \"-121.884985\"}"]}; 
    
  
  //private final static String JSON_DATA2 =
	  //"{"ACK":/{"MSH":{"MSH.4":{"HD.1":"foo"},"MSH.10":"1","MSH.6":{"HD.1":"foo"},"MSH.7":{"TS.1":"200108151718"},"MSH.9":{"MSG.3":"ACK","MSG.2":"A01","MSG.1":"ACK"},"MSH.11":{"PT.2":"P","PT.1":"D"},"MSH.12":{"VID.1":"2.4"},"MSH.1":"|","MSH.2":"^~\\&","MSH.3":{"HD.1":"foo"}},"MSA":{"MSA.1":"AA"},"xmlns":"urn:hl7-org:v2xml"}};
  
  
  //JSON--------------------------
  //{"ACK":{"MSH":{"MSH.4":{"HD.1":"foo"},"MSH.10":"1","MSH.6":{"HD.1":"foo"},"MSH.7":{"TS.1":"200108151718"},"MSH.9":{"MSG.3":"ACK","MSG.2":"A01","MSG.1":"ACK"},"MSH.11":{"PT.2":"P","PT.1":"D"},"MSH.12":{"VID.1":"2.4"},"MSH.1":"|","MSH.2":"^~\\&","MSH.3":{"HD.1":"foo"}},"MSA":{"MSA.1":"AA"},"xmlns":"urn:hl7-org:v2xml"}}
  //--------------------------
  
  //String json1 = "{"+
  //                 " "ACK":" +{"MSH":{"MSH.4":{"HD.1":"foo"},"MSH.10":"1","MSH.6":{"HD.1":"foo"},"MSH.7":{"TS.1":"200108151718"},"MSH.9":{"MSG.3":"ACK","MSG.2":"A01","MSG.1":"ACK"},"MSH.11":{"PT.2":"P","PT.1":"D"},"MSH.12":{"VID.1":"2.4"},"MSH.1":"|","MSH.2":"^~\\&","MSH.3":{"HD.1":"foo"}},"MSA":{"MSA.1":"AA"},"xmlns":"urn:hl7-org:v2xml"}}";
		  
		  private final static String JSON_DATA3 =
		     "{" 
		   + "  \"ptdata\": [" 
		   + "    {" 
		   + "      \"id\": \"1\"," 
		   + "      \"name\": \"Julie Sherman\","                  
		   + "      \"gender\" : \"female\"," 
		  			  			   + "    }," 
		   + "    {" 
		   + "      \"id\": \"2\"," 
		   + "      \"name\": \"Johnny Depp\","          
		   + "      \"gender\" : \"male\"," 
		   + "    }" 
		   + "  ]" 
		   + "}";  
		  
  public String parseJSON(String jsonData) throws JSONException {
	  
	  String parsed = null;
	  
	//final JSONObject obj = new JSONObject(JSON_DATA);
    //final JSONArray geodata = obj.getJSONArray("geodata");
    //final int n = geodata.length();
    //for (int i = 0; i < n; ++i) {
    //  final JSONObject person = geodata.getJSONObject(i);
    //  System.out.println(person.getInt("id"));
    //  System.out.println(person.getString("name"));
    //  System.out.println(person.getString("gender"));
    //  System.out.println(person.getDouble("latitude"));
    //  System.out.println(person.getDouble("longitude"));
	

	    
	 //final JSONObject obj = new JSONObject(JSON_DATA3);
	 //final JSONArray ptdata = obj.getJSONArray("ptdata");
    
	// Use passed string
		
	  System.out.println("JSONParser jsonData--------------------------");
      System.out.println(jsonData);
      System.out.println("--------------------------");
      
	//final JSONObject obj = new JSONObject(jsonData);  
	//final JSONObject ptdata = obj.getJSONObject("ACK");
    //final JSONArray ptdata = obj.getJSONArray("ACK");
		final JSONObject obj = new JSONObject(jsonData);
		//final JSONObject ptdata = obj.getJSONObject("ptdata");
		final JSONObject ack = obj.getJSONObject("ACK");
				//.getJSONArray("ACK");
	
	//final int n = ptdata.length();
	//  for (int i = 0; i < n; ++i) {
	//    final JSONObject person = ptdata.getJSONObject(i);
	    //System.out.println(person.getInt("id"));
	    //System.out.println(person.getString("name"));
	    //System.out.println(person.getString("gender"));
	    
	    //System.out.println(ptdata.getInt("id"));
	    //System.out.println(ptdata.getString("name"));
	    //System.out.println(ptdata.getString("gender"));
	
	    //System.out.println(ack.getInt("MSA"));
	    System.out.println(ack.getString("MSA"));
	    //System.out.println(ack.getString("MSA.1"));
	    
	   // get data for return
	   //parsed = person.getString("name");
	   //}
	    // parsed = ptdata.getString("name");
	    parsed = ack.getString("MSA");
	  return parsed;
  }
}
