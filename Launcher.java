import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.JOptionPane;
import java.util.*;
	
public class Launcher {

	public static void main(String[] args) throws IOException {
		String type = JOptionPane.showInputDialog("Are you joining or creating a game?");
		System.out.println(type);
		if (type.contentEquals("joining")) {
			String gameCode = JOptionPane.showInputDialog("Please enter your game code!");
			String playerUsername = JOptionPane.showInputDialog("Enter a nickname!");
		} else {
			String gamename = JOptionPane.showInputDialog("Please enter the name of this server.");
			String playerUsername = JOptionPane.showInputDialog("Enter a nickname!");
				
			System.out.println(gamename);
			System.out.println(playerUsername);
			URL url = new URL("http://144.172.83.148:85/createGame?username=" + playerUsername + "&gamename="+gamename);
			System.out.println(url);
			try {
				InputStream is = url.openStream();
				try {
					Scanner s = new Scanner(is).useDelimiter("\\A");
					String result = s.hasNext() ? s.next() : "";
			     //   Object obj = new JSONParser().parse(result);
			        
			     //   JSONObject jo = (JSONObject) obj;
			     //   JOptionPane.showMessageDialog(null, "You are connecting to " + jo.get("owner") + "'s server as " + playerUsername);
				       JOptionPane.showMessageDialog(null, "Game Created.");
					 //  new shootloader();
					// start loader
					   shootloader.main(playerUsername, result);;
				} finally {
				  is.close();
				}
				  
			} catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Unable to connect to the server!\n"+e);
			} finally {
				
			}
		}
	}
}