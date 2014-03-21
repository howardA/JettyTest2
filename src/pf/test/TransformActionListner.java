package pf.test;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import org.json.JSONException;

public class TransformActionListner implements ActionListener {
	
	private final TransformService transformService;
	
	public TransformActionListner(TransformService transformService) {
	 this.transformService = transformService;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// TODO 
			
		try {
			JButton btnTransform =  (JButton) actionEvent.getSource();
			// Check if web service available, if so perform transformation, send results back and save.
			transformService.runTransform();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		
	}

}
