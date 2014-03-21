package pf.test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import org.h2.*;

//import java.util.Date;
 

public class RunH2 {
	public static void runH2 (String patientName) {
		
		// show in console
		System.out.println("H2 parameter passed------------------------");
		System.out.println(patientName);	
		System.out.println("--------------------------");
	try {
		 
		//driver for H2 db from http://www.h2database.com
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//create database on memory
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:h2:mem:mytest", "sa", "");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test",  "sa", "");

		Statement stat = con.createStatement();

		//create table

		stat.execute("CREATE TABLE PATIENT (ID INTEGER, PATIENT_NAME VARCHAR(200),  PRIMARY KEY (ID))");

		//prepared statement
		PreparedStatement prep = con.prepareStatement("INSERT INTO PATIENT (ID, PATIENT_NAME) VALUES (?,?)");

		    //insert 10 row data
		    //for (int i = 0; i<10; i++){
		    int i=101; //only one patient for demo
			prep.setLong(1, i);
			//prep.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			//prep.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			//prep.setString(2, "Patient-" + i);
			prep.setString(2, patientName);

			//batch insert
			prep.addBatch();
		    //}
		
		con.setAutoCommit(false);
		prep.executeBatch();
		con.setAutoCommit(true);

		//query to database
		try {
			ResultSet rs = stat.executeQuery("Select ID, PATIENT_NAME from PATIENT");
			while (rs.next()) {

				//Date start = rs.getTimestamp(1);
				//Date end = rs.getTimestamp(2);
				String id = rs.getString(1);
				String ptName = rs.getString(2);

				//print query result to console
				System.out.println("DB --------------------------");
				System.out.println("ID: " + id);
				System.out.println("Patient: " + ptName);
				//System.out.println("start: " + start);
				//System.out.println("end: " + end);
				System.out.println("--------------------------");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} finally{}
	
	{


	}
	}
}
