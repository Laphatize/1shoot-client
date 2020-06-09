import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.lang.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
		setFocusable(true);
		requestFocusInWindow();
		if ((this.data).get("owner") == this.username) {
			// do some owner only stuff
			System.out.println("This client is owner of session.");
		}
		
	}

	int myx = 0 , myy = 0;

	public void paintComponent(Graphics client){
		boolean gameover = false;

		URL url;

		try {
			URL url2;
			url2 = new URL("http://144.172.83.148:85/update?x=" + (myx) + "&y=" + (myy) + "&gamecode=" + gamecode + "&username=" + username);
		//	url = new URL("http://144.172.83.148:85/coordinatesfor?username=" + username + "&gamecode="+gamecode);
			//System.out.println(url);
			try {
			//	InputStream is = url.openStream();
				InputStream is = url2.openStream();
				try {


				//	System.out.println(url2);

					/*
					Scanner s = new Scanner(is).useDelimiter("\\A");
					String result = s.hasNext() ? s.next() : "";
					//System.out.println(result);
				    Object obj = new JSONParser().parse(result);
				    JSONObject jo = (JSONObject) obj;
				    myx = (int)(long) jo.get("x");
				    myy = (int)(long) jo.get("y");
				    System.out.println(jo);
				    System.out.println(myx);
				    System.out.println(myy);
				*/
				} finally {
				  is.close();
				}
				  
			} catch(Exception e) {
			
		
				JOptionPane.showMessageDialog(null, "Connection timed out"+e);
				System.out.println(e);
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


		addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
			//	System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());

			}


			public void keyPressed(KeyEvent e) {

			}

			public void keyReleased(KeyEvent e) {
				//System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());
				int keyCode = e.getKeyCode();
				switch (keyCode) {
					case KeyEvent.VK_UP:
						/*try {
							URL url2;
							url2 = new URL("http://144.172.83.148:85/update?x=" + myx + "&y=" + (myy--) + "&gamecode=" + gamecode + "&username=" + username);
							System.out.println(url2);
							InputStream is = url2.openStream();
						} catch (MalformedURLException malformedURLException) {
							malformedURLException.printStackTrace();
						} catch (IOException ioException) {
							ioException.printStackTrace();
						}*


						 */
						myy-=1;
						repaint();
						break;
					case KeyEvent.VK_DOWN:
					/*	System.out.println("down pressed");
						try {
							URL url2;
							url2 = new URL("http://144.172.83.148:85/update?x=" + myx + "&y=" + (myy++) + "&gamecode=" + gamecode + "&username=" + username);
							System.out.println(url2);
							InputStream is = url2.openStream();
						} catch (MalformedURLException malformedURLException) {
							malformedURLException.printStackTrace();
						} catch (IOException ioException) {
							ioException.printStackTrace();
						}
					*/
						myy+=1;
						break;
					case KeyEvent.VK_RIGHT:
						System.out.println("right pressed");
						myx+=1;
						repaint();
						break;

					case KeyEvent.VK_LEFT:
						System.out.println("left pressed");
						myx-=1;
						repaint();
						break;
				}
			}
		});

		repaint();

		
		
	}

	/*public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
			case KeyEvent.VK_UP:
				try {
					URL url;
					url = new URL("http://144.172.83.148:85/update?x=" + myx + "&y=" + myy + "gamecode=" + gamecode + "&username=" + username);
					System.out.println("moved up");
				} catch (MalformedURLException malformedURLException) {
					malformedURLException.printStackTrace();
				}

				break;
			case KeyEvent.VK_DOWN:
			//	down.setText("Down: " + Integer.toString(downCount++));
				break;
			case KeyEvent.VK_RIGHT:
			//	right.setText("Right: " + Integer.toString(rightCount++));
				break;
			case KeyEvent.VK_LEFT:
			//	left.setText("Left: " + Integer.toString(leftCount++));
				break;
		}
	}*/
}
