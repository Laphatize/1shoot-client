import java.awt.*;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ClientPanel extends JPanel {
	String username, gamecode;
	JSONObject data;
	
	public ClientPanel(String username, String gamecode, JSONObject data) {
		this.username = username;
		this.gamecode = gamecode;
		this.data = data;
		setBackground(Color.black);
		setPreferredSize(new Dimension(500,500));
		
		if ((this.data).get("owner") == this.username) {
			// do some owner only stuff
			System.out.println("This client is owner of session.");
		}
		
	}
	public void paintComponent(Graphics client){
		boolean gameover = false;
		int myx = 0 , myy = 0;
		URL url;

		try {
			url = new URL("http://localhost/coordinatesfor?username=" + username + "&gamecode="+gamecode);

			try {
				InputStream is = url.openStream();
				try {
					Scanner s = new Scanner(is).useDelimiter("\\A");
					String result = s.hasNext() ? s.next() : "";
					//System.out.println(result);
				    Object obj = new JSONParser().parse(result);
				    JSONObject jo = (JSONObject) obj;
				    myx = (int) jo.get("x");
				    myy = (int) jo.get("y");
				    System.out.println(jo);
				    System.out.println(myx);
				    System.out.println(myy);
				
				} finally {
				  is.close();
				}
				  
			} catch(Exception e) {
			
		
			//	JOptionPane.showMessageDialog(null, "Connection timed out"+e);
		
			} finally {
			}
		
		} catch (MalformedURLException e1) {
// basically if some how the URL gets botched
			e1.printStackTrace();
		}
		
		
		super.paintComponent(client);
		client.setColor(Color.green);
		client.fillRect(myx, myy, 30, 30);
		client.drawString(gamecode, 20, 20);
		
		repaint();
		
		
		
	}
}
