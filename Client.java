import java.awt.*;
import javax.swing.*;

import org.json.simple.JSONObject;

public class Client {
	public static void main(String username, String gamecode, JSONObject data) {
		// Client Code
		
		// Frame Stuff
		JFrame frame = new JFrame("1shoot - Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ClientPanel panel = new ClientPanel(username, gamecode, data);
		frame.setLocationRelativeTo(null);
		frame.setPreferredSize(new Dimension(500,500));
		frame.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}
}
	