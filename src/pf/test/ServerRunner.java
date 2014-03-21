package pf.test;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

//import JettyServer;
//import ServerStartStopActionListner;

// Displays Swing window
public class ServerRunner extends JFrame{
	private static final long serialVersionUID = 8261022096695034L;
	
	private JButton btnStartStop;
	private JButton btnTransform;
	private JLabel jlab;
	
    // Class constructor
	public ServerRunner(final JettyServer jettyServer 
			            , final TransformService transformService) {
		// set frame
		super("Start/Stop Server/Transform");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Set label
		jlab=new JLabel("PF Demo Jetty/Web Service transform and H2 DB save");
		add(jlab,BorderLayout.NORTH);
		
		// Set Start/Stop button
		btnStartStop = new JButton("Start Jetty");
		btnStartStop.addActionListener
		(new ServerStartStopActionListner(jettyServer));
		add(btnStartStop,BorderLayout.NORTH);
		
		// Set Transform button
		btnTransform = new JButton("Transform HL7 to JSON");
		btnTransform.addActionListener
		(new TransformActionListner(transformService));
		add(btnTransform, BorderLayout.CENTER);
		
		setSize(300,300);
		
		// stop server if window shut
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				if(jettyServer.isStarted()) {
					try {
						jettyServer.stop();
					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}
			}
		},"Stop Jetty Hook")); 
		
		setVisible(true);
		
		
	}
} 