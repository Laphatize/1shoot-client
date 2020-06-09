/*
 * 
 * Connecting to Server Screen
 * 
 * 
 */


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javax.swing.JOptionPane;
import java.util.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import javax.swing.*;



public class shootloader {
	
	public static shootloader main(String username, String gamecode) throws Exception {
	JFrame frame = new JFrame("1shoot - Loader");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon image = new ImageIcon("./assets/loader.png");
		System.out.println("/assets/loader.png | directory loaded");
		frame.getContentPane().add(new JLabel(image));	
	    frame.setUndecorated(true); // hide controls
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.pack();
	    frame.setLocationRelativeTo(null); // center
		frame.setVisible(true);
		frame.setResizable(false);
		
		// Check if stuff provided is valid
		URL url = new URL("http://144.172.83.148:85/joinGame?username=" + username + "&gamecode="+gamecode);
		System.out.println(url);
		try {
			InputStream is = url.openStream();
			try {
				Scanner s = new Scanner(is).useDelimiter("\\A");
				String result = s.hasNext() ? s.next() : "";
				System.out.println(result);
			    Object obj = new JSONParser().parse(result);
			    JSONObject jo = (JSONObject) obj;
				Thread.sleep(1000); // oh yeah 
			//	JOptionPane.showMessageDialog(null, "Note: If a session is already full you might kick another player out of the game. This is going to be fixed in the future but for now please be considerate.");

				frame.setVisible(false);
				Client.main(username, gamecode, jo);
			} finally {
			  is.close();
			}
			  
		} catch(Exception e) {
		
			frame.setVisible(false);
			JOptionPane.showMessageDialog(null, "Unable to connect to the server!\n"+e);
	///1shoot/resources/images/error.png
		
		} finally {
		}
		
		
		return null;
	}
}