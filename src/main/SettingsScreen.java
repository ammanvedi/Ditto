package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class SettingsScreen extends JFrame implements MouseMotionListener, MouseListener, KeyListener  {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Color c = new Color(0,0,0,0);
	
	public ImageIcon bgii;
	public Image bg;
	
	public int xonw;
	public int yonw;
	
	public int mx;
	public int my;
	
	public ImageIcon setnii;
	public Image setnormal;
	
	public ImageIcon setmoii;
	public Image setmo;
	
	public ImageIcon setcii;
	public Image setc;
	
	ImageIcon cbsetii;
	Image cbset;
	
	ImageIcon cbunsetii;
	Image cbunset;
	
	ImageIcon cbsetoverii;
	Image cbsetover;
	
	ImageIcon cbunsetoverii;
	Image cbunsetover;
	
	ImageIcon editkeyii;
	Image editkey;
	
	public boolean cbmainstate;
	public boolean cbminorstate = false;
	
	public boolean overset = false;
	public boolean pressset = false;
	public boolean clickset = false;
	public boolean autocopyurl;
	
	public Font font = new Font("Arial", Font.BOLD, 13);
	
	public Color color = new Color(51, 102, 204);
	
	public String capkeychar;
	public XMLparser configedit;
	
	public Rectangle complete = new Rectangle(83,23,45, 190);
	Rectangle Mainbar = new Rectangle(0,0,478, 27);
	Rectangle closerec = new Rectangle(445,10, 20, 20);
	Rectangle minirec = new Rectangle(415,10, 20, 20);
	Rectangle cboxrec = new Rectangle(440,160, 15, 15);
	Rectangle editrec = new Rectangle(430,129, 30,20);
	
	boolean overcbox = false;
	
	Toolkit tk = Toolkit.getDefaultToolkit();
	
	boolean editlisten = false;

	public SettingsScreen() throws SAXException, IOException, ParserConfigurationException{
		
		this.setSize(478, 244);
		this.setLocation( (int) Math.round(tk.getScreenSize().getWidth()/2 - this.getWidth()/2)   , (int) Math.round(tk.getScreenSize().getHeight()/2 - this.getHeight()/2) );
		
		ImageIcon  wiii = new ImageIcon(this.getClass().getResource("Icon 64x64.png"));
		Image wimg = wiii.getImage();
		this.setIconImage(wimg);
		
		this.setUndecorated(true);
		this.setBackground(c);
		this.setVisible(true);
		this.requestFocus();
		this.toFront();
		
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		
		bgii = new ImageIcon(this.getClass().getResource("Ditto Settings Base.png"));
		bg = bgii.getImage();
		
		//buttonimage normal
		setnii = new ImageIcon(this.getClass().getResource("Complete Button Normal.png"));
		setnormal = setnii.getImage();
		//mouseover
		setmoii = new ImageIcon(this.getClass().getResource("Complete Button Mouse-over.png"));
		setmo = setmoii.getImage();
		//pressed
		setcii = new ImageIcon(this.getClass().getResource("Complete Button Pressed.png"));
		setc = setcii.getImage();
		
		
		//checkbox
		cbsetii = new ImageIcon(this.getClass().getResource("Tick Box Checked.png"));
		cbset = cbsetii.getImage();
		
		//checkbox
		cbunsetii = new ImageIcon(this.getClass().getResource("Tick Box Normal Unchecked.png"));
		cbunset = cbunsetii.getImage();
		
		//checkbox
		cbsetoverii = new ImageIcon(this.getClass().getResource("Tick Box Mouse-over Checked.png"));
		cbsetover = cbsetoverii.getImage();
		
		//checkbox
		cbunsetoverii = new ImageIcon(this.getClass().getResource("Tick Box Mouse-over Unchecked.png"));
		cbunsetover = cbunsetoverii.getImage();
		
		editkeyii = new ImageIcon(this.getClass().getResource("Toast.png"));
		editkey = editkeyii.getImage();
		
		
		configedit = new XMLparser( "config.xml");
		
		cbmainstate = configedit.getcopysetting();
		capkeychar = configedit.getcapchar();
		autocopyurl =  configedit.getcopysetting();

		
	}
	
	
	
	public void paint(Graphics g){

		
		g.drawImage(bg, 0, 0, this);
		
		Graphics2D g2d = (Graphics2D)g;

		
		g2d.drawImage(setnormal, 45, 190, this);
		
		
		if(overset == true){
			g2d.drawImage(setmo, 45, 190, this);
		}

		if(pressset == true){
			g2d.drawImage(setc, 45, 190, this);

		}
		
		if(cbmainstate == true){
			if(overcbox == true){
				g2d.drawImage(cbsetover, 440, 160, this);
			}else{
			
			g2d.drawImage(cbset, 440, 160, this);
			}
		}
		
		if(cbmainstate == false){
			
			if(overcbox == true){
				g2d.drawImage(cbunsetover, 440, 160, this);
			}else{
			g2d.drawImage(cbunset, 440, 160, this);
			}
		}
		
		if(editlisten == true){
			g2d.drawImage(editkey, 150, 190, this);
		}
		

		
		if(capkeychar != null){
		g2d.setFont(font);
		g2d.setColor(color);
		g2d.drawString(capkeychar, 188, 145);
		}
		
		//g2d.draw(editrec);
		
		Toolkit.getDefaultToolkit().sync();
		
		
	}
	
	
	
	
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		if(closerec.contains(xonw,	yonw) == true){
			this.dispose();
			try {
				new appwindow();
			} catch (IOException | SAXException | ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(minirec.contains(xonw,	yonw) == true){
			this.setExtendedState(JFrame.ICONIFIED);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent p) {
		// TODO Auto-generated method stub
		
		xonw = p.getX();
		yonw = p.getY();
		
		if(complete.contains(p.getX(), p.getY()) == true){
			pressset = true;
		}else{
			pressset = false;
		}
		repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent rel) {
		// TODO Auto-generated method stub

		pressset = false;

		if(complete.contains(rel.getX(), rel.getY()) == true){
			 FileWriter outFile = null;
			try {
				outFile = new FileWriter( "config.xml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	            PrintWriter out = new PrintWriter(outFile);

	              out.println("<Settings><values><keychar>"+ capkeychar +"</keychar><autocopy>"+ autocopyurl +"</autocopy></values></Settings>");

	               out.close();
	               
	               this.dispose();
	               try {
					new HomeScreen();
				} catch (SAXException | IOException
						| ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		if(cboxrec.contains(rel.getX(), rel.getY()) == true){
			cbmainstate = !cbmainstate;
			autocopyurl = cbmainstate;
		}
		
		if(editrec.contains(rel.getX(), rel.getY()) == true){
			editlisten = true;
		}
		
		
		
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent mdrag) {
		// TODO Auto-generated method stub
		
		if(Mainbar.contains(xonw, yonw) == true){
		mx = mdrag.getXOnScreen() - xonw;
		my = mdrag.getYOnScreen() - yonw;
		this.setBackground(c);
		
		this.setLocation(mx, my);
		repaint();
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent moved) {
		// TODO Auto-generated method stub
		
		if(complete.contains(moved.getX(), moved.getY()) == true){
			overset = true;
		}else{
			overset = false;
		}
		
		
		if(cboxrec.contains(moved.getX(), moved.getY())){
			overcbox = true;
		}else{
			overcbox = false;
		}
		repaint();
		
	}



	@Override
	public void keyPressed(KeyEvent KP) {
		if(editlisten == true ){
			if( KP.getKeyCode() != KeyEvent.VK_F1){
			capkeychar = Character.toString(KP.getKeyChar());
			}
			editlisten = false;
		}
		repaint();
		
	}



	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
